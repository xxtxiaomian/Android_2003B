package com.example.demo_2003.api;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    public static final String BASE_URL = "";

    @POST("")
    @Multipart
    Observable<ResponseBody> upDownFile(@Part("k") String str, @Part MultipartBody.Part part);
}
