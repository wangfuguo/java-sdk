package com.zeyiyouhuo;

import com.google.gson.Gson;
import com.zeyiyouhuo.api.ApiException;
import com.zeyiyouhuo.api.DefaultZeyiClient;
import com.zeyiyouhuo.api.ZeyiClient;
import com.zeyiyouhuo.api.dto.ZeyiTokenDto;
import com.zeyiyouhuo.api.request.ZeyiTokenRequest;
import com.zeyiyouhuo.api.response.ZeyiResponse;
import com.zeyiyouhuo.utils.StringUtils;

/**
 * 获取则一 token demo
 */
public class TestZeyiTokenRequest {

    public static void main(String[] args) throws ApiException {
        TestZeyiTokenRequest testZeyiTokenRequest = new TestZeyiTokenRequest();
        System.out.println(testZeyiTokenRequest.getAccessToken());
    }

    public String getAccessToken() throws ApiException {
        String url = "https://test.shjiuze.cn/auth-service-api/login/password";
        ZeyiClient zeyiClient = new DefaultZeyiClient(null, null);
        ZeyiTokenRequest zeyiTokenRequest = new ZeyiTokenRequest();
        zeyiTokenRequest.setUsername("001073");
        zeyiTokenRequest.setPassword("123");
        zeyiTokenRequest.setServerUrl(url);
        ZeyiResponse zeyiResponse = zeyiClient.execute(zeyiTokenRequest);
        if (zeyiResponse != null && zeyiResponse.isSuccess() && StringUtils.isNotBlank(zeyiResponse.getResult())) {
            ZeyiTokenDto zeyiTokenDto = new Gson().fromJson(zeyiResponse.getResult(), ZeyiTokenDto.class);
            return zeyiTokenDto.getAccessToken();
        }
        return null;
    }
}
