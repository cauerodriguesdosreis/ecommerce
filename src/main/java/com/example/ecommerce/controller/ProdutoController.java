package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ProdutoRequestDTO;
import com.example.ecommerce.dto.response.ProdutoResponseDTO;
import com.example.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping ("/listar")
    public List<ProdutoResponseDTO> findAll() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO findById(@PathVariable UUID id) {
        return produtoService.listarPorId(id);
    }

    @PostMapping ("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDTO create(@Valid @RequestBody ProdutoRequestDTO dto) {
        return produtoService.criarProduto(dto);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO update(@PathVariable UUID id, @Valid @RequestBody ProdutoRequestDTO dto) {
        return produtoService.atualizarProduto(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        produtoService.apagarProduto(id);
    }
}
