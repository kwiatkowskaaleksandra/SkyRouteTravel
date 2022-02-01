package sample;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class KontaPracownikowTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    @Test
    void wyswietlPracownikow() throws SQLException {
        String danee = "SELECT * FROM pracownik where login!='admin' ORDER BY id_pracownika ASC";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }
    @Test
    void dodajPracownika() throws SQLException {
        String danee="INSERT INTO pracownik(id_pracownika, imie,nazwisko,email,login,haslo,rola)values(20,'ja','ma','ma@wp.pl','ok','ko','pracownik')";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }
    @Test
    void edytujPracownika() throws SQLException {
       String danee = "UPDATE pracownik SET id_pracownika='20' ,imie='jacek',nazwisko='macek',email='k@wp.lp',login='rak',haslo='opak',rola='pracownik'WHERE id_pracownika=20";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }
    @Test
    void usunPracownika() throws SQLException {
        String danee="DELETE FROM pracownik WHERE id_pracownika=20";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }




}