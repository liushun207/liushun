package com.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * redis 配置, t通过 `xml` 注入
 * @author liushun
 * @date 2020/12/9
 */
public class RedisConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
    private final Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

    // region 注入 redis 参数

    /**
     * 配置是否开启redis连接
     */
    private Boolean open = false;

    /**
     * jedis节点配置,格式是ip:port,集群模式多个节点用逗号隔开
     */
    private String nodes;

    /**
     * 总连接数量
     */
    private Integer maxTotal = 500;

    /**
     * 空闲连接最大数量
     */
    private Integer maxIdle = 10;

    /**
     * 空闲连接最小数量
     */
    private Integer minIdle = 1;

    /**
     * 最大等待时间
     */
    private Integer maxWaitMillis = 5000;

    /**
     * 超时时间
     */
    private Integer timeout = 3000;

    /**
     * 集群个数
     */
    private Integer maxRedirections = 6;

    // endregion

    // region getter/setter

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxRedirections() {
        return maxRedirections;
    }

    public void setMaxRedirections(Integer maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

    @Override
    public String toString() {
        return "RedisConfiguration{" + "open='" + open + '\'' + ", nodes='" + nodes + '\'' + ", maxTotal='" + maxTotal + '\'' + ", maxIdle='" + maxIdle + '\'' + ", minIdle='" + minIdle + '\'' + ", maxWaitMillis='" + maxWaitMillis + '\'' + ", timeout='" + timeout + '\'' + ", maxRedirections='" + maxRedirections + '\'' + '}';
    }

    // endregion

    /**
     * jedis 连接信息
     * 没有链接或没有打开将直接返回 null
     * @return
     */
    @Bean(name = "jedisCluster")
    public JedisCluster jedisCluster() throws Exception {
        logger.info(this.toString());

        if(Boolean.FALSE.equals(open)) {
            logger.info("redis集群配置未开启！");
            return null;
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);

        Set<HostAndPort> haps = this.parseHostAndPort();
        return new JedisCluster(haps, timeout, maxRedirections, poolConfig);
    }

    // region 私有

    /**
     * 解析host
     * @return Set<HostAndPort>
     * @throws Exception 异常
     */
    private Set<HostAndPort> parseHostAndPort() throws Exception {
        try {
            Set<HostAndPort> haps = new HashSet<HostAndPort>();
            if(StringUtils.isBlank(nodes)) {
                logger.error("缺少 redis 节点配置");
                throw new Exception("缺少 redis 节点配置");
            }
            String[] nodesArray = nodes.split("[^(\\d\\.){3}\\d:\\d$]");
            for(String node : nodesArray) {
                boolean isIpPort = p.matcher(node).matches();
                if(!isIpPort) {
                    logger.error("ip 或 port 不合法,node:{}", node);
                    throw new IllegalArgumentException("ip 或 port 不合法");
                }
                String[] ipAndPort = node.split(":");
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                haps.add(hap);
            }
            logger.info("配置 redis success");
            return haps;
        } catch(IllegalArgumentException ex) {
            throw ex;
        } catch(Exception ex) {
            logger.error("解析 jedis 配置文件失败");
            throw new Exception("解析 jedis 配置文件失败", ex);
        }
    }

    // endregion
}
