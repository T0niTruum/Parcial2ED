package co.edu.uniquindio.estructuras.ejercicios;

import co.edu.uniquindio.estructuras.estructuras.Pila;
import co.edu.uniquindio.estructuras.model.Caja;

public class Punto1cajas {

    public static Pila<Caja> crearPilaInicial() {
        Pila<Caja> pila = new Pila<>();
        pila.push(new Caja("C1", 2));
        pila.push(new Caja("C2", 4));
        pila.push(new Caja("C3", 6));
        pila.push(new Caja("C4", 8));
        pila.push(new Caja("C5", 3));
        pila.push(new Caja("C6", 9));
        pila.push(new Caja("C7", 5));
        return pila;
    }

    public static void ejecutar() {
        System.out.println("\n--- PUNTO 1: RETIRO DE CAJAS ---");
        Pila<Caja> pila = crearPilaInicial();
        System.out.println("Pila inicial: " + pila);
    }

    public static void main(String[] args) {
        ejecutar();
    }
}