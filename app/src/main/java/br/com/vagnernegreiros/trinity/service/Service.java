package br.com.vagnernegreiros.trinity.service;

import java.util.List;

import br.com.vagnernegreiros.trinity.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {

    @POST("login")
    Call<List<Usuario>> login(@Body Usuario usuario);

}
