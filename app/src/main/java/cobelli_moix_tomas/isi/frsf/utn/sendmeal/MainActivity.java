package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
                    if(editTextDateVencimiento.getText().length() == 0 || editTextDateVencimiento.getText().toString().equals("MM/YY")) {
                        editTextDateVencimiento.setError("*Campo Obligatorio");
                    }else{
                        editTextDateVencimiento.setError(null);
                    }
                    Calendar dateToday = Calendar.getInstance();


                }else{



                    TextWatcher tw = new TextWatcher() {

                        private String current = "";
                        private String mmyy = "MMYY";
                        private Calendar calendar = Calendar.getInstance();

                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if(!s.toString().equals(current)) {

                                String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                                String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                                int c1 = clean.length();
                                int sel = c1;

                                for(int i = 2; i <= c1 && i < 6; i += 2) {
                                    sel++;
                                }

                                if(clean.equals(cleanC)) sel--;

                                if(clean.length() < 4) {
                                    clean = clean + mmyy.substring(clean.length());
                                } else {

                                    int mon  = Integer.parseInt(clean.substring(0,2));
                                    int year = Integer.parseInt(clean.substring(2,4));

                                    calendar.set(Calendar.MONTH, mon);
                                    calendar.set(Calendar.YEAR, year);

                                    clean = String.format("%02d%02d", mon, year);
                                }

                                clean = String.format("%s/%s",clean.substring(0,2),clean.substring(2,4));

                                sel = sel < 0 ? 0: sel;
                                current = clean;
                                editTextDateVencimiento.setText(current);
                                editTextDateVencimiento.setSelection(sel < current.length() ? sel : current.length());

                            }

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    };

                    editTextDateVencimiento.addTextChangedListener(tw);



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
