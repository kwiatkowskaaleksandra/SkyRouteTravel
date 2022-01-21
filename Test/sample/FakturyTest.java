package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import  static  org.junit.jupiter.api.Assertions.assertThrows;
import java.sql.*;
import java.util.Objects;

class FakturyTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void wyswietlFaktury() {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_faktury, imie, nazwisko, email, p.rodzaj,f.data,kwota, w.nazwa,w.rodzajWycieczki FROM faktury f, platnosc p,wycieczki w WHERE  f.id_platnosc=p.id_platnosc AND w.id_wycieczki=f.id_wycieczki";

        Statement st = null;
        try{
         st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoFaktur daneDoFaktur;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id_faktury");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String email = rs.getString("email");
                String platnosc =rs.getString("p.rodzaj");
                Date data=rs.getDate("f.data");
                float kwota=rs.getFloat("kwota");
                String nazwa =rs.getString("nazwa");
                String rodzaj =rs.getString("rodzajWycieczki");

                daneDoFaktur = new DaneDoFaktur(id1, imie,nazwisko,email,platnosc,data,kwota,nazwa,rodzaj);
                WczTab.add(daneDoFaktur);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
    }

    @Test

    void dodajFakture() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
      // WycieczkiKlient thrown= assertThrows(WycieczkiKlient.class,()-> {

           // try {
                Statement stat2 = null;
                stat2 = connectDB.createStatement();
                String maxID = "SELECT id_faktury FROM faktury ORDER BY  id_faktury ASC";
                ResultSet max = stat2.executeQuery(maxID);
                int idf = 0;
                while (max.next()) {
                    idf = max.getInt("id_faktury");
                }
                Statement stat3 = null;
                stat3 = connectDB.createStatement();

                String danee = "INSERT INTO faktury(id_faktury,imie,nazwisko,email,id_platnosc,data,kwota,id_wycieczki)values(1,'jarek','kot','jarek@wp.pl',4,'1999-04-04',1444,5)";
                stat3.executeUpdate(danee);
          //  } catch (Exception e) {

//            }

       // });

    }

    @Test
    void usunFakture() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat2=null;
        stat2=connectDB.createStatement();
        String danee="DELETE FROM faktury WHERE id_faktury=2";
        stat2.executeUpdate(danee);
    }

    @Test
    void edytujFakture() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat2=null;
        stat2=connectDB.createStatement();


        String danee="DELETE FROM faktury WHERE id_faktury=6";
        stat2.executeUpdate(danee);
    }
}