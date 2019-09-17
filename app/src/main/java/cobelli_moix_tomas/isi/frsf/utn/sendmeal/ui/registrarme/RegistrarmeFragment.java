package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.registrarme;

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

public class RegistrarmeFragment extends Fragment {

    private RegistrarmeViewModel registrarmeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrarmeViewModel =
                ViewModelProviders.of(this).get(RegistrarmeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registrarme, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        registrarmeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}