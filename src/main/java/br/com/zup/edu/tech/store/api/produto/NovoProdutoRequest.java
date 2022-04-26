package br.com.zup.edu.tech.store.api.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 6)
    private String codigo;

    @NotNull
    @Positive
    private BigDecimal preco;

    public NovoProdutoRequest(String nome, String codigo, BigDecimal preco) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
    }

    public NovoProdutoRequest() {
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Produto toProduto() {
        return new Produto(nome,codigo,preco);
    }
}
