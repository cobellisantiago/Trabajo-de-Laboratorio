package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.fragment.app.Fragment;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.Plato;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item.CrearItemFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_pedido.CrearPedidoFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.home.HomeFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items.ListarItemsFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.mis_pedidos.MisPedidosFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.registrarme.RegistrarmeFragment;


public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static Plato plato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseMessaging.getInstance().subscribeToTopic("general").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                String msg = "Subscrito";
                if (!task.isSuccessful()) {
                    msg = "Fallo Subscripcion";
                }
                Log.d("Notificacion_Pedido", msg);
                Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        createNotificationChannel();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_registrarme, R.id.navigation_crear_plato)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Chequea si se llamo a la clase desde la notificacion
        if(plato != null) {
            Intent notifyIntent = this.getIntent();
            String extras = notifyIntent.getStringExtra("MSJ-OFERTA");
            if (extras != null && extras.equals("OFERTA")) {
                CrearItemFragment fragmentEditarPlato = new CrearItemFragment(plato, 3);
                plato = null;
                this.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragmentEditarPlato).addToBackStack(null).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.navigation_home:
                HomeFragment fragmentHome = new HomeFragment();
                this.getSupportFragmentManager().beginTransaction().hide(fragmentHome).addToBackStack(null).commit();
                break;
            case R.id.navigation_registrarme:
                RegistrarmeFragment fragmentRegistrarme = new RegistrarmeFragment();
                this.getSupportFragmentManager().beginTransaction().hide(fragmentRegistrarme).addToBackStack(null).commit();
                break;
            case R.id.navigation_crear_plato:
                CrearItemFragment fragmentCrearPlato = new CrearItemFragment();
                this.getSupportFragmentManager().beginTransaction().hide(fragmentCrearPlato).addToBackStack(null).commit();
                break;
            case R.id.navigation_listar_items:
                ListarItemsFragment fragmentListarPlato = new ListarItemsFragment();
                this.getSupportFragmentManager().beginTransaction().hide(fragmentListarPlato).addToBackStack(null).commit();
                break;
            case R.id.navigation_crear_pedido:
                CrearPedidoFragment crearPedidoFragment = new CrearPedidoFragment();
                this.getSupportFragmentManager().beginTransaction().hide(crearPedidoFragment).addToBackStack(null).commit();
                break;
            case R.id.navigation_mis_pedidos:
                MisPedidosFragment misPedidosFragment = new MisPedidosFragment();
                this.getSupportFragmentManager().beginTransaction().hide(misPedidosFragment).addToBackStack(null).commit();
                break;
            default: return super.onOptionsItemSelected(item);
        }
        
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();
    }


    @Override
    public void onBackPressed() {
        HomeFragment fragmentHome = new HomeFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentHome).addToBackStack(null).commit();
    }

    public static Plato getPlato() {
        return plato;
    }

    public static void setPlato(Plato p) {
        plato = p;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name1 = "Canal 1 - SendMead";
            CharSequence name2 = "Canal 2 - Firebase";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel1 = new NotificationChannel("001", name1, importance);
            NotificationChannel channel2 = new NotificationChannel("002", name2, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
        }
    }
}