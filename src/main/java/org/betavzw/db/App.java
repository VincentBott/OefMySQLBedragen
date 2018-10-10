package org.betavzw.db;



import java.math.BigDecimal;
import java.sql.*;

public class App
{
    private static final String SELECT = "select id, naam, prijs, prijs * 1.21 as prijsMetBTW from product";

    public static void main( String[] args ) throws SQLException {

        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useSSL=false&serverTimezone=Europe/Brussels", "root", "VDAB")){

            try(Statement statement = conn.createStatement();

                ResultSet rs = statement.executeQuery(SELECT)){

                while(rs.next()) {

                    int id = rs.getInt("id");
                    String naam = rs.getString("naam");
                    BigDecimal prijs = rs.getBigDecimal("prijs");

                    BigDecimal prijsMetBTW = rs.getBigDecimal("prijsMetBTW");
                    System.out.printf("%d: %s kost € %.2f(€ %.2f incl BTW)%n", id, naam, prijs, prijsMetBTW);

                    // System.out.printf("%d: %s kost € %.2f(€ %.2f incl BTW)%n", id, naam, prijs, prijs.floatValue()*1.21);


                    // BigDecimal multiply() is nauwkeuriger !!!

                }
            }
        }
    }
}
