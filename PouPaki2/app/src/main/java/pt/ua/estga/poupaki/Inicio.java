package pt.ua.estga.poupaki;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Luis on 03/05/2018.
 */

public class Inicio extends Fragment {
    View view;
    Spinner supermercados;
    Button avancar;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.inicio, container, false);
        supermercados = (Spinner) view.findViewById(R.id.spinner);
        avancar = (Button) view.findViewById(R.id.button);

        ArrayAdapter<String> adapter = new ArrayAdapter <>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.supermercados));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supermercados.setAdapter(adapter);

        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment produtos = new Produtos();
                Bundle bundle = new Bundle();
                bundle.putString("supermarket",supermercados.getSelectedItem().toString());
                produtos.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, produtos).commit();


            }
        });

        return view;
    }


}
