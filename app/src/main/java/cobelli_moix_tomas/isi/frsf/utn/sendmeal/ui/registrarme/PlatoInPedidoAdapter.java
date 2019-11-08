package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.registrarme;

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
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.PlatoInPedidoViewHolder;

public class PlatoInPedidoAdapter extends RecyclerView.Adapter<PlatoInPedidoViewHolder> {

    private List<ItemsPedido> platoViewDataSet;
    private Context context;

    public PlatoInPedidoAdapter(List<ItemsPedido> platoInPedidoList) {
        this.platoViewDataSet = platoInPedidoList;
    }

    @NonNull
    @Override
    public PlatoInPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = (View) LayoutInflater.from(context).inflate(R.layout.plato_en_pedido_card, parent,false);
        return new PlatoInPedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoInPedidoViewHolder holder, int position) {
        final ItemsPedido itemsPedido = platoViewDataSet.get(position);
        DecimalFormat format = new DecimalFormat("0.00");

        holder.nombre.setText(itemsPedido.getPlato().getNombre());
        holder.precio.setText("$" + format.format(itemsPedido.getPlato().getPrecio()));
        holder.cantidad.setText(String.valueOf(itemsPedido.getCantidad()));
    }


    @Override
    public int getItemCount() {
        return platoViewDataSet.size();
    }
}
