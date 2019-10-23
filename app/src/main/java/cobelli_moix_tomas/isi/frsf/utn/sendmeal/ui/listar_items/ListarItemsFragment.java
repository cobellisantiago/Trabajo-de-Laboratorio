package cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.Home;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.R;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.PlatoAdapter;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.ThreadOfertarPlato;
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
            public void onButtonClickListaPlatos(Button button, final Plato platoApretado) {
                switch (button.getId()){
                    case R.id.buttonOferta:

                        if(!platoApretado.getOferta()) {

                            ThreadOfertarPlato hilo = new ThreadOfertarPlato(getContext(), platoApretado);
                            hilo.start();

                            for (Plato p : Plato.getPlatos()) {
                                if (p.equals(platoApretado)) {
                                    platoApretado.setOferta(true);
                                    Home.setPlato(platoApretado);
                                }

                            }


                        } else {

                            for (Plato p : Plato.getPlatos()) {
                                if (p.equals(platoApretado)) {
                                    platoApretado.setOferta(false);

                                }
                            }

                        }
                        break;

                    case R.id.buttonEditar:
                        CrearItemFragment fragmentEditarPlato = new CrearItemFragment(platoApretado, 2);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentEditarPlato).addToBackStack(null).commit();
                        break;

                    case R.id.buttonEliminar:
                        AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                        builder.setTitle("Eliminar un Plato");
                        builder.setMessage("¿Esta seguro que quiere eliminar este plato?");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Hacer cosas aqui al hacer clic en el boton de aceptar
                                Plato.getPlatos().remove(platoApretado);
                                ListarItemsFragment fragmentListarPlato = new ListarItemsFragment();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentListarPlato).addToBackStack(null).commit();
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Podemos agregar un mensaje toast que diga que el plato no fue eliminado
                            }
                        });
                        builder.show();
                        break;
                }
            }
        });
        platoRecyclerView.setAdapter(adapter);

        return root;
    }
}