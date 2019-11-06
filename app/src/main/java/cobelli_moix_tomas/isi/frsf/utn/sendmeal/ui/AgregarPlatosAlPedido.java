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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.Controller;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_pedido.CrearPedidoFragment;

public class AgregarPlatosAlPedido extends AppCompatActivity {

    private RecyclerView pedidoRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        Controller.getInstance().listarPlatos(miHandler);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_platos_al_pedido);

        pedidoRecyclerView = findViewById(R.id.pedidosRecyclerView);
        pedidoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        pedidoRecyclerView.setLayoutManager(layoutManager);

        adapter = new PedidoAdapter(Controller.getInstance().getListaPlatos(), new PedidoAdapter.EventoOnClickListenerListaPlatosPedidos() {
            @Override
            public void onButtonClickListaPlatos(Button button, ItemsPedido itemsPedido) {

            }
        });
        pedidoRecyclerView.setAdapter(adapter);


    }


    public void getItemPedidoFromAdapter (ItemsPedido itemsPedido){

        //intent.putExtra("item", itemsPedido);
        //getIntent().putExtra("item", itemsPedido);
        finish();
    }


    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler" + msg.arg1);

            switch (msg.arg1 ){
                case Controller._BORRADO_PLATO:
                    break;
                case Controller._CONSULTA_PLATO:
                    //TODO ver cuales manejar aca y en los demas handler
                    break;
            }
        }
    };
}
