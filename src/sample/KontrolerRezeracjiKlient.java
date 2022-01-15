package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class KontrolerRezeracjiKlient implements Initializable {
    PreparedStatement pst = null;

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,Float> cena;

    @FXML
    private TextField cenaR;
    @FXML
    private TextField iloscDR;
    @FXML
    private TextField iloscOR;
    @FXML
    private TextField calosc;
    @FXML
    private CheckBox porstawowePR;
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
    private ChoiceBox<String> termin;

    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void WyswietlWiadomosci() throws SQLException, IOException {
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
        DaneDoWycieczek daneDoWycieczek;

        String danee = "SELECT cena FROM wycieczki ";

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
                float cenaP=rs.getFloat("cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
cenaR.setText(cena);
                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaP,atrakcje,rodzaj,iloscDni);
                WczTab.add(daneDoWycieczek);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        Tab1.setItems(WczTab);

    }



/*
    private void ChoiceBoxrodzajPR(){

        String a="Rodzaj płatności";

        String b="Blik";
        String c="PayPal";
        String d="Karta kredytowa";
        String e="Przelwe";

        ChoiceBox choiceBox = new ChoiceBox();

        choiceBox.getItems().add("Rodzaj płatności 1");
        choiceBox.getItems().add("Blik 2");
        choiceBox.getItems().add("PayPal 3");
        choiceBox.getItems().add("Karta 4");
        choiceBox.getItems().add("Przelwe 5");

    }

 */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ChoiceBoxrodzajPR();

    }
}

