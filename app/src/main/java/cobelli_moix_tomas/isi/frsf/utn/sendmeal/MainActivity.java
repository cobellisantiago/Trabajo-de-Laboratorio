package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_activity);

        //Fecha actual


        //Inicializar campos

        final TextView textViewAliasCBU = findViewById(R.id.textViewAliasCBU);
        final TextView textViewCBU = findViewById(R.id.textViewCBU);
        final TextView textViewValorCredito = findViewById(R.id.textViewValorCredito);

        final EditText editTextNombre = findViewById(R.id.editTextNombre);
        final EditText editTextPassword = findViewById(R.id.editTextPassword);
        final EditText editTextPasswordRepeat = findViewById(R.id.editTextPasswordRepeat);
        final EditText editTextEmail = findViewById(R.id.editTextEmail);
        final EditText editTextTarjeta = findViewById(R.id.editTextCodigoTarjeta);
        final EditText editTextCodigoTarjeta = findViewById(R.id.editTextCodigoTarjeta);
        //final EditText editTextDateVencimiento = findViewById(R.id.editTextDateVencimiento);
        final EditText editTextAliasCBU = findViewById(R.id.editTextAliasCBU);
        final EditText editTextCBU = findViewById(R.id.editTextCBU);
        //Test Date Vencimiento
        final EditText editTextDateVencimientoMonth = findViewById(R.id.editTextDateVencimientoMonth);
        final EditText editTextDateVencimientoYear = findViewById(R.id.editTextDateVencimientoYear);

        final Button radioButtonCuentaBase = findViewById(R.id.radioButtonCuentaBase);
        final Button radioButtonCuentaFull = findViewById(R.id.radioButtonCuentaFull);
        final Button radioButtonCuentaPremium = findViewById(R.id.radioButtonCuentaPremium);
        final Button buttonRegistrar = findViewById(R.id.buttonRegistrar);

        final Switch switchNotificacionEmail = findViewById(R.id.switchNotificacionEmail);
        final Switch switchVendedor = findViewById(R.id.switchVendedor);

        final SeekBar seekBarValorCredito = findViewById(R.id.seekBarValorCredito);

        final CheckBox checkBoxTerminosYCondiciones = findViewById(R.id.checkBoxTerminosYCondiciones);

        textViewAliasCBU.setVisibility(View.GONE);
        editTextAliasCBU.setVisibility(View.GONE);
        textViewCBU.setVisibility(View.GONE);
        editTextCBU.setVisibility(View.GONE);
        buttonRegistrar.setEnabled(false);

        //fecha acutal
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);

        esVendedor(switchVendedor, textViewAliasCBU, textViewCBU, editTextAliasCBU, editTextCBU);
        progresoSeekBar(seekBarValorCredito, textViewValorCredito);
        estadoBotonGuardar(checkBoxTerminosYCondiciones, buttonRegistrar);
        validarDatosTarjetaCredito(editTextTarjeta, editTextCodigoTarjeta, editTextDateVencimientoMonth, editTextDateVencimientoYear, fecha);


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
                        if(!editTextPassword.getText().toString().equals(editTextPasswordRepeat.getText().toString())){
                            editTextPasswordRepeat.setError("*Passwords no coinciden");
                        }else{
                            editTextPasswordRepeat.setError(null);
                        }
                    }
                }
            }
        });
        //validarDatosTarjetaCredito(editTextTarjeta, editTextCodigoTarjeta, editTextDateVencimiento);
        validarRadioButtonGroup();
    }

    private void validarRadioButtonGroup(){
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
    private void esVendedor(final Switch s, final TextView textViewAliasCBU, final TextView textViewCBU, final EditText editTextAliasCBU, final EditText editTextCBU) {

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {

                    textViewAliasCBU.setVisibility(View.VISIBLE);
                    editTextAliasCBU.setVisibility(View.VISIBLE);
                    textViewCBU.setVisibility(View.VISIBLE);
                    editTextCBU.setVisibility(View.VISIBLE);

                }else{

                    textViewAliasCBU.setVisibility(View.GONE);
                    editTextAliasCBU.setVisibility(View.GONE);
                    textViewCBU.setVisibility(View.GONE);
                    editTextCBU.setVisibility(View.GONE);


                }
            }
        });


    }
    private void progresoSeekBar(final SeekBar s, final TextView textViewValorCredito) {

        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                textViewValorCredito.setText(new Integer(i).toString());


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void estadoBotonGuardar(final CheckBox checkBoxTerminosYCOndiciones, final Button buttonRegistrar) {
        checkBoxTerminosYCOndiciones.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    buttonRegistrar.setEnabled(true);
                    buttonRegistrar.setBackgroundResource(R.drawable.bttn_rounded);
                } else {
                    buttonRegistrar.setEnabled(false);
                    buttonRegistrar.setBackgroundResource(R.drawable.bttn_rounded_disabled);
                }
            }
        });
    }
    void validarDatosTarjetaCredito(final EditText editTextNumeroTarjeta, final EditText editTextNumeroSeguridad, final EditText editTextDateVencimientoMonth, final EditText editTextDateVencimientoYear, final String fecha){

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
        editTextDateVencimientoMonth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextDateVencimientoMonth.getText().length() == 0 || editTextDateVencimientoYear.length() == 0) {
                        editTextDateVencimientoYear.setError("*Campo Obligatorio");
                    }else{

                        Integer yearToday = Integer.parseInt(fecha.substring(2,4));
                        Integer monthToday = Integer.parseInt(fecha.substring(5,7));

                        Integer month = Integer.parseInt(editTextDateVencimientoMonth.getText().toString());
                        Integer year = Integer.parseInt(editTextDateVencimientoYear.getText().toString());

                        switch(monthToday) {
                            case 9:
                                if(year <= yearToday) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 10:
                                if(year <= yearToday || (month == 1 || yearToday == year-1)) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 11:
                                if(year <= yearToday || (month <= 2 || yearToday == year-1)) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 12:
                                if(year <= yearToday || (month <= 3 || yearToday == year-1)) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            default:
                                if(year < yearToday || (month - monthToday) <= 3) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                        }

                        if(editTextDateVencimientoYear.getError() == null && month <= 12) editTextDateVencimientoYear.setError(null);
                        else editTextDateVencimientoYear.setError("*Fecha erronea");
                    }

                }else{

                    TextWatcher twMonth = new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if(editTextDateVencimientoMonth.length() == 1) {
                                editTextDateVencimientoMonth.setText("0" + s);
                                editTextDateVencimientoMonth.setSelection(editTextDateVencimientoMonth.length());
                            } else if(editTextDateVencimientoMonth.length() == 3) {
                                editTextDateVencimientoMonth.setText(editTextDateVencimientoMonth.getText().toString().substring(1));
                                editTextDateVencimientoYear.requestFocus();
                            } else if(editTextDateVencimientoMonth.getText().toString().equals("00")){
                                editTextDateVencimientoMonth.setText("");
                            }

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    };

                    editTextDateVencimientoMonth.addTextChangedListener(twMonth);



                }
            }
        });

        editTextDateVencimientoYear.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextDateVencimientoMonth.getText().length() == 0 || editTextDateVencimientoYear.length() == 0) {
                        editTextDateVencimientoYear.setError("*Campo Obligatorio");
                    }else{

                        Integer yearToday = Integer.parseInt(fecha.substring(2,4));
                        Integer monthToday = Integer.parseInt(fecha.substring(5,7));

                        Integer month = Integer.parseInt(editTextDateVencimientoMonth.getText().toString());
                        Integer year = Integer.parseInt(editTextDateVencimientoYear.getText().toString());

                        switch(monthToday) {
                            case 9:
                                if(year <= yearToday) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 10:
                                if(year <= yearToday || (month == 1 || yearToday == year-1)) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 11:
                                if(year <= yearToday || (month <= 2 || yearToday == year-1)) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 12:
                                if(year <= yearToday || (month <= 3 || yearToday == year-1)) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            default:
                                if(year < yearToday || (month - monthToday) <= 3) editTextDateVencimientoYear.setError("*Fecha erronea");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                        }

                        if(editTextDateVencimientoYear.getError() == null && month <= 12) editTextDateVencimientoYear.setError(null);
                        else editTextDateVencimientoYear.setError("*Fecha erronea");
                    }

                }else{

                    TextWatcher twYear = new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            if(editTextDateVencimientoYear.length() == 1) {
                                editTextDateVencimientoYear.setText("0" + s);
                                editTextDateVencimientoYear.setSelection(editTextDateVencimientoYear.length());
                            } else if(editTextDateVencimientoYear.length() == 3) {
                                editTextDateVencimientoYear.setText(editTextDateVencimientoYear.getText().toString().substring(1));
                                editTextDateVencimientoYear.setSelection(editTextDateVencimientoYear.length());
                            } else if(editTextDateVencimientoYear.getText().toString().equals("00")) {
                                editTextDateVencimientoYear.setText("");
                                editTextDateVencimientoMonth.requestFocus();
                                editTextDateVencimientoMonth.setSelection(editTextDateVencimientoMonth.length());
                            }

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    };

                    editTextDateVencimientoYear.addTextChangedListener(twYear);



                }
            }
        });

    }
}
