package com.zeyiyouhuo.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zeyiyouhuo.api.request.ZeyiRequest;
import com.zeyiyouhuo.api.response.HttpResponseData;
import com.zeyiyouhuo.api.response.ZeyiResponse;
import com.zeyiyouhuo.constants.Constants;
import com.zeyiyouhuo.utils.*;

import java.io.IOException;
import java.net.Proxy;
import java.util.Map;

public class DefaultZeyiClient implements ZeyiClient {

    protected String appKey;
    protected String appSecret;
    protected int connectTimeout = 15000; // 默认连接超时时间为15秒
    protected int readTimeout = 30000; // 默认响应超时时间为30秒
    protected boolean useGzipEncoding = true; // 是否启用响应GZIP压缩
    private String version = "1.0";
    private Proxy proxy; //代理类

    public DefaultZeyiClient(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultZeyiClient(String appKey, String appSecret, int connectTimeout, int readTimeout) {
        this(appKey, appSecret);
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public <T extends ZeyiResponse> T execute(ZeyiRequest<T> request) throws ApiException {
        return execute(request, null);
    }

    public <T extends ZeyiResponse> T execute(ZeyiRequest<T> request, String accessToken) throws ApiException {
        return _execute(request, accessToken);
    }

    private <T extends ZeyiResponse> T _execute(ZeyiRequest<T> request, String accessToken) throws ApiException {
        if (!Constants.CONTENT_TYPE_JSON.equals(request.getContentType()) && !Constants.CONTENT_TYPE_FORM.equals(request.getContentType())) {
            throw new IllegalArgumentException("ContentType只支持json和form格式!");
        }
        if (!Constants.METHOD_POST.equals(request.getHttpMethod()) && !Constants.METHOD_GET.equals(request.getHttpMethod())) {
            throw new IllegalArgumentException("HttpMethod只支持GET和POST请求!");
        }
        Map<String, String> headerMap = request.getHeaderMap();
        if ((headerMap == null || headerMap.isEmpty())) {
            headerMap = new ZeyiHashMap();
        }
        if (StringUtils.isNotBlank(appKey)) {
            headerMap.put(Constants.APP_KEY, appKey);
        }
        if (StringUtils.isNotBlank(accessToken)) {
            headerMap.put(Constants.AUTHORIZATION, Constants.BEARER + accessToken);
        }
        Long timestamp = request.getTimestamp();
        if (timestamp == null) {
            timestamp = System.currentTimeMillis();
        }
        headerMap.put(Constants.TIMESTAMP, String.valueOf(timestamp));
        headerMap.put(Constants.NONCE, RandomUtils.getCharAndNumber(6, true));
        try {
            headerMap.put(Constants.SIGN, MD5Encoder.encode(ZeyiUtils.encryptMD5(headerMap.get(Constants.APP_KEY) + this.appSecret + headerMap.get(Constants.TIMESTAMP) + headerMap.get(Constants.NONCE))));
            // 添加协议级请求参数
            ZeyiHashMap protocalMustParams = new ZeyiHashMap();
            protocalMustParams.put(Constants.METHOD, request.getApiMethodName());
            protocalMustParams.put(Constants.VERSION, request.getApiVersion() != null ? request.getApiVersion() : version);
            // 是否需要压缩响应
            if (this.useGzipEncoding) {
                headerMap.put(Constants.ACCEPT_ENCODING, Constants.CONTENT_ENCODING_GZIP);
            }
            headerMap.put(Constants.ACCEPT, Constants.ACCEPT_VALUE);
            String queryString = WebUtils.buildQuery(request.getQueryParams(), Constants.CHARSET_UTF8);
            String requestUrl = WebUtils.buildRequestUrl(request.getServerUrl(), queryString);
            HttpResponseData data = null;
            if (Constants.METHOD_POST.equals(request.getHttpMethod())) {
                Boolean isJson = Constants.CONTENT_TYPE_JSON.equals(request.getContentType());
                if (isJson) {
                    byte[] bodyByte = request.getBodyParams() == null ? new byte[0] : new Gson().toJson(request.getBodyParams()).getBytes(Constants.CHARSET_UTF8);
                    data = WebUtils.doPost(requestUrl, Constants.ZEYI_CONTENT_TYPE_JSON, bodyByte, connectTimeout, readTimeout, headerMap, this.getProxy());
                } else {
                    data = WebUtils.doPost(requestUrl, request.getBodyParams(), Constants.CHARSET_UTF8, connectTimeout, readTimeout, headerMap, this.getProxy());
                }
            } else {
                data = WebUtils.doGet(requestUrl, headerMap, Constants.CHARSET_UTF8, connectTimeout, readTimeout, this.getProxy());
            }
            JsonParser parser = new JsonParser();
            JsonObject responseJson = parser.parse(data.getBody()).getAsJsonObject();
            ZeyiResponse zeyiResponse = new ZeyiResponse();
            zeyiResponse.setSuccess(responseJson.get("success").isJsonNull() ? null : responseJson.get("success").getAsBoolean());
            zeyiResponse.setErrorCode(responseJson.get("errorCode") == null ? null : responseJson.get("errorCode").isJsonNull() ? null : responseJson.get("errorCode").getAsString());
            zeyiResponse.setBusinessException(responseJson.get("businessException").isJsonNull() ? null : responseJson.get("businessException").getAsBoolean());
            zeyiResponse.setMessage(responseJson.get("message") == null ? null : responseJson.get("message").isJsonNull() ? null : responseJson.get("message").getAsString());
            zeyiResponse.setResult(responseJson.get("result") == null ? null : responseJson.get("result").isJsonNull() ? null : new Gson().toJson(responseJson.get("result")));
            return (T) zeyiResponse;
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }
}
