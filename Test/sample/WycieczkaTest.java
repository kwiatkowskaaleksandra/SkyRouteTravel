package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

class WycieczkaTest {
Wycieczka wycieczka;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pst = null;
    @BeforeEach
    void setUp() {

    }

    @Test
    void dodajWycieczke() throws SQLException {


        Statement stat1=null;
        stat1=connectDB.createStatement();
        String maxID="SELECT id_wycieczki FROM wycieczki ORDER BY  id_wycieczki ASC";
        ResultSet max=stat1.executeQuery(maxID);
        int idw = 0;
        while (max.next()){
            idw=max.getInt("id_wycieczki");
        }

        String danee="INSERT INTO wycieczki(id_wycieczki,nazwa,miejsce,cena,id_transport,czas,id_zakwaterowanie,wyzywienie,premium,cenaPrem, atrakcje,rodzajWycieczki,iloscDni, zdjecie)values(20,irak,malepg,234,4,2h,5,tak,tak,250,wielbady,egzotyka,4,kolega.jpg)";

    }

    @Test
    void usunWycieczke() throws SQLException {


        Statement stat2=null;
        stat2=connectDB.createStatement();

            String danee="DELETE FROM wycieczki WHERE id_wycieczki=20";
       stat2.executeUpdate(danee);
    }

}