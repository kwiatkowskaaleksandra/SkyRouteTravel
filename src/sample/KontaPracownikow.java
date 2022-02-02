package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class KontaPracownikow implements Initializable {

    @FXML
    private TableView<DaneUzytkownika> Tab1;
    @FXML
    private TableColumn<DaneUzytkownika,Integer> idu;
    @FXML
    private TableColumn<DaneUzytkownika,String> im;
    @FXML
    private TableColumn<DaneUzytkownika,String> naz;
    @FXML
    private TableColumn<DaneUzytkownika,String> em;
    @FXML
    private TableColumn<DaneUzytkownika,String> log;
    @FXML
    private TableColumn<DaneUzytkownika,String> has;
    @FXML
    private TableColumn<DaneUzytkownika,String> rol;
    @FXML
    private TextField I;
    @FXML
    private TextField N;
    @FXML
    private TextField E;
    @FXML
    private TextField L;
    @FXML
    private TextField H;
    @FXML
    private TextField ID;
    @FXML
    private ChoiceBox<String> R;
    @FXML
    private Button DodajPrac;
    @FXML
    private Button UsunPrac;
    @FXML
    private Button EdytujPrac;

    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    ObservableList ListaRole= FXCollections.observableArrayList();
    PreparedStatement pst = null;

    public void WyswietlPracownikow(){
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT * FROM pracownik where login!='admin' ORDER BY id_pracownika ASC";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneUzytkownika daneUzytkownika;
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id_pracownika");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String email = rs.getString("email");
                String login =rs.getString("login");
                String haslo =rs.getString("haslo");
                String rola=rs.getString("rola");

                daneUzytkownika = new DaneUzytkownika(id1,imie,nazwisko,email,login,haslo,rola);
                WczTab.add(daneUzytkownika);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idu.setCellValueFactory(new PropertyValueFactory<>("id"));
        im.setCellValueFactory(new PropertyValueFactory<>("imie"));
        naz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        em.setCellValueFactory(new PropertyValueFactory<>("email"));
        log.setCellValueFactory(new PropertyValueFactory<>("login"));
        has.setCellValueFactory(new PropertyValueFactory<>("haslo"));
        rol.setCellValueFactory(new PropertyValueFactory<>("rola"));
        Tab1.setItems(WczTab);

    }

    public void usunPracownika(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String danee="DELETE FROM pracownik WHERE id_pracownika=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1, ID.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Usunieto pomyslnie!");
            WyswietlPracownikow();
            I.clear();
            N.clear();
            E.clear();
            L.clear();
            H.clear();
            R.setValue("Funkcja");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy usuwaniu! Pracownik nie moze zotsac usuniety. "+e);
        }
    }

    public void EdytujPracownika() throws SQLException {
        String danee;

        try {
            String id1 = ID.getText();
            String im = I.getText();
            String naz = N.getText();
            String em = E.getText();
            String log = L.getText();
            String has = H.getText();
            String rol = R.getValue();
                danee = "UPDATE pracownik SET id_pracownika='" + id1 + "' ,imie='" + im + "',nazwisko='" + naz + "',email='"
                        + em + "',login='" + log + "',haslo='" + has + "',rola='" + rol + "'WHERE id_pracownika='" + id1 + "'";
                pst = (PreparedStatement) connectDB.prepareStatement(danee);

                if (I.getText().isEmpty() || N.getText().isEmpty() || E.getText().isEmpty() || L.getText().isEmpty() || H.getText().isEmpty() || R.getValue().equals("Funkcja")) {
                    throw new Exception();
                } else {
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Edycja zakonczona pomyslnie!");
                    WyswietlPracownikow();
                    I.clear();
                    N.clear();
                    E.clear();
                    L.clear();
                    H.clear();
                    R.setValue("Funkcja");
                }

            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Blad przy edycji! " + e);
        }
    }
    public void DodajPracownika() throws SQLException {
        this.index = this.Tab1.getSelectionModel().getSelectedIndex();
        if (this.index <= -1) {
            return;
        }
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Statement stat=null;
        stat=connectDB.createStatement();
        String dane="INSERT INTO pracownik(id_pracownika, imie,nazwisko,email,login,haslo,rola)values(?,?,?,?,?,?,?)";
        Statement stat2=null;
        stat2=connectDB.createStatement();
        String id="SELECT * FROM pracownik";
        ResultSet wynik=stat.executeQuery(id);
        int idw = 0;
        while(wynik.next()) {idw = wynik.getInt("id_pracownika");}
        wynik.close();

                   try {
                       Statement stat3 = null;
                       stat3 = connectDB.createStatement();
                       String w = "SELECT * FROM pracownik WHERE login='" + L.getText() + "'";
                       ResultSet odp = stat.executeQuery(w);

                       while (!odp.next()) {
                           pst = (PreparedStatement) connectDB.prepareStatement(dane);
                           pst.setString(1, String.valueOf(idw + 1));
                           pst.setString(2, I.getText());
                           pst.setString(3, N.getText());
                           pst.setString(4, E.getText());
                           pst.setString(5, L.getText());
                           pst.setString(6, H.getText());
                           pst.setString(7, R.getValue());
                           pst.execute();
                           JOptionPane.showMessageDialog(null, "Dodano pomyslnie!");
                           WyswietlPracownikow();
                       }
                   } catch(Exception e){
                           JOptionPane.showMessageDialog(null, "Blad dodawania! " + e);
                       }
    }

    public void usunPracownikaOnActionEvent(){usunPracownika();}
    public void dodajPracownikaOnActionEvent() throws SQLException {DodajPracownika();}
    public void edytujPracownikaOnActionEvent() throws SQLException {EdytujPracownika();}

    private void ChoiceBoxRolaPracownika(){
        ListaRole.removeAll(ListaRole);
        String a="Funkcja";

        String b="Sprzedawca";
        String c="Przewodnik";

        ListaRole.addAll(b,c);
        R.getItems().addAll(ListaRole);
        R.setValue(a);
    }

    public void getSelected(){
        index=Tab1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        ID.setText(idu.getCellData(index).toString());
        I.setText(im.getCellData(index));
        N.setText(naz.getCellData(index));
        E.setText(em.getCellData(index));
        L.setText(log.getCellData(index));
        H.setText(has.getCellData(index));
        R.setValue(rol.getCellData(index));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WyswietlPracownikow();
        ChoiceBoxRolaPracownika();
    }
}
