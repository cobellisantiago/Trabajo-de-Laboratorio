package cobelli_moix_tomas.isi.frsf.utn.sendmeal;

import androidx.fragment.app.Fragment;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.crear_item.CrearItemFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.home.HomeFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.listar_items.ListarItemsFragment;
import cobelli_moix_tomas.isi.frsf.utn.sendmeal.ui.registrarme.RegistrarmeFragment;

public class Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_registrarme, R.id.navigation_crear_plato)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {
            case R.id.navigation_home:
                HomeFragment fragmentHome = new HomeFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fragmentHome)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.navigation_registrarme:
                RegistrarmeFragment fragmentRegistrarme = new RegistrarmeFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fragmentRegistrarme)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.navigation_crear_plato:
                CrearItemFragment fragmentCrearPlato = new CrearItemFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fragmentCrearPlato)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.navigation_listar_items:
                ListarItemsFragment fragmentListarPlato = new ListarItemsFragment();
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fragmentListarPlato)
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {

        HomeFragment fragmentHome = new HomeFragment();
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment,fragmentHome)
                .addToBackStack(null)
                .commit();

    }
}
