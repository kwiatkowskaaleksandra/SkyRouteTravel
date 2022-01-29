package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class kontrolerSzukanieKlient extends KontrolerKlient {

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

    public Button WycieczkiKlient;
    KontrolerMenu kontrolerMenu=new KontrolerMenu();
    String m=kontrolerMenu.miejsce;

    ObservableList<DaneDoSzukania> szukanie = FXCollections.observableArrayList();
    final ObservableList WczTab = FXCollections.observableArrayList();
    private Object DaneDoSzukania;
    String danee=null;

    public void WyswietlWycieczki() {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        if (!nazwaK.isEmpty()) {
            if (!miejsceK.isEmpty()) {
                if (!transposrtK.isEmpty()) {
                    if (!zakwaterowanieK.isEmpty()) {
                        danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                                "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%' AND miejsce LIKE '" + miejsceK + "%' AND t.rodzaj LIKE '" + transposrtK + "%' AND z.rodzaj LIKE '" + zakwaterowanieK + "%';";
                    } else {
                        danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                                "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%' AND miejsce LIKE '" + miejsceK + "%' AND t.rodzaj LIKE '" + transposrtK + "%';";
                    }
                } else {
                    danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                            "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%' AND miejsce LIKE '" + miejsceK + "%';";
                }
            } else if(!transposrtK.isEmpty()){
                if (!zakwaterowanieK.isEmpty()) {
                    danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                            "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%' AND  t.rodzaj LIKE '" + transposrtK + "%' AND z.rodzaj LIKE '" + zakwaterowanieK + "%';";
                } else {
                    danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                            "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%' AND  t.rodzaj LIKE '" + transposrtK + "%';";
                }
            }else if(!zakwaterowanieK.isEmpty()){
                danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                        "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%' AND z.rodzaj LIKE '" + zakwaterowanieK + "%';";
            }else{
                danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                        "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND nazwa LIKE '" + nazwaK + "%';";
            }
        }else if(!miejsceK.isEmpty()){
            if(!transposrtK.isEmpty()){
                if (!zakwaterowanieK.isEmpty()) {
                    danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                            "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND miejsce LIKE '" + miejsceK + "%' AND  t.rodzaj LIKE '" + transposrtK + "%' AND z.rodzaj LIKE '" + zakwaterowanieK + "%';";
                } else {
                    danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                            "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND miejsce LIKE '" + miejsceK + "%' AND  t.rodzaj LIKE '" + transposrtK + "%';";
                }
            }else if (!zakwaterowanieK.isEmpty()) {
                danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                        "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND miejsce LIKE '" + miejsceK + "%' AND  z.rodzaj LIKE '" + zakwaterowanieK + "%';";
            }else {
                danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                        "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND miejsce LIKE '" + miejsceK + "%';";
            }
        }else if(!transposrtK.isEmpty()){
            if (!zakwaterowanieK.isEmpty()) {
                danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                        "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND t.rodzaj LIKE '" + transposrtK + "%' AND  z.rodzaj LIKE '" + zakwaterowanieK + "%';";
            }else{
                danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                        "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND t.rodzaj LIKE '" + transposrtK + "%';";
            }
        }else if(!zakwaterowanieK.isEmpty()){
            danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                    "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie AND z.rodzaj LIKE '" + zakwaterowanieK + "%';";
        }else{
            danee = "SELECT w.id_wycieczki,w.nazwa,w.miejsce,w.cena,t.rodzaj,w.czas,z.rodzaj,w.wyzywienie,w.premium,w.cenaPrem, w.atrakcje,w.rodzajWycieczki,w.iloscDni FROM wycieczki w, transport t , zakwaterowanie z " +
                    "WHERE t.id_transport=w.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie ORDER BY w.id_wycieczki ASC";
        }

        Statement st = null;
        try {
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
                String transport = rs.getString("t.rodzaj");
                String czasPodrozy = rs.getString("czas");
                String zakwaterowanie = rs.getString("z.rodzaj");
                String wyzywienie = rs.getString("wyzywienie");
                String atrakcje = rs.getString("atrakcje");
                String premium = rs.getString("premium");
                float cenaP = rs.getFloat("w.cenaPrem");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                daneDoSzukania = new DaneDoSzukania(id1, nazwa, miejsce, cena, transport, czasPodrozy, zakwaterowanie, wyzywienie, premium, cenaP, atrakcje, rodzaj, iloscDni);
                WczTab.add(daneDoSzukania);

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
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WyswietlWycieczki();
    }
}
