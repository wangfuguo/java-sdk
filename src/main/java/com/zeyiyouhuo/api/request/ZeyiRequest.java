package com.zeyiyouhuo.api.request;

import com.zeyiyouhuo.api.ApiException;
import com.zeyiyouhuo.api.response.ZeyiResponse;

import java.util.Map;

/**
 * 则一请求接口
 *
 * @param <T>
 */
public interface ZeyiRequest<T extends ZeyiResponse> {
    /**
     * 获取Http method，例如GET,POST
     *
     * @return
     */
    String getHttpMethod();

    void setHttpMethod(String httpMethod);

    /**
     * API对应的请求方式，包含json,form
     *
     * @return
     */
    String getContentType();

    void setContentType(String contentType);

    /**
     * 接口地址
     *
     * @return
     */
    String getServerUrl();

    void setServerUrl(String serverUrl);

    /**
     * 接口方法名称
     *
     * @return
     */
    String getApiMethodName();

    void setApiMethodName(String apiMethodName);

    /**
     * 接口版本
     *
     * @return
     */
    String getApiVersion();

    void setApiVersion(String apiVersion);

    /**
     * 获取所有的Key-Value形式的query请求参数集合。其中：
     * <ul>
     * <li>Key: 请求参数名</li>
     * <li>Value: 请求参数值</li>
     * </ul>
     *
     * @return 文本请求参数集合
     */
    Map<String, String> getQueryParams();

    /**
     * 获取所有的Key-Value形式的文本body参数集合。其中：
     * <ul>
     * <li>Key: 请求参数名</li>
     * <li>Value: 请求参数值</li>
     * </ul>
     *
     * @return 文本请求参数集合
     */
    Map<String, String> getBodyParams();


    /**
     * 获取自定义HTTP请求头参数
     */
    Map<String, String> getHeaderMap();

    /**
     * 获取请求时间戳（为空则用系统当前时间）
     */
    Long getTimestamp();

    /**
     * 获取具体响应实现类的定义
     */
    Class<T> getResponseClass();

    /**
     * 客户端参数检查，减少服务端无效调用
     */
    void check() throws ApiException;

}
