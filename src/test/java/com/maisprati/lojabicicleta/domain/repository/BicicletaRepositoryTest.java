package com.maisprati.lojabicicleta.domain.repository;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;


@DataJpaTest
@DisplayName("Testes para BicicletaRepository")
class BicicletaRepositoryTest {
    @Autowired
    private BicicletaRepository bicicletaRepository;

    @Test
    @DisplayName("Bicicleta persistida com sucesso.")
    void save_persistenciaBicicleta_Sucesso() {
        Bicicleta bicicletaParaSalvar = criarBicicleta();
        Bicicleta bicicletaSalva = this.bicicletaRepository.save(bicicletaParaSalvar);
        Assertions.assertNotNull(bicicletaSalva);
        Assertions.assertNotNull(bicicletaSalva.getId());
        Assertions.assertEquals(bicicletaSalva.getModelo(),bicicletaParaSalvar.getModelo());
        Assertions.assertEquals(bicicletaSalva.getDescricao(),bicicletaParaSalvar.getDescricao());
        Assertions.assertEquals(bicicletaSalva.getNomeComprador(),bicicletaParaSalvar.getNomeComprador());
        Assertions.assertEquals(bicicletaSalva.getDataCompra(),bicicletaParaSalvar.getDataCompra());
        Assertions.assertEquals(bicicletaSalva.getPrecoPago(),bicicletaParaSalvar.getPrecoPago());
        Assertions.assertEquals(bicicletaSalva.getNomeLoja(),bicicletaParaSalvar.getNomeLoja());

    }

    private Bicicleta criarBicicleta() {

        return Bicicleta.builder()
                .modelo("Monark 99")
                .descricao("Bicicleta vermelha monark do ano de 1999")
                .nomeComprador("João da silva")
                .dataCompra(LocalDate.from(LocalDateTime.now()))
                .precoPago(500.00)
                .nomeLoja("NewBk")
                .build();
    }

}