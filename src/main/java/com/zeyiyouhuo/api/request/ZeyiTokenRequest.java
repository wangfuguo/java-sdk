package com.zeyiyouhuo.api.request;

import com.zeyiyouhuo.api.response.ZeyiResponse;
import com.zeyiyouhuo.constants.Constants;

import java.util.Map;

/**
 * 则一 token Request
 *
 * @param <T>
 */
public class ZeyiTokenRequest<T extends ZeyiResponse> extends ZeyiBaseRequest<T> {
    /**
     * 服务地址
     */
    private String serverUrl;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    @Override
    public Map<String, String> getBodyParams() {
        Map<String, String> bodyParams = super.getBodyParams();
        bodyParams.put(Constants.USERNAME, this.username);
        bodyParams.put(Constants.PASSWORD, this.password);
        return bodyParams;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getServerUrl() {
        return serverUrl;
    }

    @Override
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String getApiMethodName() {
        return "com.zeyiyouhuo.api.request.ZeyiTokenRequest";
    }
}
