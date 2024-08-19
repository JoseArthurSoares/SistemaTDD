package model;

import java.time.LocalDate;

public class Conta {
    private String id;
    private LocalDate data;
    private double valor;
    private String tipo;

    public Conta(String id, LocalDate data, double valor, String tipo) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
