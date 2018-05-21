package com.coderfamily.lamj.common.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.util.Map;

public class HttpUtil {

    private static final int TIMEOUT = 10000;

    public static String get(String url, Map<String, Object> params) {
        String result = "";

        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(TIMEOUT);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(TIMEOUT);

        HttpMethodParams methodParams = new HttpMethodParams();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                methodParams.setParameter(key, params.get(key));
            }
        }

        HttpMethod method = new GetMethod(url);
        if (params != null && params.size() > 0) {
            method.setParams(methodParams);
        }

        try {
            int resultCode = httpClient.executeMethod(method);
            byte[] responseData = method.getResponseBody();
            result = new String(responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void post(String url) {
    }
}
