package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.UsuarioRequestDTO;
import com.example.ecommerce.dto.response.UsuarioResponseDTO;
import com.example.ecommerce.entity.Usuario;
import com.example.ecommerce.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO request) {
        Usuario usuario = new Usuario(request.getNome(), request.getEmail(), request.getNumero_telefone(), request.getSenha());

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));
        return toResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Transactional
    public UsuarioResponseDTO atualizar(UUID id, UsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));

        if (request.getNome() != null) {
            usuario.setNome(request.getNome());
        }

        if (request.getEmail() != null) {
            usuario.setEmail(request.getEmail());
        }

        if (request.getNumero_telefone() != null) {
            usuario.setNumero_telefone(request.getNumero_telefone());
        }

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponseDTO(salvo);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getNumero_telefone());
}
}
