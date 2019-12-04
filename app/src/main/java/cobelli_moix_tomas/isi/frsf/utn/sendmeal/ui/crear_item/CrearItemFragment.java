package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.PlatoRepository;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items.ListarItemsFragment;
import static android.app.Activity.RESULT_OK;


public class CrearItemFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private CrearItemViewModel crearItemViewModel;
    private Plato plato;
    private Integer option;

    private Bitmap imagenPlato;
    private ImageView imageViewPlato;


    public CrearItemFragment() {}

    public CrearItemFragment(Plato plato, Integer option) {
        this.plato = plato;
        this.option = option;

        for(Plato p: PlatoRepository.getInstance().getListaPlatos()) {
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
        final Button sacarFoto = root.findViewById(R.id.buttonSacarFoto);
        imageViewPlato = root.findViewById(R.id.imageViewPlato);

        cambiarEstadoCampo(editTextNombrePLato, true);
        cambiarEstadoCampo(editTextDecripcionPlato, true);
        cambiarEstadoCampo(editTextPrecioPlato, true);
        cambiarEstadoCampo(editTextCaloriasPlato, true);

        validarCampoObligatorio(editTextNombrePLato);
        validarCampoObligatorio(editTextDecripcionPlato);
        validarCampoObligatorio(editTextPrecioPlato);
        validarCampoObligatorio(editTextCaloriasPlato);

        sacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentFoto.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivityForResult(intentFoto, REQUEST_IMAGE_CAPTURE);
                }
            }
        });


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
                        plato.setImage(imagenPlato);
                        text = "Plato Editado correctamente";

                        PlatoRepository.getInstance().actualizar(plato,miHandler);

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
                        plato.setImage(imagenPlato);
                        text = "Plato creado correctamente";

                        PlatoRepository.getInstance().crear(plato, miHandler);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            if (data != null && data.getExtras() != null){
                imagenPlato = (Bitmap) data.getExtras().get("data");
                imageViewPlato.setImageBitmap(imagenPlato);
            }

        }
    }

    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler"+msg.arg1);

            switch (msg.arg1 ){
                case PlatoRepository._ALTA_PLATO:
                    break;
                case PlatoRepository._UPDATE_PLATO:
                    break;
            }
        }
    };

}