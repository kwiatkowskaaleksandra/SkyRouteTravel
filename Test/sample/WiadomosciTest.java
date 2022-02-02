package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class WiadomosciTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    @Test
    void wyswietlWiadomosci() throws SQLException {
        final ObservableList WczTab = FXCollections.observableArrayList();
        Statement statement = connectDB.createStatement();

        String dane = "SELECT id_klienta FROM zalogowany;";
        ResultSet queryResult = statement.executeQuery(dane);

        DaneDoWiadomosci daneDoWiadomosci;
        String dane3 = "SELECT imie, nazwisko FROM klient k WHERE k.id_klienta='1'";
        pst=(PreparedStatement) connectDB.prepareStatement(dane3);
        pst.execute();

    }




    @Test
    void wyslijWiadomosc() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat = null;
        stat = connectDB.createStatement();
        Statement stat2 = null;
        stat2 = connectDB.createStatement();
        String danee = "SELECT * FROM wiadomosci WHERE temat='Ubezpieczenie' AND adresat='Ola@wp.pl'";
        ResultSet wynik = stat.executeQuery(danee);

        int idw = 0, id_prac = 0, id_kl = 0;
        while (wynik.next()) {
            id_prac = wynik.getInt("id_pracownika");
            id_kl = wynik.getInt("id_klienta");
        }
        String maxID = "SELECT id_wiadomosci FROM wiadomosci";
        ResultSet max = stat2.executeQuery(maxID);
        while (max.next()) {
            idw = max.getInt("id_wiadomosci");
        }

        String dane = "INSERT INTO wiadomosci(id_wiadomosci,temat,adresat,tresc,data,id_pracownika,id_klienta)values(20,'ok','ko@wp.pl','ko','2020-12-12','1','1')";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();
    }
    @Test
    void usunWiadomosc() {

        String danee = "DELETE FROM wiadomosci WHERE temat='ok' AND adresat='ko@wp.pl'";

        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }

}