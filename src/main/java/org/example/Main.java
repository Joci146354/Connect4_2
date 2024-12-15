package org.example;

import java.util.Scanner;

class Main {

    private Object Pontszam;

    public static void main(String[] args) {
        Main program = new Main();
        int valasztas = program.bekerFelhasznaloiValasztas();

        if (valasztas == 1) {
            program.jatekInditasa();
        } else if (valasztas == 0) {
            program.pontszamokMegjelenitese();
        }
    }

    private int bekerFelhasznaloiValasztas() {
        Scanner scanner = new Scanner(System.in);
        int valasztas = -1;

        while (valasztas != 0 && valasztas != 1) {
            System.out.println("1-es gomb: játék indítása, 0-s gomb: pontszámok megtekintése");

            if (scanner.hasNextInt()) {
                valasztas = scanner.nextInt();
                if (valasztas != 0 && valasztas != 1) {
                    System.out.println("Hiba: csak 0 és 1 fogadható el!");
                }
            } else {
                System.out.println("Hiba: csak számot adj meg!");
                scanner.next();
            }
        }
        return valasztas;
    }

    private void jatekInditasa() {
        System.out.println("A játék elindul...");
        Connect4 amoba = new Connect4();
        amoba.jatek();
    }

    private void pontszamokMegjelenitese() {
        System.out.println("Pontszámok megjelenítése...");
        Pontszamok.megjelenitPontszamok();
    }
}
