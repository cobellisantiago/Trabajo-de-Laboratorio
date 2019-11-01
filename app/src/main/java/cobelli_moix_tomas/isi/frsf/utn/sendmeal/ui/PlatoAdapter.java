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
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;


public class PlatoAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    //creando interfaz para interactuar con el plato
    public interface EventoOnClickListenerListaPlatos {
        void onButtonClickListaPlatos(Button button, Plato plato);
    }

    private List<Plato> platoViewDataSet;
    private EventoOnClickListenerListaPlatos listener;
    private Context context;

    public PlatoAdapter (List<Plato> myPlatosDataSet, EventoOnClickListenerListaPlatos listener) {
        platoViewDataSet = myPlatosDataSet;
        this.listener = listener;
    }

    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = (View) LayoutInflater.from(context).inflate(R.layout.plato_card, parent,false);
        return new PlatoViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlatoViewHolder holder, int position) {

        final Plato plato = platoViewDataSet.get(position);
        DecimalFormat format = new DecimalFormat("0.00");

        if(!plato.getOferta()){
            holder.ofertado.setVisibility(View.GONE);
        }

        holder.textViewNombre.setText(plato.getNombre());
        holder.textViewPrecio.setText("$"+format.format(plato.getPrecio()));
        holder.imageViewFoto.setImageResource(R.drawable.hamburguesa);

        holder.setPlato(listener, plato);
    }

    @Override
    public int getItemCount() {
        return platoViewDataSet.size();
    }
}