package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;


public class PedidoAdapter extends RecyclerView.Adapter<PedidoViewHolder> {
    private List<Plato> platoViewDataSet;
    private Context context;
    private AgregarPlatosAlPedido agregarPlatosAlPedido = new AgregarPlatosAlPedido();

    public PedidoAdapter (List<Plato> myPlatosDataSet) {
        platoViewDataSet = myPlatosDataSet;
    }

    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = (View) LayoutInflater.from(context).inflate(R.layout.pedido_card, parent,false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidoViewHolder holder, int position) {

        final Plato plato = platoViewDataSet.get(position);
        DecimalFormat format = new DecimalFormat("0.00");

        holder.nombre.setText(plato.getNombre());
        holder.descripcion.setText(plato.getDescripcion());
        holder.precio.setText("$"+format.format(plato.getPrecio()));
        holder.imagen.setImageResource(R.drawable.hamburguesa);


        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.agregarPlatoAMiPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemsPedido itemsPedido = new ItemsPedido();
                itemsPedido.setCantidad(Integer.parseInt(holder.cantidad.getText().toString()));
                itemsPedido.setPrecio(plato.getPrecio());
                itemsPedido.setPlato(plato);
                agregarPlatosAlPedido.getItemPedidoFromAdapter(itemsPedido);
            }
        });
    }


    @Override
    public int getItemCount() {
        return platoViewDataSet.size();
    }
}