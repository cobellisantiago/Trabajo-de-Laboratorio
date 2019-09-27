package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;

public class NewItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_item);

        /*final EditText editTextNombre = findViewById(R.id.editTextNombrePlato);
        final EditText editTextDescripcion = findViewById(R.id.editTextDescripcionPlato);
        final EditText editTextPrecio = findViewById(R.id.editTextPrecioPlato);
        final EditText editTextCalorias = findViewById(R.id.editTextCaloriasPlato);

        Button buttonCrear = findViewById(R.id.buttonCrearPlato);
        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;

                crearPlato(editTextNombre,editTextDescripcion, editTextPrecio,editTextCalorias);

                text = "Plato creado";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });*/



    }

    private void crearPlato(EditText editTextNombre,EditText editTextDescripcion,EditText  editTextPrecio,EditText editTextCalorias){
       String nombre = editTextNombre.getText().toString();
       String descripcion = editTextDescripcion.getText().toString();
       Double precio = Double.parseDouble(editTextNombre.getText().toString());
       Integer calorias = Integer.parseInt(editTextNombre.getText().toString());

        if(nombre.isEmpty()){
            throw new NullPointerException("Nombre plato");
        }
        if(descripcion.isEmpty()){
            throw new NullPointerException("Descripcion plato");
        }
        if(precio == null){
            throw new NullPointerException("Nombre plato");
        }
        if(calorias == null){
            throw new NullPointerException("Nombre plato");
        }

        Plato platoNuevo = new Plato(nombre,descripcion,precio,calorias);
    }
}
