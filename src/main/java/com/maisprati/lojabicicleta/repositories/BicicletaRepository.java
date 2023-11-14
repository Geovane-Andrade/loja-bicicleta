package com.maisprati.lojabicicleta.repositories;

import com.maisprati.lojabicicleta.models.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta,UUID> {
    boolean existsBicicleta(Bicicleta bicicleta);
}
