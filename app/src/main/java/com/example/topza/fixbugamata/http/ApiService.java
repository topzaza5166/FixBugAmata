package com.example.topza.fixbugamata.http;

import com.example.topza.fixbugamata.dao.DefaultResponseDao;
import com.example.topza.fixbugamata.dao.NewsDao;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by topza on 8/10/2017.
 */

public interface ApiService {
    String header = "Authorization: Basic YWRtaW5AaG90bWFpbC5jb206YWRtaW4=";

    @Headers(header)
    @GET("newsupdate")
    Observable<NewsDao> NewsUpdate();

    @Headers(header)
    @Multipart
    @POST("editreportincident")
    Observable<DefaultResponseDao> EditReportIncident(
//            @Part("body") Report report,
            @Part MultipartBody.Part file
    );

    @Headers(header)
    @Multipart
    @POST("reportincident")
    Observable<DefaultResponseDao> SendReportIncident(
//            @Part("body") Report report,
            @Part MultipartBody.Part file
    );
}
