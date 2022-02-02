package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class KontrolerProfilTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    @BeforeEach
    void setUp() throws SQLException {

         }

    @Test
    void dane() throws SQLException {

            Statement stat=null;
            stat=connectDB.createStatement();
            String idZ="SELECT z.id_klienta,k.login,k.imie,k.nazwisko,k.email from zalogowany z , klient k where z.id_klienta=k.id_klienta";
            ResultSet maxz=stat.executeQuery(idZ);
    }

  /*  @Test
    void zapiszZmiany() throws SQLException {
        String dane = "INSERT INTO klient (id_klienta, imie,nazwisko,email,login,haslo,dataZalozenia) VALUES (10,'jarek','korek','jarek02@wp.pl','ok','ko','2021-05-05')";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();
        String zmiana="UPDATE klient SET imie='koran',nazwisko='moran',email='wp.pl',login='sa' WHERE id_klienta=10";
        pst=(PreparedStatement) connectDB.prepareStatement(zmiana);
        pst.execute();
        String dane2="DELETE from klient where id_klienta=10";
        pst=(PreparedStatement) connectDB.prepareStatement(dane2);
        pst.execute();
    }*/

    @Test
    void zmianaHasla() throws SQLException {
        String zmiana="UPDATE klient SET haslo='ma' WHERE id_klienta=10";
        pst=(PreparedStatement) connectDB.prepareStatement(zmiana);
        pst.execute();
    }

    @Test
    void rezerwacja() throws SQLException {
        try {
            String danee = "insert into rezerwacje(id_rezerwacji,id_klienta,id_wycieczki,termin, ileDoroslych, ileDzieci,cena, id_platnosc, statusPotwierdzenia) values (10,10,2,'2020-11-11',2,2,250,2,'Niezaakpcetowany')";
            pst = (PreparedStatement) connectDB.prepareStatement(danee);
            pst.execute();
            String daneee = "SELECT id_rezerwacji,imie,miejsce,nazwa,termin, ileDoroslych, ileDzieci,r.cena, p.rodzaj, statusPotwierdzenia " +
                    "FROM rezerwacje r, wycieczki w, platnosc p, klient k WHERE r.id_klienta=k.id_klienta AND r.id_platnosc=p.id_platnosc " +
                    "AND r.id_wycieczki=w.id_wycieczki AND r.id_klienta ='10' ORDER BY id_rezerwacji ASC";
            pst = (PreparedStatement) connectDB.prepareStatement(daneee);
            pst.execute();
        }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Blad przy rezerwacji! "+e);
    }
    }

    @Test
    void usunRezrw() throws SQLException {
        String danee="DELETE FROM rezerwacje WHERE id_rezerwacji=10";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }


    @Test
    void dodOpinie() throws SQLException {
        try{
        String dane="INSERT INTO opinie(id_opinia,tresc,data,id_klienta,id_wycieczki)values(10,'tak','2000-12-12',10,2)";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy dodawaniu opinii! "+e);
        }
    }
    @AfterEach
    void tearDown() throws SQLException {

        String danee="DELETE FROM opinie where id_opinia=10";

        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
    }

}