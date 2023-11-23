package com.maisprati.lojabicicleta.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BicicletaPostRequestBody {
    private String modelo;
    private String descricao;
    private Double precoPago;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;
    private String nomeComprador;
    private String nomeLoja;

}
