package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.rest.PedidoRest;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PedidoRepository {
    public static String _SERVER = "http://192.168.0.4:5000/";

    private List<Pedido> listaPedidos;

    public static final int _ALTA_PEDIDO = 1;
    public static final int _UPDATE_PEDIDO = 2;
    public static final int _BORRADO_PEDIDO = 3;
    public static final int _CONSULTA_PEDIDO = 4;
    public static final int _ERROR_PEDIDO = 9;

    private static PedidoRepository _INSTANCE;

    public PedidoRepository(){}

    public static PedidoRepository getInstance(){
        if(_INSTANCE == null){
            _INSTANCE = new PedidoRepository();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaPedidos = new ArrayList<>();
        }
        return _INSTANCE;
    }

    private Retrofit rf;
    private PedidoRest pedidoRest;

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder().baseUrl(_SERVER).addConverterFactory(GsonConverterFactory.create()).build();
        Log.d("APP_2", "INSTANCIA CREADA");
        this.pedidoRest = this.rf.create(PedidoRest.class);
    }

    public void listarPedidos(final Handler h){
        Call<List<Pedido>> llamada = this.pedidoRest.buscarPedidos();
        llamada.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if(response.isSuccessful()){
                    listaPedidos.clear();
                    listaPedidos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _CONSULTA_PEDIDO;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Message m = new Message();
                m.arg1 = _ERROR_PEDIDO;
                h.sendMessage(m);
            }
        });
    }

    public void crear(Pedido pedido, final Handler h) {

        Call<Pedido> llamada = this.pedidoRest.crear(pedido);
        llamada.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {

                Log.d("APP_2", "Despues que ejecuta" + response.isSuccessful());
                Log.d("APP_2", "Codigo" +response.code());

                if(response.isSuccessful()) {
                    Log.d("APP_2", "EJECUTO");
                    listaPedidos.add(response.body());
                    Message m = new Message();
                    m.arg1 = _ALTA_PEDIDO;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Log.d("APP_2", "ERROR" + t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_PEDIDO;
                h.sendMessage(m);
            }
        });
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    /*public void borrar(final Pedido pedido, final Handler h) {

        Call<Void> llamada = this.pedidoRest.borrar(pedido.getId());
        llamada.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Log.d("APP_2", "Despues que ejecuta" + response.isSuccessful());
                Log.d("App_2", "Codigo" + response.code());

                if (response.isSuccessful()) {

                    Log.d("APP_2", "EJECUTO");

                    for (Pedido p : listaPedidos) {
                        Log.d("APP_2", "Obra " + p.getId());
                    }
                    listaPedidos.remove(pedido);
                    Log.d("APP_2", "BORRA Obra " + pedido.getId());
                    for (Pedido p: listaPedidos){
                        if(p.equals(pedido)){
                            listaPedidos.remove(pedido);
                        }
                    }
                    for (Pedido p : listaPedidos) {
                        Log.d("APP_2", "Obra " + p.getId());
                    }

                    Message m = new Message();
                    m.arg1 = _BORRADO_PLATO;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("APP_2", "ERROR " + t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);
            }
        });
    }*/

    /*public void actualizar(final Pedido pedido, final Handler h) {

        final Call<Plato> llamada = this.platoRest.actualizar(plato.getId(), plato);
        llamada.enqueue(new Callback<Plato>() {
            @Override
            public void onResponse(Call<Plato> call, Response<Plato> response) {

                Log.d("APP_2", "Despues que ejecuta" + response.isSuccessful());
                Log.d("APP_2", "Codigo" + response.code());

                if (response.isSuccessful()) {
                    Log.d("App_2", "EJECUTO");
                    for (Plato p: listaPlatos) {
                        if (p.equals(plato)) {
                            listaPlatos.remove(plato);
                        }
                    }
                    listaPlatos.add(response.body());
                    Message m = new Message();
                    m.arg1 = _UPDATE_PLATO;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<Plato> call, Throwable t) {
                Log.d("APP_2", "ERROR" + t.getMessage());
                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);
            }
        });
    }*/

}
