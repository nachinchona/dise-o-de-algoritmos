package Conjuntos;
import java.util.Random;

public class Conjuntos {
    private int[] conjunto;
    private int cantConjuntos;

    public static Conjuntos generarConjuntos(int limite, int cantConjuntos) {
        Random r = new Random();
        int[] conjunto = new int[limite];

        for (int i = 0; i < limite; i++) {
            conjunto[i] = r.nextInt(1,cantConjuntos+1);
        }

        Conjuntos conjuntosDijuntos = new Conjuntos(conjunto, cantConjuntos);

        return conjuntosDijuntos;
    }
 
    public Conjuntos(int[] conjunto, int cantConjuntos){
        this.conjunto = conjunto;
        this.cantConjuntos = cantConjuntos;
    }

    public int buscar(int numero){
        return conjunto[numero];
    }

    public void fusionar(int a, int b) {
        if (a >= cantConjuntos || b >= cantConjuntos) {
            return;
        }

        int i = Math.min(a, b);
        int j = Math.max(a, b);

        for (int k = 0; k < conjunto.length; k++) {
            if (conjunto[k] == j) {
                conjunto[k] = i;
            }
        }
    }

    public String toString() {
        String r = "Elemento ";
        for (int i = 0; i < conjunto.length; i++) {
            if (i < conjunto.length-1) {
                r = r + Integer.toString(i) + " - ";
            } else {
                r = r + Integer.toString(i);
            }
        }
        r = r + "\nConjunto ";
        for (int i = 0; i < conjunto.length; i++) {
            if (i < conjunto.length-1) {
                r = r + Integer.toString(conjunto[i]) + " - ";
            } else {
                r = r + Integer.toString(conjunto[i]);
            }
        }
        return r;
    }
}
