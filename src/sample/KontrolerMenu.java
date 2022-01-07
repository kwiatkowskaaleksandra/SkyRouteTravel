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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class KontrolerMenu implements Initializable {

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,Integer> idp;
    @FXML
    private TableColumn<DaneDoWycieczek,String> nzw;
    @FXML
    private TableColumn<DaneDoWycieczek,String> ms;
    @FXML
    private TableColumn<DaneDoWycieczek,String> rdw;
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
    private TableColumn<DaneDoWycieczek, ImageView> obr;
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
    private TextField IdKraj;
    @FXML
    private TextField IdMiejsce;
    @FXML
    private TextField IdZakwaterowanie;
    @FXML
    private TextField IdTransport;
    @FXML
    private PasswordField IdHaslo;

    String rodzaj;
    float cena;

    public Button WycieczkiKlient;
    public Button LastMinute;
    public Button Promocja;
    public Button Egzotyka;
    //public Button IdSzukaj;
    public Button Konto;
    ObservableList<DaneDoWycieczek> szukanie = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        WyswietlWycieczki();
    }

    public void IdZalogujOnAction(javafx.event.ActionEvent event)
    {

        if(!IdLogin.getText().isBlank() && !IdHaslo.getText().isBlank())
        {
            Poloczenie connectNow = new Poloczenie();
            Connection connectDB = connectNow.getConnection();
            String dane="DELETE FROM zalogowany WHERE id_zalogowanego=1";
            try{
                PreparedStatement pst=null;
                pst=(PreparedStatement) connectDB.prepareStatement(dane);
                pst.execute();
            }catch(Exception e)
            {
                e.printStackTrace();
                e.getCause();
            }
            ValidateLogin();
        }
        else
        {
            IdLabel.setText("Wpisz login i haslo");
        }
    }


    public void ValidateLogin()
    {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String verifyLoginPr = "SELECT id_pracownika,login , haslo FROM pracownik WHERE login = '" +IdLogin.getText() +"'AND haslo = '" + IdHaslo.getText() + "'" ;
        String verifyLoginKl = "SELECT id_klienta,login , haslo FROM klient WHERE login = '" +IdLogin.getText() +"'AND haslo = '" + IdHaslo.getText() + "'" ;

        String dane="INSERT INTO zalogowany(id_zalogowanego,id_klienta,id_pracownika,login,haslo)values(?,?,?,?,?)";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginPr);

            while(queryResult.next())
            {

                if(queryResult.getString("login").equals(IdLogin.getText()) && queryResult.getString("haslo").equals(IdHaslo.getText()))
                {
                    String id=queryResult.getString("id_pracownika");

                    try{
                        PreparedStatement pst=null;
                        pst=(PreparedStatement) connectDB.prepareStatement(dane);
                        pst.setString(1,"1");
                        pst.setString(2,null);
                        pst.setString(3,id);
                        pst.setString(4,IdLogin.getText());
                        pst.setString(5,IdHaslo.getText());
                        pst.execute();
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                        e.getCause();
                    }
                    IdLabel.setText("Udalo sie zalogowac!");
                    KontrolerPracownik();
                }

                else
                {
                    IdLabel.setText("Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                }
            }
            ResultSet queryResultKl = statement.executeQuery(verifyLoginKl);
            while(queryResultKl.next()) {

                if (queryResultKl.getString("login").equals(IdLogin.getText()) && queryResultKl.getString("haslo").equals(IdHaslo.getText())) {
                    String id=queryResultKl.getString("id_klienta");
                    try{
                        PreparedStatement pst=null;
                        pst=(PreparedStatement) connectDB.prepareStatement(dane);
                        pst.setString(1,"1");
                        pst.setString(2,id);
                        pst.setString(3, null);
                        pst.setString(4,IdLogin.getText());
                        pst.setString(5,IdHaslo.getText());
                        pst.execute();
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                        e.getCause();
                    }
                    IdLabel.setText("Udalo sie zalogowac!");
                    KontrolerKlient();
                } else {
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
    public void WyswietlWycieczki(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium,w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w join zakwaterowanie z on w.id_zakwaterowanie=z.id_zakwaterowanie join transport t on w.id_transport=t.id_transport";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoWycieczek daneDoWycieczek;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id_wycieczki");
                String nazwa = rs.getString("nazwa");
                String miejsce = rs.getString("miejsce");
                float cena = rs.getFloat("cena");
                String transport = rs.getString("transport");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("zakwaterowanie");
                String wyzywienie =rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");

                daneDoWycieczek = new DaneDoWycieczek(id1, nazwa,miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,atrakcje,rodzaj,iloscDni);
                WczTab.add(daneDoWycieczek);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nzw.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        ms.setCellValueFactory(new PropertyValueFactory<>("miejsce"));
        cn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tran.setCellValueFactory(new PropertyValueFactory<>("transport"));
        czs.setCellValueFactory(new PropertyValueFactory<>("czasPodrozy"));
        zak.setCellValueFactory(new PropertyValueFactory<>("zakwaterowanie"));
        prem.setCellValueFactory(new PropertyValueFactory<>("premium"));
        atr.setCellValueFactory(new PropertyValueFactory<>("atrakcje"));
        rdw.setCellValueFactory(new PropertyValueFactory<>("rodzaj"));
        obr.setCellValueFactory(new PropertyValueFactory<>("Obraz"));

        Tab1.setItems(WczTab);

        FilteredList<DaneDoWycieczek>filtrowanie = new FilteredList<>(WczTab, b -> true);
        this.IdKraj.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoWycieczek->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoWycieczek.getNazwa().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        this.IdMiejsce.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoWycieczek->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoWycieczek.getMiejsce().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        this.IdZakwaterowanie.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoWycieczek->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoWycieczek.getZakwaterowanie().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        this.IdTransport.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoWycieczek->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoWycieczek.getTransport().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        SortedList<DaneDoWycieczek> posortowane = new SortedList<>(filtrowanie);
        posortowane.comparatorProperty().bind(Tab1.comparatorProperty());
        this.Tab1.setItems(posortowane);

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
                int id1 = rs.getInt("id_wycieczki");
                String nazwa = rs.getString("nazwa");
                String miejsce = rs.getString("miejsce");
                float cena = rs.getFloat("w.cena");
                String transport = rs.getString("t.rodzaj");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("z.rodzaj");
                String wyzywienie=rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");

                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,atrakcje,rodzaj,iloscDni);
                WczTab.add(daneDoWycieczek);
            }

            st.close();
        } catch (Exception var16) {
            System.out.println("WyjÄ…tek!.");
            System.out.println(var16.getMessage());
        }

        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nzw.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/Klient.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/Pracownik.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/Administrator.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiMenuKlient.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiMenuKlient.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiMenuKlient.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiMenuKlient.fxml"));
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
