package StableParing;

public class StableParing {
    private static int N;
    private int casados;
    private int[][] prefHombre;
    private int[][] prefMujer;
    private int[][] parejasProhibidas;
    private String[] hombres;
    private String[] mujeres;
    private int[] parejaDeMujer;
    private boolean[] hombreTienePareja;

    public StableParing(String[] h, String[] m, int[][] ph, int[][] pm, int[][] pp) {
        N = h.length;
        hombres = h;
        mujeres = m;
        prefHombre = ph;
        prefMujer = pm;
        parejasProhibidas = pp;
        casados = 0;
        parejaDeMujer = new int[N];
        hombreTienePareja = new boolean[N];
        for (int i = 0; i < N; i++) {
            hombreTienePareja[i] = false;
            parejaDeMujer[i] = -1;
        }
    }

    public void matchear() {
        while (casados < N) {
            int h;

            for (h = 0; h < N; h++) {
                if (!hombreTienePareja[h]) {
                    break;
                }
            }

            int m = 0;
            while (m < N && !hombreTienePareja[h]) {
                System.out.println(esParejaProhibida(h, prefHombre[h][m]) + " h " + h + " m " + prefHombre[h][m]);
                if (prefHombre[h][m] != -1 && !esParejaProhibida(h, prefHombre[h][m])) {
                    if (parejaDeMujer[prefHombre[h][m]] == -1) {
                        parejaDeMujer[prefHombre[h][m]] = h;
                        hombreTienePareja[h] = true;
                        casados++;
                    } else {
                        int hombreEnMPref = buscarPreferenciaM(h, m);
                        int parejaActual = parejaDeMujer[m];
                        if (hombreEnMPref > buscarPreferenciaM(parejaActual, m)) {
                            hombreTienePareja[parejaActual] = false;
                            parejaDeMujer[m] = h;
                            hombreTienePareja[h] = true;
                        }
                    }
                }
                m++;
            }
        }
    }

    public boolean esParejaProhibida(int h, int m) {
        boolean r = false;
        for (int i = 0; i < parejasProhibidas.length; i++) {
            System.out.println("h " + parejasProhibidas[i][0] + " m " + parejasProhibidas[i][1]);
            if (parejasProhibidas[i][0] == h & parejasProhibidas[i][1] == m) {
                r = true;
            }
        }
        return r;
    }

    private int buscarPreferenciaM(int h, int m) {
        int r = -1;
        for (int i = 0; i < N; i++) {
            if (prefMujer[m][i] == h) {
                r = i;
                break;
            }
        }
        return r;
    }

    public String toString() {
        String r = "";
        for (int i = 0; i < N; i++) {
            if (hombreTienePareja[i]) {
                r = r + "Pareja " + i + ": " + hombres[parejaDeMujer[i]] + ", " + mujeres[i] + "\n";
            }
        }
        return r;
    }
}
