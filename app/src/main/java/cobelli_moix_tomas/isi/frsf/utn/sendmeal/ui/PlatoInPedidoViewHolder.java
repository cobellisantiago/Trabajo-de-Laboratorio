package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;

public class PlatoInPedidoViewHolder extends RecyclerView.ViewHolder {

    public TextView nombre;
    public TextView precio;
    public Button quitarPlatoAMiPedido;
    public TextView cantidad;

    public PlatoInPedidoViewHolder(View base) {
        super(base);
        this.nombre = base.findViewById(R.id.textViewNombrePlatoDelPedido);
        this.precio = base.findViewById(R.id.textViewPrecioPlatoDelPedido);
        this.cantidad = base.findViewById(R.id.textViewCantidadPlatoDelPedido);
        this.quitarPlatoAMiPedido = base.findViewById(R.id.botonQuitarPlatoAMiPedido);
    }
}
