package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.PlatoAdapter;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.PlatoViewHolder;

public class ListarItemsFragment extends Fragment {

    private RecyclerView platoRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.ViewHolder viewHolder;
    private RecyclerView.LayoutManager layoutManager;

    private ListarItemsViewModel listarItemsViewModel;

    /*private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    private View listaPlatos = this.getView();*/




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listarItemsViewModel = ViewModelProviders.of(this).get(ListarItemsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listar_item, container, false);

        platoRecyclerView = root.findViewById(R.id.dishRecyclerView);
        platoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(root.getContext());
        platoRecyclerView.setLayoutManager(layoutManager);

        adapter = new PlatoAdapter(Plato.getPlatos());
        platoRecyclerView.setAdapter(adapter);

        return root;
    }

    /*@Override
    public View getView (){
        return onCreateView(inflater, container, savedInstanceState);
    }*/

   /* public View getView (int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View platoSeleccionado = convertView;

        if (platoSeleccionado == null){
            platoSeleccionado = inflater.inflate(R.layout.plato_card, parent, false);
        }

        PlatoViewHolder holder = (PlatoViewHolder) platoSeleccionado.getTag();
        if (holder == null){
            holder = new PlatoViewHolder(platoSeleccionado);
            platoSeleccionado.setTag(holder);
        }

        Plato plato = (Plato) super.getItem(position);

        holder.textViewNombre.setText(plato.getNombre());
        holder.textViewPrecio.setText(plato.getPrecio().toString());

        return platoSeleccionado;
    }*/
}