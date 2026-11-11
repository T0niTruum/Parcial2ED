package co.edu.uniquindio.estructuras.model;

public class Caja {
    private String codigo;
    private int valor;

    public Caja(String codigo, int valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return codigo + ":" + valor;
    }
}