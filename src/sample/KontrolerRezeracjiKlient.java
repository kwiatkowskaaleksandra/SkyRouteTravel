package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
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
    private ChoiceBox<String> rodzajPR;
    @FXML
    private ChoiceBox<String> terminR;
    @FXML
    private Button kwota;
    @FXML
    private Button anuluj;

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

        String danee = "SELECT * FROM wycieczki w , wycieczki_klient wk where wk.id_klienta='"+idZal+"'AND w.id_wycieczki=wk.id_wycieczki";
        float cena=0;
        int ilDni=0,id=0;
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

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        cenaR.setText(String.valueOf(cena));
        ilD.setText(String.valueOf(ilDni));
        idK.setText(String.valueOf(id));

    }

    @FXML
    void wyliczCene() {

        if(!iloscDR.getText().isEmpty() && !iloscOR.getText().isEmpty()) {

            float ilDn=Float.parseFloat(ilD.getText());
            float cenaU=0;
            float cena = Float.parseFloat(cenaR.getText());
            int ilDz = Integer.parseInt(iloscDR.getText());
            int ilDor = Integer.parseInt(iloscOR.getText());
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

            int cenaPrem=0;
            if(bzrSR.isSelected() && !bzrKR.isSelected() && !bzrPR.isSelected() ){
                cenaPrem+=150;
            }else if(bzrKR.isSelected() && !bzrSR.isSelected() && !bzrPR.isSelected() ){
                cenaPrem+=300;
            }else if(bzrPR.isSelected() && !bzrSR.isSelected()&& !bzrKR.isSelected()){
                cenaPrem+=400;
            }else if(bzrSR.isSelected() && bzrPR.isSelected() && bzrKR.isSelected()){
                JOptionPane.showMessageDialog(null,"Wybierz jeden rodzaj zmiany rezerwacji!");
            }

            if(upsR.isSelected()){
                cenaPrem+=50;
            }

            if(ukrR.isSelected() && !ukrCR.isSelected()){
                cenaPrem+=cena*0.04;
                cenaPrem *= 100;
                cenaPrem = round(cenaPrem);
                cenaPrem /= 100;
            }else if(ukrCR.isSelected() && !ukrR.isSelected()){
                cenaPrem+=cena*0.06;
                cenaPrem *= 100;
                cenaPrem = round(cenaPrem);
                cenaPrem /= 100;
            }else if(ukrCR.isSelected() && ukrR.isSelected()){
                JOptionPane.showMessageDialog(null,"Wybierz jeden rodzaj ubezpieczenia od rezygnacji!");
            }

            if(dmtR.isSelected()){
                cenaPrem+=30*ilDor+30*0.5*ilDz;
            }

            if(plmR.isSelected()){
                cenaPrem+=25;
            }
            float calaCena = calaW+cenaU+cenaPrem;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChoiceBoxrodzajPR();
        try {
            WyswietlRezrwacje();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

