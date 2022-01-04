package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

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
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class Wiadomosci implements Initializable {

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,String> tem;
    @FXML
    private TableColumn<DaneDoWycieczek,String> dat;
    @FXML
    private TableColumn<DaneDoWycieczek,String> od;
    @FXML
    private TextField DanePrac;
    @FXML
    private TextField subject;
    @FXML
    private TextField adres;
    @FXML
    private TextArea wiad;
    @FXML
    private Button WyjdzButton;
    @FXML
    private Button UsunButton;
    @FXML
    private Button OdpowiedzButton;
    @FXML
    private Button WyslijButton;
    @FXML
    private Label forma;


    PreparedStatement pst = null;
    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void WyswietlWiadomosci() {
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT w.id_wiadomosci,temat,adresat,tresc,data , imie, nazwisko FROM wiadomosci w , pracownik p WHERE w.id_pracownika=p.id_pracownika and  p.id_pracownika='" + 1 + "'";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoWiadomosci daneDoWiadomosci;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id=rs.getInt("id_wiadomosci");
                String temat = rs.getString("temat");
                String adresat = rs.getString("adresat");
                String tresc = rs.getString("tresc");
                Date data=rs.getDate("data");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                DanePrac.setText(imie+" "+nazwisko);
                daneDoWiadomosci = new DaneDoWiadomosci(id,temat,adresat,tresc,data);
                WczTab.add(daneDoWiadomosci);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        od.setCellValueFactory(new PropertyValueFactory<>("adresat"));
        tem.setCellValueFactory(new PropertyValueFactory<>("temat"));
        dat.setCellValueFactory(new PropertyValueFactory<>("data"));
        Tab1.setItems(WczTab);

    }

    public void getSelected() throws SQLException {
        this.index = this.Tab1.getSelectionModel().getSelectedIndex();
        if (this.index <= -1) {
            return;
        }
        forma.setText("Od:");
        this.adres.setText(this.od.getCellData(this.index));
        this.subject.setText(this.tem.getCellData(this.index));

        String tema=this.tem.getCellData(this.index);
        Statement stat = null;
        stat = this.connectDB.createStatement();
        String dane = "SELECT tresc FROM wiadomosci WHERE temat='" + tema + "'";
        ResultSet wynik = stat.executeQuery(dane);
        while (wynik.next()) {
            String wiado = wynik.getString("tresc");
            this.wiad.setText(wiado);
        }
        wynik.close();
    }

    public void wyjdzButtonOnAction() {
        Stage stage = (Stage) WyjdzButton.getScene().getWindow();
        stage.close();
    }

    public void usunWiadomosc(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String danee="DELETE FROM wiadomosci WHERE temat=? AND adresat=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1, subject.getText());
            pst.setString(2, adres.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Usunieto pomyslnie!");
            WyswietlWiadomosci();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy usuwaniu! "+e);
        }
    }

    public void Odpowiedz(){
            forma.setText("Do:");
            subject.setText("RE: "+subject.getText());
            wiad.clear();
    }

    public void WyslijWiadomosc() throws SQLException {
        this.index = this.Tab1.getSelectionModel().getSelectedIndex();
        if (this.index <= -1) {
            return;
        }
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        Statement stat2=null;
        stat2=connectDB.createStatement();
        String t=this.tem.getCellData(index);
        String a=this.od.getCellData(index);
        String danee="SELECT * FROM wiadomosci WHERE temat='"+t+"' AND adresat='"+a+"'";
        ResultSet wynik=stat.executeQuery(danee);

        int idw = 0, id_prac=0, id_kl=0;
        while(wynik.next()){
            id_prac=wynik.getInt("id_pracownika");
            id_kl=wynik.getInt("id_klienta");
        }
        String maxID="SELECT id_wiadomosci FROM wiadomosci";
        ResultSet max=stat2.executeQuery(maxID);
       while (max.next()){
            idw=max.getInt("id_wiadomosci");
        }

        String dane="INSERT INTO wiadomosci(id_wiadomosci,temat,adresat,tresc,data,id_pracownika,id_klienta)values(?,?,?,?,?,?,?)";
        try{
            pst=(PreparedStatement)connectDB.prepareStatement(dane);
            pst.setString(1, String.valueOf(idw+1));
            pst.setString(2,subject.getText());
            pst.setString(3,adres.getText());
            pst.setString(4,wiad.getText());
            pst.setString(5, String.valueOf(LocalDate.now()));
            pst.setString(6,String.valueOf(id_prac));
            pst.setString(7,String.valueOf(id_kl));
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
            WyswietlWiadomosci();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad dodawania! "+e);
        }
        wynik.close();
        max.close();
    }


    public void UsunWiadomoscOnActionEvent(){usunWiadomosc();}
    public void OdpowiedzOnActionEvent(){Odpowiedz();}
    public void WyslijWiadomoscOnActionEvent() throws SQLException {WyslijWiadomosc();}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        WyswietlWiadomosci();
    }
}
