package com.lojaVirtual.lojasb.repositories;

import com.lojaVirtual.lojasb.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProdutoRepository implements ProdutoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int count(){
        return jdbcTemplate.queryForObject("select count(*) from produto", Integer.class);
    }
    @Override
    public int save(Produto produto) {
        return  jdbcTemplate.update(
                "insert into produto (Nome, Descricao) VALUES (?,?)",
                produto.getNome(), produto.getDescricao());
    }
    @Override
    public int updateDescricao(Produto produto) {
        return jdbcTemplate.update(
                "update produto set Descricao = ? where Id = ?",
                produto.getDescricao(), produto.getId());
    }
    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update(
                "delete produto where Id = ?",
                id);
    }
    @Override
    public List<Produto> findAll() {
        return jdbcTemplate.query(
                "select * from produto",
                (rs, rowNum) ->
                        new Produto(
                                rs.getInt("Id"),
                                rs.getString("Nome"),
                                rs.getString("Descricao")
                        )
        );
    }
    @Override
    public List<Produto> findByName(String nome) {
        return jdbcTemplate.query(
                "select * from produto where Nome like ?",
                new Object[]{"%" + nome + "%"},
                (rs, rowNum) ->
                        new Produto(
                                rs.getInt("Id"),
                                rs.getString("Nome"),
                                rs.getString("Descricao")
                        )
        );
    }
    @Override
    public Optional<Produto> findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from produto where Id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Produto(
                                rs.getInt("Id"),
                                rs.getString("Nome"),
                                rs.getString("Descricao")
                        ))
        );
    }
    @Override
    public String getNameById(int id) {
        return jdbcTemplate.queryForObject(
                "select Nome from produto where Id = ?",
                new Object[]{id},
                String.class
        );
    }
}
