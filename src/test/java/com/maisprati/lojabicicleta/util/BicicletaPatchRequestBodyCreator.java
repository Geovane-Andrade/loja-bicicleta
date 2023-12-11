package com.maisprati.lojabicicleta.util;

import com.maisprati.lojabicicleta.requests.BicicletaPatchRequestBody;
import com.maisprati.lojabicicleta.requests.BicicletaPostRequestBody;

public class BicicletaPatchRequestBodyCreator {
    public static BicicletaPatchRequestBody criarBicicletaPatchRequestBody() {
        return BicicletaPatchRequestBody.builder()
                .id(BicicletaCreator.criarBicicletaComUnicasMudancas().getId())
                .modelo(BicicletaCreator.criarBicicletaComUnicasMudancas().getModelo())
                .descricao(BicicletaCreator.criarBicicletaComUnicasMudancas().getDescricao())
                .nomeComprador(BicicletaCreator.criarBicicletaComUnicasMudancas().getNomeComprador())
                .dataCompra(BicicletaCreator.criarBicicletaComUnicasMudancas().getDataCompra())
                .precoPago(BicicletaCreator.criarBicicletaComUnicasMudancas().getPrecoPago())
                .nomeLoja(BicicletaCreator.criarBicicletaComUnicasMudancas().getNomeLoja())
                .build();
    }
}
