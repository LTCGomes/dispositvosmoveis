package pt.ua.estga.poupaki;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import es.dmoral.toasty.Toasty;

/**
 * Created by Luis on 03/05/2018.
 */

public class Carrinho_Compras extends Fragment {
    View view;
    private Button navegar;
    private TextView precoTextView;
    private RecyclerView mRecyclerView;
    private Carrinho_adapter mCarrinhoAdapter;
    private ArrayList<Carrinho> mCarrinhoList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.carrinho_compras,container, false);
        precoTextView = view.findViewById(R.id.total);
        navegar = view.findViewById(R.id.navegar);
        bindbutton();

        try {
            mCarrinhoList = new ArrayList<>();
            JSONArray array = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String nome = obj.getString("nome");
                String preco = obj.getString("preco");

                mCarrinhoList.add(new Carrinho(nome,preco));
                buildRecycleView();
                calcularTotal();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void bindbutton() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String supermarket = preferences.getString("supermarket", "");

        navegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    JSONArray array = new JSONArray(loadCoordenadas());

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);

                        String nome = obj.getString("nome");
                        String latitude = obj.getString("latitude");
                        String longitude = obj.getString("longitude");

                        if (nome.equals(supermarket.toString())) {
                            try {
                                String strUri = "http://maps.google.com/maps?q=loc:" + latitude  + "," + longitude + " (" + "Direção para " + nome + ")";
                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                                startActivity(intent);
                            } catch (Exception e) {
                                Toasty.warning(getContext(), "Houve um erro ao abrir a localização.", Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void calcularTotal() {
        float total = 0;

        for (int i = 0; i < mCarrinhoList.size(); i++) {
            String split = mCarrinhoList.get(i).getPreco().split(" ")[0];
            total += Float.parseFloat(split.toString());
        }

        precoTextView.setText(String.format("%.2f", total) + "€");
    }

    public void buildRecycleView() {
       mRecyclerView = view.findViewById(R.id.recyclerView);
       mRecyclerView.setHasFixedSize(true);
       mCarrinhoAdapter = new Carrinho_adapter(getContext(), mCarrinhoList);
       mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       mRecyclerView.setAdapter(mCarrinhoAdapter);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("produtos.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public String loadCoordenadas() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("supermercados.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }





}
