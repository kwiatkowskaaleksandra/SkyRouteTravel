package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public ListView <String>listaOgloszen;



    public void IdWylogujOnAciotn(javafx.event.ActionEvent event)
    {
        Stage stage = (Stage) IdWyloguj.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Home.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/OfertyWycieczek.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/Faktury.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/Wiadomosci.fxml"));
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        final ObservableList WczTab = FXCollections.observableArrayList();

            String danee = "SELECT data,tresc FROM ogloszenia";

            Statement st = null;
            try{
                st = connectDB.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet rs = null;
            try {
                rs = Objects.requireNonNull(st).executeQuery(danee);
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


    }
}
