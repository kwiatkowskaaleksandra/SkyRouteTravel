package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Objects;

class WycieczkiMenuTest {
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pst = null;
    @Test
    public void WyswietlWycieczki() throws SQLException {

        final ObservableList WczTab = FXCollections.observableArrayList();



        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni, zdjecie FROM wycieczki w join zakwaterowanie z on w.id_zakwaterowanie=z.id_zakwaterowanie join transport t on w.id_transport=t.id_transport";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
        Statement st = null;
        try {
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoWycieczek daneDoWycieczek;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id_wycieczki");
                String nazwa = rs.getString("nazwa");
                String miejsce = rs.getString("miejsce");
                float cena = rs.getFloat("cena");
                String transport = rs.getString("transport");
                String czasPodrozy = rs.getString("czas");
                String zakwaterowanie = rs.getString("zakwaterowanie");
                String wyzywienie = rs.getString("wyzywienie");
                String premium = rs.getString("premium");
                float cenaPrem = rs.getFloat("cenaPrem");
                String atrakcje = rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                String zdj = rs.getString("zdjecie");
                daneDoWycieczek = new DaneDoWycieczek(id1, nazwa, miejsce, cena, transport, czasPodrozy, zakwaterowanie, wyzywienie, premium, cenaPrem, atrakcje, rodzaj, iloscDni, zdj);
                WczTab.add(daneDoWycieczek);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void validateLogin() throws SQLException {


        String verifyLoginPr = "SELECT id_pracownika,login , haslo FROM pracownik WHERE login ='j'  AND haslo ='p' " ;
        String verifyLoginKl = "SELECT id_klienta,login , haslo FROM klient WHERE login = ' o' AND haslo = ' k ' " ;

        String dane="INSERT INTO zalogowany(id_zalogowanego,id_klienta,id_pracownika,login,haslo)values(20,1,1,'o','k')";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();
    }
    @AfterEach
    void tearDown() throws SQLException {
        String usun="DELETE FROM zalogowany where id_zalogowanego = 20";
        pst=(PreparedStatement) connectDB.prepareStatement(usun);
        pst.execute();
    }
}