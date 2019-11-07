package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;


public class PedidoViewHolder extends RecyclerView.ViewHolder {

    public TextView nombre;
    public TextView descripcion;
    public TextView precio;
    public Button minus;
    public Button plus;
    public ImageView imagen;
    public EditText cantidad;
    public Button agregarPlatoAMiPedido;

    public PedidoViewHolder (View base){
        super(base);
        this.nombre = base.findViewById(R.id.textViewNombrePlatoPedido);
        this.descripcion = base.findViewById(R.id.textViewDescripcionPlatoPedidos);
        this.precio = base.findViewById(R.id.textViewPrecioPlatoPedido);
        this.minus = base.findViewById(R.id.buttonMinusPlato);
        this.plus = base.findViewById(R.id.buttonPlusPlato);
        this.imagen = base.findViewById(R.id.imageViewPlatoPedido);
        this.cantidad = base.findViewById(R.id.editTextCantidadPlato);
        this.agregarPlatoAMiPedido = base.findViewById(R.id.botonAgregarAMiPedido);
    }



    public void setPlato(final PedidoAdapter.EventoOnClickListenerListaPlatosPedidos listener, final Plato plato) {

        agregarPlatoAMiPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemsPedido itemsPedido = new ItemsPedido();
                itemsPedido.setPlato(plato);
                itemsPedido.setPrecio(plato.getPrecio());
                itemsPedido.setCantidad(Integer.parseInt(cantidad.getText().toString()));
                listener.onButtonClickListaPlatos(agregarPlatoAMiPedido, itemsPedido);
            }
        });
    }

}