package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;

public class CrearItemFragment extends Fragment {

    private CrearItemViewModel crearItemViewModel;
    private Plato plato;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        crearItemViewModel =
                ViewModelProviders.of(this).get(CrearItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_new_item, container, false);

        final EditText editTextIdPlato = root.findViewById(R.id.editTextIdPlato);
        final EditText editTextNombrePLato = root.findViewById(R.id.editTextNombrePlato);
        final EditText editTextDecripcionPlato = root.findViewById(R.id.editTextDescripcionPlato);
        final EditText editTextPrecioPlato = root.findViewById(R.id.editTextPrecioPlato);
        final EditText editTextCaloriasPlato = root.findViewById(R.id.editTextCaloriasPlato);

        final Button buttonCrearPlato = root.findViewById(R.id.buttonCrearPlato);

        validarCampoObligatorio(editTextNombrePLato);
        validarCampoObligatorio(editTextDecripcionPlato);
        validarCampoObligatorio(editTextPrecioPlato);
        validarCampoObligatorio(editTextCaloriasPlato);

        buttonCrearPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;


                if(editTextNombrePLato.getError() == null && editTextDecripcionPlato.getError() == null && editTextPrecioPlato.getError() == null && editTextCaloriasPlato.getError() == null) {

                    plato = new Plato(editTextNombrePLato.getText().toString(),
                            editTextDecripcionPlato.getText().toString(),
                            Double.parseDouble(editTextPrecioPlato.getText().toString()),
                            Integer.parseInt(editTextCaloriasPlato.getText().toString()));

                    text = "Plato creado correctamente";
                } else{
                    text = "Datos Incorrectos";
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });


        return root;
    }

    private void validarCampoObligatorio(final EditText editText) {
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus) {
                    if (editText.length() == 0) editText.setError("*Campo obligatorio");
                    else editText.setError(null);
                }

            }
        });
    }
}