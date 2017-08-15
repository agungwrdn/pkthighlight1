package com.pupukkaltim.myapplication.fragmentUtama;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pupukkaltim.myapplication.R;
import com.pupukkaltim.myapplication.fragmentproduksi.DownTimeFragment;
import com.pupukkaltim.myapplication.fragmentproduksi.KonsusmsiGasFragment;
import com.pupukkaltim.myapplication.fragmentproduksi.OnStreamFragment;
import com.pupukkaltim.myapplication.fragmentproduksi.TonaseProduksiFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProduksiFragment extends Fragment {

    public ProduksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produksi, container, false);
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(selected);
        changepr(R.id.TonaseProduksi);
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            changepr(id);

            return true;
        }
    };

    private void changepr(int id) {
        Fragment fragment = null;

        if (id == R.id.TonaseProduksi){
            fragment = new TonaseProduksiFragment();
        } else if(id == R.id.KonsumsiGas){
            fragment = new KonsusmsiGasFragment();
        } else if (id == R.id.OnStream){
            fragment = new OnStreamFragment();
        } else if (id == R.id.DownTime){
            fragment = new DownTimeFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

}
