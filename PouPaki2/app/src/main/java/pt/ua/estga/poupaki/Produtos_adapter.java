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

public class Produtos_adapter extends RecyclerView.Adapter<Produtos_adapter.ProdutosViewHolder> {

    private Context mcContext;
    private ArrayList<Class_produtos> mcarrinho;

    public Produtos_adapter(Context mcContext, ArrayList<Class_produtos> mcarrinho) {
        this.mcContext = mcContext;
        this.mcarrinho = mcarrinho;
    }

    @Override


    public ProdutosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcContext).inflate(R.layout.single_item , parent, false
        );
        return new Produtos_adapter.ProdutosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProdutosViewHolder holder, int position) {
        Class_produtos item = mcarrinho.get(position);

        String nome =  item.getNome();
        String preco = item.getPreco();

        holder.mTextViewNome.setText(nome);
        holder.mTextViewPreco.setText(preco);
    }

    @Override
    public int getItemCount() {
        return mcarrinho.size();
    }

    public class ProdutosViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNome;
        public TextView mTextViewPreco;
        public ProdutosViewHolder(View itemView) {
            super(itemView);

            mTextViewNome = itemView.findViewById(R.id.nome);
            mTextViewPreco = itemView.findViewById(R.id.preco);
        }

    }
}
