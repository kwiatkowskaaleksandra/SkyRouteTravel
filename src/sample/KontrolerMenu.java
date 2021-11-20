package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class KontrolerMenu implements Initializable {


    @FXML
    public Button IdZaloguj;
    @FXML
    public Button IdOferta;
    @FXML
    public Button IdPromocja;
    @FXML
    public Button IdLast;
    @FXML
    public Button IdEgzotyka;
    @FXML
    private Label IdLabel;
    @FXML
    private TextField IdLogin;
    @FXML
    private TextField IdMiejsce;
    @FXML
    private TextField IdTermin;
    @FXML
    private TextField IdLiczba;
    @FXML
    private TextField IdTransport;
    @FXML
    private PasswordField IdHaslo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void IdZalogujOnAction(javafx.event.ActionEvent event)
    {

        if(!IdLogin.getText().isBlank() && !IdHaslo.getText().isBlank())
        {
            validateLogin();
        }
        else
        {
            IdLabel.setText("Wpisz login i haslo");
        }
    }

    public void validateLogin()
    {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT * FROM uzytkownicy WHERE Login = '" +IdLogin.getText() +"'AND Haslo = '" + IdHaslo.getText() + "'" ;

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);



            while(queryResult.next())
            {

                System.out.println(queryResult);
                    if(queryResult.getString("Login").equals("Klient"))
                    {

                        IdLabel.setText("Udalo sie zalogowac!");
                        KontrolerKlient();
                    }
                    if(queryResult.getString("Login").equals("Pracownik"))
                    {
                        IdLabel.setText("Udalo sie zalogowac!");
                        KontrolerPracownik();
                    }
                    if(queryResult.getString("Login").equals("Admin"))
                    {
                        IdLabel.setText("Udalo sie zalogowac!");
                        KontrolerAdministrator();
                    }

                else
                {
                    IdLabel.setText("Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                }
            }
            Stage stage = (Stage) IdZaloguj.getScene().getWindow();
            stage.close();


        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void KontrolerKlient(){
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Klient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1920,1080));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void KontrolerPracownik(){
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Pracownik.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1720,880));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void KontrolerAdministrator(){
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Administrator.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1720,880));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }


}
