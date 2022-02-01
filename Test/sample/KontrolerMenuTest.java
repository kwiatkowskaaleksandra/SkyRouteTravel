package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class KontrolerMenuTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    @Test
    void validateLogin() throws SQLException {
        String verifyLoginPr = "SELECT id_pracownika,login , haslo FROM pracownik WHERE login = 'j'AND haslo = 'p'" ;
        String verifyLoginKl = "SELECT id_klienta,login , haslo FROM klient WHERE login = 'o'AND haslo = 'k'" ;

        pst=(PreparedStatement) connectDB.prepareStatement(verifyLoginKl);
        pst.execute();
        pst=(PreparedStatement) connectDB.prepareStatement(verifyLoginPr);
        pst.execute();
        String dane="INSERT INTO zalogowany(id_zalogowanego,id_klienta,id_pracownika,login,haslo)values(21,1,1,'o','k')";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();
    }

    @AfterEach
    void tearDown() throws SQLException {
        String usun="DELETE FROM zalogowany where id_zalogowanego = 21";
        pst=(PreparedStatement) connectDB.prepareStatement(usun);
        pst.execute();
    }
}