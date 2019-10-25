package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.Controller;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items.ListarItemsFragment;


public class CrearItemFragment extends Fragment {

    private CrearItemViewModel crearItemViewModel;
    private Plato plato;
    private Integer option;
    private Controller controller;


    public CrearItemFragment() {}

    public CrearItemFragment(Plato plato, Integer option) {
        this.plato = plato;
        this.option = option;

        //TODO Buscar en base de datos
        for(Plato p: Plato.getPlatos()) {
            if(p.equals(plato)) plato = p;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        crearItemViewModel = ViewModelProviders.of(this).get(CrearItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_new_item, container, false);

        final EditText editTextNombrePLato = root.findViewById(R.id.editTextNombrePlato);
        final EditText editTextDecripcionPlato = root.findViewById(R.id.editTextDescripcionPlato);
        final EditText editTextPrecioPlato = root.findViewById(R.id.editTextPrecioPlato);
        final EditText editTextCaloriasPlato = root.findViewById(R.id.editTextCaloriasPlato);

        final Button buttonCrearPlato = root.findViewById(R.id.buttonCrearPlato);

        cambiarEstadoCampo(editTextNombrePLato, true);
        cambiarEstadoCampo(editTextDecripcionPlato, true);
        cambiarEstadoCampo(editTextPrecioPlato, true);
        cambiarEstadoCampo(editTextCaloriasPlato, true);

        validarCampoObligatorio(editTextNombrePLato);
        validarCampoObligatorio(editTextDecripcionPlato);
        validarCampoObligatorio(editTextPrecioPlato);
        validarCampoObligatorio(editTextCaloriasPlato);

        if(plato != null) {
            editTextNombrePLato.setText(plato.getNombre());
            editTextDecripcionPlato.setText(plato.getDescripcion());
            editTextPrecioPlato.setText(plato.getPrecio().toString());
            editTextCaloriasPlato.setText(plato.getCalorias().toString());

            if (option == 3) {
                cambiarEstadoCampo(editTextNombrePLato, false);
                cambiarEstadoCampo(editTextDecripcionPlato, false);
                cambiarEstadoCampo(editTextPrecioPlato, false);
                cambiarEstadoCampo(editTextCaloriasPlato, false);
            }
        }

        buttonCrearPlato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;

                Double precio;
                try {
                    precio = Double.parseDouble(editTextPrecioPlato.getText().toString());
                } catch (NumberFormatException e) {
                    precio = 0.0; //default value
                }

                Integer calorias;
                try {
                    calorias = Integer.parseInt(editTextCaloriasPlato.getText().toString());
                } catch (NumberFormatException e) {
                    calorias = 0; //default value
                }


                if (plato != null) {
                    if (!editTextNombrePLato.getText().toString().equals(null) && !editTextDecripcionPlato.getText().toString().equals(null) && precio != 0.0 && calorias != 0) {

                        plato.setNombre(editTextNombrePLato.getText().toString());
                        plato.setDescripcion((editTextDecripcionPlato.getText().toString()));
                        plato.setPrecio(precio);
                        plato.setCalorias(calorias);
                        text = "Plato Editado correctamente";
                        //TODO Guardar en base de datos
                        /*for(Plato p: Plato.getPlatos()) {
                            if(p.equals(plato)) p = plato;
                        }*/

                        ListarItemsFragment fragmentListaPlato = new ListarItemsFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentListaPlato).addToBackStack(null).commit();

                    } else {
                        text = "El plato no se ha podido editar, revise los campos erroneos.";
                    }

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else {
                    if (!editTextNombrePLato.getText().toString().equals(null) && !editTextDecripcionPlato.getText().toString().equals(null) && precio != 0.0 && calorias != 0) {

                        plato = new Plato(editTextNombrePLato.getText().toString(), editTextDecripcionPlato.getText().toString(), precio, calorias);
                        text = "Plato creado correctamente";

                        controller.getInstance().crear(plato, miHandler);

                        editTextNombrePLato.setText(null);
                        editTextDecripcionPlato.setText(null);
                        editTextPrecioPlato.setText(null);
                        editTextCaloriasPlato.setText(null);

                        editTextNombrePLato.requestFocus();

                        editTextNombrePLato.setError(null);
                        editTextDecripcionPlato.setError(null);
                        editTextPrecioPlato.setError(null);
                        editTextCaloriasPlato.setError(null);
                    } else {
                        text = "Datos Incorrectos";
                    }

                    plato = null;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }



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

    private void cambiarEstadoCampo(EditText editText, Boolean b){
        editText.setFocusable(b);
        editText.setEnabled(b);
        editText.setCursorVisible(b);
    }


    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler"+msg.arg1);

            switch (msg.arg1 ){
                case Controller._ALTA_PLATO:
                    break;
                case Controller._UPDATE_PLATO:
                    break;
            }
        }
    };

}