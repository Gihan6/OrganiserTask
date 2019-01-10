package com.alokda.loginwithfacebook.NetWorkControler.WebServices;

import com.alokda.loginwithfacebook.NetWorkControler.WebModel.Response.ListEventResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface WebServicesEvents {

    @GET("End url")
    @Headers({"Content-Type: application/json"})
    Observable<ListEventResponse> getListEvent(@Header("Authorization") String auth);

}
