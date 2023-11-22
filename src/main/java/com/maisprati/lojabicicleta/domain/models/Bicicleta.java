package com.maisprati.lojabicicleta.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_bikes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String modelo;
    private String descricao;
    private Double precoPago;
    private LocalDate dataCompra;
    private String nomeComprador;
    private String nomeLoja;


}
