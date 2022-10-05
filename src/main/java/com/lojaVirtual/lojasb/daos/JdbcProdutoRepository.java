package com.lojaVirtual.lojasb.daos;

import com.lojaVirtual.lojasb.models.Produto;
import com.lojaVirtual.lojasb.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProdutoRepository implements ProdutoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JdbcProdutoRepository.class, args);
    }
    @Override
    public int count(){
        return jdbcTemplate.queryForObject("select count(*) from produto", Integer.class);
    }
    @Override
    public int save(Produto produto) {
        return jdbcTemplate.update(
                "insert into produto (Nome, Descricao) VALUES ('TECLADO', 'TECLADO MECANICO')",
                produto.getNome(), produto.getDescricao());
    }
}
