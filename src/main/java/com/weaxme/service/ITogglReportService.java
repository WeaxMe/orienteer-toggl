package com.weaxme.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ITogglReportService {

    String AUTH_TYPE = "Basic";

    @GET("weekly")
    @Headers("Content-Type: application/json")
    Single<JsonNode> getWeeklyReport(@Header("Authorization") String authorization,
                                     @Query("user_agent") String userAgent,
                                     @Query("workspace_id") String workspaceId);

    @GET("details")
    @Headers("Content-Type: application/json")
    Single<JsonNode> getDetailedWeeklyReport(@Header("Authorization") String authorization,
                                             @Query("user_agent") String userAgent,
                                             @Query("workspace_id") String workspaceId,
                                             @Query("page") int page);
}
