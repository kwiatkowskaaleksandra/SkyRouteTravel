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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class Wycieczka implements Initializable {
    PreparedStatement pst = null;

    @FXML
    private TableView<DaneDoWycieczek> Tab1;
    @FXML
    private TableColumn<DaneDoWycieczek,Integer> idw;
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
    private TableColumn<DaneDoWycieczek,Float> cP;
    @FXML
    private TableColumn<DaneDoWycieczek,String> czs;
    @FXML
    private TableColumn<DaneDoWycieczek,Float> cn;
    @FXML
    private TableColumn<DaneDoWycieczek,Integer> ilDn;
    @FXML
    private TableColumn<DaneDoWycieczek,String> idZ;
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
    private Button zdjecie;
    @FXML
    private TextField cnPr;
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
    private TextField nazwZdj;
    @FXML
    private TextField id;
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

        String danee = "SELECT id_wycieczki,nazwa,miejsce,w.cena,t.rodzaj,czas, z.rodzaj, wyzywienie, premium, w.cenaPrem, atrakcje, rodzajWycieczki,iloscDni, zdjecie FROM wycieczki w, zakwaterowanie z, transport t WHERE w.id_transport=t.id_transport AND w.id_zakwaterowanie=z.id_zakwaterowanie ORDER BY id_wycieczki ASC";
        String zdj=null;
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
                float cenaPrem=rs.getFloat("w.cenaPrem");
                String atrakcje =rs.getString("atrakcje");
                String rodzaj = rs.getString("rodzajWycieczki");
                int iloscDni = rs.getInt("iloscDni");
                zdj = rs.getString("zdjecie");

                daneDoWycieczek = new DaneDoWycieczek(id1,nazwa, miejsce,cena,transport,czasPodrozy,zakwaterowanie,wyzywienie,premium,cenaPrem,atrakcje,rodzaj,iloscDni,zdj);
                WczTab.add(daneDoWycieczek);
            }

           // st.close();
        } catch (Exception e) {
            System.out.println("There is an Exception.");
            System.out.println(e.getMessage());
        }
        idw.setCellValueFactory(new PropertyValueFactory<>("id"));
        nzw.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        ms.setCellValueFactory(new PropertyValueFactory<>("miejsce"));
        cn.setCellValueFactory(new PropertyValueFactory<>("cena"));
        tran.setCellValueFactory(new PropertyValueFactory<>("transport"));
        czs.setCellValueFactory(new PropertyValueFactory<>("czasPodrozy"));
        zak.setCellValueFactory(new PropertyValueFactory<>("zakwaterowanie"));
        wyz.setCellValueFactory(new PropertyValueFactory<>("wyzywienie"));
        prem.setCellValueFactory(new PropertyValueFactory<>("premium"));
        cP.setCellValueFactory(new PropertyValueFactory<>("cenaP"));
        atr.setCellValueFactory(new PropertyValueFactory<>("atrakcje"));
        rodz.setCellValueFactory(new PropertyValueFactory<>("rodzaj"));
        ilDn.setCellValueFactory(new PropertyValueFactory<>("iloscDni"));
        idZ.setCellValueFactory(new PropertyValueFactory<>("zdjecie"));

        Tab1.setItems(WczTab);
    }

    public void WybierzZdjecie() {
        FileChooser fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("all","*.*"),new FileChooser.ExtensionFilter("jpg","*.jpg"),new FileChooser.ExtensionFilter("png","*.png"),new FileChooser.ExtensionFilter("jpeg","*.jpeg"));
        fileChooser.setInitialDirectory(new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy"));
        File selectedFile=fileChooser.showOpenDialog(zdjecie.getScene().getWindow());
        nazwZdj.setText(selectedFile.getName());

    }

    public void DodajWycieczke() throws SQLException {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        Statement stat1=null;
        stat1=connectDB.createStatement();
        String maxID="SELECT id_wycieczki FROM wycieczki ORDER BY  id_wycieczki ASC";
        ResultSet max=stat1.executeQuery(maxID);
        int idw = 0;
        while (max.next()){
            idw=max.getInt("id_wycieczki");
        }

        String danee="INSERT INTO wycieczki(id_wycieczki,nazwa,miejsce,cena,id_transport,czas,id_zakwaterowanie,wyzywienie,premium,cenaPrem, atrakcje,rodzajWycieczki,iloscDni, zdjecie)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1, String.valueOf(idw+1));
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
            pst.setString(11,TextK7.getText());
            pst.setString(10,cnPr.getText());
            pst.setString(9,TextK8.getText());
            pst.setString(12,RodzajWycieczki.getValue());
            pst.setString(13, TextK11.getText());
            pst.setString(3,TextK2.getText());
            pst.setString(14,nazwZdj.getText());
           if(TextK2.getText().isEmpty()||cnPr.getText().isEmpty()||TextK3.getText().isEmpty()|TextK5.getText().isEmpty()||TextK8.getText().isEmpty()||((!TextK9.isSelected()) && !TextK10.isSelected())||((TextK9.isSelected()) && TextK10.isSelected())){
               throw new Exception();
           }
            else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Dodano pomyslnie!");
                WyswietlWycieczki();
                TextK2.clear();
                TextK3.clear();
                TextK5.clear();
                TextK7.clear();
                TextK8.clear();
                TextK9.setSelected(false);
                TextK10.setSelected(false);
                TextK11.clear();
                TextK12.clear();
                cnPr.clear();
                nazwZdj.clear();
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

        String danee="DELETE FROM wycieczki WHERE nazwa= ? AND miejsce=? AND cena= ?";
        try {
            pst=(PreparedStatement) connectDB.prepareStatement(danee);
            pst.setString(1,TextK12.getText());
            pst.setString(2,TextK2.getText());
            pst.setString(3,TextK3.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Usunieto pomyslnie!");
            WyswietlWycieczki();
            TextK2.clear();
            TextK3.clear();
            TextK5.clear();
            TextK7.clear();
            TextK8.clear();
            TextK9.setSelected(false);
            TextK10.setSelected(false);
            TextK11.clear();
            TextK12.clear();
            cnPr.clear();
            nazwZdj.clear();
            RodzajWycieczki.setValue("Rodzaj wycieczki");
            RodzajTransportu.setValue("Rodzaj transportu");
            RodzajZakwaterowania.setValue("Rodzaj zakwaterowania");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Blad przy usuwaniu! "+e);
        }
    }

    public void EdytujWycieczke() throws SQLException {
    String danee;

        try{

            String nz=TextK12.getText();
            String ms=TextK2.getText();
            String cn=TextK3.getText();
            String rT = null;
            if(RodzajTransportu.getValue().equals("Samolot")) {
                rT="1";
            } else  if(RodzajTransportu.getValue().equals("Prom")) {
                rT="2";
            }else  if(RodzajTransportu.getValue().equals("Pociąg")) {
                rT="3";
            }else  if(RodzajTransportu.getValue().equals("Autokar")) {
                rT="4";
            }
            String czs=TextK5.getText();
            String rZ = null;
            if(RodzajZakwaterowania.getValue().equals("Hotel")) {
                 rZ="1";
            }else  if(RodzajZakwaterowania.getValue().equals("Motel")) {
                rZ="2";
            }else  if(RodzajZakwaterowania.getValue().equals("Kurort")) {
                rZ="3";
            }else  if(RodzajZakwaterowania.getValue().equals("Domek jednorodzinny")) {
                rZ="4";
            }
            String cT=TextK9.getText();
            String cN=TextK10.getText();
            String rW=RodzajWycieczki.getValue();
            String ilD=TextK11.getText();
            String atr=TextK7.getText();
            String cP=cnPr.getText();
            String pr=TextK8.getText();
            String zdj=nazwZdj.getText();

            if(TextK9.isSelected() && !TextK10.isSelected()){
                TextK9.setSelected(true);
                TextK10.setSelected(false);


                danee = "UPDATE wycieczki SET id_wycieczki='"+id.getText()+"' ,nazwa='" + nz + "',miejsce='" + ms + "',cena='"
                        + cn + "',id_transport='" + rT + "',czas='" + czs + "',id_zakwaterowanie='" + rZ + "',wyzywienie='" + cT + "',premium='" + pr+"',cenaPrem='"+cP + "',atrakcje='" + atr +
                        "',rodzajWycieczki='"+rW+"',iloscDni='"+ ilD+"',zdjecie='"+zdj+"'WHERE id_wycieczki='"+id.getText()+"'";
                pst = (PreparedStatement) connectDB.prepareStatement(danee);

            }
            else if(TextK10.isSelected() && !TextK9.isSelected()){
                TextK9.setSelected(false);
                TextK10.setSelected(true);


                danee = "UPDATE wycieczki SET id_wycieczki='"+id.getText()+"' ,nazwa='" + nz + "',miejsce='" + ms + "',cena='"
                        + cn + "',id_transport='" + rT + "',czas='" + czs + "',id_zakwaterowanie='" + rZ + "',wyzywienie='" + cN + "',premium='" + pr+"',cenaPrem='"+cP + "',atrakcje='" + atr +
                        "',rodzajWycieczki='"+rW+"',iloscDni='"+ ilD+"',zdjecie='"+zdj+"'WHERE id_wycieczki='"+id.getText()+"'";
                pst = (PreparedStatement) connectDB.prepareStatement(danee);
                System.out.println(id.getText());
            }

            if(TextK2.getText().isEmpty()||TextK3.getText().isEmpty()||TextK5.getText().isEmpty()||TextK8.getText().isEmpty()||((!TextK9.isSelected()) && !TextK10.isSelected())||((TextK9.isSelected()) && TextK10.isSelected())){
                throw new Exception();
            }else{
                pst.execute();
                JOptionPane.showMessageDialog(null,"Edycja zakonczona pomyslnie!");
                WyswietlWycieczki();
                TextK2.clear();
                TextK3.clear();
                TextK5.clear();
                TextK7.clear();
                TextK8.clear();
                TextK9.setSelected(false);
                TextK10.setSelected(false);
                TextK11.clear();
                cnPr.clear();
                TextK12.clear();
                nazwZdj.clear();
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

    public void DodajButtonOnActionEvent() throws SQLException { DodajWycieczke();}

    public void UsunButtonOnActionEvent(){ UsunWycieczke();}

    public void EdytujButtonOnActionEvent() throws SQLException { EdytujWycieczke();}

    public void getSelected(){
        index=Tab1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        id.setText(String.valueOf(idw.getCellData(index)));
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
        cnPr.setText(cP.getCellData(index).toString());
        TextK7.setText(atr.getCellData(index));
        TextK11.setText(ilDn.getCellData(index).toString());
        RodzajWycieczki.setValue(rodz.getCellData(index));
        RodzajTransportu.setValue(tran.getCellData(index));
        RodzajZakwaterowania.setValue(zak.getCellData(index));
        nazwZdj.setText(idZ.getCellData(index));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WyswietlWycieczki();
        ChoiceBoxWycieczki();
        ChoiceBoxZakwaterowania();
        ChoiceBoxTransport();
    }
}

