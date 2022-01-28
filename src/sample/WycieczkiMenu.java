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

import javax.swing.*;
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
    private TableColumn<DaneDoWycieczek,String> nzw;
    @FXML
    private TableColumn<DaneDoWycieczek,String> ms;
    @FXML
    private TableColumn<DaneDoWycieczek,String> zak;
    @FXML
    private TableColumn<DaneDoWycieczek,String> rdw;
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
    private Button ZarezerwujButton;

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
    private TextField TextK11;
    @FXML
    private TextField TextK12;

    @FXML
    private Label label;
    @FXML
    private Label rodzaj;
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
    float cena;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void zalogujButtonOnAction(javafx.event.ActionEvent event)
    {


        if(!login.getText().isBlank() && !haslo.getText().isBlank())
        { String dane2="DELETE  FROM wycieczki_klient WHERE id>0;";
            try {
                PreparedStatement pst2=null;
                pst2=(PreparedStatement) connectDB.prepareStatement(dane2);
                pst2.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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

        String verifyLoginPr = "SELECT id_pracownika,login , haslo FROM pracownik WHERE login = '" +login.getText() +"'AND haslo = '" + haslo.getText() + "'" ;
        String verifyLoginKl = "SELECT id_klienta,login , haslo FROM klient WHERE login = '" +login.getText() +"'AND haslo = '" + haslo.getText() + "'" ;

        String dane="INSERT INTO zalogowany(id_zalogowanego,id_klienta,id_pracownika,login,haslo)values(?,?,?,?,?)";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginPr);

            while(queryResult.next())
            {
                if(queryResult.getString("login").equals(login.getText()) && queryResult.getString("haslo").equals(haslo.getText()) ) {
                    if (login.getText().equals("admin")) {
                        Stage stage = (Stage) zalogujButton.getScene().getWindow();
                        KontrolerMenu kontrolerMenu=new KontrolerMenu();
                        kontrolerMenu.KontrolerAdministrator();
                        stage.close();
                    }
                    else {
                        String id = queryResult.getString("id_pracownika");
                        try {
                            PreparedStatement pst = null;
                            pst = (PreparedStatement) connectDB.prepareStatement(dane);
                            pst.setString(1, "1");
                            pst.setString(2, null);
                            pst.setString(3, id);
                            pst.setString(4, login.getText());
                            pst.setString(5, haslo.getText());
                            pst.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                            e.getCause();
                        }
                        label.setText("Udalo sie zalogowac!");
                        KontrolerMenu kontrolerMenu=new KontrolerMenu();
                        kontrolerMenu.KontrolerPracownik();
                        Stage stage = (Stage) zalogujButton.getScene().getWindow();
                        stage.close();
                    }
                }
                else {
                    Stage stage = (Stage) zalogujButton.getScene().getWindow();
                    label.setText("Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                    JOptionPane.showMessageDialog(null,"Nieprawidlowe logowanie! Prosze sprobowac ponownie");
                }
            }
            if(queryResult.next()==false ){
                label.setText("Błędny login lub hasło.");
            }
            ResultSet queryResultKl = statement.executeQuery(verifyLoginKl);
            while (queryResultKl.next()) {

                if (queryResultKl.getString("login").equals(login.getText()) && queryResultKl.getString("haslo").equals(haslo.getText())) {
                    String id = queryResultKl.getString("id_klienta");
                    try {
                        PreparedStatement pst = null;
                        pst = (PreparedStatement) connectDB.prepareStatement(dane);
                        pst.setString(1, "1");
                        pst.setString(2, id);
                        pst.setString(3, null);
                        pst.setString(4, login.getText());
                        pst.setString(5, haslo.getText());
                        pst.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                    label.setText("Udalo sie zalogowac!");
                    KontrolerMenu kontrolerMenu=new KontrolerMenu();
                    kontrolerMenu.KontrolerKlient();
                    Stage stage = (Stage) zalogujButton.getScene().getWindow();
                    stage.close();
                }
            }
            if(queryResultKl.next()==false ){
                label.setText("Błędny login lub hasło.");
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

        String rodz=null;
        if(rodzaj.getText().equals("PROMOCJE")){
            rodz="Promocja";
        }else if(rodzaj.getText().equals("LAST MINUTE")){
            rodz="Last Minute";
        }else if(rodzaj.getText().equals("EGZOTYKA")){
            rodz="EGZOTYKA";
        }else if(rodzaj.getText().equals("OFERTY WYCIECZEK")){
            rodz="Promocja' OR w.rodzajWycieczki='Last Minute' OR w.rodzajWycieczki='Egzotyka";
        }

        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj as transport,w.czas,z.rodzaj as zakwaterowanie,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni, zdjecie FROM wycieczki w join zakwaterowanie z on w.id_zakwaterowanie=z.id_zakwaterowanie join transport t on w.id_transport=t.id_transport where w.rodzajWycieczki='"+rodz+"' ORDER BY id_wycieczki ASC";

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
                float cenaPrem=rs.getFloat("cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                String zdj=rs.getString("zdjecie");
                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaPrem,atrakcje,rodzaj,iloscDni,zdj);
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/Home.fxml"));
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

    public void WycieczkiKlient(){
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/WycieczkiKlient.fxml"));
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
            root = FXMLLoader.load(getClass().getResource("../javaFX/pracownicy/OfertyWycieczek.fxml"));
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
        this.TextK11.setText(this.nzw.getCellData(this.index));
        this.TextK1.setText(this.ms.getCellData(this.index));
        this.TextK2.setText(this.cn.getCellData(this.index).toString());
        this.TextK3.setText(this.tran.getCellData(this.index));
        this.TextK4.setText(this.czs.getCellData(this.index));
        this.TextK5.setText(this.zak.getCellData(this.index));
        this.TextK6.setText(this.prem.getCellData(this.index));
        this.TextK7.setText(this.atr.getCellData(this.index));
        this.TextK12.setText(this.rdw.getCellData(this.index));
        int id = this.idp.getCellData(this.index);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WyswietlWycieczki();
    }

    public void rezerwuj() {
        Stage stage = (Stage) ZarezerwujButton.getScene().getWindow();
        JOptionPane.showMessageDialog(null,"Aby zarezerwować wycieczkę należy się zalogować. ");
    }
}
