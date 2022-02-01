package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.*;
import java.util.Objects;

class OpinieAdminTest {
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pst = null;
    @Test
    void zglosUzytkownika() throws SQLException {


        String dane3 = "SELECT k.id_klienta FROM klient k, opinie o WHERE o.id_klienta=k.id_klienta";
        ResultSet rs2 = null;
        Statement st = null;

        st = connectDB.createStatement();
        rs2 = Objects.requireNonNull(st).executeQuery(dane3);
        int idK=0;
        while (Objects.requireNonNull(rs2).next()) {
            idK=rs2.getInt("id_klienta");
        }
        String danee="Update klient set login='zablokowany', haslo='zablokowany' where id_klienta=3";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1,"Zablokowany");
            pst.setString(2,"Zablokowany");
            pst.setString(3, String.valueOf(idK));
            pst.execute();
            JOptionPane.showMessageDialog(null,"Zgłoszono użytkownika!");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Nie można zgłościć użytkownika. "+e);
        }

    }

    @Test
    void pokazOpinie() throws SQLException {

        final ObservableList WczTab = FXCollections.observableArrayList();
        Statement statement = connectDB.createStatement();
        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneOpinie daneOpinie;
        String stan="Zablokowany";
        String dane="SELECT id_opinia, k.imie,k.nazwisko,w.nazwa,w.miejsce,o.data,o.tresc FROM opinie o , wycieczki w, klient k WHERE o.id_klienta=k.id_klienta AND w.id_wycieczki=o.id_wycieczki AND  not k.login='"+stan+"';";
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(dane);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() throws SQLException {
        String danee="Update klient set login='kasia', haslo='kasia' where id_klienta=3";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }

}