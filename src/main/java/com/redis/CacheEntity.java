package com.redis;

import java.io.Serializable;

/**
 * 缓存实体
 * @author liushun
 * @date 2020/12/10
 */
public class CacheEntity implements Serializable {
    private static final long serialVersionUID = -1;

    /**
     * appId
     */
    private String appId;

    /**
     * ticket
     */
    private String token;

    // region getter/setter

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // endregion
}
