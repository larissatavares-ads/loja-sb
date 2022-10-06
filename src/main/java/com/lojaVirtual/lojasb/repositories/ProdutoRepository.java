package com.lojaVirtual.lojasb.repositories;

import com.lojaVirtual.lojasb.models.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    int count();
    int save(Produto produto);
    int updateDescricao(Produto produto);
    int deleteById(int id);
    List<Produto> findAll();
    List<Produto> findByName(String nome);
    Optional<Produto> findById(int id);
    String getNameById(int id);
}
