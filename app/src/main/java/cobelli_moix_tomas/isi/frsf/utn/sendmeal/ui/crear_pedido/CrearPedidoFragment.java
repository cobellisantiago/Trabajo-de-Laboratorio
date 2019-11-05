package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_pedido;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Calendar;
import java.util.Date;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.AgregarPlatosAlPedido;

import static android.app.Activity.RESULT_OK;


public class CrearPedidoFragment extends Fragment {

    private static final int requestCode = 777;

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
                //TODO utilizar constructor entero
                Pedido pedido = new Pedido();
            }
        });

        enviarPedido = root.findViewById(R.id.buttonEnviarPedido);
        enviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 777){
            if (resultCode == RESULT_OK){
                //TODO usar el constructor con todo
                ItemsPedido itemsPedido = new ItemsPedido();
            }
        }

    }

}
