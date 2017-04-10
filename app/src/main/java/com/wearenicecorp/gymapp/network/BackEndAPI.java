package com.wearenicecorp.gymapp.network;

import com.wearenicecorp.gymapp.model.TrainingDTO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by JulioAndres on 4/26/16.
 */
public interface BackEndAPI {

    @GET("entrenamientos.php")
    Call<TrainingDTO> getTrainings();

    //@GET("usuarios.php")
    //Usuario getUsuarios();
}
