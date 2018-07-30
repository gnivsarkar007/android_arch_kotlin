package com.example.kotlin.myapplication.api;

import com.example.kotlin.myapplication.api.model.Response;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("svc/books/v3/lists/best-sellers/history.json")
    Observable<Response> getBestSellerList(@Query("api-key") String apiKey);

    class Factory {

        public static ApiInterface create() {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            clientBuilder.addInterceptor(new ApiRequestInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .client(clientBuilder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.nytimes.com/")
                    .build();

            return retrofit.create(ApiInterface.class);
        }
    }

}
