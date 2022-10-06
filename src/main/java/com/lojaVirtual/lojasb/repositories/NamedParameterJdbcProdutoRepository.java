package com.lojaVirtual.lojasb.repositories;

import com.lojaVirtual.lojasb.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJdbcProdutoRepository extends JdbcProdutoRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int updateDescricao(Produto produto) {
        return namedParameterJdbcTemplate.update(
                "update produto set Descricao = :descricao where Id = :id",
                new BeanPropertySqlParameterSource(produto));
    }
    @Override
    public Optional<Produto> findById(int id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from produto where Id = :id",
                new MapSqlParameterSource("Id", id),
                (rs, rowNum) ->
                        Optional.of(new Produto(
                                rs.getInt("Id"),
                                rs.getString("Nome"),
                                rs.getString("Descricao")
                        ))
        );
    }
    @Override
    public List<Produto> findByName(String nome) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("Nome", "%" + nome + "%");

        return namedParameterJdbcTemplate.query(
                "select * from produto where Nome like :nome",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new Produto(
                                rs.getInt("Id"),
                                rs.getString("Nome"),
                                rs.getString("Descricao")
                        )
        );
    }
}
