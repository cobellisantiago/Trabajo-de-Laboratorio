package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.PlatoAdapter;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item.CrearItemFragment;

public class ListarItemsFragment extends Fragment {

    private RecyclerView platoRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.ViewHolder viewHolder;
    private RecyclerView.LayoutManager layoutManager;

    private ListarItemsViewModel listarItemsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listarItemsViewModel = ViewModelProviders.of(this).get(ListarItemsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_listar_item, container, false);

        platoRecyclerView = root.findViewById(R.id.dishRecyclerView);
        platoRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(root.getContext());
        platoRecyclerView.setLayoutManager(layoutManager);

        adapter = new PlatoAdapter(Plato.getPlatos(), new PlatoAdapter.EventoOnClickListenerListaPlatos() {
            @Override
            public void onButtonClickListaPlatos(Button button) {
                switch (button.getId()){
                    //case R.id.buttonOferta:
                    case R.id.buttonEditar:
                        CrearItemFragment fragmentEditarPlato = new CrearItemFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment,fragmentEditarPlato)
                                .addToBackStack(null)
                                .commit();
                        break;
                    //case R.id.buttonEliminar:
                }
            }
        });
        platoRecyclerView.setAdapter(adapter);

        /*((PlatoAdapter)adapter).setOnClickListener(new PlatoAdapter.EventoOnClickListenerListaPlatos() {
            @Override
            public void onButtonClickListaPlatos(Button button) {

                switch (button.getId()){
                    //case R.id.buttonOferta:
                    case R.id.buttonEditar:
                                CrearItemFragment fragmentEditarPlato = new CrearItemFragment();
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.nav_host_fragment,fragmentEditarPlato)
                                        .addToBackStack(null)
                                        .commit();
                        break;
                    //case R.id.buttonEliminar:
                }

            }
        });*/



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