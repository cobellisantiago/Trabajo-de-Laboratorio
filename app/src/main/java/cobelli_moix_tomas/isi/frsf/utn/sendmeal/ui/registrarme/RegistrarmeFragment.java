package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.registrarme;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class RegistrarmeFragment extends Fragment {

    private RegistrarmeViewModel registrarmeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrarmeViewModel =
                ViewModelProviders.of(this).get(RegistrarmeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registrarme, container, false);


        final TextView textViewAliasCBU = root.findViewById(R.id.textViewAliasCBU);
        final TextView textViewCBU = root.findViewById(R.id.textViewCBU);
        final TextView textViewValorCredito = root.findViewById(R.id.textViewValorCredito);

        final EditText editTextNombre = root.findViewById(R.id.editTextNombre);
        final EditText editTextPassword = root.findViewById(R.id.editTextPassword);
        final EditText editTextPasswordRepeat = root.findViewById(R.id.editTextPasswordRepeat);
        final EditText editTextEmail = root.findViewById(R.id.editTextEmail);
        final EditText editTextTarjeta = root.findViewById(R.id.editTextNumeroTarjeta);
        final EditText editTextCodigoTarjeta = root.findViewById(R.id.editTextCodigoTarjeta);
        final EditText editTextAliasCBU = root.findViewById(R.id.editTextAliasCBU);
        final EditText editTextCBU = root.findViewById(R.id.editTextCBU);
        final EditText editTextDateVencimientoMonth = root.findViewById(R.id.editTextDateVencimientoMonth);
        final EditText editTextDateVencimientoYear = root.findViewById(R.id.editTextDateVencimientoYear);

        final RadioGroup radioGroupTipoCuenta = root.findViewById(R.id.radioGroupTipoCuenta);
        final RadioButton radioButtonCuentaBase = root.findViewById(R.id.radioButtonCuentaBase);
        final RadioButton radioButtonCuentaFull = root.findViewById(R.id.radioButtonCuentaFull);
        final RadioButton radioButtonCuentaPremium = root.findViewById(R.id.radioButtonCuentaPremium);
        final Button buttonRegistrar = root.findViewById(R.id.buttonRegistrar);

        final Switch switchNotificacionEmail = root.findViewById(R.id.switchNotificacionEmail);
        final Switch switchVendedor = root.findViewById(R.id.switchVendedor);

        final SeekBar seekBarValorCredito = root.findViewById(R.id.seekBarValorCredito);

        final CheckBox checkBoxTerminosYCondiciones = root.findViewById(R.id.checkBoxTerminosYCondiciones);

        textViewAliasCBU.setVisibility(View.GONE);
        editTextAliasCBU.setVisibility(View.GONE);
        textViewCBU.setVisibility(View.GONE);
        editTextCBU.setVisibility(View.GONE);
        buttonRegistrar.setEnabled(false);


        //fecha acutal
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);

        //Funciones
        esVendedor(switchVendedor, textViewAliasCBU, textViewCBU, editTextAliasCBU, editTextCBU);
        progresoSeekBar(seekBarValorCredito, textViewValorCredito);
        estadoBotonGuardar(checkBoxTerminosYCondiciones, buttonRegistrar);
        validarDatosTarjetaCredito(editTextTarjeta, editTextCodigoTarjeta, editTextDateVencimientoMonth, editTextDateVencimientoYear, fecha);
        validarRadioButtonGroup(radioGroupTipoCuenta, radioButtonCuentaBase, radioButtonCuentaFull, radioButtonCuentaPremium, textViewValorCredito, seekBarValorCredito);

        //Boton
        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Context context = view.getContext();
                CharSequence text;
                int duration = Toast.LENGTH_SHORT;

                if(editTextEmail.length() == 0) editTextEmail.setError("*Campo obligatorio");
                if(editTextPassword.length() == 0) editTextPassword.setError("*Campo obligatorio");
                if(editTextPasswordRepeat.length() == 0) editTextPasswordRepeat.setError("*Campo obligatorio");
                if(editTextCodigoTarjeta.length() == 0) editTextCodigoTarjeta.setError("*Campo obligatorio");
                if(editTextTarjeta.length() == 0) editTextTarjeta.setError("*Campo obligatorio");
                if(editTextDateVencimientoYear.length() == 0) editTextDateVencimientoYear.setError("*Campo obligatorio");
                if(radioGroupTipoCuenta.getCheckedRadioButtonId() == -1)radioButtonCuentaFull.setError("*Campo Obligatorio");
                else radioButtonCuentaFull.setError(null);
                if(editTextAliasCBU.length() == 0) editTextAliasCBU.setError("*Campo Obligatorio");
                if(editTextCBU.length() == 0) editTextCBU.setError("*Campo Obligatorio");

                if(editTextEmail.getError()==null && editTextPassword.getError() == null && editTextPasswordRepeat.getError() == null && editTextCodigoTarjeta.getError() == null && editTextTarjeta.getError() == null && editTextDateVencimientoYear.getError() == null && radioButtonCuentaFull.getError() == null && editTextAliasCBU.getError() == null && editTextCBU.getError() == null && radioButtonCuentaFull.getError() == null){
                    text = "Datos guardados correctamente";
                } else{
                    text = "Datos Incorrectos";
                }
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

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
                            editTextEmail.setError("El email debe contener al menos un @ y 3 letras luego del mismo");
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
                    }else{
                        editTextEmail.setError(null);
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

        return  root;
    }

    private void validarRadioButtonGroup(final RadioGroup radioGroupTipoCuenta, final RadioButton radioButtonCuentaBase, final RadioButton radioButtonCuentaFull, final RadioButton radioButtonCuentaPremium, final TextView textViewCreditoInicial, final SeekBar s){

        radioGroupTipoCuenta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioButtonCuentaBase.isChecked()){
                    textViewCreditoInicial.setText("100");
                    s.setMax(1400);
                }
                else if(radioButtonCuentaPremium.isChecked()){
                    textViewCreditoInicial.setText("250");
                    s.setMax(1250);
                }
                else if(radioButtonCuentaFull.isChecked()){
                    textViewCreditoInicial.setText("500");
                    s.setMax(1000);
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

                textViewValorCredito.setText(new Integer(i + (1500-s.getMax())).toString());


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
    private void validarDatosTarjetaCredito(final EditText editTextNumeroTarjeta, final EditText editTextNumeroSeguridad, final EditText editTextDateVencimientoMonth, final EditText editTextDateVencimientoYear, final String fecha){

        editTextNumeroTarjeta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(!focus){
                    if(editTextNumeroTarjeta.length()==0){
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
                    if(editTextNumeroSeguridad.length()==0){
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
                    if(editTextDateVencimientoMonth.length() == 0 || editTextDateVencimientoYear.length() == 0) {
                        editTextDateVencimientoYear.setError("*Campo Obligatorio");
                    }else{

                        Integer yearToday = Integer.parseInt(fecha.substring(2,4));
                        Integer monthToday = Integer.parseInt(fecha.substring(5,7));

                        Integer month = Integer.parseInt(editTextDateVencimientoMonth.getText().toString());
                        Integer year = Integer.parseInt(editTextDateVencimientoYear.getText().toString());

                        switch(monthToday) {
                            case 9:
                                if(year <= yearToday) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 10:
                                if(year <= yearToday || (month == 1 || yearToday == year-1)) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 11:
                                if(year <= yearToday || (month <= 2 || yearToday == year-1)) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 12:
                                if(year <= yearToday || (month <= 3 || yearToday == year-1)) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            default:
                                if(year < yearToday || (month - monthToday) <= 3) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                        }

                        if (month>12) editTextDateVencimientoYear.setError("*Debe ingresar un mes valido entre 1 y 12");
                        else if(editTextDateVencimientoYear.getError() == null) editTextDateVencimientoYear.setError(null);
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
                                if(year <= yearToday) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 10:
                                if(year <= yearToday || (month == 1 || yearToday == year-1)) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 11:
                                if(year <= yearToday || (month <= 2 || yearToday == year-1)) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            case 12:
                                if(year <= yearToday || (month <= 3 || yearToday == year-1)) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                            default:
                                if(year < yearToday || (month - monthToday) <= 3) editTextDateVencimientoYear.setError("*La fecha de vencimiento debe ser superior a los proximos 3 meses");
                                else editTextDateVencimientoYear.setError(null);
                                break;
                        }
                        if (month>12) editTextDateVencimientoYear.setError("*Debe ingresar un mes valido entre 1 y 12");
                        else if(editTextDateVencimientoYear.getError() == null) editTextDateVencimientoYear.setError(null);
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

