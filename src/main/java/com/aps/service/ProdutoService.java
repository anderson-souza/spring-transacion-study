package com.aps.service;

import com.aps.domain.Produto;
import com.aps.repository.ProdutoRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ProdutoService {

    public static final int MAX_NUMBER_OF_ENTITIES = 1000;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> gerarProdutos() {
        Faker faker = new Faker(new Locale("pt-BR"));

        List<Produto> produtos = IntStream.range(0, 1000)
                .mapToObj(i -> new Produto(faker.book().title()))
                .collect(Collectors.toList());

        return produtoRepository.saveAll(produtos);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteProdutos() {
        log.debug("DELETANDO O REGISTRO");

        Long id = (long) new Random().nextInt(MAX_NUMBER_OF_ENTITIES);

        log.info("DELETANDO O REGISTRO {} DE PRODUTO", id);

        produtoRepository.deleteById(id);
    }

}
