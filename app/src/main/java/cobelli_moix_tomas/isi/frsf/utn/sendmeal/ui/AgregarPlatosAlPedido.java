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

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.Controller;

public class AgregarPlatosAlPedido extends AppCompatActivity {

    private RecyclerView pedidoRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button agregarPlatosAlPedido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Controller.getInstance().listarPlatos(miHandler);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_platos_al_pedido);

        pedidoRecyclerView = findViewById(R.id.pedidosRecyclerView);
        pedidoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        pedidoRecyclerView.setLayoutManager(layoutManager);

        adapter = new PedidoAdapter(Controller.getInstance().getListaPlatos());
        pedidoRecyclerView.setAdapter(adapter);

        agregarPlatosAlPedido = findViewById(R.id.buttonAgregarAMiPedido);
        agregarPlatosAlPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler" + msg.arg1);

            switch (msg.arg1 ){
                case Controller._BORRADO_PLATO:
                    break;
                case Controller._CONSULTA_PLATO:
                    //TODO ver cuales manejar aca
                    break;
            }
        }
    };
}
