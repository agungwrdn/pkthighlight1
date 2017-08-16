package com.pupukkaltim.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.pupukkaltim.myapplication.fragmentUtama.KeuanganFragment;
import com.pupukkaltim.myapplication.fragmentUtama.LainFragment;
import com.pupukkaltim.myapplication.fragmentUtama.PenjualanFragment;
import com.pupukkaltim.myapplication.fragmentUtama.ProduksiFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        if (mAuth.getCurrentUser() == null) {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        change(R.id.Produksi);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        change(id);
        return true;
    }

    private void change(int id) {
        Fragment fragment = null;
        if (id == R.id.Produksi) {
            fragment = new ProduksiFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();
        } else if(id == R.id.Keuangan){
            fragment = new KeuanganFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();
        } else if(id == R.id.lain){
            fragment = new LainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();
        } else if(id == R.id.Penjualan){
            fragment = new PenjualanFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitNow();
        } else {
            Intent dia = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(dia);
            mAuth.signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
