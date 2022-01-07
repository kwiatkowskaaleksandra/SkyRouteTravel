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
    private Button ZamknijButton;

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

        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium,w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w join zakwaterowanie z on w.id_wycieczki=z.id_zakwaterowanie join transport t on w.id_wycieczki=t.id_transport";

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
            menuStage.setScene(new Scene(root, 1720.0D, 880.0D));
            menuStage.show();
        } catch (Exception e) {
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
        this.TextK11.setText(this.nzw.getCellData(this.index));
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
        String dane = "SELECT rodzajWycieczki, iloscDni FROM wycieczki WHERE id_wycieczki='" + id + "'";

        ResultSet wynik=stat.executeQuery(dane);
        while (wynik.next()){

            if (wynik.getString("rodzajWycieczki").equals("Last Minute")) {
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.05);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;
                this.TextK9.setText(String.valueOf(nowaCena));
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
            else  if (wynik.getString("rodzajWycieczki").equals("Egzotyka")) {
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.02);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;
                this.TextK9.setText(String.valueOf(nowaCena));
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
            else  if (wynik.getString("rodzajWycieczki").equals("Promocja")) {
                float cena2 = this.cn.getCellData(this.index);
                double nowaCena = cena2 - (cena2 * 0.30);
                nowaCena *= 100;
                nowaCena = round(nowaCena);
                nowaCena /= 100;
                this.TextK9.setText(String.valueOf(nowaCena));
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
        ChoiceBoxWycieczki();
        WyswietlWycieczki();
    }
    }
