package CobelliMoixTomas.isi.frsf.utn.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_activity);

        final EditText editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextEmail.getText().length()==0) {
                        editTextEmail.setError("*Campo obligatorio");
                    }else{
                        if(editTextEmail.getText().toString().contains("@") && (editTextEmail.getText().toString().substring(
                               editTextEmail.getText().toString().indexOf("@"),editTextEmail.getText().toString().length()-1)).length()>=3){
                            editTextEmail.setError(null);
                        }else {
                            editTextEmail.setError("Email incorrecto");
                        }
                    }
                }
            }
        });

        final EditText editTextPassword = findViewById(R.id.editTextPassword);
        final EditText editTextPasswordRepeat = findViewById(R.id.editTextPasswordRepeat);
        //final TextView textViewPasswordError = findViewById(R.id.textViewPasswordError);
        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextPassword.getText().length()==0){
                       editTextPassword.setError("*Campo obligatorio");
                        //textViewPasswordError.setText("*Campo obligatorio");
                    }else{
                        editTextEmail.setError(null);
                        //textViewPasswordError.setText("");
                    }
                }
            }
        });
        editTextPasswordRepeat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextPasswordRepeat.getText().length()==0){
                        editTextPasswordRepeat.setError("*Campo Obligatorio");
                    }else{
                        if(!editTextPassword.getText().equals(editTextPasswordRepeat.getText())){
                            editTextPasswordRepeat.setError("*Passwords no coinciden");
                        }else{
                            editTextPasswordRepeat.setError(null);
                        }
                    }
                }
            }
        });
        validarDatosTarjetaCredito();
        validarRadioButtonGroup();
    }

    void validarDatosTarjetaCredito(){
        final EditText editTextNumeroTarjeta = findViewById(R.id.editTextNumeroTarjeta);
        final EditText editTextNumeroSeguridad = findViewById(R.id.editTextCodigoTarjeta);
        final EditText editTextDateVencimiento = findViewById(R.id.editTextDateVencimiento);

        editTextNumeroTarjeta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextNumeroTarjeta.getText().length()==0){
                        editTextNumeroTarjeta.setError("*Campo Obligatorio");
                    }else{
                        editTextNumeroTarjeta.setError(null);
                    }
                }
            }
        });
        editTextNumeroSeguridad.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextNumeroSeguridad.getText().length()==0){
                        editTextNumeroSeguridad.setError("*Campo Obligatorio");
                    }else{
                        editTextNumeroSeguridad.setError(null);
                    }
                }
            }
        });
        editTextDateVencimiento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextDateVencimiento.getText().length()==0){
                        editTextDateVencimiento.setError("*Campo Obligatorio");
                    }else{
                        editTextDateVencimiento.setError(null);
                    }
                }else{
                    editTextDateVencimiento.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }


                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                if (i1 == 1 && charSequence.charAt(1) != '/') {
                                    editTextDateVencimiento.setText(charSequence + "/");
                                    editTextDateVencimiento.setSelection(editTextDateVencimiento.getText().length());
                                }
                                if (i1 == 4) {
                                    if (charSequence.toString().charAt(3) > 1 && charSequence.toString().charAt(3) <= 9) {
                                        Character lastChar = charSequence.toString().charAt(3);
                                        editTextDateVencimiento.setText(charSequence.toString().replace(lastChar, '0') + lastChar);
                                    }
                                }

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                }
            }
        });
    }
    void validarRadioButtonGroup(){
        final RadioGroup radioGroupTipoCuenta = findViewById(R.id.radioGroup2);
        final RadioButton radioButtonCuentaFull = findViewById(R.id.radioButtonCuentaFull);
        radioGroupTipoCuenta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(radioGroupTipoCuenta.getCheckedRadioButtonId() == -1)radioButtonCuentaFull.setError("*Campo Obligatorio");
                    else radioButtonCuentaFull.setError(null);
                }
            }
        });
    }
}
