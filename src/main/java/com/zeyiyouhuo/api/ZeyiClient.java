package com.zeyiyouhuo.api;

import com.zeyiyouhuo.api.request.ZeyiRequest;
import com.zeyiyouhuo.api.response.ZeyiResponse;

public interface ZeyiClient {
    /**
     * 执行公开API请求。
     *
     * @param <T>     具体的API响应类
     * @param request 具体的API请求类
     * @return 具体的API响应
     */
    <T extends ZeyiResponse> T execute(ZeyiRequest<T> request) throws ApiException;

    /**
     * 执行公开API请求。
     *
     * @param <T>         具体的API响应类
     * @param request     具体的API请求类
     * @param accessToken accessToken
     * @return 具体的API响应
     */
    <T extends ZeyiResponse> T execute(ZeyiRequest<T> request, String accessToken) throws ApiException;
}
