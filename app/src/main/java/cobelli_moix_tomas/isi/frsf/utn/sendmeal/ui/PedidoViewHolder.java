package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

    }

}
