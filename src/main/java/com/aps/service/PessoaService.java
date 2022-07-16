package com.aps.service;

import com.aps.domain.Pessoa;
import com.aps.repository.PessoaRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class PessoaService {

    public static final int MAX_NUMBER_OF_ENTITIES = 1000;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<Pessoa> gerarPessoas() {
        Faker faker = new Faker(new Locale("pt-BR"));

        List<Pessoa> pessoas = IntStream.range(0, MAX_NUMBER_OF_ENTITIES)
                .mapToObj(pessoa -> new Pessoa(faker.name().fullName()))
                .collect(Collectors.toList());

        return pessoaRepository.saveAll(pessoas);
    }

    @Transactional
    public void deletePessoaAndProduto() {
        produtoService.deleteProdutos();

        Long id = (long) new Random().nextInt(MAX_NUMBER_OF_ENTITIES);

        log.info("DELETANDO O REGISTRO {} DE PESSOA", id);

        pessoaRepository.deleteById(id);

        int numero = 10 / 0; // Forçando uma RuntimeException
    }
}
