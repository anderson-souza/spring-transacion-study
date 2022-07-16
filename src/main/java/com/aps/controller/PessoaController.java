package com.aps.controller;

import com.aps.domain.Pessoa;
import com.aps.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Pessoa> generatePessoas() {
        return pessoaService.gerarPessoas();
    }

    @DeleteMapping("/delete-pessoa-produto")
    public void deletePessoaAndProduto() {
        pessoaService.deletePessoaAndProduto();
    }

}
