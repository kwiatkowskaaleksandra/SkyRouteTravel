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
import static java.lang.StrictMath.round;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class WycieczkiKlient implements Initializable {
    PreparedStatement pst = null;

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
    private TableColumn<DaneDoWycieczek,Float> cenaprem;
    @FXML
    private Button ZamknijButton;
    @FXML
    private Button ZarewerwujButton;

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
    private TextField TextK9;
    @FXML
    private TextField TextK10;
    @FXML
    private TextField TextK11;
    @FXML
    private TextField cenaPr;
    @FXML
    private CheckBox wyborPrem;
    @FXML
    private Label rodz;

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



    public void WyswietlWycieczki(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        final ObservableList WczTab = FXCollections.observableArrayList();

        String rod=null;
        if(rodz.getText().equals("PROMOCJE")){
            rod="Promocja";
        }else if(rodz.getText().equals("LAST MINUTE")){
            rod="Last Minute";
        }else if(rodz.getText().equals("EGZOTYKA")){
            rod="EGZOTYKA";
        }else if(rodz.getText().equals("OFERTY WYCIECZEK")){
            rod="Promocja' OR w.rodzajWycieczki='Last Minute' OR w.rodzajWycieczki='Egzotyka";
        }

        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium, w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni, zdjecie FROM wycieczki w join zakwaterowanie z on w.id_zakwaterowanie=z.id_zakwaterowanie join transport t on w.id_transport=t.id_transport  where w.rodzajWycieczki='"+rod+"' ORDER BY id_wycieczki ASC";

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
                float cena = rs.getFloat("w.cena");
                String transport = rs.getString("transport");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("zakwaterowanie");
                String wyzywienie=rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                float cenaP=rs.getFloat("w.cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                String zdj= rs.getString("zdjecie");
                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaP,atrakcje,rodzaj,iloscDni,zdj);
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
        cenaprem.setCellValueFactory(new PropertyValueFactory<>("cenaP"));

        Tab1.setItems(WczTab);

    }



    public void zamknijButtonOnAction() {
        Stage stage = (Stage) ZamknijButton.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Klient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1910, 1000));
            menuStage.setTitle("SKY ROUTE TRAVEL");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void ZarezerwujButtonOnAction() throws SQLException {
        Stage stage = (Stage) ZamknijButton.getScene().getWindow();
       // stage.close();

        int id = this.idp.getCellData(this.index);
        Statement statement = connectDB.createStatement();
        String daneKl="SELECT id_klienta FROM zalogowany;";
        ResultSet queryResult = statement.executeQuery(daneKl);
        int idZal=0;
        while (queryResult.next()) {
            idZal = queryResult.getInt("id_klienta");
        }

        Statement statement2 = connectDB.createStatement();
        String daneID="SELECT id FROM wycieczki_klient;";
        ResultSet queryResult2 = statement.executeQuery(daneID);
        int idWK=0;
        while (queryResult2.next()) {
            idWK = queryResult2.getInt("id");
        }

        String danew = "INSERT INTO wycieczki_klient(id,id_klienta,id_wycieczki,TwojaCena,status,premium, cenaPrem)values(?,?,?,?,?,?,?)";
        pst = (PreparedStatement) connectDB.prepareStatement(danew);
        pst.setString(1, String.valueOf(idWK+1));
        pst.setString(2, String.valueOf(idZal));
        pst.setString(3, String.valueOf(id));
        pst.setString(4, TextK9.getText());
        pst.setString(5, "Nie");

        if(wyborPrem.isSelected()) {
            pst.setString(6, TextK6.getText());
        }else {
            pst.setString(6, "");
        }
        pst.setString(7, cenaPr.getText());
        pst.execute();
        queryResult.close();
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/RezerwacjaKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 600, 800.0D));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void getSelected() throws SQLException {
        this.index = this.Tab1.getSelectionModel().getSelectedIndex();
        if (this.index <= -1) {
            return;
        }
        this.TextK11.setText(this.nzw.getCellData(this.index));
        this.TextK1.setText(this.ms.getCellData(this.index));
        this.TextK2.setText(this.cn.getCellData(this.index).toString());
        this.TextK3.setText(this.tran.getCellData(this.index));
        this.TextK4.setText(this.czs.getCellData(this.index));
        this.TextK5.setText(this.zak.getCellData(this.index));
        this.TextK6.setText(this.prem.getCellData(this.index));
        this.TextK7.setText(this.atr.getCellData(this.index));
        this.cenaPr.setText(this.cenaprem.getCellData(this.index).toString());
        int id = this.idp.getCellData(this.index);
        Statement stat2=null;
        stat2=connectDB.createStatement();
        int liczba = 0;
        String daneK ="SELECT wk.id_klienta FROM zalogowany z , wycieczki_klient wk where z.id_klienta=wk.id_klienta";
        ResultSet wynikK=stat2.executeQuery(daneK);
        while (wynikK.next()){
            liczba+=1;
        }

        Statement stat=null;
        stat=connectDB.createStatement();
        String dane = "SELECT rodzajWycieczki, iloscDni FROM wycieczki WHERE id_wycieczki='" + id + "'";

        ResultSet wynik=stat.executeQuery(dane);
        while (wynik.next()){

            if (wynik.getString("rodzajWycieczki").equals("Last Minute")) {
                if(liczba>=10){
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.15);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;
                this.TextK9.setText(String.valueOf(nowaCena));
                }
                else if(liczba<10 && liczba>=5) {
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.07);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }
                else if (liczba<5 || liczba==0){
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.02);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }

            }

            else  if (wynik.getString("rodzajWycieczki").equals("Egzotyka")) {
                if(liczba>10){
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.15);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }
                else if(liczba<10 && liczba>5) {
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.07);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }
                else if (liczba<5){
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.02);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }
            }
            else  if (wynik.getString("rodzajWycieczki").equals("Promocja")) {
                if(liczba>10){
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.15);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }
                else if(liczba<10 && liczba>5) {
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.07);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
                }
                else if (liczba<5){
                    float cena2 = this.cn.getCellData(this.index);
                    double nowaCena = cena2 - (cena2 * 0.02);
                    nowaCena *= 100;
                    nowaCena = round(nowaCena);
                    nowaCena /= 100;
                    this.TextK9.setText(String.valueOf(nowaCena));
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
