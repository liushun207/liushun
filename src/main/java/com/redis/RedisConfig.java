package com.redis;

/**
 * 配置参数
 * @author liushun
 * @date 2020/12/15
 */
public class RedisConfig {
    /**
     * appId
     */
    private String appId;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 版本号
     */
    private String version;

    /**
     * idasc url
     */
    private String idascUrl;

    /**
     * 腾讯云动作活体人脸验证Api
     */
    private String faceCoreUrl;

    // region getter/setter

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdascUrl() {
        return idascUrl;
    }

    public void setIdascUrl(String idascUrl) {
        this.idascUrl = idascUrl;
    }

    public String getFaceCoreUrl() {
        return faceCoreUrl;
    }

    public void setFaceCoreUrl(String faceCoreUrl) {
        this.faceCoreUrl = faceCoreUrl;
    }

    // endregion
}
