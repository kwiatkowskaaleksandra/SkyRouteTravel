package sample;/*

 * @project BiuroPodróży.iml
 * @author kola
 */



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DaneDoRezerwacji;
import sample.Poloczenie;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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
    @FXML
    private Button zam;

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

    public void akceptujRezerwacje() throws SQLException {
    String status="Zaakceptowana";
        try{
            String dane="UPDATE rezerwacje SET statusPotwierdzenia='"+status+"'WHERE id_rezerwacji='"+ idRezrw.getText() +"'";
            pst=(PreparedStatement) connectDB.prepareStatement(dane);
            pst.execute();
            WyswietlRezerwacje();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy zmienie statusu! "+e);
        }

       dodajFakture();
        wyslijWiadomosc();
    }

    public void dodajFakture() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        Statement statement = connectDB.createStatement();
        String daneFk="SELECT id_faktury FROM faktury order by id_faktury ASC;";
        ResultSet queryResult = statement.executeQuery(daneFk);
        int idFakt=0;
        while (queryResult.next()) {
            idFakt = queryResult.getInt("id_faktury");
        }

        Statement statement2 = connectDB.createStatement();
        String daneKl="SELECT k.id_klienta, k.imie, k.nazwisko, k.email,r.id_platnosc,r.cena,r.id_wycieczki FROM klient k, rezerwacje r " +
                "WHERE r.id_klienta=k.id_klienta AND id_rezerwacji='"+ idRezrw.getText() +"'";
        ResultSet queryResult2 = statement2.executeQuery(daneKl);
        int idZal=0, idP=0,idW=0;
        float cn=0;
        String im=null, naz=null, em=null;

        while (queryResult2.next()) {
            idZal = queryResult2.getInt("id_klienta");
            im=queryResult2.getString("imie");
            naz=queryResult2.getString("nazwisko");
            em=queryResult2.getString("email");
            idP=queryResult2.getInt("r.id_platnosc");
            cn=queryResult2.getFloat("cena");
            idW = queryResult2.getInt("r.id_wycieczki");
        }

        String daneFaktury="INSERT INTO faktury(id_faktury,imie,nazwisko,email,id_platnosc,data,kwota,id_wycieczki)VALUES(?,?,?,?,?,?,?,?)";
        try{
            pst=(PreparedStatement) connectDB.prepareStatement(daneFaktury);
            pst.setString(1, String.valueOf(idFakt+1));
            pst.setString(2,im);
            pst.setString(3,naz);
            pst.setString(4,em);
            pst.setString(5, String.valueOf(idP));
            pst.setString(6,String.valueOf(LocalDate.now()));
            pst.setString(7, String.valueOf(cn));
            pst.setString(8,String.valueOf(idW));
            pst.execute();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Błędne dane faktury! "+e);
        }
    }

    public void wyslijWiadomosc() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement statement3 = connectDB.createStatement();

        String dane="SELECT z.id_pracownika, imie, nazwisko ,email FROM zalogowany z, pracownik p WHERE z.id_pracownika=p.id_pracownika;";
        ResultSet queryResult3 = statement3.executeQuery(dane);
        int idZal=0;
        String im=null, naz=null,em=null;
        while (queryResult3.next()) {
            idZal = queryResult3.getInt("id_pracownika");
            im=queryResult3.getString("imie");
            naz=queryResult3.getString("nazwisko");
            em=queryResult3.getString("email");
        }

        Statement statement = connectDB.createStatement();
        String daneWiad="SELECT id_wiadomosci FROM wiadomosci order by id_wiadomosci ASC;";
        ResultSet queryResult = statement.executeQuery(daneWiad);
        int idWiad=0;
        while (queryResult.next()) {
            idWiad = queryResult.getInt("id_wiadomosci");
        }

        Statement statement2 = connectDB.createStatement();
        String daneKl="SELECT k.id_klienta, r.id_wycieczki,w.rodzajWycieczki FROM klient k, rezerwacje r, wycieczki w WHERE r.id_klienta=k.id_klienta AND w.id_wycieczki=r.id_wycieczki AND id_rezerwacji='"+ idRezrw.getText() +"'";
        ResultSet queryResult2 = statement2.executeQuery(daneKl);
        int idKl=0,idW=0;
        String rodz=null;
        while (queryResult2.next()) {
            idKl = queryResult2.getInt("id_klienta");
            idW=queryResult2.getInt("id_wycieczki");
            rodz=queryResult2.getString("rodzajWycieczki");
        }

        Statement statement4 = connectDB.createStatement();
        String daneW="SELECT nazwa, miejsce, cena FROM wycieczki WHERE rodzajWycieczki='"+ rodz +"'";
        ResultSet queryResult4 = statement4.executeQuery(daneW);
        String nazwa=null,miejsce=null;
        float cn=0;
        while (queryResult4.next()) {
            nazwa=queryResult4.getString("nazwa");
            miejsce=queryResult4.getString("miejsce");
            cn=queryResult4.getFloat("cena");
        }

        String daneWiado="INSERT INTO wiadomosci(id_wiadomosci,temat,adresat,tresc,data,id_pracownika,id_klienta)VALUES(?,?,?,?,?,?,?)";
        try{
            pst=(PreparedStatement) connectDB.prepareStatement(daneWiado);
            pst.setString(1, String.valueOf(idWiad+1));
            pst.setString(2,"Akceptacja rezerwacji.");
            pst.setString(3,em);
            pst.setString(4,"Dzień dobry!\nTwoja rezerwacja została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel i życzymy udanej wycieczki!\n\n\t" +
                    "Polecana oferta:\n"+nazwa+" - " +miejsce+" - "+cn);
            pst.setString(5, String.valueOf(LocalDate.now()));
            pst.setString(6,String.valueOf(idZal));
            pst.setString(7, String.valueOf(idKl));
            pst.execute();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Błędne wysłanie wiadomosci! "+e);
        }

    }

    public void wyslijWiadomoscOdrzucona() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement statement3 = connectDB.createStatement();

        String dane="SELECT z.id_pracownika, imie, nazwisko ,email FROM zalogowany z, pracownik p WHERE z.id_pracownika=p.id_pracownika;";
        ResultSet queryResult3 = statement3.executeQuery(dane);
        int idZal=0;
        String im=null, naz=null,em=null;
        while (queryResult3.next()) {
            idZal = queryResult3.getInt("id_pracownika");
            im=queryResult3.getString("imie");
            naz=queryResult3.getString("nazwisko");
            em=queryResult3.getString("email");
        }

        Statement statement = connectDB.createStatement();
        String daneWiad="SELECT id_wiadomosci FROM wiadomosci order by id_wiadomosci ASC;";
        ResultSet queryResult = statement.executeQuery(daneWiad);
        int idWiad=0;
        while (queryResult.next()) {
            idWiad = queryResult.getInt("id_wiadomosci");
        }

        Statement statement2 = connectDB.createStatement();
        String daneKl="SELECT k.id_klienta, r.id_wycieczki,w.rodzajWycieczki FROM klient k, rezerwacje r, wycieczki w WHERE r.id_klienta=k.id_klienta AND w.id_wycieczki=r.id_wycieczki AND id_rezerwacji='"+ idRezrw.getText() +"'";
        ResultSet queryResult2 = statement2.executeQuery(daneKl);
        int idKl=0,idW=0;
        String rodz=null;
        while (queryResult2.next()) {
            idKl = queryResult2.getInt("id_klienta");
            idW=queryResult2.getInt("id_wycieczki");
            rodz=queryResult2.getString("rodzajWycieczki");
        }

        Statement statement4 = connectDB.createStatement();
        String daneW="SELECT nazwa, miejsce, cena FROM wycieczki WHERE rodzajWycieczki='"+ rodz +"'";
        ResultSet queryResult4 = statement4.executeQuery(daneW);
        String nazwa=null,miejsce=null;
        float cn=0;
        while (queryResult4.next()) {
            nazwa=queryResult4.getString("nazwa");
            miejsce=queryResult4.getString("miejsce");
            cn=queryResult4.getFloat("cena");
        }

        String daneWiado="INSERT INTO wiadomosci(id_wiadomosci,temat,adresat,tresc,data,id_pracownika,id_klienta)VALUES(?,?,?,?,?,?,?)";
        try{
            pst=(PreparedStatement) connectDB.prepareStatement(daneWiado);
            pst.setString(1, String.valueOf(idWiad+1));
            pst.setString(2,"Akceptacja rezerwacji.");
            pst.setString(3,em);
            pst.setString(4,"Dzień dobry!\nTwoja rezerwacja nie została zaakceptowana.\nDziękujemy za wybranie Sky Route Travel!\n\n\t" +
                    "Polecana oferta:\n"+nazwa+" - " +miejsce+" - "+cn);
            pst.setString(5, String.valueOf(LocalDate.now()));
            pst.setString(6,String.valueOf(idZal));
            pst.setString(7, String.valueOf(idKl));
            pst.execute();

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Błędne wysłanie wiadomosci! "+e);
        }

    }

    public void odzrucRezerwacje() throws SQLException {
        String status="Odrzucona";
        try{
            String dane="UPDATE rezerwacje SET statusPotwierdzenia='"+status+"'WHERE id_rezerwacji='"+ idRezrw.getText() +"'";
            pst=(PreparedStatement) connectDB.prepareStatement(dane);
            pst.execute();
            WyswietlRezerwacje();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy zmienie statusu! "+e);
        }
        wyslijWiadomoscOdrzucona();
    }

    public void wyjdzButtonOnAction() {
        Stage stage = (Stage) zam.getScene().getWindow();
        stage.close();
    }
}
