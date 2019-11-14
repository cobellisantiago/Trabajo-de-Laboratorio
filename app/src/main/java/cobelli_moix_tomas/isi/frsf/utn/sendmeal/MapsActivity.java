package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.PedidoRepository;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Pedido;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Spinner spinnerEstados;
    private ArrayAdapter<CharSequence> estadosPedidos;
    private Button agregarUbicacion;
    private Marker ubicacion;
    private Pedido pedido;
    private List<Pedido> pedidoList = new ArrayList<Pedido>();
    private List<Pedido> pedidosEnEnvio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        agregarUbicacion = findViewById(R.id.botonAgregarUbicacion);

        Intent intent = getIntent();

        if (intent.getExtras().get("pedido").equals(1)){
            PedidoRepository.getInstance().listarPedidos(miHandler);
            agregarUbicacion.setVisibility(View.GONE);
            spinnerEstados = findViewById(R.id.spinner);
            estadosPedidos = ArrayAdapter.createFromResource(this, R.array.states_array, android.R.layout.simple_spinner_item);
            estadosPedidos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerEstados.setAdapter(estadosPedidos);

            spinnerEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    pedidosEnEnvio = new ArrayList<Pedido>();
                    googleMap.clear();
                    if (pos == 0){
                        showPedidosOnMap();
                    }
                    else {
                        for (Pedido p : pedidoList){
                            if (p.getEstadoPedido() == pos){
                                googleMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(p.getLatitudCordenada(), p.getLongitudCordenada()))
                                        .title(p.getId() + " " + p.getId())
                                        .snippet(p.getEstadoPedido() + "-" + "$ " + p.getPrecioTotal())
                                        .icon(BitmapDescriptorFactory.defaultMarker(addMarker(p.getEstadoPedido()))));
                            }
                            if (p.getEstadoPedido() == 6){
                                pedidosEnEnvio.add(p);
                            }
                        }
                    }

                    if (pedidosEnEnvio.size() > 0){
                        caminoPedidosEnEnvio(pedidosEnEnvio);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        else {
            pedido = (Pedido) getIntent().getSerializableExtra("pedido");
            agregarUbicacion.setVisibility(View.VISIBLE);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    }

    public void showPedidosOnMap (){
        for (Pedido p : pedidoList){
            googleMap.addMarker(new MarkerOptions().position(new LatLng(p.getLatitudCordenada(), p.getLongitudCordenada()))
                    .title(p.getId() + " " + p.getId())
                    .snippet(p.getEstadoPedido() + "-" + "$ " + p.getPrecioTotal())
                    .icon(BitmapDescriptorFactory.defaultMarker(addMarker(p.getEstadoPedido()))));
        }
    }


    public float addMarker (Integer estadoPedido){
        float marcador = 0;
        switch (estadoPedido){
            case 1:
                marcador = BitmapDescriptorFactory.HUE_YELLOW;
                break;
            case 2:
                marcador = BitmapDescriptorFactory.HUE_AZURE;
            break;
            case 3:
                marcador = BitmapDescriptorFactory.HUE_ROSE;
            break;
            case 4:
                marcador = BitmapDescriptorFactory.HUE_RED;
            break;
            case 5:
                marcador = BitmapDescriptorFactory.HUE_MAGENTA;
            break;
            case 6:
                marcador = BitmapDescriptorFactory.HUE_GREEN;
            break;
            case 7:
                marcador = BitmapDescriptorFactory.HUE_CYAN;
                break;
            case 8:
                marcador = BitmapDescriptorFactory.HUE_AZURE;
                break;
        }
        return marcador;
    }

    public void caminoPedidosEnEnvio(List<Pedido> pedidosEnEnvio){
        PolygonOptions camino = new PolygonOptions();
        LatLng latLng;
        for (Pedido p : pedidosEnEnvio){
            latLng = new LatLng(p.getLatitudCordenada(), p.getLongitudCordenada());
            camino.add(latLng).fillColor(Color.GREEN).strokeColor(Color.GREEN);
        }
        googleMap.addPolygon(camino);
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
                    pedidoList = PedidoRepository.getInstance().getListaPedidos();
                    showPedidosOnMap();
                    break;
                }
            }
        }
    };
}