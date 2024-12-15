package org.example;

import java.util.Scanner;

public class Connect4 {

    private final Tabla tabla;
    private boolean jatekNyert;
    private String jatekosNev1;
    private String jatekosNev2;
    private int lepesekSzama;

    public Connect4() {
        tabla = new Tabla();
        jatekNyert = false;
        lepesekSzama = 0;
        inicializalJatek();
    }

    private void inicializalJatek() {
        Scanner scanner = new Scanner(System.in);
        int valasztas = -1;

        while (valasztas != 0 && valasztas != 1) {
            System.out.println("Nyomja meg az 1-es gombot a mentett játék betöltéséhez, vagy a 0-s gombot új játék indításához:");
            if (scanner.hasNextInt()) {
                valasztas = scanner.nextInt();
                scanner.nextLine();
                if (valasztas != 0 && valasztas != 1) {
                    System.out.println("Hiba: csak 0 és 1 elfogadható!");
                }
            } else {
                System.out.println("Hiba: csak számot adjon meg!");
                scanner.nextLine();
            }
        }

        if (valasztas == 1) {
            if (JatekAllapotMentes.jatekAllapotBetoltes(tabla)) {
                System.out.println("Játékállapot betöltve.");
            } else {
                System.out.println("Betöltés sikertelen. Új játék indul.");
            }
        } else {
            System.out.println("Új játék indul.");
        }
    }

    public void jatek() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Adja meg az első játékos nevét: ");
        jatekosNev1 = scanner.nextLine();

        System.out.print("Adja meg a második játékos nevét: ");
        jatekosNev2 = scanner.nextLine();

        char aktualisJatekos = 'X';
        String aktualisJatekosNev = jatekosNev1;

        while (!jatekNyert) {
            tabla.tablaKiiratas();
            int oszlop = -1;

            boolean ervenyesBemeno = false;
            while (!ervenyesBemeno) {
                System.out.println(aktualisJatekosNev + ", válasszon egy oszlopot (1-7):");
                if (scanner.hasNextInt()) {
                    oszlop = scanner.nextInt() - 1;
                    if (oszlop >= 0 && oszlop < tabla.getOszlopokSzama()) {
                        ervenyesBemeno = true;
                    } else {
                        System.out.println("Csak 1-től 7-ig adhat meg oszlopot.");
                    }
                } else {
                    System.out.println("Csak 1-től 7-ig adhat meg oszlopot.");
                    scanner.nextLine();
                }
            }

            if (tabla.uresHely(0, oszlop)) {
                tabla.lehelyezKorong(aktualisJatekos, tabla.getElsoUresSor(oszlop), oszlop);
                lepesekSzama++;

                if (tabla.Gyoztes(aktualisJatekos)) {
                    tabla.tablaKiiratas();
                    System.out.println(aktualisJatekosNev + " nyert!");
                    int pontszam = Highscore.szamoldPontszam(lepesekSzama);
                    System.out.println("Pontszáma: " + pontszam);

                    Adatbazis adatbazis = new Adatbazis();
                    adatbazis.mentesEredmeny(aktualisJatekosNev, lepesekSzama);

                    jatekNyert = true;
                    JatekAllapotMentes.jatekAllapotMentese(tabla);
                }


                if (aktualisJatekos == 'X') {
                    aktualisJatekos = 'O';
                    aktualisJatekosNev = jatekosNev2;
                } else {
                    aktualisJatekos = 'X';
                    aktualisJatekosNev = jatekosNev1;
                }
            } else {
                System.out.println("Az oszlop tele van! Próbálja újra.");
            }
        }
    }

    public String getJatekosNev1() {
        return jatekosNev1;
    }

    public String getJatekosNev2() {
        return jatekosNev2;
    }

    public int getLepesekSzama() {
        return lepesekSzama;
    }

    public static void main(String[] args) {
        Connect4 connect4 = new Connect4();
        connect4.jatek();
    }

    public Tabla getTabla() {
        return tabla;
    }
}
