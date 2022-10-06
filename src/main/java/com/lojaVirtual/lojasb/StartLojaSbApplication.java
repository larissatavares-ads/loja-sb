package com.lojaVirtual.lojasb;

import com.lojaVirtual.lojasb.models.Produto;
import com.lojaVirtual.lojasb.repositories.ProdutoRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class StartLojaSbApplication implements CommandLineRunner {

	private static final Logger log = (Logger) LoggerFactory.getLogger(StartLojaSbApplication.class);

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	//@Qualifier("jdbcProdutoRepository")              // Test JdbcTemplate
	@Qualifier("namedParameterJdbcProdutoRepository")  // Test NamedParameterJdbcTemplate
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(StartLojaSbApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("StartApplication...");
		runJDBC();
	}
	void runJDBC() {

		log.info("Creating tables for testing...");

		jdbcTemplate.execute("DROP TABLE produto IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE produto(" +
				"Id IDENTITY(1,1) PRIMARY KEY, Nome VARCHAR(255), Descricao VARCHAR(500))");

		List<Produto> produtos = Arrays.asList(
				new Produto("1", new String("TESTE")),
				new Produto("Mkyong in Java", new BigDecimal("1.99")),
				new Produto("Getting Clojure", new BigDecimal("37.3")),
				new Produto("Head First Android Development", new BigDecimal("41.19"))
		);
}
