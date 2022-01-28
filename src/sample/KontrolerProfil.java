package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DaneDoRezerwacji;
import sample.Poloczenie;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class KontrolerProfil implements Initializable {
    public Button prof;
    public Button rez;
    public Button hsl;
    public Button ust;
    public Button plt;
    public Button op;
    public Button fakt;
    public Button zapisz;
    public Button zapiszZmiany;
    public TextField imieKl;
    public TextField nazwKl;
    public TextField emailKl;
    public TextField dataKl;
    public TextField loginKl;
    public Label rodzajUst;
    public TextField stareHasl;
    public TextField noweHasl;
    public TextField powNoweHasl;
    public TextField im;
    public TextField nazw;
    public TextField email;
    public TextField log;
    public TextField id;
    public TextArea polityka;

    @FXML
    public TextField msc;
    @FXML
    public TextField nazwa;
    @FXML
    public TextField miejsce2;
    @FXML
    public TextField nazwa2;
    @FXML
    public TextArea tresc;
    public Button wrc;
    public Button dod;
    int index=-1;

    PreparedStatement pst = null;
    @FXML
    private TableView<DaneDoRezerwacji> Tab;
    @FXML
    private TableColumn<DaneDoRezerwacji,Integer> idR;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> term;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> naz;
    @FXML
    private TableColumn<DaneDoRezerwacji,Float> cen;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> plat;
    @FXML
    private TableColumn<DaneDoRezerwacji,String> stat;
    @FXML
    public TextField idRezrw;
    String n=null;
    String m=null;

    public void ProfilOnAction()  {
        Stage stage = (Stage) prof.getScene().getWindow();
        stage.close();
     try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/Profil.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.setTitle("Mój profil");
            menuStage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void RezerwacjaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) rez.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/ProfilRezerwacje.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.setTitle("Rezerwacje");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void PolitykaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) plt.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/ProfilPolityka.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.setTitle("Polityka");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void HasloOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) hsl.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/ProfilHaslo.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.setTitle("Zmiana hasła");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void UstawieniaOnAction(javafx.event.ActionEvent event){
        Stage stage = (Stage) ust.getScene().getWindow();
        stage.close();

        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/ProfilUstawienia.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220,740));
            menuStage.setTitle("Ustawienia");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
    Object object=new Object();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement statement2 = null;
        try {
            statement2 = connectDB.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String daneKl="SELECT k.id_klienta,k.login,k.imie,k.nazwisko, k.email, k.dataZalozenia FROM zalogowany z , klient k WHERE z.id_klienta=k.id_klienta;";
        ResultSet queryResult2 = null;
        int idZal=0;
        String imie=null,nazwisko=null,email=null,data=null,login=null;
        try {
            queryResult2 = statement2.executeQuery(daneKl);

            while (queryResult2.next()) {
                idZal = queryResult2.getInt("id_klienta");
                imie=queryResult2.getString("imie");
                nazwisko=queryResult2.getString("nazwisko");
                login=queryResult2.getString("login");
                email=queryResult2.getString("email");
                data=queryResult2.getString("dataZalozenia");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(rodzajUst.getText().equals("Szczegóły konta"))
        {
            imieKl.setText(imie);
            nazwKl.setText(nazwisko);
            emailKl.setText(email);
            dataKl.setText(data);
            loginKl.setText(login);
        }
        if(rodzajUst.getText().equals("Rezerwacje")){
            try {
                rezerwacja();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(rodzajUst.getText().equals("Edycja konta")){
            try {
                dane();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        String idr=null;

        if(this.nazwa!=null){

            Statement s = null;
            try {
                s = connectDB.createStatement();
            } catch (
                    SQLException throwables) {
                throwables.printStackTrace();
            }
            String daneRez = "SELECT id_rezerwacji, nazwa, miejsce from rezerwacje r, wycieczki w where w.id_wycieczki=r.id_wycieczki and r.id_rezerwacji='" + idRezrw.getText() + "'";
            ResultSet q = null;
           // System.out.println(idRezrw.getText());
            int idRez = 0;
            String nazwaW = null, miejsce = null;
            try {
                q = s.executeQuery(daneRez);

                while (q.next()) {
                    idRez = q.getInt("id_rezerwacji");
                    nazwaW = q.getString("nazwa");
                    miejsce = q.getString("miejsce");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            nazwa.setText(nazwaW);
            msc.setText(miejsce);

        }

    }

    public void dane() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        int idZal=0;
        String login=null,imie=null,nazwisko=null,em=null;
        String idZ="SELECT z.id_klienta,k.login,k.imie,k.nazwisko,k.email from zalogowany z , klient k where z.id_klienta=k.id_klienta";
        ResultSet maxz=stat.executeQuery(idZ);
        while (maxz.next()){
            idZal=maxz.getInt("id_klienta");
            login=maxz.getString("login");
            imie=maxz.getString("imie");
            nazwisko=maxz.getString("nazwisko");
            em=maxz.getString("email");
        }
        im.setText(imie);
        nazw.setText(nazwisko);
        email.setText(em);
        log.setText(login);
    }

    public void zapiszZmiany() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        int idZal=0;
        String login=null,imie=null,nazwisko=null,em=null;
        String idZ="SELECT z.id_klienta,k.imie,k.nazwisko,k.email from zalogowany z , klient k where z.id_klienta=k.id_klienta";
        ResultSet maxz=stat.executeQuery(idZ);
        while (maxz.next()){
            idZal=maxz.getInt("id_klienta");
        }
        String zmiana="UPDATE klient SET imie='"+im.getText()+"',nazwisko='"+nazw.getText()+"',email='"+email.getText()+"',login='"+log.getText()+"' WHERE id_klienta='"+idZal+"'";
        pst=(PreparedStatement) connectDB.prepareStatement(zmiana);
        if(im.getText().isEmpty()||nazw.getText().isEmpty()||email.getText().isEmpty()||log.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Uzupełnij wszytskie dane.");
        }else{
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dane zostały pomyślnie zmienione.");
        }

    }

    public void zmianaHasla() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        int idZal=0;
        String log=null,hasl=null;
        String idZ="SELECT z.id_klienta,k.login,k.haslo from zalogowany z , klient k where z.id_klienta=k.id_klienta";
        ResultSet maxz=stat.executeQuery(idZ);
        while (maxz.next()){
            idZal=maxz.getInt("id_klienta");
            log=maxz.getString("login");
            hasl=maxz.getString("haslo");
        }

        if(stareHasl.getText().equals(hasl)){
            if(!noweHasl.getText().equals("")|| !noweHasl.getText().isEmpty()) {
                if(!powNoweHasl.getText().equals("") || !powNoweHasl.getText().isEmpty()){
                    String zmiana="UPDATE klient SET haslo='"+noweHasl.getText()+"'WHERE id_klienta='"+idZal+"'";
                    pst=(PreparedStatement) connectDB.prepareStatement(zmiana);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Hasło zostało pomyślnie zmienione.");
                }
               else JOptionPane.showMessageDialog(null, "Powtórz nowe hasło.");
            }
            else  JOptionPane.showMessageDialog(null, "Podaj nowe hasło.");
        }else  JOptionPane.showMessageDialog(null, "Podano błędne stare hasło");
    }

    public void rezerwacja() throws SQLException {
        final ObservableList WczTab = FXCollections.observableArrayList();
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        Statement statement = connectDB.createStatement();

        String dane = "SELECT id_klienta FROM zalogowany;";
        ResultSet queryResult = statement.executeQuery(dane);
        int idZal = 0;
        while (queryResult.next()) {
            idZal = queryResult.getInt("id_klienta");
        }

        String danee = "SELECT id_rezerwacji,imie,miejsce,nazwa,termin, ileDoroslych, ileDzieci,r.cena, p.rodzaj, statusPotwierdzenia " +
                "FROM rezerwacje r, wycieczki w, platnosc p, klient k WHERE r.id_klienta=k.id_klienta AND r.id_platnosc=p.id_platnosc " +
                "AND r.id_wycieczki=w.id_wycieczki AND r.id_klienta ='"+idZal+"' ORDER BY id_rezerwacji ASC";

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
                Date term=rs.getDate("termin");
                String naz = rs.getString("nazwa");
                String msc = rs.getString("miejsce");
                Float cn = rs.getFloat("cena");
                String rodz = rs.getString("rodzaj");
                String stat = rs.getString("statusPotwierdzenia");
                String im=rs.getString("imie");
                int ilDor=rs.getInt("ileDoroslych");
                int ilDz=rs.getInt("ileDzieci");

                daneDoRezerwacji = new DaneDoRezerwacji(id1,naz+" "+im,naz+" "+msc, (java.sql.Date) term,ilDor,ilDz,cn,rodz,stat);
                WczTab.add(daneDoRezerwacji);
            }

            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idR.setCellValueFactory(new PropertyValueFactory<>("id"));
        naz.setCellValueFactory(new PropertyValueFactory<>("wycieczka"));
        term.setCellValueFactory(new PropertyValueFactory<>("termin"));
        cen.setCellValueFactory(new PropertyValueFactory<>("cena"));
        plat.setCellValueFactory(new PropertyValueFactory<>("platnosc"));
        stat.setCellValueFactory(new PropertyValueFactory<>("status"));
        Tab.setItems(WczTab);
    }

    public void usunRezrw(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String danee="DELETE FROM rezerwacje WHERE id_rezerwacji=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1, idRezrw.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Pomyślnie anulowano rezerwacje!");
            rezerwacja();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy anulowaniu rezerwacji!  "+e);
        }
    }

    public void opinia() throws SQLException {
        try{
            System.out.println("przed: "+idRezrw.getText());


            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/opinieKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 500,410));
            menuStage.setTitle("Opinie");
            menuStage.show();
            getSelected();
            System.out.println("po: "+idRezrw.getText());
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void dodOpinie() throws SQLException {

        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        int idZal=0;
        String idZ="SELECT id_klienta from zalogowany";
        ResultSet maxz=stat.executeQuery(idZ);
        while (maxz.next()){
            idZal=maxz.getInt("id_klienta");
        }

        Statement stat2=null;
        stat2=connectDB.createStatement();
        int ido=0;
        String maxID="SELECT id_opinia FROM opinie";
        ResultSet max=stat2.executeQuery(maxID);
        while (max.next()){
            ido=max.getInt("id_opinia");
        }

        Statement stat3=null;
        stat3=connectDB.createStatement();
        int idW=0;
        String idWyc="SELECT r.id_wycieczki FROM rezerwacje r , wycieczki w where r.id_klienta='"+idZal+"'AND w.nazwa='"+nazwa.getText()+"'AND w.miejsce='"+msc.getText()+"' AND w.id_wycieczki=r.id_wycieczki";
        ResultSet idwycieczki=stat3.executeQuery(idWyc);
        while (idwycieczki.next()){
            idW=idwycieczki.getInt("id_wycieczki");
        }

        System.out.println(idW);
        String dane="INSERT INTO opinie(id_opinia,tresc,data,id_klienta,id_wycieczki)values(?,?,?,?,?)";
        try{
            pst=(PreparedStatement)connectDB.prepareStatement(dane);
            pst.setString(1, String.valueOf(ido+1));
            pst.setString(2,tresc.getText());
            pst.setString(3,String.valueOf(LocalDate.now()));
            pst.setString(4, String.valueOf(idZal));
            pst.setString(5, String.valueOf(idW));
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad dodawania! "+e);
        }
    }
    public void wyjdzButtonOnAction() {
        Stage stage = (Stage) wrc.getScene().getWindow();
        stage.close();
    }

    public void getSelected() {
        index=Tab.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
            idRezrw.setText(String.valueOf(idR.getCellData(index)));

           System.out.println(idRezrw.getText());
    }


    public void fakturaKlient() {
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/klient/fakturaKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 500,410));
            menuStage.setTitle("Faktura");
            menuStage.show();
        }catch(Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }
}
