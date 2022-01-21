package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

public class KontrolerKlient  implements Initializable {
    @FXML
    private Button IdWyloguj;
    public Button WycieczkiKlient;
    public Button Profil;
    public Button Konto;
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public Label tekst1;
    public Label tekst2;
    public Label tekst3;
    public Label tekst4;
    @FXML
    private Button wiad;
    @FXML
    private Button Promocja;
    @FXML
    private Button LastMinute;
    @FXML
    private Button Egzotyka;
    @FXML
    private Button rezerw;

    public void IdWylogujOnAciotn(javafx.event.ActionEvent event) {
        Stage stage = (Stage) IdWyloguj.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Home.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1910, 1000));
            menuStage.setTitle("SKY ROUTE TRAVEL");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WycieczkiOnAction(javafx.event.ActionEvent event) {
        Stage stage = (Stage) WycieczkiKlient.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820, 980));
            menuStage.setTitle("Oferty wycieczek");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WycieczkiPromocjeOnAction(){
        Stage stage = (Stage) Promocja.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiPromocjeKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820, 980));
            menuStage.setTitle("Oferty wycieczek");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WycieczkiEgzotykaOnAction(){
        Stage stage = (Stage) Egzotyka.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiEgzotykaKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820, 980));
            menuStage.setTitle("Oferty wycieczek");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void WycieczkiLastMinuteOnAction(){
        Stage stage = (Stage) LastMinute.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WycieczkiLastMinuteKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820, 980));
            menuStage.setTitle("Oferty wycieczek");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void ProfilOnAction(javafx.event.ActionEvent event) throws SQLException {
        Stage stage = (Stage) Profil.getScene().getWindow();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Profil.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1220, 740));
            menuStage.setTitle("Profil");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void Wiadomosci(ActionEvent event) {
        Stage stage = (Stage) wiad.getScene().getWindow();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/WiadomosciKlient.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1250,650));
            menuStage.setTitle("Wiadomości");
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Random rand = new Random();
        Statement stat1 = null;
        int r = 0;
        try {
            stat1 = connectDB.createStatement();
            r = rand.nextInt(13)+1;
            System.out.println(r);
            String maxID = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet max = null;

            max = stat1.executeQuery(maxID);
            String foto = null, nazwa = null;
            float cena = 0;
            int id = 0;
            while (max.next()) {
                id = max.getInt("id_wycieczki");
                foto = max.getString("zdjecie");
                nazwa = max.getString("nazwa");
                cena = max.getFloat("cena");
                System.out.println(foto);
                File kat = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki[] = kat.list();
                for (int i = 0; i < pliki.length; i++) {

                    if ((pliki[i].equals(foto))) {
                        System.out.println("mam taki");
                        image1.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki[i]));
                        tekst1.setText(nazwa+ " TYLKO " + cena + " zł");
                    }
                }
            }

            Statement stat2 = null;
            stat2 = connectDB.createStatement();
r+=1;
            String maxID2 = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet max2 = null;
            System.out.println(r);
            max2 = stat2.executeQuery(maxID2);
            String foto2 = null, nazwa2 = null;
            float cena2 = 0;
            int id2 = 0;
            while (max2.next()) {
                id2 = max2.getInt("id_wycieczki");
                foto2 = max2.getString("zdjecie");
                nazwa2 = max2.getString("nazwa");
                cena2 = max2.getFloat("cena");
                System.out.println(foto);
                File kat2 = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki2[] = kat2.list();
                for (int i = 0; i < pliki2.length; i++) {

                    if ((pliki2[i].equals(foto2))) {
                        System.out.println("mam taki");
                        image2.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki2[i]));
                        tekst2.setText(nazwa2 + " TYLKO " + cena2 + " zł");
                    }
                }
            }

            Statement stat3 = null;
            r+=1;
            stat3= connectDB.createStatement();
            System.out.println(r);
            String maxID3 = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet maxIDZ = null;

            maxIDZ = stat3.executeQuery(maxID3);
            String foto3 = null, nazwa3 = null;
            float cena3 = 0;
            int id3 = 0;
            while (maxIDZ.next()) {
                id3 = maxIDZ.getInt("id_wycieczki");
                foto3 = maxIDZ.getString("zdjecie");
                nazwa3 = maxIDZ.getString("nazwa");
                cena3= maxIDZ.getFloat("cena");
                System.out.println(foto3);
                File kat3 = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki3[] = kat3.list();
                for (int i = 0; i < pliki3.length; i++) {

                    if ((pliki3[i].equals(foto3))) {
                        System.out.println("mam taki");
                        image3.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki3[i]));
                        tekst3.setText(nazwa3 + " TYLKO " + cena3 + " zł");
                    }
                }
            }

            Statement stat4 = null;
            r+=1;
            stat4= connectDB.createStatement();
            System.out.println(r);
            String maxID4 = "SELECT * FROM wycieczki where id_wycieczki='" + r + "'";
            ResultSet maxIDZd = null;

            maxIDZd = stat4.executeQuery(maxID4);
            String foto4 = null, nazwa4 = null;
            float cena4 = 0;
            int id4 = 0;
            while (maxIDZd.next()) {
                id4 = maxIDZd.getInt("id_wycieczki");
                foto4 = maxIDZd.getString("zdjecie");
                nazwa4 = maxIDZd.getString("nazwa");
                cena4= maxIDZd.getFloat("cena");
                System.out.println(foto4);
                File kat4 = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki4[] = kat4.list();
                for (int i = 0; i < pliki4.length; i++) {

                    if ((pliki4[i].equals(foto4))) {
                        System.out.println("mam taki");
                        image4.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki4[i]));
                        tekst4.setText(nazwa4 + " TYLKO " + cena4 + " zł");
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}