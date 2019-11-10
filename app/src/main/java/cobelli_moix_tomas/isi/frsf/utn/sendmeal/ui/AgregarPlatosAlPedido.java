package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.PlatoRepository;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;

public class AgregarPlatosAlPedido extends AppCompatActivity {

    private RecyclerView pedidoRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        PlatoRepository.getInstance().listarPlatos(miHandler);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_platos_al_pedido);

        pedidoRecyclerView = findViewById(R.id.platoInPedidoRecyclerView);
        pedidoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        pedidoRecyclerView.setLayoutManager(layoutManager);

        adapter = new PedidoAdapter(PlatoRepository.getInstance().getListaPlatos(), new PedidoAdapter.EventoOnClickListenerListaPlatosPedidos() {
            @Override
            public void onButtonClickListaPlatos(Button button, ItemsPedido itemsPedido) {
                Intent intent = getIntent();
                intent.putExtra("item", itemsPedido);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        pedidoRecyclerView.setAdapter(adapter);
    }


    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler" + msg.arg1);

            switch (msg.arg1 ){
                case PlatoRepository._BORRADO_PLATO:
                    break;
                case PlatoRepository._CONSULTA_PLATO:
                    //TODO ver cuales manejar aca y en los demas handler
                    break;
            }
        }
    };
}
