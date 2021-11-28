package com.example.zendesk.Objects;

import com.example.zendesk.DataModels.Zendesk;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ZendeskAPI
{
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("api/v2/tickets?page[size]=25")
    Call<Zendesk> getZendeskData(@Query("page[after]") String cursor);
}