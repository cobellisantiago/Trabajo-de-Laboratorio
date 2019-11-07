package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_pedido;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import java.io.Serializable;
import java.util.Date;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.DBClient;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.ItemsPedidoDao;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.PedidoDao;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.PedidoRepository;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.AgregarPlatosAlPedido;
import static android.app.Activity.RESULT_OK;


public class CrearPedidoFragment extends Fragment implements Serializable{

    private static final int requestCode = 13;
    private Pedido pedido = new Pedido();

    private CrearPedidoViewModel crearPedidoViewModel;
    private Button agregarPlato;
    private Button crearPedido;
    private  Button enviarPedido;

    public CrearPedidoFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        crearPedidoViewModel = ViewModelProviders.of(this).get(CrearPedidoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_pedido, container, false);

        agregarPlato = root.findViewById(R.id.buttonAgregarPlato);
        agregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AgregarPlatosAlPedido.class);
                startActivityForResult(intent, requestCode);
            }
        });


        crearPedido = root.findViewById(R.id.buttonCrearPedido);
        crearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.setEstadoPedido(1);
                pedido.setFechaCreacion(new Date());

                //TODO cambiar las coordenadas una vez que usemos el mapa
                pedido.setLatitudCordenada(0.0);
                pedido.setLongitudCordenada(0.0);

                PedidoDao pedidoDao = DBClient.getInstance(getActivity()).getAppDB().pedidoDao();
                pedidoDao.insert(pedido);

                Toast toast = Toast.makeText(getContext(), "Pedido creado correctamente!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                enviarPedido.setEnabled(true);
                enviarPedido.setBackgroundResource(R.drawable.bttn_rounded);
            }
        });

        enviarPedido = root.findViewById(R.id.buttonEnviarPedido);
        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.setEstadoPedido(2);
                ItemsPedidoDao itemsPedidoDao = DBClient.getInstance(getActivity()).getAppDB().itemsPedidoDao();
                pedido.setItemsPedidoList(itemsPedidoDao.getAll());
                PedidoRepository.getInstance().crear(pedido, miHandler);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        ItemsPedidoDao itemsPedidoDao = DBClient.getInstance(getActivity()).getAppDB().itemsPedidoDao();

        if (requestCode == 13 && resultCode == RESULT_OK){
            ItemsPedido itemsPedido = (ItemsPedido) data.getSerializableExtra("item");
            itemsPedido.setPedido(pedido.getId());
            itemsPedidoDao.insert(itemsPedido);

            Pedido.PedidoToItemsPedido.getItemsPedidoList().add(itemsPedido);
        }

    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler"+msg.arg1);

            switch (msg.arg1 ){
                case PedidoRepository._ALTA_PEDIDO:
                    break;
                case PedidoRepository._UPDATE_PEDIDO:
                    break;
            }
        }
    };

}
