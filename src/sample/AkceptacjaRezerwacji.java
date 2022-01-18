package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AkceptacjaRezerwacji implements Initializable {
    PreparedStatement pst = null;
    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    @FXML
    private TableView<DaneDoRezerwacji> Tab1;
    @FXML
    private TableColumn<DaneDoRezerwacji,Integer> idR;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> nazwisko;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> wycieczka;
    @FXML
    private TableColumn<DaneDoRezerwacji, Date> termin;
    @FXML
    private TableColumn<DaneDoRezerwacji,Integer> dzieci;
    @FXML
    private TableColumn<DaneDoRezerwacji,Integer> dorosli;
    @FXML
    private TableColumn<DaneDoRezerwacji,Float> cena;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> platnosc;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> status;
    @FXML
    private TextField idRezrw;

    public void WyswietlRezerwacje(){
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_rezerwacji,imie, nazwisko,miejsce,termin, ileDoroslych, ileDzieci,r.cena, p.rodzaj, statusPotwierdzenia FROM rezerwacje r, wycieczki w, platnosc p, klient k WHERE r.id_klienta=k.id_klienta AND r.id_platnosc=p.id_platnosc AND r.id_wycieczki=w.id_wycieczki ORDER BY id_rezerwacji ASC";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoRezerwacji daneDoRezerwacji;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id_rezerwacji");
                String im = rs.getString("imie");
                String naz = rs.getString("nazwisko");
                String msc = rs.getString("miejsce");
                Date termin=rs.getDate("termin");
                int ilDor=rs.getInt("ileDoroslych");
                int ilDz=rs.getInt("ileDzieci");
                float cn=rs.getFloat("cena");
                String rodz = rs.getString("rodzaj");
                String stat = rs.getString("statusPotwierdzenia");
                daneDoRezerwacji = new DaneDoRezerwacji(id1,naz+" "+im,msc, (java.sql.Date) termin,ilDor,ilDz,cn,rodz,stat);
                WczTab.add(daneDoRezerwacji);
            }

             st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idR.setCellValueFactory(new PropertyValueFactory<>("id"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        wycieczka.setCellValueFactory(new PropertyValueFactory<>("wycieczka"));
        termin.setCellValueFactory(new PropertyValueFactory<>("termin"));
        dorosli.setCellValueFactory(new PropertyValueFactory<>("ileDoroslych"));
        dzieci.setCellValueFactory(new PropertyValueFactory<>("ileDzieci"));
        cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        platnosc.setCellValueFactory(new PropertyValueFactory<>("platnosc"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Tab1.setItems(WczTab);
    }

    @FXML
    void getSelected(){
        index=Tab1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        idRezrw.setText(String.valueOf(idR.getCellData(index)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WyswietlRezerwacje();
    }

    public void akceptujRezerwacje() {
    String status="Zaakceptowana";
        try{
            String dane="UPDATE rezerwacje SET statusPotwierdzenia='"+status+"'WHERE id_rezerwacji='"+ idRezrw.getText() +"'";
            pst=(PreparedStatement) connectDB.prepareStatement(dane);
            pst.execute();
            WyswietlRezerwacje();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy zmienie statusu! "+e);
        }

    }

    public void odzrucRezerwacje() {
        String status="Odrzucona";
        try{
            String dane="UPDATE rezerwacje SET statusPotwierdzenia='"+status+"'WHERE id_rezerwacji='"+ idRezrw.getText() +"'";
            pst=(PreparedStatement) connectDB.prepareStatement(dane);
            pst.execute();
            WyswietlRezerwacje();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy zmienie statusu! "+e);
        }
    }
}
