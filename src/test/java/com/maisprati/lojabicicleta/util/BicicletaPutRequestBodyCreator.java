package com.maisprati.lojabicicleta.util;

import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPutRequestBody;

public class BicicletaPutRequestBodyCreator {
    public static BicicletaPutRequestBody criarBicicletaPutRequestBody() {
        return BicicletaPutRequestBody.builder()
                .id(BicicletaCreator.criarBicicletaComAlteracoes().getId())
                .modelo(BicicletaCreator.criarBicicletaComAlteracoes().getModelo())
                .descricao(BicicletaCreator.criarBicicletaComAlteracoes().getDescricao())
                .nomeComprador(BicicletaCreator.criarBicicletaComAlteracoes().getNomeComprador())
                .dataCompra(BicicletaCreator.criarBicicletaComAlteracoes().getDataCompra())
                .precoPago(BicicletaCreator.criarBicicletaComAlteracoes().getPrecoPago())
                .nomeLoja(BicicletaCreator.criarBicicletaComAlteracoes().getNomeLoja())
                .build();
    }
}
