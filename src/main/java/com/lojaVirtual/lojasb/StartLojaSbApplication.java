package com.lojaVirtual.lojasb;

import com.lojaVirtual.lojasb.repositories.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class StartLojaSbApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(StartLojaSbApplication.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	//@Qualifier("jdbcProdutoRepository")              // Test JdbcTemplate
	@Qualifier("namedParameterJdbcProdutoRepository")  // Test NamedParameterJdbcTemplate

	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(StartLojaSbApplication.class, args);
		ctx.close();
	}

	@Override
	public void run(String... args) throws Exception{
		log.info("#######StartApplication...");
		runJDBC();
	}
	void runJDBC() {

		log.info("############Creating tables for testing...");

		jdbcTemplate.execute("DROP TABLE produto;");
		jdbcTemplate.execute("CREATE TABLE produto(" +
				"Id int IDENTITY(1,1) PRIMARY KEY, Nome VARCHAR(255), Descricao VARCHAR(500))");

		List<Produto> produtos = Arrays.asList(
				new Produto(1, "Notebook","Acer Nitro"),
				new Produto(1, "Celular", "Samsung"),
				new Produto(1, "JBL", "GO2"),
				new Produto(1, "Notebook", "Lenovo Ideapad")
		);

		log.info("####[SAVE]");

		produtos.forEach(produto -> {
			log.info("####Saving...{}", produto.getNome());
			produtoRepository.save(produto);
		});

      // count
		log.info("###[COUNT] Total produtos: {}", produtoRepository.count());
//
//		// find all
//		log.info("####[FIND_ALL] {}", produtoRepository.findAll());
//
//		// find by name
//		log.info("[FIND_BY_NAME] : like '%Notebook%'");
//		log.info("{}", produtoRepository.findByName("Notebook"));
//
//		// find by id
//		log.info("[FIND_BY_ID] :1");
//		Produto produto = produtoRepository.findById(1).orElseThrow(IllegalArgumentException::new);
//		log.info("{}", produto);
//
//		// get name (string) by id
//		log.info("[GET_NAME_BY_ID] :1 = {}", produtoRepository.getNameById(2));
//
//		// update
//		log.info("[UPDATE] :TESTANDO UPDATE :1");
//		produto.setDescricao("TESTANDO UPDATE");
//		log.info("rows affected: {}", produtoRepository.updateDescricao(produto));
//
//		// delete
//		log.info("[DELETE] :1");
//		log.info("rows affected: {}", produtoRepository.deleteById(1));
	}








}
