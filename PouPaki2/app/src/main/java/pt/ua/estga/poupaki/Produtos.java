package pt.ua.estga.poupaki;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luis on 03/05/2018.
 */

public class Produtos extends Fragment {
    View view;
    TextView value;
    Button avancar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.produtos,container, false);
        value=(TextView)view.findViewById(R.id.value_supermercado);
        avancar = (Button) view.findViewById(R.id.button);

        Bundle bundle=getArguments();
        if (bundle != null)
            value.setText(String.valueOf(bundle.getString("supermarket")));

        //  Toast.makeText(getContext(), bundle.getString("supermarket"), Toast.LENGTH_SHORT).show();


        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Lista_Compras() ).commit();
            }
        });
        return view;
    }
}
