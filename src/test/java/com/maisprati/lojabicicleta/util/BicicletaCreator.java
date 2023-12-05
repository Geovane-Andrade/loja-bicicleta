package com.maisprati.lojabicicleta.util;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class BicicletaCreator {
    public static Bicicleta criarBicicletaSemId() {
        return Bicicleta.builder()
                .modelo("Monark 99")
                .descricao("Bicicleta vermelha monark do ano de 1999")
                .nomeComprador("João da silva")
                .dataCompra(LocalDate.from(LocalDateTime.now()))
                .precoPago(500.00)
                .nomeLoja("NewBk")
                .build();
    }
    public static Bicicleta criarBicicletaComId() {
        return Bicicleta.builder()
                .id(UUID.fromString("573a8a5d-843d-4892-b7f1-dabacd9963ae"))
                .modelo("Monark 99")
                .descricao("Bicicleta vermelha monark do ano de 1999")
                .nomeComprador("João da silva")
                .dataCompra(LocalDate.from(LocalDateTime.now()))
                .precoPago(500.00)
                .nomeLoja("NewBk")
                .build();
    }
    public static  Bicicleta criarBicicletaComAlteracoes() {
        return Bicicleta.builder()
                .id(UUID.fromString("573a8a5d-843d-4892-b7f1-dabacd9963ae"))
                .modelo("Caloi Velox MY23 ")
                .descricao("Bicicleta azul do ano de 2023")
                .nomeComprador("João da silva")
                .dataCompra(LocalDate.from(LocalDateTime.now()))
                .precoPago(500.00)
                .nomeLoja("NewBk")
                .build();
    }
}
