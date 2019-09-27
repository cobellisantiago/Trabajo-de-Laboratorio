package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class PlatoViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewNombre;
    public TextView textViewPrecio;

    public PlatoViewHolder(@NonNull View base){
        super(base);
        this.textViewNombre = (TextView) base.findViewById(R.id.textViewNombre);
        this.textViewPrecio = (TextView) base.findViewById(R.id.textViewPrecio);

    }
}
