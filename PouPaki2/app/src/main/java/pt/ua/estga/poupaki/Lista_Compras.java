package pt.ua.estga.poupaki;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Luis on 03/05/2018.
 */

public class Lista_Compras extends Fragment {
    @Nullable

    View view;
    private RecyclerView mRecyclerView;
    private Lista_adapter mListadapter;
    private ArrayList <Lista>  mlistaList;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lista_compras,container, false);

        try {
            mlistaList = new ArrayList<>();
            JSONArray array = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String nome = obj.getString("nome");
                mlistaList.add(new Lista(nome));
                buildRecycleView();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void buildRecycleView() {
        mRecyclerView = view.findViewById(R.id.listacompras_view);
        mRecyclerView.setHasFixedSize(true);
        mListadapter = new Lista_adapter(getContext(), mlistaList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mListadapter);
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
}
