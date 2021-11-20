/*package sample;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.image.Image;


public class Slider {
    private static FadeTransition transition;
    private final StackPane stackPane=new StackPane();
    private final AnchorPane backPane=new AnchorPane();
    private final AnchorPane frontPane=new AnchorPane();
    private final ObservableList<Image> backImages= FXCollections.observableArrayList();
    private final ObservableList<Image> frontImages= FXCollections.observableArrayList();
    private int backIndex=0;
    private int frontIndex=0;

public Slider(){
    initialize();
}
private void initialize(){
    this.stackPane.getChildren().addAll(backPane, frontPane);
    frontPane.toFront();;
    frontPane.setOpacity(0);
    backPane.toBack();
    AnchorUtil.setAnchor(stackPane,0.0,0.0,0.0,0.0);
    getChildren().add(stackPane);
}
private void setBackgroundImage(Slider target, Image image)
    {
        BackgroundSize backgroundSize=new BackgroundSize(100,100,true,true,true,true);
        BackgroundImage backgroundImage=new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize);
        Background background =new Background(backgroundImage);
        target.setBackground(background);

    }
}*/