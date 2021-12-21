package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.StrictMath.round;

public class WycieczkiMenu implements Initializable {
    PreparedStatement pst = null;

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
    private Button ZamknijButton;
    @FXML
    private Button zalogujButton;

    @FXML
    private TextField TextK1;
    @FXML
    private TextField TextK2;
    @FXML
    private TextField TextK3;
    @FXML
    private TextField TextK4;
    @FXML
    private TextField TextK5;
    @FXML
    private TextField TextK6;
    @FXML
    private TextArea TextK7;





    @FXML
    private Label label;
    @FXML
    private TextField login;

    @FXML
    private PasswordField haslo;
    @FXML
    private TextField TextK9;
    @FXML
    private TextField TextK10;
    @FXML
    private ChoiceBox<String> RodzajUbezpieczenia;

    ObservableList rodzajUbezpieczenia= FXCollections.observableArrayList();
    int index=-1;
    ResultSet rs = null;
    Statement st = null;
    String rodzaj;
    float cena;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void zalogujButtonOnAction(javafx.event.ActionEvent event)
    {

        if(!login.getText().isBlank() && !haslo.getText().isBlank())
        {
            validateLogin();
        }
        else
        {
            label.setText("Wpisz login i haslo");
        }
    }


    public void validateLogin()
    {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT * FROM uzytkownicy WHERE Login = '" +login.getText() +"'AND Haslo = '" + haslo.getText() + "'" ;

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);



            while(queryResult.next())
            {

                System.out.println(queryResult);
                if(queryResult.getString("Login").equals("Klient"))
                {

                    label.setText("Udalo sie zalogowac!");
                     WycieczkiKlient();
                }
                if(queryResult.getString("Login").equals("Pracownik"))
                {
                    label.setText("Udalo sie zalogowac!");
                    Wycieczka();
                }
                if(queryResult.getString("Login").equals("Admin"))
                {
                    label.setText("Udalo sie zalogowac!");
                    WycieczkiKlient();
                }

                else
                {
                    label.setText("Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                }
            }
            Stage stage = (Stage) login.getScene().getWindow();
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

        String danee = "SELECT * FROM wycieczki";

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
                int id1 = rs.getInt("id");
                String miejsce = rs.getString("miejsce");
                float cena = rs.getFloat("cena");
                String transport = rs.getString("transport");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("zakwaterowanie");
                String wyzywienie =rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzaj");
                int iloscDni = rs.getInt("iloscDni");
                daneDoWycieczek = new DaneDoWycieczek(id1, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,atrakcje,rodzaj,iloscDni);
                WczTab.add(daneDoWycieczek);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        ms.setCellValueFactory(new PropertyValueFactory<>("miejsce"));
        cn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tran.setCellValueFactory(new PropertyValueFactory<>("transport"));
        czs.setCellValueFactory(new PropertyValueFactory<>("czasPodrozy"));
        zak.setCellValueFactory(new PropertyValueFactory<>("zakwaterowanie"));
        prem.setCellValueFactory(new PropertyValueFactory<>("premium"));
        atr.setCellValueFactory(new PropertyValueFactory<>("atrakcje"));

        Tab1.setItems(WczTab);

    }



    public void zamknijButtonOnAction() {
        Stage stage = (Stage) ZamknijButton.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1720.0D, 880.0D));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WycieczkiKlient(){
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("WycieczkiKlient.fxml"));
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
    public void Wycieczka(){
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("OfertyWycieczek.fxml"));
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



    private void ChoiceBoxWycieczki(){
        rodzajUbezpieczenia.removeAll(rodzajUbezpieczenia);
        String a="Rodzaj ubezpieczenia";

        String b="Standardowe";
        String c="Optymalne";
        rodzajUbezpieczenia.addAll(b,c);
        RodzajUbezpieczenia.getItems().addAll(rodzajUbezpieczenia);
        RodzajUbezpieczenia.setValue(a);
    }

