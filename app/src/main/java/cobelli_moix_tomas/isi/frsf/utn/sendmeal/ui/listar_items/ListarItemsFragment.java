package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class ListarItemsFragment extends Fragment {

    private ListarItemsViewModel listarItemsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listarItemsViewModel =
                ViewModelProviders.of(this).get(ListarItemsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listar_item, container, false);

        return root;
    }
}
