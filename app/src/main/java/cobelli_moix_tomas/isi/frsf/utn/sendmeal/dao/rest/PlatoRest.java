package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.rest;

import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import retrofit2.Call;
import retrofit2.http.*;


public interface PlatoRest {

    @GET("platos/")
    Call<List<Plato>> buscarPlatos();

    @DELETE("platos/{id}")
    Call<Void> borrar(@Path("id") String id);

    @PUT("platos/{id}")
    Call<Plato> actualizar(@Path("id") String id, @Body Plato plato);

    @POST("platos/")
    Call<Plato> crear(@Body Plato plato);

}