    public void getSelected() throws SQLException {
        this.index = this.Tab1.getSelectionModel().getSelectedIndex();
        if (this.index <= -1) {
            return;
        }
        this.TextK1.setText(this.ms.getCellData(this.index));
        this.TextK2.setText(this.cn.getCellData(this.index).toString());
        this.TextK3.setText(this.tran.getCellData(this.index));
        this.TextK4.setText(this.czs.getCellData(this.index));
        this.TextK5.setText(this.zak.getCellData(this.index));
        this.TextK6.setText(this.prem.getCellData(this.index));
        this.TextK7.setText(this.atr.getCellData(this.index));
        int id = this.idp.getCellData(this.index);

        Statement stat=null;
        stat=connectDB.createStatement();
        String dane = "SELECT rodzaj, iloscDni FROM wycieczki WHERE id='" + id + "'";

        ResultSet wynik=stat.executeQuery(dane);
        while (wynik.next()){

            if (wynik.getString("rodzaj").equals("Last Minute")) {
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.05);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;

                if(RodzajUbezpieczenia.getValue().equals("Standardowe")){
                    int iloscDni=wynik.getInt("iloscDni");
                    double cenaUbezpieczenia=iloscDni*20.64;
                    cenaUbezpieczenia*=100;
                    cenaUbezpieczenia=round(cenaUbezpieczenia);
                    cenaUbezpieczenia/=100;
                    this.TextK10.setText(String.valueOf(cenaUbezpieczenia));
                }
                else if(RodzajUbezpieczenia.getValue().equals("Optymalne")){
                    int iloscDni=wynik.getInt("iloscDni");
                    double cenaUbezpieczenia=iloscDni*30.98;
                    cenaUbezpieczenia*=100;
                    cenaUbezpieczenia=round(cenaUbezpieczenia);
                    cenaUbezpieczenia/=100;
                    this.TextK10.setText(String.valueOf(cenaUbezpieczenia));
                }
            }
            else  if (wynik.getString("rodzaj").equals("Egzotyka")) {
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.02);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;

                if(RodzajUbezpieczenia.getValue().equals("Standardowe")){
                    int iloscDni=wynik.getInt("iloscDni");
                    double cenaUbezpieczenia=iloscDni*20.64;
                    cenaUbezpieczenia*=100;
                    cenaUbezpieczenia=round(cenaUbezpieczenia);
                    cenaUbezpieczenia/=100;
                    this.TextK10.setText(String.valueOf(cenaUbezpieczenia));
                }
                else if(RodzajUbezpieczenia.getValue().equals("Optymalne")){
                    int iloscDni=wynik.getInt("iloscDni");
                    double cenaUbezpieczenia=iloscDni*30.98;
                    cenaUbezpieczenia*=100;
                    cenaUbezpieczenia=round(cenaUbezpieczenia);
                    cenaUbezpieczenia/=100;
                    this.TextK10.setText(String.valueOf(cenaUbezpieczenia));
                }
            }
            else  if (wynik.getString("rodzaj").equals("Promocja")) {
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.30);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;

                if(RodzajUbezpieczenia.getValue().equals("Standardowe")){
                    int iloscDni=wynik.getInt("iloscDni");
                    double cenaUbezpieczenia=iloscDni*20.64;
                    cenaUbezpieczenia*=100;
                    cenaUbezpieczenia=round(cenaUbezpieczenia);
                    cenaUbezpieczenia/=100;
                    this.TextK10.setText(String.valueOf(cenaUbezpieczenia));
                }
                else if(RodzajUbezpieczenia.getValue().equals("Optymalne")){
                    int iloscDni=wynik.getInt("iloscDni");
                    double cenaUbezpieczenia=iloscDni*30.98;
                    cenaUbezpieczenia*=100;
                    cenaUbezpieczenia=round(cenaUbezpieczenia);
                    cenaUbezpieczenia/=100;
                    this.TextK10.setText(String.valueOf(cenaUbezpieczenia));
                }
            }
        }
        wynik.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WyswietlWycieczki();
    }
    }
