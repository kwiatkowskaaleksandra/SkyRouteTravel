package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class KontrolerMenu implements Initializable {

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,Integer> idp;
    @FXML
    private TableColumn<DaneDoWycieczek,String> ms;
    @FXML
    private TableColumn<DaneDoWycieczek,String> zak;
    @FXML
    private TableColumn<DaneDoWycieczek,String> tran;
    @FXML
    private TableColumn<DaneDoWycieczek,String> atr;
    @FXML
    private TableColumn<DaneDoWycieczek,String> prem;
    @FXML
    private TableColumn<DaneDoWycieczek,String> czs;
    @FXML
    private TableColumn<DaneDoWycieczek,Float> cn;
    @FXML
    public Button IdZaloguj;
    @FXML
    public Button IdSzukaj;
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



    public Button WycieczkiKlient;
    public Button LastMinute;
    public Button Promocja;
    public Button Egzotyka;
    //public Button IdSzukaj;
    public Button Konto;

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

    public void wyszukaj() {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        ObservableList WczTab = FXCollections.observableArrayList();
        String danee = "SELECT * FROM wycieczki";
        Statement st = null;

        try {
            st = connectDB.createStatement();
        } catch (SQLException var15) {
            var15.printStackTrace();
        }
        DaneDoWycieczek daneDoWycieczek;

        ResultSet rs = null;

        try {
            assert st != null;

            rs = st.executeQuery(danee);
        } catch (SQLException var14) {
            var14.printStackTrace();
        }

        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id");
                String miejsce = rs.getString("miejsce");
                float cena = rs.getFloat("cena");
                String transport = rs.getString("transport");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("zakwaterowanie");
                String premium =rs.getString("premium");
                String atrakcje =rs.getString("atrakcje");
                daneDoWycieczek = new DaneDoWycieczek(id1, miejsce,cena,transport,czasPodrozy,zakwaterowanie,premium,atrakcje);
                WczTab.add(daneDoWycieczek);
            }

            st.close();
        } catch (Exception var16) {
            System.out.println("WyjÄ…tek!.");
            System.out.println(var16.getMessage());
        }

        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        ms.setCellValueFactory(new PropertyValueFactory<>("miejsce"));
        cn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tran.setCellValueFactory(new PropertyValueFactory<>("transport"));
        czs.setCellValueFactory(new PropertyValueFactory<>("czasPodrozy"));
        zak.setCellValueFactory(new PropertyValueFactory<>("zakwaterowanie"));
        prem.setCellValueFactory(new PropertyValueFactory<>("premium"));
        atr.setCellValueFactory(new PropertyValueFactory<>("atrakcje"));
        FilteredList<DaneDoWycieczek> wyszukiwanie = new FilteredList(WczTab, (b) -> {
            return true;
        });
        this.IdMiejsce.textProperty().addListener((observable, oldValue, newValue) -> {
            wyszukiwanie.setPredicate((DaneDoWycieczek) -> {
                if (newValue != null && !newValue.isEmpty()) {
                    String literki = newValue.toLowerCase();
                    if (DaneDoWycieczek.getMiejsce().toLowerCase().contains(literki)) {
                        return true;
                    } else {
                        return DaneDoWycieczek.getMiejsce().toLowerCase().contains(literki) ? true : String.valueOf(DaneDoWycieczek.getTransport()).contains(literki);
                    }
                } else {
                    return true;
                }
            });
        });
        SortedList<DaneDoWycieczek> posortowane = new SortedList(wyszukiwanie);
        posortowane.comparatorProperty().bind(this.Tab1.comparatorProperty());
        this.Tab1.setItems(posortowane);
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

    public void WycieczkiMenuOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) WycieczkiKlient.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("WycieczkiMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void IdSzukajOnAction (javafx.event.ActionEvent event){
        Stage stage = (Stage) IdSzukaj.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("WyszukajTabela.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void LastMinuteOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) WycieczkiKlient.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("WycieczkiMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void PromocjaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) WycieczkiKlient.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("WycieczkiMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void EgzotykaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) WycieczkiKlient.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("WycieczkiMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}
