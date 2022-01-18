package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.util.ResourceBundle;

public class KontrolerKlient  implements Initializable {
    @FXML
    private Button IdWyloguj;
    public Button WycieczkiKlient;
    public Button Profil;
    public Button Konto;
    public ImageView image;
    public Image urlIm;

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

    public void ProfilOnAction(javafx.event.ActionEvent event) {
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
        Poloczenie connectNow = new Poloczenie();
        Connection connectDB = connectNow.getConnection();

        Statement stat1 = null;
        try {
            stat1 = connectDB.createStatement();

            String maxID = "SELECT zdjecie FROM wycieczki WHERE id_wycieczki=3;";
            ResultSet max = null;

            max = stat1.executeQuery(maxID);
            String foto = null;
            while (max.next()) {

                foto = max.getString("zdjecie");
                System.out.println(foto);
                File kat = new File("C:\\Users\\48732\\Desktop\\BiuroPodróży\\src\\obrazy");
                String pliki[] = kat.list();
                for (int i = 0; i < pliki.length; i++) {

                    if ((pliki[i].equals(foto))) {
                        System.out.println("mam taki");
                        image.setImage(new Image("file:///C:/Users/48732/Desktop/BiuroPodróży/src/obrazy/" + pliki[i]));

                    }


                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();


        }
    }
}