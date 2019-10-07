package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class PlatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textViewNombre;
    public TextView textViewPrecio;
    public ImageView imageViewFoto;

    PlatoAdapter.EventoOnClickListenerListaPlatos listener; // Recibe la interfaz enviada desde el adaptador
    Button button; // Recibe el button enviado desde el adaptador

    public PlatoViewHolder(@NonNull View base, PlatoAdapter.EventoOnClickListenerListaPlatos listener) {
        super(base);
        this.textViewNombre = (TextView) base.findViewById(R.id.textViewNombre);
        this.textViewPrecio = (TextView) base.findViewById(R.id.textViewPrecio);
        this.imageViewFoto = (ImageView) base.findViewById(R.id.imageViewPlato);

        base.setOnClickListener(this);

        // Inicializas el listener
        this.listener = listener;

    }

    public void setButton(Button button) {
        this.button = button; // Inicializa la posicion
    }

    @Override
    public void onClick(View view) {
        listener.onButtonClickListaPlatos(button);
    }

}