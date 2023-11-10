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
    private Double preco;
    private LocalDate dataDeCompra;
    private String nomeDoComprador;

    public Bicicleta() {
    }

    public Bicicleta(UUID id, String modelo, String descricao, Double preco, LocalDate dataDeCompra, String nomeDoComprador) {
        this.id = id;
        this.modelo = modelo;
        this.descricao = descricao;
        this.preco = preco;
        this.dataDeCompra = dataDeCompra;
        this.nomeDoComprador = nomeDoComprador;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataDeCompra() {
        return dataDeCompra;
    }

    public void setDataDeCompra(LocalDate dataDeCompra) {
        this.dataDeCompra = dataDeCompra;
    }

    public String getNomeDoComprador() {
        return nomeDoComprador;
    }

    public void setNomeDoComprador(String nomeDoComprador) {
        this.nomeDoComprador = nomeDoComprador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicicleta bicicleta = (Bicicleta) o;
        return Objects.equals(id, bicicleta.id) && Objects.equals(modelo, bicicleta.modelo) && Objects.equals(preco, bicicleta.preco) && Objects.equals(nomeDoComprador, bicicleta.nomeDoComprador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, preco, nomeDoComprador);
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", dataDeCompra=" + dataDeCompra +
                ", nomeDoComprador='" + nomeDoComprador + '\'' +
                '}';
    }
}
