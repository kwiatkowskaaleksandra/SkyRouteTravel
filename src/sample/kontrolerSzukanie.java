package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;


public class kontrolerSzukanie extends KontrolerMenu {

    @FXML
    private TableView<sample.DaneDoSzukania> Tab1;
    @FXML
    private TableColumn<DaneDoSzukania,Integer> idk;
    @FXML
    private TableColumn<DaneDoSzukania,String> nzw;
    @FXML
    private TableColumn<DaneDoSzukania,String> ms;
    @FXML
    private TableColumn<DaneDoSzukania,String> rdw;
    @FXML
    private TableColumn<DaneDoSzukania,Integer> zak;
    @FXML
    private TableColumn<DaneDoSzukania,Integer> tran;
    @FXML
    private TableColumn<DaneDoSzukania,String> atr;
    @FXML
    private TableColumn<DaneDoSzukania,String> prem;
    @FXML
    private TableColumn<DaneDoSzukania,String> czs;
    @FXML
    private TableColumn<DaneDoSzukania,Float> cn;
    @FXML
    private TableColumn<DaneDoSzukania, ImageView> obr;
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

    ObservableList<DaneDoSzukania> szukanie = FXCollections.observableArrayList();
    final ObservableList WczTab = FXCollections.observableArrayList();
    private Object DaneDoSzukania;

    public void WyswietlWycieczki(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();





        String danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,w.id_transport,w.czas,id_zakwaterowanie,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w ";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoSzukania daneDoSzukania;

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
                int transport = rs.getInt("id_transport");
                String czasPodrozy =rs.getString("czas");
                int zakwaterowanie =rs.getInt("id_zakwaterowanie");
                String wyzywienie =rs.getString("wyzywienie");
                String atrakcje =rs.getString("atrakcje");
                String premium =rs.getString("premium");
                float cenaP = rs.getFloat("w.cenaPrem");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                daneDoSzukania = new DaneDoSzukania(id1, nazwa,miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaP,atrakcje,rodzaj,iloscDni);
                WczTab.add(DaneDoSzukania);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idk.setCellValueFactory(new PropertyValueFactory<>("id"));
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
/*
        FilteredList<DaneDoSzukania> filtrowanie = new FilteredList<>(WczTab, b -> true);
        this.IdKraj.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoSzukania->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoSzukania.getNazwa().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        this.IdMiejsce.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoSzukania->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoSzukania.getMiejsce().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        this.IdZakwaterowanie.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoSzukania->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoSzukania.getZakwaterowanie().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        this.IdTransport.textProperty().addListener(((observable, oldValue, newValue)->{

            filtrowanie.setPredicate(DaneDoSzukania->{
                if(newValue.isEmpty() || newValue.isBlank()|| newValue==null)
                {
                    return true;
                }
                String szukaneSlowo = newValue.toLowerCase();
                if(DaneDoSzukania.getTransport().toLowerCase().indexOf(szukaneSlowo) > -1)
                {
                    return true;
                }else
                    return false;


            });

        }));
        SortedList<DaneDoSzukania> posortowane = new SortedList<>(filtrowanie);
        posortowane.comparatorProperty().bind(Tab1.comparatorProperty());
        this.Tab1.setItems(posortowane);
*/
    }
/*
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
        DaneDoSzukania daneDoSzukania;

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
                String transport = rs.getString("id_transport");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("id_zakwaterowanie");
                String wyzywienie=rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                float cenaPrem=rs.getFloat("cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                daneDoSzukania = new DaneDoSzukania(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaPrem,atrakcje,rodzaj,iloscDni);
                WczTab.add(DaneDoSzukania);
            }

            st.close();
        } catch (Exception var16) {
            System.out.println("Wyjątek!.");
            System.out.println(var16.getMessage());
        }

        idk.setCellValueFactory(new PropertyValueFactory<>("id"));
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
        FilteredList<DaneDoSzukania> wyszukiwanie = new FilteredList(WczTab, (b) -> {
            return true;
        });
        this.IdMiejsce.textProperty().addListener((observable, oldValue, newValue) -> {
            wyszukiwanie.setPredicate((DaneDoSzukania) -> {
                if (newValue != null && !newValue.isEmpty()) {
                    String literki = newValue.toLowerCase();
                    if (DaneDoSzukania.getMiejsce().toLowerCase().contains(literki)) {
                        return true;
                    } else {
                        return DaneDoSzukania.getMiejsce().toLowerCase().contains(literki) ? true : String.valueOf(DaneDoSzukania.getTransport()).contains(literki);
                    }
                } else {
                    return true;
                }
            });
        });
        SortedList<DaneDoSzukania> posortowane = new SortedList(wyszukiwanie);
        posortowane.comparatorProperty().bind(this.Tab1.comparatorProperty());
        this.Tab1.setItems(posortowane);
    }
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WyswietlWycieczki();
        //wyszukaj();
    }

}
