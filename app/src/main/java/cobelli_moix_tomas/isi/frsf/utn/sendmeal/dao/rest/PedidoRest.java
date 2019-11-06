package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.rest;

import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface PedidoRest {
    @GET("pedidos/")
    Call<List<Pedido>> buscarPedidos();

    @DELETE("pedidos/{id}")
    Call<Void> borrar(@Path("id") String id);

    @PUT("pedidos/{id}")
    Call<Pedido> actualizar(@Path("id") String id, @Body Pedido pedido);

    @POST("pedidos/")
    Call<Pedido> crear(@Body Pedido pedido);
}
