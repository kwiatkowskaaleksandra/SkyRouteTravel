package sample;

import org.junit.jupiter.api.AfterEach;

import java.sql.*;

class RejestracjaKlientTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    public void zaloz() throws SQLException {

        Statement stat = null;
        stat = connectDB.createStatement();
        String maxID = "SELECT id_klienta FROM klient";
        ResultSet max = stat.executeQuery(maxID);
        int idK = 0;
        while (max.next()) {
            idK = max.getInt("id_klienta");
        }
        String dane = "INSERT INTO klient (id_klienta, imie,nazwisko,email,login,haslo,dataZalozenia) VALUES (10,jarek,korek,jarek02@wp.pl,ok,ko,2022-12-12)";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();

    }
    @AfterEach
    void tearDown() throws SQLException {
        String danee="DELETE FROM klient where id_klienta=10";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }
}