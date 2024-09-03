package Conjuntos;
public class App {
    public static void main(String[] args) throws Exception {
        Conjuntos conjuntos = Conjuntos.generarConjuntos(9, 4);
        System.out.println(conjuntos.toString());

        System.out.println(conjuntos.buscar(4)); // O(1)

        conjuntos.fusionar(1, 3); // O(n)

        System.out.println(conjuntos.toString());
    }
}
