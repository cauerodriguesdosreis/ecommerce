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

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> criar(@PathVariable UUID pedidoId,
                                                      @RequestBody(required = false) PagamentoRequestDTO request) {
        PagamentoRequestDTO safeRequest = (request != null) ? request : new PagamentoRequestDTO();
        PagamentoResponseDTO response = pagamentoService.criarPagamento(pedidoId, safeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PagamentoResponseDTO> buscar(@PathVariable UUID pedidoId) {
        return ResponseEntity.ok(pagamentoService.buscarPagamentoDoPedido(pedidoId));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<PagamentoResponseDTO> atualizar(@PathVariable UUID pedidoId,
                                                          @RequestBody(required = false) PagamentoRequestDTO request) {
        PagamentoRequestDTO safeRequest = (request != null) ? request : new PagamentoRequestDTO();
        return ResponseEntity.ok(pagamentoService.atualizarPagamentoDoPedido(pedidoId, safeRequest));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable UUID pedidoId) {
        pagamentoService.deletarPagamentoDoPedido(pedidoId);
        return ResponseEntity.noContent().build();
    }
}