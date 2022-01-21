package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import static java.lang.StrictMath.round;

public class KontrolerRezeracjiKlient implements Initializable {
    PreparedStatement pst = null;

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,Float> cena;

    @FXML
    private TextField cenaR;
    @FXML
    private TextField ilD;
    @FXML
    private TextField idK;
    @FXML
    private TextField idWyc;
    @FXML
    private TextField iloscDR;
    @FXML
    private TextField iloscOR;
    @FXML
    private TextField calosc;
    @FXML
    private CheckBox podstawowePR;
    @FXML
    private CheckBox podstawoweR;
    @FXML
    private CheckBox premR;
    @FXML
    private CheckBox bzrSR;
    @FXML
    private CheckBox bzrPR;
    @FXML
    private CheckBox ukrCR;
    @FXML
    private CheckBox dmtR;
    @FXML
    private CheckBox ukrR;
    @FXML
    private CheckBox upsR;
    @FXML
    private CheckBox bzrKR;
    @FXML
    private CheckBox plmR;
    @FXML
    private CheckBox wyborPrem;
    @FXML
    private Label cenaPrem;
    @FXML
    private ChoiceBox<String> rodzajPR;
    @FXML
    private ChoiceBox<String> terminR;
    @FXML
    private Button kwota;
    @FXML
    private Button anuluj;
    @FXML
    private Button zaplac;

    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void WyswietlRezrwacje() throws SQLException, IOException {
        final ObservableList WczTab = FXCollections.observableArrayList();
        Statement statement = connectDB.createStatement();

        String dane="SELECT id_klienta FROM zalogowany;";
        ResultSet queryResult = statement.executeQuery(dane);
        int idZal=0;
        while (queryResult.next()) {
            idZal = queryResult.getInt("id_klienta");
        }
        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String danee = "SELECT wk.TwojaCena, w.iloscDni,wk.id,wk.cenaPrem, wk.premium, wk.id_wycieczki FROM wycieczki w , wycieczki_klient wk where wk.id_klienta='"+idZal+"'AND w.id_wycieczki=wk.id_wycieczki";
        float cena=0,cenaPr=0;
        int ilDni=0,id=0, idWcie=0;
        String prem=null;
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {

                 cena= rs.getFloat("TwojaCena");
                 ilDni=rs.getInt("iloscDni");
                 id=rs.getInt("id");
                 cenaPr=rs.getFloat("cenaPrem");
                 prem=rs.getString("premium");
                 idWcie=rs.getInt("wk.id_wycieczki");
            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        cenaR.setText(String.valueOf(cena));
        ilD.setText(String.valueOf(ilDni));
        idK.setText(String.valueOf(id));
        idWyc.setText(String.valueOf(idWcie));

        if(!prem.equals("")){
            cenaPrem.setText(String.valueOf(cenaPr));
            wyborPrem.setSelected(true);
        }else if(prem.equals("")){
            cenaPrem.setText(String.valueOf(cenaPr));
            wyborPrem.setSelected(false);
        }
    }

    @FXML
    void wyliczCene() {

        if(!iloscDR.getText().isEmpty() && !iloscOR.getText().isEmpty()) {

            float ilDn=Float.parseFloat(ilD.getText());
            float cenaU=0;
            float cena = Float.parseFloat(cenaR.getText());
            int ilDz = Integer.parseInt(iloscDR.getText());
            int ilDor = Integer.parseInt(iloscOR.getText());
            float cnPr=Float.parseFloat(cenaPrem.getText());
            float calaW = (float) (cena * ilDz * 0.5 + cena * ilDor);
            calaW *= 100;
            calaW = round(calaW);
            calaW /= 100;

            if(podstawoweR.isSelected() && !podstawowePR.isSelected() && !premR.isSelected()){
                cenaU= (float) (ilDn*40*ilDz*0.5+ilDn*40*ilDor);
                cenaU *= 100;
                cenaU = round(cenaU);
                cenaU /= 100;
            }
            else if(podstawowePR.isSelected() &&!podstawoweR.isSelected() && !premR.isSelected() ){
                cenaU= (float) (ilDn*60*ilDz*0.5+ilDn*60*ilDor);
                cenaU *= 100;
                cenaU = round(cenaU);
                cenaU /= 100;
            }
            else if(premR.isSelected() && !podstawoweR.isSelected() && !podstawowePR.isSelected()){
                cenaU= (float) (ilDn*80*ilDz*0.5+ilDn*80*ilDor);
                cenaU *= 100;
                cenaU = round(cenaU);
                cenaU /= 100;
            } else JOptionPane.showMessageDialog(null,"Wybierz jeden rodzaj ubezpiczenia!");

            int cenaPremium=0;
            if(bzrSR.isSelected() && !bzrKR.isSelected() && !bzrPR.isSelected() ){
                cenaPremium+=150;
            }else if(bzrKR.isSelected() && !bzrSR.isSelected() && !bzrPR.isSelected() ){
                cenaPremium+=300;
            }else if(bzrPR.isSelected() && !bzrSR.isSelected()&& !bzrKR.isSelected()){
                cenaPremium+=400;
            }else if(bzrSR.isSelected() && bzrPR.isSelected() && bzrKR.isSelected()){
                JOptionPane.showMessageDialog(null,"Wybierz jeden rodzaj zmiany rezerwacji!");
            }

            if(upsR.isSelected()){
                cenaPremium+=50;
            }

            if(ukrR.isSelected() && !ukrCR.isSelected()){
                cenaPremium+=cena*0.04;
                cenaPremium *= 100;
                cenaPremium = round(cenaPremium);
                cenaPremium /= 100;
            }else if(ukrCR.isSelected() && !ukrR.isSelected()){
                cenaPremium+=cena*0.06;
                cenaPremium *= 100;
                cenaPremium = round(cenaPremium);
                cenaPremium /= 100;
            }else if(ukrCR.isSelected() && ukrR.isSelected()){
                JOptionPane.showMessageDialog(null,"Wybierz jeden rodzaj ubezpieczenia od rezygnacji!");
            }

            if(dmtR.isSelected()){
                cenaPremium+=30*ilDor+30*0.5*ilDz;
            }

            if(plmR.isSelected()){
                cenaPremium+=25;
            }

            if(wyborPrem.isSelected()){
                cenaPremium+=cnPr;
            }

            float calaCena = calaW+cenaU+cenaPremium;
            calaCena *= 100;
            calaCena = round(calaCena);
            calaCena /= 100;

            calosc.setText(String.valueOf(calaCena));
        }
        else JOptionPane.showMessageDialog(null,"Uzupełnij pola: Ilość dzieci, Ilość Osób!");
    }

@FXML
void anulujRezerwacje() throws SQLException {
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    String danee="DELETE FROM wycieczki_klient  WHERE id='"+idK.getText()+"'";
    try {
        pst=(PreparedStatement) connectDB.prepareStatement(danee);

        pst.execute();
    } catch (Exception e) {
        System.out.println("Blad przy usuwaniu!  "+e);
    }
    Stage stage = (Stage) anuluj.getScene().getWindow();
    stage.close();
}

@FXML
void zaplac() throws SQLException {
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    Statement statement = connectDB.createStatement();

    String daneID="SELECT id_rezerwacji FROM rezerwacje ORDER BY id_rezerwacji ASC;";
    ResultSet queryResult = statement.executeQuery(daneID);
    int idWK=0;
    while (queryResult.next()) {
        idWK = queryResult.getInt("id_rezerwacji");
    }

    Statement statement2 = connectDB.createStatement();
    String daneKl="SELECT id_klienta FROM zalogowany;";
    ResultSet queryResult2 = statement2.executeQuery(daneKl);
    int idZal=0;
    while (queryResult2.next()) {
        idZal = queryResult2.getInt("id_klienta");
    }

    String daneRez="INSERT INTO rezerwacje (id_rezerwacji,id_klienta,id_wycieczki,termin, cena,ileDoroslych, ileDzieci,id_platnosc,statusPotwierdzenia) VALUES (?,?,?,?,?,?,?,?,?)";
    try{
        pst=(PreparedStatement) connectDB.prepareStatement(daneRez);
        pst.setString(1, String.valueOf(idWK+1));
        pst.setString(2,String.valueOf(idZal));
        pst.setString(3, String.valueOf(idWyc.getText()));
        pst.setString(4, String.valueOf(terminR.getValue()));
        pst.setString(5,calosc.getText());
        pst.setString(6,iloscOR.getText());
        pst.setString(7,iloscDR.getText());

        if(rodzajPR.getValue().equals("Blik")){
            pst.setString(8,"1");
        }else  if(rodzajPR.getValue().equals("Karta kredytowa")){
            pst.setString(8,"2");
        }else if(rodzajPR.getValue().equals("Przelew")){
            pst.setString(8,"3");
        }else if(rodzajPR.getValue().equals("PayPal")){
            pst.setString(8,"4");
        }
        pst.setString(9,"Niezaakceptowana");
        pst.execute();
        JOptionPane.showMessageDialog(null,"Zarezerwowano wycieczkę!");
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,"Błędne dane! "+e);
    }
    }

