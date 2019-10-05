package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class PlatoViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewNombre;
    public TextView textViewPrecio;
    public ImageView imageViewFoto;
    public Button oferta;
    public Button editar;
    public Button eliminar;

    public PlatoViewHolder(@NonNull View base){
        super(base);
        this.textViewNombre = (TextView) base.findViewById(R.id.textViewNombre);
        this.textViewPrecio = (TextView) base.findViewById(R.id.textViewPrecio);
        this.imageViewFoto = (ImageView) base.findViewById(R.id.imageViewPlato);
        this.oferta = base.findViewById(R.id.buttonOferta);
        this.editar = base.findViewById(R.id.buttonEditar);
        this.eliminar = base.findViewById(R.id.buttonEliminar);
    }
}