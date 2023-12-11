package com.maisprati.lojabicicleta.util;

import com.maisprati.lojabicicleta.domain.model.Bicicleta;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BicicletaPostRequestBodyCreator {
    public static BicicletaPostRequestBody criarBicicletaPostRequestBody() {
        return BicicletaPostRequestBody.builder()
                .modelo(BicicletaCreator.criarBicicletaSemId().getModelo())
                .descricao(BicicletaCreator.criarBicicletaSemId().getDescricao())
                .nomeComprador(BicicletaCreator.criarBicicletaSemId().getNomeComprador())
                .dataCompra(BicicletaCreator.criarBicicletaSemId().getDataCompra())
                .precoPago(BicicletaCreator.criarBicicletaSemId().getPrecoPago())
                .nomeLoja(BicicletaCreator.criarBicicletaSemId().getNomeLoja())
                .build();
    }
}
