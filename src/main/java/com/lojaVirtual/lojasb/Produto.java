package com.lojaVirtual.lojasb;

public class Produto {
    private int Id;
    private String Nome;
    private String Descricao;

    public Produto(int id, String nome, String descricao) {
        Id = id;
        Nome = nome;
        Descricao = descricao;
    }

    @Override
    public String toString() {
        return "LojaSbModel{" +
                "Id=" + Id +
                ", Nome='" + Nome + '\'' +
                ", Descricao='" + Descricao + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
}