    private void ChoiceBoxrodzajPR(){
        ObservableList rodzajPlatnosci= FXCollections.observableArrayList();
        String a="Rodzaj płatności";

        String b="Blik";
        String c="Karta kredytowa";
        String d="Przelew";
        String e="PayPal";

       rodzajPlatnosci.addAll(b,c,d,e);
       rodzajPR.getItems().addAll(rodzajPlatnosci);
       rodzajPR.setValue(a);
    }

    private void ChoiceBoxrodzajTR(){
        ObservableList rodzajTermin= FXCollections.observableArrayList();
        Random rD=new Random();
        int dzien= rD.nextInt(28)+1;
        int miesiac= rD.nextInt(12)+1;
        String a="Terminy";

        String b=2022+"-"+miesiac+"-"+dzien;
        int dzien1= rD.nextInt(28)+1;
        int miesiac1= rD.nextInt(12)+1;
        String c=2022+"-"+miesiac1+"-"+dzien1;
        int dzien2= rD.nextInt(28)+1;
        int miesiac2= rD.nextInt(12)+1;
        String d=2022+"-"+miesiac2+"-"+dzien2;
        int dzien3= rD.nextInt(28)+1;
        int miesiac3= rD.nextInt(12)+1;
        String e=2022+"-"+miesiac3+"-"+dzien3;

        rodzajTermin.addAll(b,c,d,e);
        terminR.getItems().addAll(rodzajTermin);
        terminR.setValue(a);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBoxrodzajPR();
        ChoiceBoxrodzajTR();
        try {
            WyswietlRezrwacje();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

