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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class Faktury implements Initializable {
    PreparedStatement pst = null;
    ObservableList listWycieczki= FXCollections.observableArrayList();
    ObservableList listPlatnosc= FXCollections.observableArrayList();

    @FXML
    private Button ZamknijButton;
    @FXML
    private TableView<DaneDoFaktur> Tab1;
    @FXML
    private TableColumn<DaneDoFaktur,Integer> idp;
    @FXML
    private TableColumn<DaneDoFaktur,String> im;
    @FXML
    private TableColumn<DaneDoFaktur,String> naz;
    @FXML
    private TableColumn<DaneDoFaktur,String> mail;
    @FXML
    private TableColumn<DaneDoFaktur,String> pln;
    @FXML
    private TableColumn<DaneDoFaktur,Date> dta;
    @FXML
    private TableColumn<DaneDoFaktur,String> rodz;
    @FXML
    private TableColumn<DaneDoFaktur,String> nazw;
    @FXML
    private TableColumn<DaneDoFaktur,Float> kwot;
    @FXML
    private TableColumn<DaneDoFaktur,Integer> idw;
    @FXML
    private Button DodajButton;
    @FXML
    private Button UsunButton;
    @FXML
    private Button EdytujButton;
    @FXML
    private TextField TextK1;
    @FXML
    private TextField TextK2;
    @FXML
    private TextField TextK3;
    @FXML
    private TextField TextK4;
    @FXML
    private TextField TextK6;
    @FXML
    private TextField TextK7;
    @FXML
    private TextField TextK8;
    @FXML
    private TextField TextK9;
    @FXML
    private TextField TextK10;
    @FXML
    private ChoiceBox<String> RodzajPlatnosci;

    int index=-1;

    public void WyswietlFaktury(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_faktury, imie, nazwisko, email, p.rodzaj,f.data,kwota, w.nazwa,w.rodzajWycieczki FROM faktury f, platnosc p,wycieczki w WHERE  f.id_platnosc=p.id_platnosc AND w.id_wycieczki=f.id_wycieczki";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoFaktur daneDoFaktur;

        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(st).executeQuery(danee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (Objects.requireNonNull(rs).next()) {
                int id1 = rs.getInt("id_faktury");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String email = rs.getString("email");
                String platnosc =rs.getString("p.rodzaj");
                Date data=rs.getDate("f.data");
                float kwota=rs.getFloat("kwota");
                String nazwa =rs.getString("nazwa");
                String rodzaj =rs.getString("rodzajWycieczki");

                daneDoFaktur = new DaneDoFaktur(id1, imie,nazwisko,email,platnosc,data,kwota,nazwa,rodzaj);
                WczTab.add(daneDoFaktur);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        im.setCellValueFactory(new PropertyValueFactory<>("imie"));
        naz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        pln.setCellValueFactory(new PropertyValueFactory<>("platnosc"));
        dta.setCellValueFactory(new PropertyValueFactory<>("data"));
        kwot.setCellValueFactory(new PropertyValueFactory<>("kwota"));
        nazw.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        rodz.setCellValueFactory(new PropertyValueFactory<>("rodzajWycieczki"));

        Tab1.setItems(WczTab);

    }

    public void DodajFakture() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();


        Statement stat2=null;
        stat2=connectDB.createStatement();
        String maxID="SELECT id_faktury FROM faktury ORDER BY  id_faktury ASC";
        ResultSet max=stat2.executeQuery(maxID);
        int idf = 0;
        while (max.next()){
            idf=max.getInt("id_faktury");
        }

        String danee="INSERT INTO faktury(id_faktury,imie,nazwisko,email,id_platnosc,data,kwota,id_wycieczki)values(?,?,?,?,?,?,?,?)";
        try{
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1, String.valueOf(idf+1));
            pst.setString(2,TextK2.getText());
            pst.setString(3,TextK3.getText());
            pst.setString(4,TextK4.getText());
            pst.setString(6,TextK6.getText());
            pst.setString(7,TextK7.getText());
            pst.setString(8,"1");
            if(RodzajPlatnosci.getValue().equals("BLIK")) {
                pst.setString(5,"1");
            } else  if(RodzajPlatnosci.getValue().equals("Karta kredytowa")) {
                pst.setString(5,"2");
            }else  if(RodzajPlatnosci.getValue().equals("Przelew bankowy")) {
                pst.setString(5,"3");
            }else  if(RodzajPlatnosci.getValue().equals("PayPal")) {
                pst.setString(5,"4");
            }
         //   pst.setString(10,TextK9.getText());
         //   pst.setString(11,TextK10.getText());

         //   if(TextK1.getText().isEmpty()||TextK2.getText().isEmpty()||TextK3.getText().isEmpty()||TextK4.getText().isEmpty()||TextK6.getText().isEmpty()||TextK8.getText().isEmpty()||TextK9.getText().isEmpty()||RodzajPlatnosci.getValue().equals("Rodzaj platnosci")||TextK10.getText().isEmpty()){
      //          throw new Exception();
      //    }
         //   else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
                WyswietlFaktury();
                TextK1.clear();
                TextK2.clear();
                TextK3.clear();
                TextK4.clear();
                TextK6.clear();
                TextK7.clear();
                TextK8.clear();
                TextK9.clear();
                TextK10.clear();
                RodzajPlatnosci.setValue("Rodzaj platnosci");

        //    }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad dodawania! "+e);
        }
    }

    public void UsunFakture(){

        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String danee="DELETE FROM faktury WHERE id_faktury=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1,TextK1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Usunieto pomyslnie!");
            WyswietlFaktury();
            TextK1.clear();
            TextK2.clear();
            TextK3.clear();
            TextK4.clear();
            TextK6.clear();
            TextK7.clear();
            TextK8.clear();
            TextK9.clear();
            TextK10.clear();
            RodzajPlatnosci.setValue("Rodzaj platnosci");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy usuwaniu! "+e);
        }
    }

    public void EdytujFakture(){
        try{
            Poloczenie connectNow = new Poloczenie();
            Connection connectDB = connectNow.getConnection();

            String val1=TextK1.getText();
            String val2=TextK2.getText();
            String val3=TextK3.getText();
            String val4=TextK4.getText();
            String val7=TextK7.getText();
            String val8=TextK8.getText();
            String val9=null;
            if(RodzajPlatnosci.getValue().equals("BLIK")) {
               val9="1";
            } else  if(RodzajPlatnosci.getValue().equals("Karta kredytowa")) {
                val9="2";
            }else  if(RodzajPlatnosci.getValue().equals("Przelew bankowy")) {
                val9="3";
            }else  if(RodzajPlatnosci.getValue().equals("PayPal")) {
                val9="4";
            }
            String val10=TextK6.getText();

            String danee="UPDATE faktury SET id_faktury='"+val1+"',imie='"+val2+"',nazwisko='"+val3+"',email='"+val4+"',data='"+val10+"', kwota='"+val7+"',id_wycieczki=4 WHERE id_faktury='"+val1+"'";
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
        //    if(TextK1.getText().isEmpty()||TextK2.getText().isEmpty()||TextK3.getText().isEmpty()||TextK4.getText().isEmpty()||TextK6.getText().isEmpty()||TextK8.getText().isEmpty()||TextK9.getText().isEmpty()||RodzajPlatnosci.getValue().equals("Rodzaj platnosci")||TextK10.getText().isEmpty()){
       //         throw new Exception();
       //     }else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Edycja zakonczona pomyslnie!");
                WyswietlFaktury();
                TextK1.clear();
                TextK2.clear();
                TextK3.clear();
                TextK4.clear();
                TextK6.clear();
                TextK7.clear();
                TextK8.clear();
                TextK9.clear();
                TextK10.clear();
                RodzajPlatnosci.setValue("Rodzaj platnosci");
           // }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy edycji! "+e);
        }
    }

    public void zamknijButtonOnAction() {
        Stage stage = (Stage) ZamknijButton.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Pracownik.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1720.0D, 880.0D));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void DodajButtonOnActionEvent() throws SQLException { DodajFakture();}

    public void UsunButtonOnActionEvent(){ UsunFakture();}

    public void EdytujButtonOnActionEvent(){ EdytujFakture();}

    private void ChoiceBoxPlatnosc(){
        listPlatnosc.removeAll(listPlatnosc);
        String a="Rodzaj platnosci";
        String b="BLIK";
        String c="PayPal";
        String d="Przelew bankowy";
        listPlatnosc.addAll(b,c,d);
        RodzajPlatnosci.getItems().addAll(listPlatnosc);
        RodzajPlatnosci.setValue(a);
    }

    public void getSelected(){
        index=Tab1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        TextK1.setText(idp.getCellData(index).toString());
        TextK2.setText(im.getCellData(index));
        TextK3.setText(naz.getCellData(index));
        TextK4.setText(mail.getCellData(index));
        TextK6.setText(dta.getCellData(index).toString());
        TextK7.setText(kwot.getCellData(index).toString());
        TextK8.setText(nazw.getCellData(index));
        TextK9.setText(rodz.getCellData(index));
        RodzajPlatnosci.setValue(pln.getCellData(index));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ChoiceBoxPlatnosc();
        WyswietlFaktury();
    }
}
