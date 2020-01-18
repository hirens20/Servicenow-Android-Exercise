package com.servicenow.exercise_java.apiRequestRetroFit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebApiService {
        @GET("/f5bf443c-160d-11ea-ab7b-c5ee597d34d8")
        Call<GamesObject> getAllGames();
}
