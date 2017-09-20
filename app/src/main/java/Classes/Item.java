package Classes;

import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Item {

    private String ItemId;
    private String Categoria;
    @NonNull
    private String Nome;
    @Nullable
    private String Descricao;
    private String Obs;
    private Double Valor;
    private int Quantidade;

    public Item() {
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }
}
