package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Objects;

class OgloszeniaTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void wyswietlOgloszenia() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        PreparedStatement pst = null;

        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_ogloszenia,data,tresc FROM ogloszenia order by id_ogloszenia asc";

        Statement st = null;
        try {
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneOgloszenia daneOgloszenia;
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {

                int id = rs.getInt("id_ogloszenia");
                Date data = rs.getDate("data");
                String tresc = rs.getString("tresc");
                daneOgloszenia = new DaneOgloszenia(id, tresc, data);
                WczTab.add(daneOgloszenia);
            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }

    }

    @Test
    void dodajO() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();


        Statement stat2 = null;
        stat2 = connectDB.createStatement();
        String danee = "SELECT * FROM ogloszenia";
        stat2.executeQuery(danee);

        String dane = "INSERT INTO ogloszenia(id_ogloszenia,data,tresc)values(6,'1999-02-02','ok')";
        stat2.executeUpdate(dane);
    }

    @Test
    void usunO() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat2 = null;
        stat2 = connectDB.createStatement();
        String danee = "DELETE FROM ogloszenia WHERE id_ogloszenia=6";
        stat2.executeUpdate(danee);


    }

    @Test
    void edytujO() throws SQLException {

        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat2 = null;
        stat2 = connectDB.createStatement();
        String danee = "UPDATE ogloszenia SET id_ogloszenia='1' ,data='1999-04-05',tresc='ok' WHERE id_ogloszenia=1";
        stat2.executeUpdate(danee);

    }
}