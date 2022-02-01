package sample;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class FakturaKlientTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    @Test
    void pokazFakt() throws SQLException {
        String daneF="SELECT f.imie, f.nazwisko, f.email,f.data,f.kwota,w.nazwa,w.miejsce,r.statusPotwierdzenia,r.ileDzieci,r.ileDoroslych " +
                "FROM faktury f, rezerwacje r, wycieczki w WHERE r.id_wycieczki=w.id_wycieczki AND w.id_wycieczki=f.id_wycieczki  AND r.id_rezerwacji='2';";
        pst=(PreparedStatement) connectDB.prepareStatement(daneF);
        pst.execute();
    }
}