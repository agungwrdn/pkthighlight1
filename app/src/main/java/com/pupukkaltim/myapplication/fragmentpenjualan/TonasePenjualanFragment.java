package com.pupukkaltim.myapplication.fragmentpenjualan;


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
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.pupukkaltim.myapplication.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.github.barteksc.pdfviewer.util.Util.toByteArray;

/**
 * A simple {@link Fragment} subclass.
 */
public class TonasePenjualanFragment extends Fragment {
    private PDFView pdfViewa;
    private ProgressDialog pDialoga;
    boolean connected = false;

    public TonasePenjualanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmentview, container, false);
        pdfViewa = (PDFView) view.findViewById(R.id.pdfView);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            new TonasePenjualanFragment.DownloadFileFromURL().execute("https://firebasestorage.googleapis.com/v0/b/pkt-highlight.appspot.com/o/produksi%2Fdowntime.pdf?alt=media&token=92301bc0-698b-48eb-84ed-bd7021482da5");
        }
        else{
            connected = false;
            pdfViewa.setVisibility(View.GONE);
            Toast.makeText(getActivity().getBaseContext(), "Connection Lost !!", Toast.LENGTH_LONG).show();
        }
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

}
