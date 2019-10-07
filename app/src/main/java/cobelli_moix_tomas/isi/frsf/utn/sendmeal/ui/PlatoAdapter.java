package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item.CrearItemFragment;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

    //creando interfaz para interactuar con el plato
    public interface EventoOnClickListenerListaPlatos {
        public void onButtonClickListaPlatos(Button button);
    }

    EventoOnClickListenerListaPlatos listener;

    private List<Plato> platoViewDataSet;
    private Context context;

    public PlatoAdapter (List<Plato> myPlatosDataSet) {
        platoViewDataSet = myPlatosDataSet;
    }

    @NonNull
    @Override
    public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = (View) LayoutInflater.from(context).inflate(R.layout.plato_card, parent,false);
        return new PlatoViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {

        Plato plato = platoViewDataSet.get(position);

        DecimalFormat format = new DecimalFormat("0.00");

        holder.textViewNombre.setText(plato.getNombre());
        holder.textViewPrecio.setText("$"+format.format(plato.getPrecio()));
        holder.imageViewFoto.setImageResource(R.drawable.hamburguesa);

    }

    @Override
    public int getItemCount() {
        return platoViewDataSet.size();
    }

    public void setOnClickListener(EventoOnClickListenerListaPlatos listener){
        this.listener=listener;
    }
}