package laberinto;

/**
 * Autor: Alejandro Pérez Durán. Descripción: Laberinto que se resuelve
 * automáticamente empleando recursividad. Fecha de creación: 19/08/2016.
 */
public class Laberinto {

    String laberinto(byte m[][], byte f, byte c, String cad) {
        String temp = null;

        if (m[f][c] == 2) {
            return cad;
        } else {
            m[f][c] = 1;
            if (f > 0 && m[f - 1][c] >= 2) {//Mover arriba
                temp = laberinto(m, (byte) (f - 1), c, cad + "A-");
            }
            if (temp == null && c < l[0].length - 1 && m[f][c + 1] >= 2) {//Mover derecha
                temp = laberinto(m, f, (byte) (c + 1), cad + "D-");
            }
            if (temp == null && f < l.length - 1 && m[f + 1][c] >= 2) {//Mover abajo
                temp = laberinto(m, (byte) (f + 1), c, cad + "B-");
            }
            if (temp == null && c > 0 && m[f][c - 1] >= 2) {//Mover izquierda
                temp = laberinto(m, f, (byte) (c - 1), cad + "I-");
            }
            return temp;
        }
    }

    byte l[][] = {//0 = Muro, 1 = Inicio, 2 = Meta y 3 = Camino libre. 
        {3, 0, 0, 0, 0, 0, 2},
        {3, 3, 3, 0, 3, 0, 3},
        {3, 0, 3, 3, 3, 0, 3},
        {3, 0, 3, 0, 3, 0, 3},
        {3, 0, 3, 0, 3, 0, 3},
        {1, 0, 3, 0, 3, 3, 3}};

    byte l2[][] = {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0}};

    void marcarL2(String caminoDirecto, int f, int c) {
        char ch;
        l2[f][c] = 1;
        for (int i = 0; i < caminoDirecto.length(); i++) {
            ch = caminoDirecto.charAt(i);

            switch (ch) {
                case 'A':
                    f -= 1;
                    break;
                case 'D':
                    c += 1;
                    break;
                case 'B':
                    f += 1;
                    break;
                case 'I':
                    c -= 1;
                    break;
            }
            l2[f][c] = 1;
        }
    }

    void imprimirLaberintoL2() {
        for (int i = 0; i < l2.length; i++) {
            for (int j = 0; j < l2[0].length; j++) {
                System.out.print("[" + l2[i][j] + "]");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Laberinto la = new Laberinto();
        byte f = 5, c = 0;
        String caminoDirecto;
        String caminoExtra = la.laberinto(la.l, f, c, "");
        if (caminoExtra != null) {
            System.out.println("\n[Salida encontrada]");
            System.out.println("[CAMINO]:");
            System.out.println(caminoExtra + "\n");
            caminoDirecto = caminoExtra.replace("-", "");
            la.marcarL2(caminoDirecto, f, c);
            la.imprimirLaberintoL2();
        } else {
            System.out.println("\n[Salida no encontrada]");
        }
    }
}
