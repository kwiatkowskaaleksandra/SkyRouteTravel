package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AkceptacjaRezerwacjiTest {

    @BeforeEach
    void setUp() throws SQLException {
        try{
String dane="insert into rezerwacje(id_rezerwacji,id_klienta,id_wycieczki,termin, ileDoroslych, ileDzieci,cena, id_platnosc, statusPotwierdzenia) values (20,1,2,'2020-11-11',2,2,250,2,'Niezaakpcetowany');";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();}catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy rezrwacji! "+e);
        }
    }

    PreparedStatement pst = null;
    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    @Test
    void wyswietlRezerwacje() throws SQLException {
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_rezerwacji,imie, nazwisko,miejsce,termin, ileDoroslych, ileDzieci,r.cena, p.rodzaj, statusPotwierdzenia FROM rezerwacje r, wycieczki w, platnosc p, klient k WHERE r.id_klienta=k.id_klienta AND r.id_platnosc=p.id_platnosc AND r.id_wycieczki=w.id_wycieczki ORDER BY id_rezerwacji ASC";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoRezerwacji daneDoRezerwacji;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void akceptujRezerwacje() {
        String status="Zaakceptowana";
        try{
            String dane="UPDATE rezerwacje SET statusPotwierdzenia='Zaakceptowany' WHERE id_rezerwacji=20";
            pst=(PreparedStatement) connectDB.prepareStatement(dane);
            pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy zmienie statusu! "+e);
        }
    }

    @Test
    void dodajFakture() throws SQLException {
        String daneFaktury="INSERT INTO faktury(id_faktury,imie,nazwisko,email,id_platnosc,data,kwota,id_wycieczki)VALUES(20,'jarek','kot','jare@wp.pl',2,'2020-10-10',100,2)";
        pst=(PreparedStatement) connectDB.prepareStatement(daneFaktury);
        pst.execute();
    }

    @Test
    void wyslijWiadomosc() throws SQLException {
        try {
            String daneWiado = "INSERT INTO wiadomosci(id_wiadomosci,temat,adresat,tresc,data,id_pracownika,id_klienta)VALUES(20,'jar','jarek@wp.pl','jar','2020-05-05',2,3)";
            pst = (PreparedStatement) connectDB.prepareStatement(daneWiado);
            pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy wysylaniu wiadomosci! "+e);
        }
    }

    @Test
    void wyslijWiadomoscOdrzucona() throws SQLException {
        try{
        String daneWiado="INSERT INTO wiadomosci(id_wiadomosci,temat,adresat,tresc,data,id_pracownika,id_klienta)VALUES(20,'jar','jarek@wp.pl','jar','2020-05-05',2,3)";
        pst=(PreparedStatement) connectDB.prepareStatement(daneWiado);
        pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy wysylaniu wiadomosci! "+e);
        }
    }

    @Test
    void odzrucRezerwacje() {
    }
    @AfterEach
    void tearDown() throws SQLException {
        String usun="DELETE FROM rezerwacje where id_rezerwacji = 20";
        pst=(PreparedStatement) connectDB.prepareStatement(usun);
        pst.execute();
        String usun2="DELETE FROM faktury where id_faktury = 20";
        pst=(PreparedStatement) connectDB.prepareStatement(usun2);
        pst.execute();
        String usun3="DELETE FROM wiadomosci where id_wiadomosci = 20";
        pst=(PreparedStatement) connectDB.prepareStatement(usun3);
        pst.execute();
    }
}