package com.zeyiyouhuo.api.request;

import com.zeyiyouhuo.api.ApiException;
import com.zeyiyouhuo.api.response.ZeyiResponse;
import com.zeyiyouhuo.constants.Constants;
import com.zeyiyouhuo.utils.ZeyiHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 则一基础T请求类，存放一些通用的请求参数
 *
 * @param <T>
 */
public abstract class ZeyiBaseRequest<T extends ZeyiResponse> implements ZeyiRequest<T> {

    protected Long timestamp; // 请求时间戳

    protected String serverUrl;     //请求地址
    protected String httpMethod;    //请求方式
    protected String contentType;   //内容编码类型

    protected Map<String, String> headerMap; // HTTP请求头参数
    protected Map<String, String> queryMap; //query参数
    protected Map<String, String> bodyMap; // 自定义body参数

    public Map<String, String> getHeaderMap() {
        if (this.headerMap == null) {
            this.headerMap = new ZeyiHashMap();
        }
        return this.headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    /**
     * 添加头部自定义请求参数。
     */
    public void putHeaderParam(String key, String value) {
        getHeaderMap().put(key, value);
    }

    @Override
    public Map<String, String> getQueryParams() {
        if (this.queryMap == null) {
            this.queryMap = new ZeyiHashMap();
        }
        return this.queryMap;
    }

    /**
     * 添加自定义query参数
     */
    public void putQueryParam(String key, String value) {
        this.getQueryParams().put(key, value);
    }

    /**
     * 设置自定义query参数
     */
    public void setQueryParam(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    @Override
    public Map<String, String> getBodyParams() {
        if (this.bodyMap == null) {
            this.bodyMap = new HashMap<>();
        }
        return this.bodyMap;
    }

    /**
     * 添加自定义body参数
     */
    public void putBodyParam(String key, String value) {
        this.getBodyParams().put(key, value);
    }

    /**
     * 设置自定义body参数
     */
    public void setBodyParam(Map<String, String> bodyMap) {
        this.bodyMap = bodyMap;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
    public String getHttpMethod() {
        if (this.httpMethod == null) {
            return Constants.METHOD_POST;
        } else {
            return this.httpMethod;
        }
    }

    @Override
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String getContentType() {
        if (this.contentType == null) {
            return Constants.CONTENT_TYPE_JSON;
        } else {
            return this.contentType;
        }
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getApiMethodName() {
        return null;
    }

    @Override
    public void setApiMethodName(String apiMethodName) {

    }

    @Override
    public String getApiVersion() {
        return null;
    }

    @Override
    public void setApiVersion(String apiVersion) {

    }

    @Override
    public Class<T> getResponseClass() {
        return null;
    }

    @Override
    public void check() throws ApiException {

    }

}
