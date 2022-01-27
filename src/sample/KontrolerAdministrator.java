package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KontrolerAdministrator {
    @FXML
    private Button IdWyloguj;
    @FXML
    private Button OfertyWycieczek;
    @FXML
    private Button Faktury;
    @FXML
    private Button kontaPracownikow;
    @FXML
    private Button Ogloszenia;
    @FXML
    private Button opinie;

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
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/OfertyWycieczekAdmin.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/FakturyAdmin.fxml"));
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

    public void KontoPracownikaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) kontaPracownikow.getScene().getWindow();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/KontaPracownikow.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 900,650));
            menuStage.setTitle("Konta pracownikow");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void OgloszeniaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) Ogloszenia.getScene().getWindow();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/Ogloszenia.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 470,510));
            menuStage.setTitle("Ogloszenia");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void OpinieKlientow() {
        Stage stage = (Stage) opinie.getScene().getWindow();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/opinieAdmin.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 810,480));
            menuStage.setTitle("Opinie Klientów");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
