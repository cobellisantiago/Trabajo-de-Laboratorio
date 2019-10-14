package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;

public class PlatoViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewNombre;
    public TextView textViewPrecio;
    public ImageView imageViewFoto;
    public Button oferta;
    public Button editar;
    public Button eliminar;
    public TextView ofertado;

    public PlatoViewHolder(@NonNull View base, PlatoAdapter.EventoOnClickListenerListaPlatos listener) {
        super(base);
        this.textViewNombre = (TextView) base.findViewById(R.id.textViewNombre);
        this.textViewPrecio = (TextView) base.findViewById(R.id.textViewPrecio);
        this.imageViewFoto = (ImageView) base.findViewById(R.id.imageViewPlato);
        this.oferta = base.findViewById(R.id.buttonOferta);
        this.editar = base.findViewById(R.id.buttonEditar);
        this.eliminar = base.findViewById(R.id.buttonEliminar);
        this.ofertado = base.findViewById(R.id.ofertado);

    }

    public void setPlato(final PlatoAdapter.EventoOnClickListenerListaPlatos listener, final Plato plato) {

        oferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonClickListaPlatos(oferta, plato);

                if(!plato.getOferta()) {
                    ofertado.setVisibility(View.GONE);
                } else {
                    ofertado.setVisibility(View.VISIBLE);
                }

            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonClickListaPlatos(editar, plato);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonClickListaPlatos(eliminar, plato);
            }
        });
    }

}