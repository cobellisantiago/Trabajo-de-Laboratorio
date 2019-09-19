package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class CrearItemFragment extends Fragment {

    private CrearItemViewModel crearItemViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        crearItemViewModel =
                ViewModelProviders.of(this).get(CrearItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_new_item, container, false);

        return root;
    }
}