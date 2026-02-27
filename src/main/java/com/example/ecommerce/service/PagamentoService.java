package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.PagamentoRequestDTO;
import com.example.ecommerce.dto.response.PagamentoResponseDTO;
import com.example.ecommerce.entity.Pagamento;
import com.example.ecommerce.entity.Pedido;
import com.example.ecommerce.repository.PagamentoRepository;
import com.example.ecommerce.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public PagamentoResponseDTO criarPagamento(UUID pedidoId, PagamentoRequestDTO request) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));

        if (pedido.getPagamento() != null) {
            throw new IllegalStateException("Este pedido já possui pagamento.");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);

        pagamento.setId(pedido.getId()); // 🔥 ESSA LINHA RESOLVE
        // manter consistência dos dois lados do relacionamento
        pedido.setPagamento(pagamento);

        System.out.println("ID do pagamento após o flush: " + pagamento.getId());
        pedidoRepository.save(pedido);
        return toResponseDTO(pagamento);
    }

    @Transactional(readOnly = true)
    public PagamentoResponseDTO buscarPagamentoDoPedido(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));

        Pagamento pagamento = pedido.getPagamento();
        if (pagamento == null) {
            throw new IllegalArgumentException("Este pedido não possui pagamento.");
        }

        return toResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO atualizarPagamentoDoPedido(UUID pedidoId, PagamentoRequestDTO request) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));

        Pagamento pagamento = pedido.getPagamento();
        if (pagamento == null) {
            throw new IllegalArgumentException("Este pedido não possui pagamento para atualizar.");
        }

        pagamento.setMetodo(request.metodo());
        pagamento.setValor(request.valor());
        pagamento.setStatus(request.status());
        pagamento.setDataPagamento(request.dataPagamento());
        pagamento.setNumeroTransacao(request.numeroTransacao());

        Pagamento salvo = pagamentoRepository.save(pagamento);
        return toResponseDTO(salvo);
    }

    @Transactional
    public void deletarPagamentoDoPedido(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + pedidoId));

        Pagamento pagamento = pedido.getPagamento();
        if (pagamento == null) {
            throw new IllegalArgumentException("Este pedido não possui pagamento para deletar.");
        }

        // quebra o vínculo do lado do Pedido
        pedido.setPagamento(null);

        pagamentoRepository.delete(pagamento);
    }

    private PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {
        UUID pedidoId = (pagamento.getPedido() != null) ? pagamento.getPedido().getId() : null;
        return new PagamentoResponseDTO(pagamento.getId(), pedidoId);
    }
}