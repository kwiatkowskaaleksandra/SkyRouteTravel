package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Poloczenie;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class KontrolerPracownik implements Initializable  {

    @FXML
    private Button IdWyloguj;
    public Button OfertyWycieczek;
    public Button Faktury;
    public Button Wiadomosci;
    public Button Akceptacja;
    public ListView <String>listaOgloszen;
    public ListView <String>listaOpinie;



    public void IdWylogujOnAciotn(javafx.event.ActionEvent event)
    {
        Stage stage = (Stage) IdWyloguj.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/Home.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1910, 1000));
            menuStage.setTitle("SKY ROUTE TRAVEL");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WycieczkiOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) OfertyWycieczek.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/OfertyWycieczek.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.setTitle("Oferty Wycieczek");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void FakturyOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) Faktury.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/Faktury.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1430,1000));
            menuStage.setTitle("Faktury");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WiadomosciOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) Wiadomosci.getScene().getWindow();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/Wiadomosci.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1250,650));
            menuStage.setTitle("Wiadomości");
            menuStage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void AkceptacjaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) Akceptacja.getScene().getWindow();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/AkceptacjaRezerwacji.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1010,550));
            menuStage.setTitle("Akceptacja Rezerwacji");
            menuStage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

            String daneOgl = "SELECT data,tresc FROM ogloszenia";
            Statement st = null;
            try{
                st = connectDB.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet rs = null;
            try {
                rs = Objects.requireNonNull(st).executeQuery(daneOgl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                while (Objects.requireNonNull(rs).next()) {

                    String data =rs.getString("data");
                    String tresc = rs.getString("tresc");
                    listaOgloszen.getItems().addAll(data+" - "+tresc);
                }
                st.close();
            } catch (Exception e) {
                System.out.println("There is an Exception.");
                System.out.println(e.getMessage());
            }

        String daneO = "SELECT o.tresc, o.data, w.nazwa  FROM opinie o, wycieczki w WHERE o.id_wycieczki=w.id_wycieczki";
        Statement st2 = null;
        try{
            st2 = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs2 = null;
        try {
            rs2 = Objects.requireNonNull(st2).executeQuery(daneO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs2).next()) {

                String data =rs2.getString("data");
                String tresc = rs2.getString("tresc");
                String nazwa=rs2.getString("nazwa");
                listaOpinie.getItems().addAll(data+"  -  "+nazwa+"  -  "+tresc);
            }
            st2.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }


    }
}
