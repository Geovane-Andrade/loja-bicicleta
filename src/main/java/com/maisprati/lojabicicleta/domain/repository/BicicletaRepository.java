package com.maisprati.lojabicicleta.domain.repository;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, UUID> {
    boolean existsById(UUID id);

}
