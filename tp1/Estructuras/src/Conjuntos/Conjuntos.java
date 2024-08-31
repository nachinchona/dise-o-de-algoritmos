import java.util.Random;

public class Conjuntos {
    private int[] set;
    private int cantConjuntos;

    public static Conjuntos generarConjuntos(int limite, int cantConjuntos) {
        Random r = new Random();
        int[] set = new int[limite];

        for (int i = 0; i < limite; i++) {
            set[i] = r.nextInt(1,cantConjuntos+1);
        }

        Conjuntos conjunto = new Conjuntos(set, cantConjuntos);

        return conjunto;
    }
 
    public Conjuntos(int[] set, int cantConjuntos){
        this.set = set;
        this.cantConjuntos = cantConjuntos;
    }

    public int buscar(int numero){
        return set[numero];
    }

    public void fusionar(int a, int b) {
        if (a >= cantConjuntos || b >= cantConjuntos) {
            return;
        }

        int i = Math.min(a, b);
        int j = Math.max(a, b);

        for (int j2 = 0; j2 < set.length; j2++) {
            if (set[j2] == j) {
                set[j2] = i;
            }
        }
    }

    public String toString() {
        String r = "Elemento ";
        for (int i = 0; i < set.length; i++) {
            if (i < set.length-1) {
                r = r + Integer.toString(i) + " - ";
            } else {
                r = r + Integer.toString(i);
            }
        }
        r = r + "\nConjunto ";
        for (int i = 0; i < set.length; i++) {
            if (i < set.length-1) {
                r = r + Integer.toString(set[i]) + " - ";
            } else {
                r = r + Integer.toString(set[i]);
            }
        }
        return r;
    }
}
