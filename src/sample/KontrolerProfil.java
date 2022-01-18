package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class KontrolerProfil implements Initializable {
    public Button prof;
    public Button rez;
    public Button hsl;
    public Button ust;
    public Button plt;
    public TextField imieKl;
    public TextField nazwKl;
    public TextField emailKl;
    public TextField dataKl;
    public TextField loginKl;


    public void ProfilOnAction()  {
        Stage stage = (Stage) prof.getScene().getWindow();
        stage.close();

     try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Profil.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void RezerwacjaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) rez.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/ProfilRezerwacje.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void PolitykaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) plt.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/ProfilPolityka.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void HasloOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) hsl.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/ProfilHaslo.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void UstawieniaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) ust.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/ProfilUstawienia.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
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
        Statement statement2 = null;
        try {
            statement2 = connectDB.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String daneKl="SELECT k.id_klienta,k.login,k.imie,k.nazwisko, k.email, k.dataZalozenia FROM zalogowany z , klient k WHERE z.id_klienta=k.id_klienta;";
        ResultSet queryResult2 = null;
        try {
            queryResult2 = statement2.executeQuery(daneKl);
            int idZal=0;
            String imie=null,nazwisko=null,email=null,data=null,login=null;
            while (queryResult2.next()) {
                idZal = queryResult2.getInt("id_klienta");
                imie=queryResult2.getString("imie");
                nazwisko=queryResult2.getString("nazwisko");
                login=queryResult2.getString("login");
                email=queryResult2.getString("email");
                data=queryResult2.getString("dataZalozenia");
            }
            imieKl.setText(imie);
            System.out.println(imieKl.getText());
            nazwKl.setText(nazwisko);
            emailKl.setText(email);
            dataKl.setText(data);
            loginKl.setText(login);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
