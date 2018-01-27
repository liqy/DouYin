package com.liqy.douyin.network;

import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by leepood on 21/11/2017.
 */

public class HttpCommonParamsInterceptor implements Interceptor {


    public static final String TAG = "HttpParamsInterceptor";

    private Builder.Type mType;

    private Map<String, Object> mParams;

    private HttpCommonParamsInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        switch (mType) {
            case HEADER:
                request = addParams2Header(request, mParams);
                break;
            case QUERY_STRING:
                request = addParams2UrlQueryString(request, mParams);
                break;
            case FORM:
                request = addParams2FormBody(request, mParams);
                break;
            case AUTO: {
                String method = request.method();
                if (method.equalsIgnoreCase("get")) {
                    request = addParams2UrlQueryString(request, mParams);
                } else if (method.equalsIgnoreCase("post")) {
                    request = addParams2FormBody(request, mParams);
                }
            }
            break;
            default:
                throw new IllegalArgumentException("unknown type");
        }
        return chain.proceed(request);
    }

    private Request addParams2Header(Request request, Map<String, Object> mParams) {

        Set<Map.Entry<String, Object>> entrySets = mParams.entrySet();
        Request.Builder builder = request.newBuilder();
        for (Map.Entry<String, Object> entry : entrySets) {
            builder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return builder.build();
    }

    private Request addParams2UrlQueryString(Request request, Map<String, Object> mParams) {

        HttpUrl httpUrl = request.url();
        HttpUrl.Builder builder = httpUrl.newBuilder();
        Set<Map.Entry<String, Object>> entrySets = mParams.entrySet();
        for (Map.Entry<String, Object> entry : entrySets) {
            builder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return request.newBuilder()
                .url(builder.build())
                .build();
    }

    private Request addParams2FormBody(Request request, Map<String, Object> mParams) {

        RequestBody requestBody = request.body();
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            int size = formBody.size();

            TreeMap<String, String> params = new TreeMap<>();
            for (int i = 0; i < size; i++) {
                String name = formBody.encodedName(i);
                String value = formBody.encodedValue(i);
                params.put(name, value);
            }


            // add mParams to params
            Set<Map.Entry<String, Object>> entrySets = mParams.entrySet();
            for (Map.Entry<String, Object> entry : entrySets) {
                params.put(entry.getKey(), String.valueOf(entry.getValue()));
            }

            // convert params to new formBody
            FormBody.Builder builder = new FormBody.Builder();

            Set<Map.Entry<String, String>> newEnterySet = params.entrySet();
            for (Map.Entry<String, String> entry : newEnterySet) {
                builder.add(entry.getKey(), entry.getValue());
            }

            // to new request
            return request.newBuilder().post(builder.build()).build();
        } else {
            Log.w(TAG, "RequestBody => " + request.body().getClass().getCanonicalName() + " Not Support Yet!");
        }


        return request;
    }


    public static class Builder {

        private Type mType;

        private Map<String, Object> mParams;

        public enum Type {
            /**
             * add params to header
             */
            HEADER,

            /**
             * add params to query string with urlencoded
             */
            QUERY_STRING,

            /**
             * add params to body according to form type
             */
            FORM,

            /**
             * only works for http GET & POST
             */
            AUTO,
        }

        public Builder() {
            mParams = new TreeMap<>();
        }


        public Builder type(Type type) {
            mType = type;
            return this;
        }

        public Builder params(String key, String value) {
            mParams.put(key, value);
            return this;
        }

        public Builder params(String key, Integer value) {
            mParams.put(key, value);
            return this;
        }

        public Builder params(String key, Double value) {
            mParams.put(key, value);
            return this;
        }

        public Builder params(Map<String, String> value) {
            mParams.putAll(value);
            return this;
        }


        public HttpCommonParamsInterceptor build() {
            if (mType == null) {
                throw new IllegalArgumentException("must set type");
            }
            HttpCommonParamsInterceptor interceptor = new HttpCommonParamsInterceptor();
            interceptor.mType = mType;
            interceptor.mParams = mParams;
            return interceptor;
        }
    }

}
