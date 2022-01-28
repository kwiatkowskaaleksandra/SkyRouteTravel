package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FakturaKlient implements Initializable {
    @FXML
    private TextField imNaz;
    @FXML
    private TextField em;
    @FXML
    private TextField data;
    @FXML
    private TextField kwota;
    @FXML
    private TextField msce;
    @FXML
    private TextField ilOs;
    @FXML
    private TextField statR;
    @FXML
    public Button wroc;

 KontrolerProfil kontrolerProfil=new KontrolerProfil();
public  int idR=kontrolerProfil.ideRez;

    public void pokazFakt() throws SQLException {

        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();


        Statement stat2=null;
        String naz=null,im=null,email=null, miejsce=null,nazwa=null,status=null;
        int ileD=0,ileDz=0;
        float cena=0;
        Date datF=null;
        stat2=connectDB.createStatement();
        String daneF="SELECT f.imie, f.nazwisko, f.email,f.data,f.kwota,w.nazwa,w.miejsce,r.statusPotwierdzenia,r.ileDzieci,r.ileDoroslych " +
                "FROM faktury f, rezerwacje r, wycieczki w WHERE r.id_wycieczki=w.id_wycieczki AND w.id_wycieczki=f.id_wycieczki  AND r.id_rezerwacji='"+idR+"';";
        ResultSet fakKl=stat2.executeQuery(daneF);
        while (fakKl.next()){
            naz=fakKl.getString("nazwisko");
            im=fakKl.getString("imie");
            email=fakKl.getString("email");
            miejsce=fakKl.getString("miejsce");
            nazwa=fakKl.getString("nazwa");
            status=fakKl.getString("statusPotwierdzenia");
            ileD=fakKl.getInt("ileDoroslych");
            ileDz=fakKl.getInt("ileDzieci");
            cena=fakKl.getFloat("kwota");
            datF=fakKl.getDate("data");
        }
        imNaz.setText(naz+" "+im);
        em.setText(email);
        msce.setText(nazwa+" "+miejsce);
        statR.setText(status);
        ilOs.setText(String.valueOf(ileDz+ileD));
        kwota.setText(String.valueOf(cena));
        data.setText(String.valueOf(datF));
    }

    public void wrocButtonOnAction() {
        Stage stage = (Stage) wroc.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pokazFakt();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
