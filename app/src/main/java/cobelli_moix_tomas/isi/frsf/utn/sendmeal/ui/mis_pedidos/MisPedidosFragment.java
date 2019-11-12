package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.mis_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.MapsActivity;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;


public class MisPedidosFragment extends Fragment {

    private MisPedidosViewModel misPedidosViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        misPedidosViewModel = ViewModelProviders.of(this).get(MisPedidosViewModel.class);
        final View root = inflater.inflate(R.layout.activity_maps, container, false);

        Intent intentMap = new Intent(getActivity(), MapsActivity.class);
        intentMap.putExtra("Mis pedidos", 1);
        startActivity(intentMap);

        return root;
    }

}
