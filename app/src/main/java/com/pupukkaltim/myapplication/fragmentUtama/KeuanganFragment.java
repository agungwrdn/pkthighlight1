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
import com.pupukkaltim.myapplication.fragmentproduksi.DownTimeFragment;
import com.pupukkaltim.myapplication.fragmentproduksi.KonsusmsiGasFragment;
import com.pupukkaltim.myapplication.fragmentproduksi.OnStreamFragment;
import com.pupukkaltim.myapplication.fragmentproduksi.TonaseProduksiFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeuanganFragment extends Fragment {


    public KeuanganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keuangan, container, false);
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(selected);
        changek(R.id.LR);
        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            changek(id);

            return true;
        }
    };

    private void changek(int id) {
        Fragment fragment = null;
        if (id == R.id.LR){
            fragment = new LRFragment();
        } else if(id == R.id.Kas){
            fragment = new KasFragment();
        } else if (id == R.id.Pinjaman){
            fragment = new PinjamanFragment();
        } else if (id == R.id.HPP){
            fragment = new HPPFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }
}
