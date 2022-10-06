package com.lojaVirtual.lojasb.repositories;

import com.lojaVirtual.lojasb.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    //CREATE
    int save(Produto produto);
    //READ
    int count();
    List<Produto> findAll();
    List<Produto> findByName(String nome);
    Optional<Produto> findById(int id);
    String getNameById(int id);
    //UPDATE
    int updateDescricao(Produto produto);
    //DELETE
    int deleteById(int id);

}
