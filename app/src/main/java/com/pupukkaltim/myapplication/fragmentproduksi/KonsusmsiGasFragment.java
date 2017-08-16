package com.pupukkaltim.myapplication.fragmentproduksi;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pupukkaltim.myapplication.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.github.barteksc.pdfviewer.util.Util.toByteArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class KonsusmsiGasFragment extends Fragment {
    private PDFView pdfViewa;
    private ProgressDialog pDialoga;
    boolean connected = false;
    private FirebaseDatabase mDB;
    private DatabaseReference mDBuser;
    private TextView watermark;
    private FirebaseAuth mAuth;
    private TextView date;

    public KonsusmsiGasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentview, container, false);
        pdfViewa = (PDFView) view.findViewById(R.id.pdfView);
        date = (TextView) view.findViewById(R.id.dateTime);
        mDB = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDBuser = mDB.getReference().child("users");
        watermark = (TextView) view.findViewById(R.id.watermark);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            new KonsusmsiGasFragment.DownloadFileFromURL().execute("https://firebasestorage.googleapis.com/v0/b/pkt-highlight.appspot.com/o/produksi%2Fkonsumsi.pdf?alt=media&token=20454ac5-bbf0-4382-a7f5-0649768d0f95");
        }
        else{
            connected = false;
            pdfViewa.setVisibility(View.GONE);
            Toast.makeText(getActivity().getBaseContext(), "Connection Lost !!", Toast.LENGTH_LONG).show();
        }
        retrieveData();
        return view;
    }

    private class DownloadFileFromURL extends AsyncTask<String, Void, byte[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("Starting download");
            pDialoga = new ProgressDialog(getActivity());
            pDialoga.setMessage("Please wait...");
            pDialoga.setIndeterminate(false);
            pDialoga.setCancelable(false);
            pDialoga.show();
        }
        /**
         * Downloading file in background thread
         * */
        @Override
        protected byte[] doInBackground(String... strings) {
            InputStream inputStream;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }
            catch (Exception e)
            {
                return  null;
            }
            try {
                return toByteArray(inputStream);
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            System.out.println("Downloaded");
            pdfViewa.fromBytes(bytes).load();
            pDialoga.dismiss();
        }
    }

    private void retrieveData() {
        DatabaseReference userName = mDBuser.child(mAuth.getCurrentUser().getUid()).child("username");
        userName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue(String.class);
                watermark.setText(username);
                String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
                date.setText(formattedDate);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
