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
import com.pupukkaltim.myapplication.fragmentLain.DownloadDailyFragment;
import com.pupukkaltim.myapplication.fragmentLain.HargaKomoditasFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.HPPFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.KasFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.LRFragment;
import com.pupukkaltim.myapplication.fragmentkeuangan.PinjamanFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LainFragment extends Fragment {


    public LainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lain, container, false);
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(selected);
        changeL(R.id.HargaKomoditas);
        return view;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener selected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            changeL(id);

            return true;
        }
    };

    private void changeL(int id) {
        Fragment fragment = null;
        if (id == R.id.DownloadDaily){
            fragment = new DownloadDailyFragment();
        } else if(id == R.id.HargaKomoditas){
            fragment = new HargaKomoditasFragment();
        }

        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }
}
