package com.wearenicecorp.gymapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by JulioAndres on 4/26/16.
 */
public interface BackEndAPI {

    @GET("entrenamientos.php")
    Call<TrainingDTO> getTrainings();

    //@GET("usuarios.php")
    //Usuario getUsuarios();
}
