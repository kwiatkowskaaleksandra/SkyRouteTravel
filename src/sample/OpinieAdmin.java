package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class OpinieAdmin implements Initializable {
    @FXML
    private TableView<DaneOpinie> Tab1;
    @FXML
    private TableColumn<DaneOpinie,Integer> idOp;
    @FXML
    private TableColumn<DaneOpinie,String> im;
    @FXML
    private TableColumn<DaneOpinie,String> nazw;
    @FXML
    private TableColumn<DaneOpinie,String> naz;
    @FXML
    private TableColumn<DaneOpinie,String> msc;
    @FXML
    private TableColumn<DaneOpinie,String> opi;
    @FXML
    private TableColumn<DaneOpinie, Date> dat;
    @FXML
    private TextArea trescOpinia;
    @FXML
    private TextField IDo;
    @FXML
    private Button zglos;

    PreparedStatement pst = null;
    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();

    public void getSelected() {
        this.index = this.Tab1.getSelectionModel().getSelectedIndex();
        if (this.index <= -1) {
            return;
        }
        trescOpinia.setText(opi.getCellData(index));
        IDo.setText(idOp.getCellData(index).toString());
    }

    public void ZglosUzytkownika() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String dane3 = "SELECT k.id_klienta FROM klient k, opinie o WHERE o.id_klienta=k.id_klienta";
        ResultSet rs2 = null;
        Statement st = null;

        st = connectDB.createStatement();
        rs2 = Objects.requireNonNull(st).executeQuery(dane3);
       int idK=0;
        while (Objects.requireNonNull(rs2).next()) {
              idK=rs2.getInt("id_klienta");
            }
        String danee="Update klient set login=?, haslo=? where id_klienta=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1,"Zablokowany");
            pst.setString(2,"Zablokowany");
            pst.setString(3, String.valueOf(idK));
            pst.execute();
            JOptionPane.showMessageDialog(null,"Zgłoszono użytkownika!");
            PokazOpinie();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Nie można zgłościć użytkownika. "+e);
        }
    }

    public void PokazOpinie() throws SQLException {
        final ObservableList WczTab = FXCollections.observableArrayList();
        Statement statement = connectDB.createStatement();
        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneOpinie daneOpinie;
        String stan="Zablokowany";
        String dane="SELECT id_opinia, k.imie,k.nazwisko,w.nazwa,w.miejsce,o.data,o.tresc FROM opinie o , wycieczki w, klient k WHERE o.id_klienta=k.id_klienta AND w.id_wycieczki=o.id_wycieczki AND  not k.login='"+stan+"';";
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(dane);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id=rs.getInt("id_opinia");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String nazwa = rs.getString("nazwa");
                String miejsce=rs.getString("miejsce");
                Date data=rs.getDate("data");
                String opinia = rs.getString("tresc");
                daneOpinie = new DaneOpinie(id,imie,nazwisko,nazwa,miejsce,opinia, (java.sql.Date) data);
                WczTab.add(daneOpinie);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idOp.setCellValueFactory(new PropertyValueFactory<>("idop"));
        im.setCellValueFactory(new PropertyValueFactory<>("imie"));
        nazw.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        naz.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        msc.setCellValueFactory(new PropertyValueFactory<>("miejsce"));
        dat.setCellValueFactory(new PropertyValueFactory<>("data"));
        opi.setCellValueFactory(new PropertyValueFactory<>("tresc"));
        Tab1.setItems(WczTab);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            PokazOpinie();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
