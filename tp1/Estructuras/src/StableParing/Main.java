package StableParing;

public class Main {
    public static void main(String[] args) {
        String[] h = { "Carlos", "Ricardo", "Pepe", "Enrique", "Rene" };
        String[] m = { "Carla", "Erica", "Penelope", "Paula", "Rocio" };

        //int[][] ph = { { 3, 2, 0, 1, 4 }, { 4, 0, 1, 2, -1 }, { 1, 4, 0, 3, 2 }, { 0, 2, 4, 3, 1 }, { 1, 2, 3, 4, -1 } };
        //int[][] pm = { { 1, 0, 2, 3, 4 }, { 2, 4, 3, 0, 1 }, { 4, 0, 2, 3, -1 }, { 1, 0, 3, 4, 2 }, { 0, 3, 2, 1, -1 } };
        //int[][] pp = { { 1, 2 }, { 4, 0 } };


        int[][] ph = { { 1, 3, 4, -1, -1 }, { 1, 0, 4, 2, -1 }, { 2, 3, 4, 0, 1 }, { 3, 2, 1, 0, -1 }, { 4, 3, 0, 1, 2 } };
        int[][] pm = { { 1, 3, 4, 2, -1 }, { 0, 2, 1, 4, 3 }, { 4, 3, 2, 1, -1 }, { 2, 3, 0, 4, -1 }, { 2, 0, 1, 4, -1 } };

        int[][] pp = { { 0, 2 }, { 3, 4 }, { 1, 3 }, { 0, 0 }};
        StableParing p = new StableParing(h, m, ph, pm, pp);
        p.matchear();
        System.out.println(p.toString());
    }
}
