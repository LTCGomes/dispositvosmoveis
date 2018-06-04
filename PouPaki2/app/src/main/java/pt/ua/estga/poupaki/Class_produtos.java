package pt.ua.estga.poupaki;

/**
 * Created by Luis on 04/06/2018.
 */

public class Class_produtos {

    private String nome;
    private String preco;

    public Class_produtos(String nome, String preco) {
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
