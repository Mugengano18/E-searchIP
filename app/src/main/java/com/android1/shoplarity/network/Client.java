package com.android1.shoplarity.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android1.shoplarity.credentials.Base_URL;
import static com.android1.shoplarity.credentials.YELP_API_KEY;

public class Client {
    private static Retrofit square=null;

    public static Api getclient(){
        if(square==null){
            OkHttpClient okHttpClient=new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Authorization",YELP_API_KEY)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            square = new Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return square.create(Api.class);
    }
}
