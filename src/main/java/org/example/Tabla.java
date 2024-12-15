package org.example;

public class Tabla {

    private static final int SOROK = 6;
    private static final int OSZLOPOK = 7;
    private static final char URES = '.';
    private char[][] tabla;

    public Tabla() {
        tabla = new char[SOROK][OSZLOPOK];
        inicializalTabla();
    }

    public void inicializalTabla() {
        for (int sor = 0; sor < SOROK; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
                tabla[sor][oszlop] = URES;
            }
        }
    }

    public void tablaKiiratas() {
        for (int sor = 0; sor < SOROK; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
                System.out.print(tabla[sor][oszlop] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean uresHely(int sor, int oszlop) {
        return tabla[sor][oszlop] == URES;
    }

    public void lehelyezKorong(char jatekos, int sor, int oszlop) {
        tabla[sor][oszlop] = jatekos;
    }

    public char getPozicio(int sor, int oszlop) {
        return tabla[sor][oszlop];
    }

    public int getOszlopokSzama() {
        return OSZLOPOK;
    }

    public int getElsoUresSor(int oszlop) {
        for (int sor = SOROK - 1; sor >= 0; sor--) {
            if (tabla[sor][oszlop] == URES) {
                return sor;
            }
        }
        return -1;
    }

    public boolean Gyoztes(char jatekos) {

        for (int sor = 0; sor < SOROK; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK - 3; oszlop++) {
                if (tabla[sor][oszlop] == jatekos &&
                        tabla[sor][oszlop + 1] == jatekos &&
                        tabla[sor][oszlop + 2] == jatekos &&
                        tabla[sor][oszlop + 3] == jatekos) {
                    return true;
                }
            }
        }


        for (int sor = 0; sor < SOROK - 3; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
                if (tabla[sor][oszlop] == jatekos &&
                        tabla[sor + 1][oszlop] == jatekos &&
                        tabla[sor + 2][oszlop] == jatekos &&
                        tabla[sor + 3][oszlop] == jatekos) {
                    return true;
                }
            }
        }


        for (int sor = 0; sor < SOROK - 3; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK - 3; oszlop++) {
                if (tabla[sor][oszlop] == jatekos &&
                        tabla[sor + 1][oszlop + 1] == jatekos &&
                        tabla[sor + 2][oszlop + 2] == jatekos &&
                        tabla[sor + 3][oszlop + 3] == jatekos) {
                    return true;
                }
            }
        }


        for (int sor = 0; sor < SOROK - 3; sor++) {
            for (int oszlop = 3; oszlop < OSZLOPOK; oszlop++) {
                if (tabla[sor][oszlop] == jatekos &&
                        tabla[sor + 1][oszlop - 1] == jatekos &&
                        tabla[sor + 2][oszlop - 2] == jatekos &&
                        tabla[sor + 3][oszlop - 3] == jatekos) {
                    return true;
                }
            }
        }

        return false;
    }

    public void alaphelyzet() {
        inicializalTabla();
    }

    public void korongLerakas(char c, int i, int j) {
    }
}
