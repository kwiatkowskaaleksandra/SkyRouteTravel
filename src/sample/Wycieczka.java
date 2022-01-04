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
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class Wycieczka implements Initializable {
    PreparedStatement pst = null;

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,Integer> idp;
    @FXML
    private TableColumn<DaneDoWycieczek,String> nzw;
    @FXML
    private TableColumn<DaneDoWycieczek,String> ms;
    @FXML
    private TableColumn<DaneDoWycieczek,String> zak;
    @FXML
    private TableColumn<DaneDoWycieczek,String> wyz;
    @FXML
    private TableColumn<DaneDoWycieczek,String> tran;
    @FXML
    private TableColumn<DaneDoWycieczek,String> atr;
    @FXML
    private TableColumn<DaneDoWycieczek,String> prem;
    @FXML
    private TableColumn<DaneDoWycieczek,String> czs;
    @FXML
    private TableColumn<DaneDoWycieczek,Float> cn;
    @FXML
    private TableColumn<DaneDoWycieczek,Integer> ilDn;
    @FXML
    private TableColumn<DaneDoWycieczek,String> rodz;
    @FXML
    private Button ZamknijButton;
    @FXML
    private Button DodajButton;
    @FXML
    private Button UsunButton;
    @FXML
    private Button EdytujButton;
    @FXML
    private TextField id;
    @FXML
    private TextField TextK2;
    @FXML
    private TextField TextK3;
    @FXML
    private TextField TextK4;
    @FXML
    private TextField TextK5;
    @FXML
    private TextField TextK6;
    @FXML
    private TextField TextK8;
    @FXML
    private TextArea TextK7;
    @FXML
    private CheckBox TextK9;
    @FXML
    private CheckBox TextK10;
    @FXML
    private TextField TextK11;
    @FXML
    private TextField TextK12;
    @FXML
    private ChoiceBox<String> RodzajWycieczki;
    @FXML
    private ChoiceBox<String> RodzajTransportu;
    @FXML
    private ChoiceBox<String> RodzajZakwaterowania;

    int index=-1;
    Poloczenie connectNow = new Poloczenie();
    Connection connectDB = connectNow.getConnection();
    ObservableList listWycieczki= FXCollections.observableArrayList();
    ObservableList listTransport= FXCollections.observableArrayList();
    ObservableList listZakwaterowania= FXCollections.observableArrayList();

    public void WyswietlWycieczki(){
        final ObservableList WczTab = FXCollections.observableArrayList();

        String danee = "SELECT id_wycieczki,nazwa,miejsce,w.cena,t.rodzaj,czas, z.rodzaj, wyzywienie, premium, atrakcje, rodzajWycieczki,iloscDni FROM wycieczki w, zakwaterowanie z, transport t WHERE w.id_transport=t.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie";

        Statement st = null;
        try{
            st = connectDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DaneDoWycieczek daneDoWycieczek;

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
                float cena = rs.getFloat("w.cena");
                String transport = rs.getString("t.rodzaj");
                String czasPodrozy =rs.getString("czas");
                String zakwaterowanie =rs.getString("z.rodzaj");
                String wyzywienie=rs.getString("wyzywienie");
                String premium =rs.getString("premium");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,atrakcje,rodzaj,iloscDni);
                WczTab.add(daneDoWycieczek);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nzw.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        ms.setCellValueFactory(new PropertyValueFactory<>("miejsce"));
        cn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tran.setCellValueFactory(new PropertyValueFactory<>("transport"));
        czs.setCellValueFactory(new PropertyValueFactory<>("czasPodrozy"));
        zak.setCellValueFactory(new PropertyValueFactory<>("zakwaterowanie"));
        wyz.setCellValueFactory(new PropertyValueFactory<>("wyzywienie"));
        prem.setCellValueFactory(new PropertyValueFactory<>("premium"));
        atr.setCellValueFactory(new PropertyValueFactory<>("atrakcje"));
        rodz.setCellValueFactory(new PropertyValueFactory<>("rodzaj"));
        ilDn.setCellValueFactory(new PropertyValueFactory<>("iloscDni"));

        Tab1.setItems(WczTab);

    }

    public void DodajWycieczke(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();


        String danee="INSERT INTO wycieczki(id_wycieczki,nazwa,miejsce,cena,id_transport,czas,id_zakwaterowanie,wyzywienie,premium,atrakcje,rodzajWycieczki,iloscDni)values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try{

            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1,id.getText());
            pst.setString(2,TextK12.getText());
            pst.setString(4,TextK3.getText());
            if(RodzajTransportu.getValue().equals("Samolot")) {
                pst.setString(5,"1");
            } else  if(RodzajTransportu.getValue().equals("Prom")) {
                pst.setString(5,"2");
            }else  if(RodzajTransportu.getValue().equals("Pociąg")) {
                pst.setString(5,"3");
            }else  if(RodzajTransportu.getValue().equals("Autokar")) {
                pst.setString(5,"4");
            }
            if(RodzajZakwaterowania.getValue().equals("Hotel")) {
                pst.setString(7,"1");
            }else  if(RodzajZakwaterowania.getValue().equals("Motel")) {
                pst.setString(7,"2");
            }else  if(RodzajZakwaterowania.getValue().equals("Kurort")) {
                pst.setString(7,"3");
            }else  if(RodzajZakwaterowania.getValue().equals("Domek jednorodzinny")) {
                pst.setString(7,"4");
            }

            if(TextK9.isSelected() && !TextK10.isSelected()){
                TextK9.setSelected(true);
                TextK10.setSelected(false);
                pst.setString(8,TextK9.getText());

            }
            else if(TextK10.isSelected() && !TextK9.isSelected()){
                TextK9.setSelected(false);
                TextK10.setSelected(true);
                pst.setString(8,TextK10.getText());
            }
            pst.setString(6,TextK5.getText());
            pst.setString(9,TextK7.getText());
            pst.setString(10,TextK8.getText());
            pst.setString(11,RodzajWycieczki.getValue());
            pst.setString(12, TextK11.getText());
            pst.setString(3,TextK2.getText());
           if(id.getText().isEmpty()||TextK2.getText().isEmpty()||TextK3.getText().isEmpty()|TextK5.getText().isEmpty()||TextK8.getText().isEmpty()||((!TextK9.isSelected()) && !TextK10.isSelected())||((TextK9.isSelected()) && TextK10.isSelected())){
               throw new Exception();
           }
            else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
                WyswietlWycieczki();
                id.clear();
                TextK2.clear();
                TextK3.clear();
                TextK5.clear();
                TextK7.clear();
                TextK8.clear();
                TextK9.setSelected(false);
                TextK10.setSelected(false);
                TextK11.clear();
                TextK12.clear();
                RodzajWycieczki.setValue("Rodzaj wycieczki");
                RodzajTransportu.setValue("Rodzaj transportu");
                RodzajZakwaterowania.setValue("Rodzaj zakwaterowania");
           }

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad dodawania! "+e);
        }
    }

    public void UsunWycieczke(){
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        String danee="DELETE FROM wycieczki WHERE id_wycieczki=?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1,id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Usunieto pomyslnie!");
            WyswietlWycieczki();
            id.clear();
            TextK2.clear();
            TextK3.clear();
            TextK5.clear();
            TextK7.clear();
            TextK8.clear();
            TextK9.setSelected(false);
            TextK10.setSelected(false);
            TextK11.clear();
            TextK12.clear();
            RodzajWycieczki.setValue("Rodzaj wycieczki");
            RodzajTransportu.setValue("Rodzaj transportu");
            RodzajZakwaterowania.setValue("Rodzaj zakwaterowania");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy usuwaniu! "+e);
        }
    }

    public void EdytujWycieczke(){
    String danee;
        try{

            String val1=id.getText();
            String val2=TextK12.getText();
            String val3=TextK2.getText();
            String val4=TextK3.getText();
            String val5 = null;
            if(RodzajTransportu.getValue().equals("Samolot")) {
                val5="1";
            } else  if(RodzajTransportu.getValue().equals("Prom")) {
                 val5="2";
            }else  if(RodzajTransportu.getValue().equals("Pociąg")) {
                val5="3";
            }else  if(RodzajTransportu.getValue().equals("Autokar")) {
                val5="4";
            }
            String val6=TextK5.getText();
            String val7 = null;
            if(RodzajZakwaterowania.getValue().equals("Hotel")) {
                 val7="1";
            }else  if(RodzajZakwaterowania.getValue().equals("Motel")) {
               val7="2";
            }else  if(RodzajZakwaterowania.getValue().equals("Kurort")) {
               val7="3";
            }else  if(RodzajZakwaterowania.getValue().equals("Domek jednorodzinny")) {
                 val7="4";
            }
            String val8=TextK8.getText();
            String val9=TextK9.getText();
            String val10=TextK10.getText();
            String val12=RodzajWycieczki.getValue();
            String val11=TextK11.getText();
            String val13=TextK7.getText();

            if(TextK9.isSelected() && !TextK10.isSelected()){
                TextK9.setSelected(true);
                TextK10.setSelected(false);
                danee = "UPDATE wycieczki SET id_wycieczki='"+val1+"',nazwa='" + val2 + "',miejsce='" + val3 + "',cena='"
                        + val4 + "',id_transport='" + val5 + "',czas='" + val6 + "',id_zakwaterowanie='" + val7 + "',wyzywienie='" + val9 + "',premium='" + val13 + "',atrakcje='" + val8 +
                        "',rodzajWycieczki='"+val12+"',iloscDni='"+ val11+"'WHERE id_wycieczki='" + val1 + "'";
                pst = (PreparedStatement) connectDB.prepareStatement(danee);

            }
            else if(TextK10.isSelected() && !TextK9.isSelected()){
                TextK9.setSelected(false);
                TextK10.setSelected(true);
                danee = "UPDATE wycieczki SET id_wycieczki='"+val1+"',nazwa='" + val2 + "',miejsce='" + val3 + "',cena='"
                        + val4 + "',id_transport='" + val5 + "',czas='" + val6 + "',id_zakwaterowanie='" + val7 + "',wyzywienie='" + val10 + "',premium='" + val13 + "',atrakcje='" + val8 +
                        "',rodzajWycieczki='"+val12+"',iloscDni='"+ val11+"' WHERE id_wycieczki" + "='" + val1 + "'";
                pst = (PreparedStatement) connectDB.prepareStatement(danee);
            }

            if(id.getText().isEmpty()||TextK2.getText().isEmpty()||TextK3.getText().isEmpty()||TextK5.getText().isEmpty()||TextK8.getText().isEmpty()||((!TextK9.isSelected()) && !TextK10.isSelected())||((TextK9.isSelected()) && TextK10.isSelected())){
                throw new Exception();
            }else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Edycja zakonczona pomyslnie!");
                WyswietlWycieczki();
                id.clear();
                TextK2.clear();
                TextK3.clear();
                TextK5.clear();
                TextK7.clear();
                TextK8.clear();
                TextK9.setSelected(false);
                TextK10.setSelected(false);
                TextK11.clear();
                RodzajWycieczki.setValue("Rodzaj wycieczki");
                RodzajTransportu.setValue("Rodzaj transportu");
                RodzajZakwaterowania.setValue("Rodzaj zakwaterowania");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Blad przy edycji! "+e);
        }
    }

    public void zamknijButtonOnAction() {
        Stage stage = (Stage) ZamknijButton.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Pracownik.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1720.0D, 880.0D));
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void ChoiceBoxWycieczki(){
        listWycieczki.removeAll(listWycieczki);
        String a="Rodzaj wycieczki";

        String b="Last Minute";
        String c="Promocja";
        String d="Egzotyka";
        listWycieczki.addAll(b,c,d);
        RodzajWycieczki.getItems().addAll(listWycieczki);
        RodzajWycieczki.setValue(a);
    }

    private void ChoiceBoxTransport(){
        listTransport.removeAll(listTransport);
        String a="Rodzaj transportu";

        String b="Samolot";
        String c="Prom";
        String d="Pociąg";
        String e="Autokar";
        listTransport.addAll(b,c,d,e);
        RodzajTransportu.getItems().addAll(listTransport);
        RodzajTransportu.setValue(a);
    }

    private void ChoiceBoxZakwaterowania(){
        listZakwaterowania.removeAll(listZakwaterowania);
        String a="Rodzaj zakwaterowania";

        String b="Hotel";
        String c="Motel";
        String d="Kurort";
        String e="Domek jednorodzinny";
        listZakwaterowania.addAll(b,c,d,e);
        RodzajZakwaterowania.getItems().addAll(listZakwaterowania);
        RodzajZakwaterowania.setValue(a);
    }

    public void DodajButtonOnActionEvent(){ DodajWycieczke();}

    public void UsunButtonOnActionEvent(){ UsunWycieczke();}

    public void EdytujButtonOnActionEvent(){ EdytujWycieczke();}

    public void getSelected(){
        index=Tab1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        id.setText(idp.getCellData(index).toString());
        TextK12.setText(nzw.getCellData(index));
        TextK2.setText(ms.getCellData(index));
        TextK3.setText(cn.getCellData(index).toString());
        TextK5.setText(czs.getCellData(index));
        TextK9.setText(wyz.getCellData(index));
        TextK10.setText(wyz.getCellData(index));
        if(TextK9.getText().equals("Tak"))
        {
            TextK9.setSelected(true);
            TextK10.setText("Nie");
            TextK10.setSelected(false);

        }else
        {
            TextK9.setSelected(false);
            TextK9.setText("Tak");
            TextK10.setSelected(true);
        }
        TextK8.setText(prem.getCellData(index));
        TextK7.setText(atr.getCellData(index));
        TextK11.setText(ilDn.getCellData(index).toString());
        RodzajWycieczki.setValue(rodz.getCellData(index));
        RodzajTransportu.setValue(tran.getCellData(index));
        RodzajZakwaterowania.setValue(zak.getCellData(index));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WyswietlWycieczki();
        ChoiceBoxWycieczki();
        ChoiceBoxZakwaterowania();
        ChoiceBoxTransport();
    }
}

