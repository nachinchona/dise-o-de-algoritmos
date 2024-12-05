package StablePairing;

public class App {
    public static void main(String[] args) {
        String[] h = { "Carlos", "Ricardo", "Pepe", "Eric", "Rene" };
        String[] m = { "Carla", "Erica", "Penelope", "Paula", "Rocio" };
        int[][] ph = { { 3, 2, 0, 1, 4 }, { 4, 3, 0, 1, 2 }, { 1, 4, 0, 3, 2 }, { 0, 2, 4, 3, 1 }, { 1, 2, 0, 3, 4 } };
        int[][] pm = { { 1, 0, 2, 3, 4 }, { 2, 4, 3, 0, 1 }, { 4, 0, 2, 3, 1 }, { 1, 0, 3, 4, 2 }, { 0, 4, 3, 2, 1 } };
        int[][] pp = { { 1, 2 }, { 4, 0 } };
        StablePairing p = new StablePairing(h, m, ph, pm, pp);
        p.matchear();
        System.out.println(p.toString());
        System.out.println(p.esParejaProhibida(4, 0));
    }
}


