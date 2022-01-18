package sample;

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
    public Label wyc1;
    public Label wyc2;

    public void IdWylogujOnAciotn(javafx.event.ActionEvent event) {
        Stage stage = (Stage) IdWyloguj.getScene().getWindow();
        stage.close();

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../javaFX/Home.fxml"));
            Stage menuStage = new Stage();
            menuStage.initStyle(StageStyle.DECORATED);
            menuStage.setScene(new Scene(root, 1820, 980));
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
            menuStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    /*    Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();
        Random rand = new Random();
        Statement stat1 = null;
        int r = 0, r2 = 0;
        try {
            stat1 = connectDB.createStatement();
            r = rand.nextInt(10);
            String maxID = "SELECT * FROM wycieczki where id_wycieczki='" + 12 + "'";
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
                        wyc1.setText("OKAZAJA: " + nazwa + " TYLKO " + cena + " zł");
                    }
                }
                Statement stat2 = null;
                r2 = rand.nextInt(10);
                stat2 = connectDB.createStatement();
                String maxID2 = "SELECT * FROM wycieczki where id_wycieczki='" + 4 + "'";
                ResultSet max2 = null;

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
                            image2.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki[i]));
                            wyc2.setText("NAJCZESCIEJ WYBIERANA: " + nazwa2 + " TYLKO " + cena2 + " zł");
                        }
                    }
                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }
}