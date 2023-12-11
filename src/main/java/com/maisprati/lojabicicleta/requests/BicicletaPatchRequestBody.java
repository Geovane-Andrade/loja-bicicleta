package com.maisprati.lojabicicleta.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class BicicletaPatchRequestBody {
    private UUID id;
    private String modelo;
    private String descricao;
    private Double precoPago;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;
    private String nomeComprador;
    private String nomeLoja;

}
