package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class RejestracjaKlient {
    public TextField im;
    public TextField naz;
    public TextField em;
    public TextField log;
    public TextField has;
    public TextField powHas;
    public Button zaloz;

    PreparedStatement pst = null;
    int index = -1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void zaloz() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat = null;
        stat = connectDB.createStatement();
        String maxID = "SELECT id_klienta FROM klient";
        ResultSet max = stat.executeQuery(maxID);
        int idK=0;
        while (max.next()) {
            idK = max.getInt("id_klienta");
        }
        String dane="INSERT INTO klient (id_klienta, imie,nazwisko,email,login,haslo,dataZalozenia) VALUES (?,?,?,?,?,?,?)";

        if(im.getText().isEmpty()||naz.getText().isEmpty()||em.getText().isEmpty()||log.getText().isEmpty() || has.getText().isEmpty() || powHas.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uzupełnij wszytskie dane!");

        }else {
            if(has.getText().equals(powHas.getText())){
                try{
                    pst=(PreparedStatement) connectDB.prepareStatement(dane);
                    pst.setString(1, String.valueOf(idK+1));
                    pst.setString(2,im.getText());
                    pst.setString(3,naz.getText());
                    pst.setString(4,em.getText());
                    pst.setString(5,log.getText());
                    pst.setString(6,has.getText());
                    pst.setString(7,String.valueOf(LocalDate.now()));
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Konto zostało pomyślnie założone. Proszę się zalogować.");
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Blad dodawania! " + e);
                }
            }else JOptionPane.showMessageDialog(null, "Podane hasła różnią się!");
        }
    }
}
