package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.PedidoRequestDTO;
import com.example.ecommerce.dto.response.PedidoResponseDTO;
import com.example.ecommerce.entity.Pagamento;
import com.example.ecommerce.entity.Pedido;
import com.example.ecommerce.entity.Usuario;
import com.example.ecommerce.enums.StatusDoPedido;
import com.example.ecommerce.repository.PedidoRepository;
import com.example.ecommerce.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO request) {
        Usuario cliente = usuarioRepository.findById(request.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + request.getClienteId()));

        Pedido pedido = new Pedido();
        pedido.setTimestamp(LocalDate.now());
        pedido.setStatus(request.getStatus() != null ? request.getStatus() : StatusDoPedido.AGUARDANDO_PAGAMENTO);
        pedido.setCliente(cliente);

        Pedido salvo = pedidoRepository.save(pedido);
        return toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPorId(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));
        return toResponseDTO(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listar() {
        return pedidoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    // ... existing code ...
    @Transactional
    public PedidoResponseDTO atualizar(UUID id, PedidoRequestDTO request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado: " + id));

        if (request.getClienteId() != null
                && (pedido.getCliente() == null || !request.getClienteId().equals(pedido.getCliente().getId()))) {

            Usuario novoCliente = usuarioRepository.findById(request.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + request.getClienteId()));
            pedido.setCliente(novoCliente);
        }

        if (request.getStatus() != null) {
            pedido.setStatus(request.getStatus());
        }

        Pedido salvo = pedidoRepository.save(pedido);
        return toResponseDTO(salvo);
    }
// ... existing code ...

    @Transactional
    public void deletar(UUID id) {
        if (!pedidoRepository.existsById(id)) {
            throw new IllegalArgumentException("Pedido não encontrado: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    private PedidoResponseDTO toResponseDTO(Pedido pedido) {
        UUID clienteId = (pedido.getCliente() != null) ? pedido.getCliente().getId() : null;
        Pagamento pagamento = pedido.getPagamento();
        UUID pagamentoId = (pagamento != null) ? pagamento.getId() : null;

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getTimestamp(),
                pedido.getStatus(),
                clienteId,
                pagamentoId
        );
    }
}