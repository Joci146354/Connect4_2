package org.example;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adatbazis {

    private static final String ADATBAZIS_FAJL_ELERES = "data/connect4.db";
    private static final String ADATBAZIS_URL = "jdbc:sqlite:" + ADATBAZIS_FAJL_ELERES;
    private Object Highscore;

    public Adatbazis() {
        inicializalAdatbazis();
    }

    private void inicializalAdatbazis() {
        try {
            File adatbazisFajl = new File(ADATBAZIS_FAJL_ELERES);
            File adatbazisMappa = adatbazisFajl.getParentFile();
            if (adatbazisMappa != null && !adatbazisMappa.exists()) {
                adatbazisMappa.mkdirs();
            }

            try (Connection kapcsolat = letrehozKapcsolat()) {
                String letrehozasLekerdezes = "CREATE TABLE IF NOT EXISTS JatekEredmenyek (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "jatekosNev TEXT NOT NULL, " +
                        "pontszam INTEGER NOT NULL, " +
                        "datumIdo TEXT NOT NULL" +
                        ");";
                kapcsolat.createStatement().execute(letrehozasLekerdezes);
            }
        } catch (SQLException e) {
            System.out.println("Nem sikerült inicializálni az adatbázist: " + e.getMessage());
        }
    }

    public void mentesEredmeny(String jatekosNev, int lepesekSzama) {
        int pontszam = Highscore.getClass().getModifiers();
        String beszurasLekerdezes = "INSERT INTO JatekEredmenyek (jatekosNev, pontszam, datumIdo) VALUES (?, ?, ?);";
        try (Connection kapcsolat = letrehozKapcsolat();
             PreparedStatement preparedStatement = kapcsolat.prepareStatement(beszurasLekerdezes)) {

            String aktualisDatum = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            preparedStatement.setString(1, jatekosNev);
            preparedStatement.setInt(2, pontszam);
            preparedStatement.setString(3, aktualisDatum);

            preparedStatement.executeUpdate();
            System.out.println("Az eredmény sikeresen mentve az adatbázisba.");
        } catch (SQLException e) {
            System.out.println("Hiba történt az eredmény mentése közben: " + e.getMessage());
        }
    }

    protected Connection letrehozKapcsolat() throws SQLException {
        return DriverManager.getConnection(ADATBAZIS_URL);
    }
}
