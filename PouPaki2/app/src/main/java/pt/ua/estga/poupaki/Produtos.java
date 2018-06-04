package pt.ua.estga.poupaki;

import android.os.Bundle;
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

/**
 * Created by Luis on 03/05/2018.
 */

public class Produtos extends Fragment {
    View view;
    TextView value;
    Button avancar;

    private RecyclerView mRecyclerView;
    private Produtos_adapter mCarrinhoAdapter;
    private ArrayList<Class_produtos> mCarrinhoList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.produtos, container, false);
        value = (TextView) view.findViewById(R.id.value_supermercado);
        avancar = (Button) view.findViewById(R.id.button);

        Bundle bundle = getArguments();
        if (bundle != null)
            value.setText(String.valueOf(bundle.getString("supermarket")));

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment carrinho_compras = new Carrinho_Compras();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, carrinho_compras).commit();

            }

        });

        try {
            mCarrinhoList = new ArrayList<>();
            JSONArray array = new JSONArray(loadJSON());

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String nome = obj.getString("nome");
                String preco = obj.getString("preco");

                mCarrinhoList.add(new Class_produtos(nome, preco));
                buildRecycleView();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;

        //  Toast.makeText(getContext(), bundle.getString("supermarket"), Toast.LENGTH_SHORT).show();

    }


    public void buildRecycleView() {
        mRecyclerView = view.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mCarrinhoAdapter = new Produtos_adapter(getContext(), mCarrinhoList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCarrinhoAdapter);
    }


    public String loadJSON() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("produto.json");
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
