package br.edu.fatecpg.spring.jackson.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaCarrinho{

    private List<Carrinho> carts;
    private int total;
    private int skip;
    private int limit;

    public RespostaCarrinho() {
    }

    public List<Carrinho> getCarts() {
        return carts;
    }

    public int getTotal() {
        return total;
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "RespostaCarrinho{" +
                "carts=" + carts +
                ", total=" + total +
                ", skip=" + skip +
                ", limit=" + limit +
                '}';
    }
}