package ProgDinamica;

public class Main {
    public static void main(String[] args) {
        Monedas m = new Monedas();
        int[] denominaciones = { 3, 7, 9, 19 };
        for (int i = 0; i < 200; i++) {
            System.out.println("Billete: " + i + ", Monedas: " + m.devolverCambio(denominaciones, i));
        }
    }
}
