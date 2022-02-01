package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


class WiadomosciKlientTest {
    PreparedStatement pst = null;
    int index = -1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    @Test
    public void WyswietlWiadomosci() throws SQLException, IOException {
        final ObservableList WczTab = FXCollections.observableArrayList();
        Statement statement = connectDB.createStatement();

        String dane = "SELECT id_klienta FROM zalogowany;";
        ResultSet queryResult = statement.executeQuery(dane);
        int idZal = 0;
        while (queryResult.next()) {
            idZal = queryResult.getInt("id_klienta");
        }
        Statement st = null;
        try {
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoWiadomosci daneDoWiadomosci;
        String dane3 = "SELECT imie, nazwisko FROM klient k WHERE k.id_klienta='1'";
        ResultSet rs2 = null;
        try {
            rs2 = Objects.requireNonNull(st).executeQuery(dane3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void usunWiadomosc() throws SQLException {


        String danee = "DELETE FROM wiadomosci WHERE temat='dfd' AND adresat='rfe'";

        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }
    @Test
    public void WyslijWiadomosc() throws SQLException, IOException {

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


    }




    @AfterEach
    void tearDown() throws SQLException {
        String usun="DELETE FROM wiadomosci where id_wiadomosci = 20";
        pst=(PreparedStatement) connectDB.prepareStatement(usun);
        pst.execute();
    }
}