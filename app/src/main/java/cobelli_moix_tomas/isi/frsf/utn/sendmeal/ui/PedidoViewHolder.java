package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;


public class PedidoViewHolder extends RecyclerView.ViewHolder {

    public TextView nombre;
    public TextView descripcion;
    public TextView precio;
    public Button minus;
    public Button plus;
    public ImageView imagen;
    public EditText cantidad;

    public PedidoViewHolder (View base){
        super(base);
        this.nombre = base.findViewById(R.id.textViewNombrePlatoPedido);
        this.descripcion = base.findViewById(R.id.textViewDescripcionPlatoPedidos);
        this.precio = base.findViewById(R.id.textViewPrecioPlatoPedido);
        this.minus = base.findViewById(R.id.buttonMinusPlato);
        this.plus = base.findViewById(R.id.buttonPlusPlato);
        this.imagen = base.findViewById(R.id.imageCardPlatoPedido);
        this.cantidad = base.findViewById(R.id.editTextCantidadPlato);
    }
}