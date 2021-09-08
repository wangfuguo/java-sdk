package com.zeyiyouhuo;

import com.zeyiyouhuo.api.ApiException;
import com.zeyiyouhuo.api.DefaultZeyiClient;
import com.zeyiyouhuo.api.ZeyiClient;
import com.zeyiyouhuo.api.request.ZeyiDataRequest;
import com.zeyiyouhuo.api.response.ZeyiResponse;
import com.zeyiyouhuo.constants.Constants;
import com.zeyiyouhuo.utils.ZeyiHashMap;

import java.util.Map;

/**
 * 则一数据请求demo
 */
public class TestZeyiDataRequest {

    public static void main(String[] args) throws ApiException {
        String addUrl = "https://test.shjiuze.cn/tms-web-api/dlOrder/add";
        String queryUrl = "https://test.shjiuze.cn/tms-web-api/dlOrder/page";
        String appKey = "2edc3e0230014aa99178c75dd1f6b7b1";
        String appSecret = "ea29cb560a574c488700634b3318d204";
        String accessToken = new TestZeyiTokenRequest().getAccessToken();
        // POST
        ZeyiClient zeyiClient = new DefaultZeyiClient(appKey, appSecret);
        ZeyiDataRequest zeyiDataPostRequest = new ZeyiDataRequest();
        Map<String, String> bodyMap = new ZeyiHashMap();
        bodyMap.put("orderCode", "Test123");
        zeyiDataPostRequest.setBodyParam(bodyMap);
        zeyiDataPostRequest.setServerUrl(addUrl);
        ZeyiResponse zeyiPostResponse = zeyiClient.execute(zeyiDataPostRequest, accessToken);
        if (zeyiPostResponse != null && zeyiPostResponse.isSuccess()) {
            System.out.println(zeyiPostResponse.getResult());
        }

        // GET
        ZeyiDataRequest zeyiDataGetRequest = new ZeyiDataRequest();
        Map<String, String> queryMap = new ZeyiHashMap();
        queryMap.put("code", "DL21090100000007");
        zeyiDataGetRequest.setHttpMethod(Constants.METHOD_GET);
        zeyiDataGetRequest.setQueryParam(queryMap);
        zeyiDataGetRequest.setServerUrl(queryUrl);
        ZeyiResponse zeyiGetResponse = zeyiClient.execute(zeyiDataGetRequest, accessToken);
        if (zeyiGetResponse != null && zeyiGetResponse.isSuccess()) {
            System.out.println(zeyiGetResponse.getResult());
        }
    }
}
