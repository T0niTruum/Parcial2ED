package co.edu.uniquindio.estructuras.model;

public class Paquete {
    private String codigo;
    private String tipo;   // "URGENTE" o "NORMAL"
    private int peso;

    public Paquete(String codigo, String tipo, int peso) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return codigo + " (" + tipo + ", " + peso + " kg)";
    }
}