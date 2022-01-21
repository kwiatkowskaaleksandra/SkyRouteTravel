package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class opiniaKlient implements Initializable {
    @FXML
    public TextField msc;
    @FXML
    public TextField nazwa;
    @FXML
    public TextArea tresc;
    @FXML
    private TextField idRezrw;
    public Button wrc;
    public Button dod;
    PreparedStatement pst = null;

    public void dodOpinie() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        int idZal=0;
        String idZ="SELECT id_klienta from zalogowany";
        ResultSet maxz=stat.executeQuery(idZ);
        while (maxz.next()){
            idZal=maxz.getInt("id_klienta");
        }

        Statement stat2=null;
        stat2=connectDB.createStatement();
        int ido=0;
        String maxID="SELECT id_opinia FROM opinie";
        ResultSet max=stat2.executeQuery(maxID);
        while (max.next()){
            ido=max.getInt("id_opinia");
        }

        Statement stat3=null;
        stat3=connectDB.createStatement();
        int idW=0;
        String idWyc="SELECT r.id_wycieczki FROM rezerwacje r , wycieczki w where r.id_klienta='"+idZal+"'AND w.nazwa='"+nazwa.getText()+"'AND w.miejsce='"+msc.getText()+"' AND w.id_wycieczki=r.id_wycieczki";
        ResultSet idwycieczki=stat3.executeQuery(idWyc);
        while (idwycieczki.next()){
            idW=idwycieczki.getInt("id_wycieczki");
        }

System.out.println(idW);
        String dane="INSERT INTO opinie(id_opinia,tresc,data,id_klienta,id_wycieczki)values(?,?,?,?,?)";
        try{
            pst=(PreparedStatement)connectDB.prepareStatement(dane);
            pst.setString(1, String.valueOf(ido+1));
            pst.setString(2,tresc.getText());
            pst.setString(3,String.valueOf(LocalDate.now()));
            pst.setString(4, String.valueOf(idZal));
            pst.setString(5, String.valueOf(idW));
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad dodawania! "+e);
        }
    }

    public void wyjdzButtonOnAction() {
        Stage stage = (Stage) wrc.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement statement2 = null;
        try {
            statement2 = connectDB.createStatement();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        String daneRez = "SELECT id_rezerwacji, nazwa, miejsce from rezerwacje r, wycieczki w where w.id_wycieczki=r.id_wycieczki and r.id_rezerwacji='" + 1 + "'";
        ResultSet queryResult2 = null;
        int idRez = 0;
        String nazwaW = null, miejsce = null;
        try {
            queryResult2 = statement2.executeQuery(daneRez);

            while (queryResult2.next()) {
                idRez = queryResult2.getInt("id_rezerwacji");
                nazwaW = queryResult2.getString("nazwa");
                miejsce = queryResult2.getString("miejsce");
            }
            nazwa.setText(nazwaW);
            msc.setText(miejsce);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
