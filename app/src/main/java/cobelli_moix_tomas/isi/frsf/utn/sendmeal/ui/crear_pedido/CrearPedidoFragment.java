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
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;


public class CrearPedidoFragment extends Fragment {

    private CrearPedidoViewModel crearPedidoViewModel;
    private Button agregarPlato;

    public CrearPedidoFragment(){}

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        crearPedidoViewModel = ViewModelProviders.of(this).get(CrearPedidoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_crear_pedido, container, false);

        agregarPlato = root.findViewById(R.id.buttonAgregarPlato);
        agregarPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });




        return root;
    }

}
