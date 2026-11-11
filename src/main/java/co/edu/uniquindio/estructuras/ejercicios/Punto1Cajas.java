package co.edu.uniquindio.estructuras.ejercicios;

import co.edu.uniquindio.estructuras.estructuras.Pila;
import co.edu.uniquindio.estructuras.model.Caja;

public class Punto1Cajas {

    // Crea la pila de la prueba: C1 entra primero, C7 entra de último (queda en el tope)
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

    // Retira la caja con el código dado y reorganiza las demás según su valor
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        // Si la pila está vacía no hay nada que retirar
        if (pila.estaVacia()) {
            return false;
        }

        // PARTE 1: buscar y retirar la caja
        Pila<Caja> aux = new Pila<>();
        Caja retirada = null;

        while (!pila.estaVacia()) {
            Caja c = pila.pop();
            if (retirada == null && c.getCodigo().equals(codigo)) {
                retirada = c;      // se guarda y no se devuelve a la pila
            } else {
                aux.push(c);
            }
        }

        // Devolver las demás cajas a la pila (quedan en su orden original)
        while (!aux.estaVacia()) {
            pila.push(aux.pop());
        }

        // Si no se encontró, la pila ya quedó igual que al inicio
        if (retirada == null) {
            return false;
        }

        // PARTE 2: separar las cajas por posición (la cima es la posición 1)
        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();
        int posicion = 1;

        while (!pila.estaVacia()) {
            Caja c = pila.pop();
            if (posicion % 2 == 1) {
                impares.push(c);
            } else {
                pares.push(c);
            }
            posicion++;
        }

        // PARTE 3: reconstruir la pila
        // El grupo que va arriba se apila de último.
        Pila<Caja> arriba;
        Pila<Caja> abajo;

        if (retirada.getValor() % 2 == 0) {
            // Valor par: arriba las posiciones impares, luego las pares
            arriba = impares;
            abajo = pares;
        } else {
            // Valor impar: arriba las posiciones pares, luego las impares
            arriba = pares;
            abajo = impares;
        }

        while (!abajo.estaVacia()) {
            pila.push(abajo.pop());
        }
        while (!arriba.estaVacia()) {
            pila.push(arriba.pop());
        }

        return true;
    }

    public static void ejecutar() {
        System.out.println("\n--- PUNTO 1: RETIRO DE CAJAS ---");

        Pila<Caja> pila = crearPilaInicial();
        System.out.println("Pila inicial: " + pila);

        boolean r1 = retirarCaja(pila, "C3");
        System.out.println("Retirar C3 -> " + r1);
        System.out.println(pila);

        boolean r2 = retirarCaja(pila, "C4");
        System.out.println("Retirar C4 -> " + r2);
        System.out.println(pila);

        boolean r3 = retirarCaja(pila, "C7");
        System.out.println("Retirar C7 -> " + r3);
        System.out.println(pila);

        System.out.println("Pila final: " + pila);
    }

    // Para probar este punto solo, sin tocar Main
    public static void main(String[] args) {
        ejecutar();
    }
}