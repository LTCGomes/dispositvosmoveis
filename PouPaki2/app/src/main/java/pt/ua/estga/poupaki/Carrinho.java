package pt.ua.estga.poupaki;

/**
 * Created by Luis on 04/06/2018.
 */

public class Carrinho {
    private String nome;
    private String preco;

    public Carrinho(String nome, String preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }
}
