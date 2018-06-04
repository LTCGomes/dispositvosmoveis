package pt.ua.estga.poupaki;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Luis on 04/06/2018.
 */

public  class Lista_adapter extends RecyclerView.Adapter<Lista_adapter.ListaViewHolder> {

    private Context mcContext;
    private ArrayList<Lista> mlista;

    public Lista_adapter(Context mcContext, ArrayList<Lista> mlista) {
        this.mcContext = mcContext;
        this.mlista = mlista;
    }

    public Lista_adapter.ListaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcContext).inflate(R.layout.single_item_lista, parent, false
        );
        return new ListaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        Lista item = mlista.get(position);

        String nome = item.getNome();

        holder.mTextViewNome.setText(nome);

    }


    @Override
    public int getItemCount() {
        return mlista.size();
    }

    public class ListaViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewNome;

        public ListaViewHolder(View itemView) {
            super(itemView);

            mTextViewNome = itemView.findViewById(R.id.nome_produto);


        }
    }
}






