package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;


public class PedidoAdapter extends RecyclerView.Adapter<PedidoViewHolder> {

    public interface EventoOnClickListenerListaPlatosPedidos {
        void onButtonClickListaPlatos(Button button, ItemsPedido itemsPedido);
    }

    private List<Plato> platoViewDataSet;
    private Context context;
    private EventoOnClickListenerListaPlatosPedidos listener;

    public PedidoAdapter (List<Plato> myPlatosDataSet, EventoOnClickListenerListaPlatosPedidos listener) {
        platoViewDataSet = myPlatosDataSet;
        this.listener = listener;
    }

    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = (View) LayoutInflater.from(context).inflate(R.layout.pedido_card, parent,false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidoViewHolder holder, final int position) {

        final Plato plato = platoViewDataSet.get(position);
        DecimalFormat format = new DecimalFormat("0.00");

        holder.cantidad.setShowSoftInputOnFocus(false);
        holder.nombre.setText(plato.getNombre());
        holder.descripcion.setText(plato.getDescripcion());
        holder.precio.setText("$" + format.format(plato.getPrecio()));
        holder.imagen.setImageResource(R.drawable.hamburguesa);
        holder.cantidad.setText("0");



        holder.setPlato(listener, plato);

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.cantidad.getText().toString()) > 0){
                    holder.cantidad.setText(String.valueOf(Integer.parseInt(holder.cantidad.getText().toString()) - 1));
            }   }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cantidad.setText(String.valueOf(Integer.parseInt(holder.cantidad.getText().toString()) + 1));
            }
        });
    }

    @Override
    public int getItemCount() {
        return platoViewDataSet.size();
    }
}