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

public class Carrinho_adapter extends RecyclerView.Adapter<Carrinho_adapter.CarrinhoViewHolder> {

    private Context mcContext;
    private ArrayList<Carrinho> mcarrinho;

    public Carrinho_adapter(Context mcContext, ArrayList<Carrinho> mlista) {
        this.mcContext = mcContext;
        this.mcarrinho = mlista;
    }

    @Override
    public Carrinho_adapter.CarrinhoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcContext).inflate(R.layout.single_item , parent, false
        );
        return new CarrinhoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarrinhoViewHolder holder, int position) {
        Carrinho item = mcarrinho.get(position);

        String nome =  item.getNome();
        String preco = item.getPreco();

        holder.mTextViewNome.setText(nome);
        holder.mTextViewPreco.setText(preco);
    }

    @Override
    public int getItemCount() {
        return mcarrinho.size();
    }

    public class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewNome;
        public TextView mTextViewPreco;
        public CarrinhoViewHolder(View itemView) {
            super(itemView);

            mTextViewNome = itemView.findViewById(R.id.nome);
            mTextViewPreco = itemView.findViewById(R.id.preco);
        }
    }
}
