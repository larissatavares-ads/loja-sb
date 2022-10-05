package com.lojaVirtual.lojasb.repositories;

import com.lojaVirtual.lojasb.models.Produto;

import java.util.List;

public interface ProdutoRepository {
    int count();
    int save(Produto produto);
    int update(Produto produto);
    int deleteById(Long id);
    List<Produto> findAll();
    List<Produto> findByName(String name);
    String getNameById(Long id);
}
