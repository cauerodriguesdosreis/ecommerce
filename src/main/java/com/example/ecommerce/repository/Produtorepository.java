package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface Produtorepository extends JpaRepository<Produto, UUID> {
}
