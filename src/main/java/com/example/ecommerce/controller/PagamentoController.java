package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.PagamentoRequestDTO;
import com.example.ecommerce.dto.response.PagamentoResponseDTO;
import com.example.ecommerce.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pedido/{pedidoId}/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping ("/criar")
    public ResponseEntity<PagamentoResponseDTO> criar(@PathVariable UUID pedidoId, @RequestBody PagamentoRequestDTO request) {
        return ResponseEntity.ok(pagamentoService.criarPagamento(pedidoId, request));
    }

    @GetMapping("/listar")
    public ResponseEntity<PagamentoResponseDTO> buscar(@PathVariable UUID pedidoId) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoDoPedido(pedidoId));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<PagamentoResponseDTO> atualizar(@PathVariable UUID pedidoId, @RequestBody PagamentoRequestDTO request) {
        return ResponseEntity.ok(pagamentoService.atualizarPagamentoDoPedido(pedidoId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID pedidoId) {
        pagamentoService.deletarPagamentoDoPedido(pedidoId);
        return ResponseEntity.noContent().build();
    }
}