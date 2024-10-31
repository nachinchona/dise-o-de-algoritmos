package ProgDinamica;

import java.util.LinkedList;
import java.util.HashMap;

public class Monedas {
    private HashMap<Integer, LinkedList<Integer>> memoization = new HashMap<>();
    private int[][] C;

    public LinkedList<Integer> devolverCambio(int[] denominaciones, int m) {

        // ya se computó la solución alguna vez
        if (memoization.containsKey(m)) {
            return memoization.get(m);
        }

        // calculo
        int n = denominaciones.length;
        int INFINITO = Integer.MAX_VALUE / 2;
        C = new int[n][m+1];

        for (int i = 0; i < n; i++) {
            C[i][0] = 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 0) {
                    if (j < denominaciones[i]) {
                        C[i][j] = INFINITO;
                    } else {
                        C[i][j] = 1 + C[0][j - denominaciones[i]];
                    }
                } else {
                    if (i - 1 >= 0) {
                        if (j < denominaciones[i]) {
                            C[i][j] = C[i - 1][j];
                        } else {
                            C[i][j] = Math.min(C[i - 1][j], 1 + C[i][j - denominaciones[i]]);
                        }
                    }
                }
            }
        }
        LinkedList<Integer> S = new LinkedList<>();
        S = buscarSolucion(n-1, m, denominaciones);

        memoization.put(m, S);
        return S;
    }

    public LinkedList<Integer> buscarSolucion(int n, int m, int[] denominaciones) {
        LinkedList<Integer> S = new LinkedList<>();

        while (n >= 0 && m > 0) {
                if (m - denominaciones[n] >= 0 && C[n][m - denominaciones[n]] != -1 && C[n][m] == 1 + C[n][m - denominaciones[n]]) {        
                    m = m - denominaciones[n];
                    S.add(denominaciones[n]);
                } 
                else if (n - 1 >= 0 && C[n][m] == C[n - 1][m]) {
                    n--;
                } else {
                    // no hay solucion
                    S.clear();
                    break;
                }
                /*
                 * if (C[n][m] == C[n - 1][m] && C[n][m] == 1 + C[n][m - denominaciones[n]]) {
                 * mas de una solucion optima
                 * }
                 */
                if (m == 0) {
                    break;
                }
        }
        return S;
    }
}
