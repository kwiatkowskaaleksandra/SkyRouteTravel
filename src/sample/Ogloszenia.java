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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class Ogloszenia implements Initializable {

    @FXML
    private TableView<DaneOgloszenia> Tab1;
    @FXML
    private TableColumn<DaneOgloszenia,Integer> ido;
    @FXML
    private TableColumn<DaneOgloszenia,String> ogl;
    @FXML
    private TableColumn<DaneOgloszenia,Date> dat;


    @FXML
    private Button dodaj;
    @FXML
    private Button edytuj;
    @FXML
    private Button usun;
    @FXML
    private TextArea tresc;
    @FXML
    private TextField data;
    @FXML
    private TextField ide;

    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    PreparedStatement pst = null;

    public void getSelected(){
        index=Tab1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        ide.setText(ido.getCellData(index).toString());
        tresc.setText(ogl.getCellData(index));
        data.setText(dat.getCellData(index).toString());


    }

    public void wyswietlOgloszenia(){
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_ogloszenia,data,tresc FROM ogloszenia order by id_ogloszenia asc";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneOgloszenia daneOgloszenia;
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {

                int id=rs.getInt("id_ogloszenia");
                Date data =rs.getDate("data");
                String tresc = rs.getString("tresc");
                daneOgloszenia =new DaneOgloszenia(id,tresc,data);
                WczTab.add(daneOgloszenia);
            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        ido.setCellValueFactory(new PropertyValueFactory<>("id"));
        ogl.setCellValueFactory(new PropertyValueFactory<>("tresc"));
        dat.setCellValueFactory(new PropertyValueFactory<>("data"));
        Tab1.setItems(WczTab);

    }

    public void dodajO() throws SQLException {

        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();


        Statement stat2=null;
        stat2=connectDB.createStatement();
        String danee="SELECT * FROM ogloszenia";
        ResultSet wynik=stat2.executeQuery(danee);

        int idw = 0;
        while(wynik.next()){
            idw=wynik.getInt("id_ogloszenia");
        }

        String dane="INSERT INTO ogloszenia(id_ogloszenia,data,tresc)values(?,?,?)";
        try{
            System.out.println(idw);
            pst=(PreparedStatement)connectDB.prepareStatement(dane);
            pst.setString(1, String.valueOf(idw+1));
            pst.setString(2,data.getText());
            pst.setString(3,tresc.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
            wyswietlOgloszenia();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad dodawania! "+e);
        }
        wynik.close();
    }

    public void usunO(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String danee="DELETE FROM ogloszenia WHERE id_ogloszenia=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1, ide.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Usunieto pomyslnie!");
            wyswietlOgloszenia();
            tresc.clear();
            data.clear();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy usuwaniu! "+e);
        }
    }

    public void edytujO(){
        String danee;

        try {
            String id1 = ide.getText();
            String tr = tresc.getText();
            String dat = data.getText();

            danee = "UPDATE ogloszenia SET id_ogloszenia='" + id1 + "' ,data='" + dat + "',tresc='" +tr+ "'WHERE id_ogloszenia='" + id1 + "'";
            pst = (PreparedStatement) connectDB.prepareStatement(danee);

            if (tresc.getText().isEmpty() || data.getText().isEmpty()) {
                throw new Exception();
            } else {
                pst.execute();
                JOptionPane.showMessageDialog(null, "Edycja zakonczona pomyslnie!");
                wyswietlOgloszenia();
                tresc.clear();
                data.clear();
            }

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Blad przy edycji! " + e);
        }
    }

    public void dodajOgloszenieOnAction() throws SQLException {dodajO();};

    public void usunOgloszenieOnAction(){usunO();};

    public void edytujOgloszenieOnAction(){edytujO();};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wyswietlOgloszenia();
    }
}
