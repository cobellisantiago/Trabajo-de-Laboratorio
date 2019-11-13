package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.PedidoRepository;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Spinner spinnerEstados;
    private ArrayAdapter<CharSequence> estadosPedidos;
    private ProgressDialog progressDialog;
    private Button agregarUbicacion;
    private Marker ubicacion;
    private Pedido pedido;
    private List<Pedido> pedidoList = new ArrayList<Pedido>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        agregarUbicacion = findViewById(R.id.botonAgregarUbicacion);

        Intent intent = getIntent();
        if (intent.getExtras().get("pedido").equals(1)){
            PedidoRepository.getInstance().listarPedidos(miHandler);
            agregarUbicacion.setVisibility(View.GONE);
        }else {
            pedido = (Pedido) getIntent().getSerializableExtra("pedido");
            agregarUbicacion.setVisibility(View.VISIBLE);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);





        spinnerEstados = findViewById(R.id.spinner);
        estadosPedidos = ArrayAdapter.createFromResource(this, R.array.states_array, android.R.layout.simple_spinner_item);
        estadosPedidos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstados.setAdapter(estadosPedidos);


        //TODO hacer aca el if para ver si se llama a la actividad para seleccionar ubicacion al crear pedido o si se llama para showPedidosOnMap

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.actualizarMapa();
    }

    private void actualizarMapa(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 9999);
            return;
        }
        this.googleMap.setMyLocationEnabled(true);

        this.googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                //TODO agregarle .icon depende los estados

                if (ubicacion != null){
                    ubicacion.setPosition(latLng);
                }
                else{
                    ubicacion = googleMap.addMarker(new MarkerOptions().position(latLng).draggable(true).title("Enviar pedido"));
                }

                agregarUbicacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pedido.setLongitudCordenada(latLng.longitude);
                        pedido.setLatitudCordenada(latLng.latitude);
                        Intent intent = getIntent();
                        intent.putExtra("Pedido", pedido);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
        });


        //TODO visualizar pedidos en el mapa
        //TODO no anda. Ver como hacer para esperar a que traiga los pedidos antes de acceder a dicha lista desde showPedidosOnMap

    }

    public void showPedidosOnMap (){

        for (Pedido p : pedidoList){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(p.getLatitudCordenada(), p.getLongitudCordenada()))
                    .title(p.getId() + " " + p.getId())
                    .snippet(p.getEstadoPedido() + "-" + "$ " + p.getPrecioTotal()).draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 9999: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    actualizarMapa();
                }
                else {
                    //
                }
                return;
            }
        }
    }


    Handler miHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(Message msg) {
            Log.d("APP_2","Vuelve al handler"+msg.arg1);

            switch (msg.arg1){
                case PedidoRepository._CONSULTA_PEDIDO:{
                    //TODO manejar esto
                    pedidoList = PedidoRepository.getInstance().getListaPedidos();
                    showPedidosOnMap();
                    break;
                }
            }
        }
    };

}
