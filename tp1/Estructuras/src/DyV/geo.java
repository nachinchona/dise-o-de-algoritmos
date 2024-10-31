package DyV;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;
public class geo {

    public static double calcularDistancia(Point P, Point Q, Point A) {
        double rectaX = Q.x - P.x;
        double rectaY = Q.y - P.y;
        double normalX = -rectaY;
        double normalY = rectaX;
        double vectorX = A.x - P.x;
        double vectorY = A.y - P.y;

        return Math.abs((vectorX*normalX) + (vectorY*normalY))/Math.sqrt(normalX*normalX + normalY*normalY);
    }

    public static LinkedList<Point> encontrarHull(LinkedList<Point> segmento, Point P, Point Q, char tipo){
        LinkedList<Point> convexHull = new LinkedList<>();
        if (!segmento.isEmpty() && P != null && Q != null) {
/*             System.out.println("LLAMADA");
            System.out.println("P: "+ P.toString());
            System.out.println("Q: "+ Q.toString()); */
            double distanciaMasLejana = -1;
            Point puntoMasLejano = null;
            for (Point point : segmento) {
                double distanciaActual = calcularDistancia(P, Q, point);
                if (distanciaActual > distanciaMasLejana) {
                    distanciaMasLejana = distanciaActual;
                    puntoMasLejano = point;
                }
/*                 System.out.println("SEGMENTO ACTUAL: " + segmento.toString());
                System.out.println("CURRENT POINT : " + "("+point.x+","+point.y+"), P: ("+P.x +","+P.y+"), Q: ("+Q.x +","+Q.y+")" );
                System.out.println("DISTANCIA ---- " + distanciaActual);
                System.out.println("MAS GRANDE SDIST ---- " + distanciaMasLejana); */
            }
            convexHull.add(puntoMasLejano);
            segmento.remove(puntoMasLejano);

            // System.out.println("segmento resultante: "+ segmento+ " tipo actual: "+ tipo);
            LinkedList<Point>[] segmentosP = segmentar(segmento, P, puntoMasLejano);
            LinkedList<Point>[] segmentosQ = segmentar(segmento, puntoMasLejano, Q);

            LinkedList<Point> P_arriba = segmentosP[0];
            LinkedList<Point> P_abajo = segmentosP[1];
            LinkedList<Point> Q_arriba = segmentosQ[0];
            LinkedList<Point> Q_abajo = segmentosQ[1];
/*             System.out.println("resegmentado P: arriba/abajo de P y puntoMasLejano");
            System.out.println(P_arriba.toString());
            System.out.println(P_abajo.toString());
            System.out.println("resegmentado Q: arriba/abajo de puntoMasLejano y Q");
            System.out.println(Q_arriba.toString());
            System.out.println(Q_abajo.toString()); */

            if (tipo == '0') {
                convexHull.addAll(encontrarHull(P_arriba, P, puntoMasLejano, '0'));
                convexHull.addAll(encontrarHull(Q_arriba, puntoMasLejano, Q, '0'));
            } else {
                convexHull.addAll(encontrarHull(P_abajo, P, puntoMasLejano, '1'));
                convexHull.addAll(encontrarHull(Q_abajo, puntoMasLejano, Q, '1'));
            }
        }
        return convexHull;
    }

    public static LinkedList<Point>[] segmentar(LinkedList<Point> S, Point P, Point Q) {
        LinkedList<Point>[] segmentos = new LinkedList[2];
        LinkedList<Point> arriba = new LinkedList<>();
        LinkedList<Point> abajo = new LinkedList<>();
        segmentos[0] = arriba;
        segmentos[1] = abajo;

        if (Q.x - P.x != 0) {
            // no son puntos verticales
            double m = (Q.y - P.y)/(Q.x - P.x);
            double b = -m * P.x + P.y;
    
            for (Point point : S) {
                if (point.y > m * point.x + b) {
                    arriba.add(point);
                } else if (point.y < m * point.x + b) {
                    abajo.add(point);
                }
            }
        }

        return segmentos;
    }

    public static void main(String[] args) {
        Random r = new Random();
        LinkedList<Point> convexHull = new LinkedList<>();
        LinkedList<Point> P = new LinkedList<>();
   
        for (int i = 0; i < 20; i++) {
            P.add(new Point(r.nextInt(0,20), r.nextInt(0,20)));
        } 

        P.sort((p1, p2) -> Integer.compare(p1.x, p2.x));; // ordenados segun X, P[0] es +izq y P[length-1] es +der
        System.out.println("Puntos:");
        imprimir(P);

        System.out.println();

        Point P1 = P.pollFirst();
        Point P2 = P.pollLast();
        
        convexHull.add(P1);
        convexHull.add(P2);

        LinkedList<Point>[] segmentos = segmentar(P, P1, P2);

        convexHull.addAll(encontrarHull(segmentos[0], P1, P2, '0'));
        convexHull.addAll(encontrarHull(segmentos[1], P2, P1, '1'));

        System.out.println("ConvexHull: ");
        imprimir(convexHull);
    }

    public static void imprimir(LinkedList<Point> P) {
        for (Point point : P) {
            System.out.print("("+point.x+","+point.y+"); ");
        }
    }
}
