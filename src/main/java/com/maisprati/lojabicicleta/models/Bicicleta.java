package com.maisprati.lojabicicleta.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_bikes")
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

    public Bicicleta() {
    }

    public Bicicleta(UUID id, String modelo, String descricao, Double precoPago, LocalDate dataCompra, String nomeComprador, String nomeLoja) {
        this.id = id;
        this.modelo = modelo;
        this.descricao = descricao;
        this.precoPago = precoPago;
        this.dataCompra = dataCompra;
        this.nomeComprador = nomeComprador;
        this.nomeLoja = nomeLoja;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(Double precoPago) {
        this.precoPago = precoPago;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getNomeLoja(){
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicicleta bicicleta = (Bicicleta) o;
        return Objects.equals(id, bicicleta.id) && Objects.equals(modelo, bicicleta.modelo) && Objects.equals(descricao, bicicleta.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, descricao);
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + precoPago +
                ", dataDeCompra=" + dataCompra +
                ", nomeDoComprador='" + nomeComprador + '\'' +
                '}';
    }
}
