package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoViewHolder> {

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
        return new PlatoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoViewHolder holder, int position) {

        Plato plato = platoViewDataSet.get(position);
        DecimalFormat format = new DecimalFormat("0.00");

        holder.textViewNombre.setText(plato.getNombre());
        holder.textViewPrecio.setText("$"+format.format(plato.getPrecio()));
        holder.imageViewFoto.setImageResource(R.drawable.hamburguesa);

        holder.oferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ofertar
            }
        });

        holder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO editar
            }
        });

        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO eliminar
            }
        });
    }

    @Override
    public int getItemCount() {
        return platoViewDataSet.size();
    }
}