package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Objects;

class WycieczkiKlientTest {

    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pst = null;
    @Test
    void wyswietlWycieczki() throws SQLException {



        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium, w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni, w.zdjecie FROM wycieczki w join zakwaterowanie z on w.id_zakwaterowanie=z.id_zakwaterowanie join transport t on w.id_transport=t.id_transport  ORDER BY id_wycieczki ASC";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
        Statement st = null;
        try{
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
                float cena = rs.getFloat("w.cena");
                String transport = rs.getString("transport");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("zakwaterowanie");
                String wyzywienie=rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                float cenaP=rs.getFloat("w.cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                String zdj= rs.getString("zdjecie");
                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaP,atrakcje,rodzaj,iloscDni,zdj);
                WczTab.add(daneDoWycieczek);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }

    }
}