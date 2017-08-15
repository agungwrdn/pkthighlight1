package com.pupukkaltim.myapplication.fragmentUtama;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pupukkaltim.myapplication.R;
import com.pupukkaltim.myapplication.fragmentkeuangan.HPPFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.KasFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.LRFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.PinjamanFragment;
import com.pupukkaltim.myapplication.fragmentpenjualan.StokFragment;
import com.pupukkaltim.myapplication.fragmentpenjualan.TonasePenjualanFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PenjualanFragment extends Fragment {


    public PenjualanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_penjualan, container, false);
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(selected);
        changePe(R.id.TonasePenjualan);
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            changePe(id);

            return true;
        }
    };

    private void changePe(int id) {
        Fragment fragment = null;
        if (id == R.id.TonasePenjualan){
            fragment = new TonasePenjualanFragment();
        } else if(id == R.id.Stok){
            fragment = new StokFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

}
