public class App {
    public static void main(String[] args) throws Exception {
        Conjuntos conjuntos = Conjuntos.generarConjuntos(9, 4);
        System.out.println(conjuntos.toString());

        System.out.println(conjuntos.buscar(4));

        conjuntos.fusionar(1, 3);

        System.out.println(conjuntos.toString());
    }
}
