package com.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

/**
 * Test
 * @author liushun
 * @date 2020/12/15
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    /**
     * 测试分布式锁
     */
    private static volatile Integer atomicInteger = 0;

    public static void main(String[] args) {
        for(int i = 0; i < 200; i++) {
            Thread thread = new Thread(new LockTest());
            thread.setName("lock-task-" + i);
            thread.start();
        }
    }

    private static String LOCK_PREFIX = "redis_lock";

    private static void redisLock() throws Exception {
        String uuid = UUID.randomUUID().toString();
        int i = 0;
        int max = 200;
        String msg = Thread.currentThread().getId() + "---" + Thread.currentThread().getName();
        Random random = new Random();

        try {
            // 自旋
            while(i < max) {
                // 尝试获取锁
                boolean hasLock = JedisClient.tryGetLock(LOCK_PREFIX, uuid);
                if(hasLock) {
                    logger.info(msg + "----加锁成功---.{}", uuid);
                    atomicInteger++;

                    String s = JedisClient.get(LOCK_PREFIX);
                    if(uuid.equals(s)) {
                        logger.info(msg + "----加锁成功-----就是我。---[{}][{}]", uuid, s);
                    } else {
                        logger.info(msg + "----加锁成功----居然不是我。---[{}][{}]", uuid, s);
                    }

                    Thread.sleep(random.nextInt(100) + 400);
                    JedisClient.set("test", String.valueOf(atomicInteger), 600);
                    //返回
                    return;
                } else {
                    String s = JedisClient.get(LOCK_PREFIX);
                    if(uuid.equals(s)) {
                        logger.error(msg + "----加锁失败-----居然就是我。---[{}][{}]", uuid, s);
                    } else {
                        logger.error(msg + "----加锁失败-----不是我。---[{}][{}]", uuid, s);
                    }

                    // 加锁失败 随机 10 到 20 毫秒
                    Thread.sleep(random.nextInt(10) + 10);
                }

                i++;
            }
        } catch(Exception e) {
            throw new Exception("获取ticket失败: " + e.getMessage());
        } finally {
            // 关闭锁
            JedisClient.releaseDistributedLock(LOCK_PREFIX, uuid);

            logger.info(msg + "----释放锁成功-----。---[{}][{}]", uuid);
        }
    }

    static class LockTest implements Runnable {
        @Override
        public void run() {
            try {
                redisLock();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
