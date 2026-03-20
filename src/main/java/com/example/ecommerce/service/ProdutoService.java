package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.ProdutoRequestDTO;
import com.example.ecommerce.dto.response.ProdutoResponseDTO;
import com.example.ecommerce.entity.Produto;
import com.example.ecommerce.repository.Produtorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private Produtorepository produtoRepository;

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    public ProdutoResponseDTO listarPorId(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto);
    }

    public void apagarProduto(UUID id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        try {
            produtoRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Produto está em uso e não pode ser removido", ex);
        }
    }
}
