
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aitor
 */
public class HerrienTaulaKontsultatu {
    
    public static void main(String[] args) {
        ikusi();
    }
    
    public static Connection konektatu() {
        String server = "localhost"; // ip-a edo izena
        String db = "KoronabirusDBa";
        String url = "jdbc:mysql://" + server + "/" + db;
        String user = "ikaslea";
        String pass = "ikaslea";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println(server + " zerbitzariko " + db + " datu-basera konektatu zara.");
        } catch (SQLException e) {
            System.out.println("Ezin izan zara datu basera konektatu");
            System.exit(0);
        }
        return conn;

    }
    public static void ikusi() {
        Scanner in = new Scanner(System.in);
        System.out.print("Zein herrialdetako datuak ikusi nahi dituzu (* dena ikusteko)? ");
        String herria = in.next();

        String taula = "Herrialdea";
        String[] eremua = {"Herrialdea", "Populazioa", "Azalera"}; //gero imprimatu ahal izateko

        String sql = "SELECT * FROM " + taula;
        if (!"*".equals(herria)) {
            sql += " WHERE herrialdea='" + herria + "'"; //SELECT FROM....  '*' ez badago WHERE gehituko zaio herri bakar bat ikusteko 
        }

        try (Connection conn = konektatu();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            boolean primeraFila = true;
            while (rs.next()) {
                if (primeraFila) {
                    for (int j = 0; j < eremua.length; j++) { // eremua arraia 
                        System.out.printf("%20s", eremua[j], " | ");
                    }
                    System.out.printf("%20s", "Detsitatea");
                    System.out.println("");
                    primeraFila = false;
                }
               
                for (int j = 0; j < eremua.length; j++) {
                    System.out.printf("%20s", rs.getString(eremua[j]));
                }
                double dentsitatea = rs.getDouble("Populazioa") / rs.getDouble("Azalera");
                System.out.printf("%20s", dentsitatea);
                System.out.println("");
            }

            if (primeraFila) {
                System.out.println("Ez dago herri hori datu basean");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Konektatu behar zara datu-basera datuak ikusteko");
        }
    }
}
