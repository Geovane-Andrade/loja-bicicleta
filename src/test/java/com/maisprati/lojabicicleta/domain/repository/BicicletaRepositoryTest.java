package com.maisprati.lojabicicleta.domain.repository;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.util.BicicletaCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


@DataJpaTest
@DisplayName("Testes para BicicletaRepository")
class BicicletaRepositoryTest {
    @Autowired
    private BicicletaRepository bicicletaRepository;

    @Test
    @DisplayName("Bicicleta persistida com sucesso.")
    void save_persistenciaBicicleta_Sucesso() {
        Bicicleta bicicletaParaSalvar = BicicletaCreator.criarBicicletaSemId();
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
    @Test
    @DisplayName("Bicicleta atualizada com sucesso.")
    void save_atualizacaoBicicleta_Sucesso() {
        Bicicleta bicicletaParaSalvar = BicicletaCreator.criarBicicletaSemId();
        Bicicleta bicicletaSalva = this.bicicletaRepository.save(bicicletaParaSalvar);

        bicicletaSalva.setModelo("Caloi");
        bicicletaSalva.setDescricao("Bicicleta azul caloi do ano de 2005");
        bicicletaSalva.setNomeComprador("Gisele gomes");
        bicicletaSalva.setDataCompra(LocalDate.of(2002,2,11));
        bicicletaSalva.setPrecoPago(600.00);
        bicicletaSalva.setNomeLoja("Montain Bikes");

        Bicicleta bicicletaAtualizada = this.bicicletaRepository.save(bicicletaSalva);

        Assertions.assertNotNull(bicicletaAtualizada);
        Assertions.assertNotNull(bicicletaAtualizada.getId());
        Assertions.assertEquals(bicicletaAtualizada.getModelo(),bicicletaSalva.getModelo());
        Assertions.assertEquals(bicicletaAtualizada.getDescricao(),bicicletaSalva.getDescricao());
        Assertions.assertEquals(bicicletaAtualizada.getNomeComprador(),bicicletaSalva.getNomeComprador());
        Assertions.assertEquals(bicicletaAtualizada.getDataCompra(),bicicletaSalva.getDataCompra());
        Assertions.assertEquals(bicicletaAtualizada.getPrecoPago(),bicicletaSalva.getPrecoPago());
        Assertions.assertEquals(bicicletaAtualizada.getNomeLoja(),bicicletaSalva.getNomeLoja());

    }

    @Test
    @DisplayName("Bicicleta removida com sucesso.")
    void delete_remocaoBicicleta_Sucesso() {
        Bicicleta bicicletaParaSalvar = BicicletaCreator.criarBicicletaSemId();
        Bicicleta bicicletaSalva = this.bicicletaRepository.save(bicicletaParaSalvar);

        this.bicicletaRepository.delete(bicicletaSalva);

        Optional<Bicicleta> bicicletaOptional = this.bicicletaRepository.findById(bicicletaSalva.getId());

        Assertions.assertTrue(bicicletaOptional.isEmpty());


    }
    @Test
    @DisplayName("Bicicleta existe e foi encontrada pelo id com sucesso.")
    void existencia_bicicletaPorId_Sucesso() {
        Bicicleta bicicletaParaSalvar = BicicletaCreator.criarBicicletaSemId();
        Bicicleta bicicletaSalva = this.bicicletaRepository.save(bicicletaParaSalvar);


        Optional<Bicicleta> bicicletaOptional = this.bicicletaRepository.findById(bicicletaSalva.getId());

        Assertions.assertFalse(bicicletaOptional.isEmpty());



    }
    @Test
    @DisplayName("Bicicleta não existe e a lista está vazia.")
    void existencia_retornoListaVazia_BicicletaNotFound() {

        Optional<Bicicleta> bicicletaOptional = this.bicicletaRepository.findById(UUID.fromString("a598c138-92ae-11ee-b9d1-0242ac120002"));

        Assertions.assertTrue(bicicletaOptional.isEmpty());


    }



}