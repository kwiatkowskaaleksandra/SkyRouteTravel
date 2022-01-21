package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Random;
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
    @FXML
    private Button zalKonto;

    String rodzaj;
    float cena;

    public Button WycieczkiKlient;
    public Button LastMinute;
    public Button Promocja;
    public Button Egzotyka;
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public Label tekst1;
    public Label tekst2;
    public Label tekst3;
    public Label tekst4;
    public Button Konto;
    ObservableList<DaneDoWycieczek> szukanie = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
      //  WyswietlWycieczki();
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Random rand = new Random();
        Statement stat1 = null;
        int r = 0;
        try {
            stat1 = connectDB.createStatement();
            r = rand.nextInt(13)+1;
            System.out.println(r);
            String maxID = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet max = null;

            max = stat1.executeQuery(maxID);
            String foto = null, nazwa = null;
            float cena = 0;
            int id = 0;
            while (max.next()) {
                id = max.getInt("id_wycieczki");
                foto = max.getString("zdjecie");
                nazwa = max.getString("nazwa");
                cena = max.getFloat("cena");
                System.out.println(foto);
                File kat = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki[] = kat.list();
                for (int i = 0; i < pliki.length; i++) {

                    if ((pliki[i].equals(foto))) {
                        System.out.println("mam taki");
                        image1.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki[i]));
                        tekst1.setText(nazwa+ " TYLKO " + cena + " zł");
                    }
                }
            }

            Statement stat2 = null;
            stat2 = connectDB.createStatement();
            r+=1;
            String maxID2 = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet max2 = null;
            System.out.println(r);
            max2 = stat2.executeQuery(maxID2);
            String foto2 = null, nazwa2 = null;
            float cena2 = 0;
            int id2 = 0;
            while (max2.next()) {
                id2 = max2.getInt("id_wycieczki");
                foto2 = max2.getString("zdjecie");
                nazwa2 = max2.getString("nazwa");
                cena2 = max2.getFloat("cena");
                System.out.println(foto);
                File kat2 = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki2[] = kat2.list();
                for (int i = 0; i < pliki2.length; i++) {

                    if ((pliki2[i].equals(foto2))) {
                        System.out.println("mam taki");
                        image2.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki2[i]));
                        tekst2.setText(nazwa2 + " TYLKO " + cena2 + " zł");
                    }
                }
            }

            Statement stat3 = null;
            r+=1;
            stat3= connectDB.createStatement();
            System.out.println(r);
            String maxID3 = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet maxIDZ = null;

            maxIDZ = stat3.executeQuery(maxID3);
            String foto3 = null, nazwa3 = null;
            float cena3 = 0;
            int id3 = 0;
            while (maxIDZ.next()) {
                id3 = maxIDZ.getInt("id_wycieczki");
                foto3 = maxIDZ.getString("zdjecie");
                nazwa3 = maxIDZ.getString("nazwa");
                cena3= maxIDZ.getFloat("cena");
                System.out.println(foto3);
                File kat3 = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki3[] = kat3.list();
                for (int i = 0; i < pliki3.length; i++) {

                    if ((pliki3[i].equals(foto3))) {
                        System.out.println("mam taki");
                        image3.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki3[i]));
                        tekst3.setText(nazwa3 + " TYLKO " + cena3 + " zł");
                    }
                }
            }

            Statement stat4 = null;
            r+=1;
            stat4= connectDB.createStatement();
            System.out.println(r);
            String maxID4 = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet maxIDZd = null;

            maxIDZd = stat4.executeQuery(maxID4);
            String foto4 = null, nazwa4 = null;
            float cena4 = 0;
            int id4 = 0;
            while (maxIDZd.next()) {
                id4 = maxIDZd.getInt("id_wycieczki");
                foto4 = maxIDZd.getString("zdjecie");
                nazwa4 = maxIDZd.getString("nazwa");
                cena4= maxIDZd.getFloat("cena");
                System.out.println(foto4);
                File kat4 = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki4[] = kat4.list();
                for (int i = 0; i < pliki4.length; i++) {

                    if ((pliki4[i].equals(foto4))) {
                        System.out.println("mam taki");
                        image4.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki4[i]));
                        tekst4.setText(nazwa4 + " TYLKO " + cena4 + " zł");
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
                if(queryResult.getString("login").equals(IdLogin.getText()) && queryResult.getString("haslo").equals(IdHaslo.getText()) ) {
                    if (IdLogin.getText().equals("admin")) {
                        Stage stage = (Stage) IdZaloguj.getScene().getWindow();
                        stage.close();
                        KontrolerAdministrator();
                    }
                    else {
                        String id = queryResult.getString("id_pracownika");
                        try {
                            PreparedStatement pst = null;
                            pst = (PreparedStatement) connectDB.prepareStatement(dane);
                            pst.setString(1, "1");
                            pst.setString(2, null);
                            pst.setString(3, id);
                            pst.setString(4, IdLogin.getText());
                            pst.setString(5, IdHaslo.getText());
                            pst.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                        IdLabel.setText("Udalo sie zalogowac!");
                        KontrolerPracownik();
                        Stage stage = (Stage) IdZaloguj.getScene().getWindow();
                        stage.close();
                    }
                }
                else {
                    Stage stage = (Stage) IdZaloguj.getScene().getWindow();
                    IdLabel.setText("Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                    JOptionPane.showMessageDialog(null,"Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                }
            }
            if(queryResult.next()==false ){
                IdLabel.setText("Błędny login lub hasło.");
            }
            ResultSet queryResultKl = statement.executeQuery(verifyLoginKl);
                while (queryResultKl.next()) {

                    if (queryResultKl.getString("login").equals(IdLogin.getText()) && queryResultKl.getString("haslo").equals(IdHaslo.getText())) {
                        String id = queryResultKl.getString("id_klienta");
                        try {
                            PreparedStatement pst = null;
                            pst = (PreparedStatement) connectDB.prepareStatement(dane);
                            pst.setString(1, "1");
                            pst.setString(2, id);
                            pst.setString(3, null);
                            pst.setString(4, IdLogin.getText());
                            pst.setString(5, IdHaslo.getText());
                            pst.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                        IdLabel.setText("Udalo sie zalogowac!");
                        KontrolerKlient();
                        Stage stage = (Stage) IdZaloguj.getScene().getWindow();
                        stage.close();
                    }
                }
            if(queryResultKl.next()==false ){
                IdLabel.setText("Błędny login lub hasło.");
            }


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

        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni, zdjecie FROM wycieczki w join zakwaterowanie z on w.id_zakwaterowanie=z.id_zakwaterowanie join transport t on w.id_transport=t.id_transport  ORDER BY id_wycieczki ASC";

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
                String atrakcje =rs.getString("atrakcje");
                String premium =rs.getString("premium");
                float cenaP = rs.getFloat("w.cenaPrem");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                String zdj = rs.getString("zdjecie");
                daneDoWycieczek = new DaneDoWycieczek(id1, nazwa,miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaP,atrakcje,rodzaj,iloscDni, zdj);
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


        Tab1.setItems(WczTab);

     /*   FilteredList<DaneDoWycieczek>filtrowanie = new FilteredList<>(WczTab, b -> true);
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
*/
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
                float cena = rs.getFloat("cena");
                String transport = rs.getString("t.rodzaj");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("z.rodzaj");
                String wyzywienie=rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                float cenaPrem=rs.getFloat("w.cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                String zdj =rs.getString("zdjecia");
                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaPrem,atrakcje,rodzaj,iloscDni,zdj);
                WczTab.add(daneDoWycieczek);
            }

            st.close();
        } catch (Exception var16) {
            System.out.println("Wyjątek!.");
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
            menuStage.setScene(new Scene(root, 1910, 1000));
            menuStage.setTitle("SKY ROUTE TRAVEL");
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
            menuStage.setScene(new Scene(root, 1910, 1000));
            menuStage.setTitle("SKY ROUTE TRAVEL");
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
            menuStage.setScene(new Scene(root, 1910, 1000));
            menuStage.setTitle("SKY ROUTE TRAVEL");
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
            menuStage.setTitle("Wycieczki");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void LastMinuteOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) LastMinute.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiLastMinuteMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.setTitle("Wycieczki - Last Minute");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void PromocjaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) Promocja.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiPromocjeMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.setTitle("Wycieczki - Promocja");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void EgzotykaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) Egzotyka.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiEgzotykaMenuKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820,980));
            menuStage.setTitle("Wycieczki - Egzotyka");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void szukajWycieczki() {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/TabelkaSzukaj.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1053,526));
            menuStage.setTitle("Wynik wyszukiwania");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
        WyswietlWycieczki();
    }

    public void zalozKonto() {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/RejestracjaKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 440,400));
            menuStage.setTitle("Rejestracja");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
