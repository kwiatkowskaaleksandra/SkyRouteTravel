package sample;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class KontrolerRezeracjiKlientTest {
    PreparedStatement pst = null;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();



    @Test
    void wyswietlRezrwacje() throws SQLException {
        String danee = "SELECT wk.TwojaCena, w.iloscDni,wk.id,wk.cenaPrem, wk.premium, wk.id_wycieczki FROM wycieczki w , wycieczki_klient wk where wk.id_klienta='1'AND w.id_wycieczki=wk.id_wycieczki";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();  }




    @Test
    void anulujRezerwacje() throws SQLException {
        String dane="INSERT INTO `wycieczki_klient` (`id`, `id_klienta`, `id_wycieczki`, `TwojaCena`, `status`, `premium`, `cenaPrem`) VALUES ('1', '1', '1', '200', 'ok', 'ok', '1213')";
        String danee="DELETE FROM wycieczki_klient  WHERE id='1'";
        pst=(PreparedStatement) connectDB.prepareStatement(dane);
        pst.execute();
    pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();

    }

    @Test
    void zaplac() throws SQLException {
        try{
        String danee2="DELETE from rezerwacje where id_rezerwacji = 10";
        pst=(PreparedStatement) connectDB.prepareStatement(danee2);
        pst.execute();
        String danee="insert into rezerwacje(id_rezerwacji,id_klienta,id_wycieczki,termin, ileDoroslych, ileDzieci,cena, id_platnosc, statusPotwierdzenia) values (10,10,2,'2020-11-11',2,2,250,2,'Niezaakpcetowany')";
        pst=(PreparedStatement) connectDB.prepareStatement(danee);
        pst.execute();
String danee3="DELETE from rezerwacje where id_rezerwacji = 10";

        pst=(PreparedStatement) connectDB.prepareStatement(danee3);
        pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy zaplacie! "+e);
        }
    }

}