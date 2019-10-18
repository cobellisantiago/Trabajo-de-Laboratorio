package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.rest;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Controller implements PlatoRest {

    private static String _SERVER = "http://192.168.99.1:5000";
    private List<Plato> listaPlatos;

    public static final int _ALTA_PLATO = 1;
    public static final int _UPDATE_PLATO = 2;
    public static final int _BORRADO_PLATO = 3;
    public static final int _CONSULTA_PLATO = 4;
    public static final int _ERROR_PLATO = 9;

    private static Controller _INSTANCE;

    private Controller(){}

    public static Controller getInstance(){
        if(_INSTANCE == null){
            _INSTANCE = new Controller();
            _INSTANCE.configurarRetrofit();
            _INSTANCE.listaPlatos = new ArrayList<>();
        }
        return _INSTANCE;
    }

    private Retrofit rf;
    private PlatoRest platoRest;

    private void configurarRetrofit(){
        this.rf = new Retrofit.Builder().baseUrl("http://192.168.99.1:50000/").addConverterFactory(GsonConverterFactory.create()).build();
        Log.d("APP_2", "INSTANCIA CREADA");
        this.platoRest = this.rf.create(PlatoRest.class);
    }

    public void listarPlatos(final Handler h){
        Call<List<Plato>> llamada = this.platoRest.buscarPlatos();
        llamada.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if(response.isSuccessful()){
                    listaPlatos.clear();
                    listaPlatos.addAll(response.body());
                    Message m = new Message();
                    m.arg1 = _CONSULTA_PLATO;
                    h.sendMessage(m);
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Message m = new Message();
                m.arg1 = _ERROR_PLATO;
                h.sendMessage(m);
            }
        });
    }


    @Override
    public Call<List<Plato>> buscarPlatos() {
       return null;
    }

    @Override
    public Call<Void> borrar(Integer id) {
        return null;
    }

    @Override
    public Call<Plato> actualizar(Integer id, Plato plato) {
        return null;
    }

    @Override
    public Call<Plato> crear(Plato plato) {
        return null;
    }
}