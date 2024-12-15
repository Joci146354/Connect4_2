package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pontszamok {

    private static final String ADATBAZIS_URL = "jdbc:sqlite:data/connect4.db";

    public static void megjelenitPontszamok() {
        try (Connection kapcsolat = DriverManager.getConnection(ADATBAZIS_URL);
             Statement utasitas = kapcsolat.createStatement()) {

            String lekerdezes = "SELECT jatekosNev, pontszam, datumIdo FROM JatekEredmenyek ORDER BY datumIdo DESC;";
            ResultSet eredmenyhalmaz = utasitas.executeQuery(lekerdezes);

            System.out.println("--- Pontszámok ---");
            while (eredmenyhalmaz.next()) {
                String nev = eredmenyhalmaz.getString("jatekosNev");
                int pontszam = eredmenyhalmaz.getInt("pontszam");
                String datumIdo = eredmenyhalmaz.getString("datumIdo");

                System.out.printf("Név: %s | Pontszám: %d | Idő: %s%n", nev, pontszam, datumIdo);
            }

        } catch (SQLException e) {
            System.out.println("Nem sikerült lekérni a pontszámokat: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        megjelenitPontszamok();
    }
}
