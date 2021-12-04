package sample;/*
 * @project OfertyWycieczek.fxml
 * @author kola
 */

import java.sql.Time;
import java.sql.Timestamp;

public class DaneDoWycieczek {
    String miejsce, zakwaterowanie,transport,atrakcje,premium;
    float cena;
    int id;
    String czasPodrozy;
    public DaneDoWycieczek(int id,String miejsce, float cena,  String transport, String czasPodrozy, String zakwaterowanie, String atrakcje, String premium){
        this.id=id;
        this.miejsce=miejsce;
        this.zakwaterowanie=zakwaterowanie;
        this.transport=transport;
        this.atrakcje=atrakcje;
        this.premium=premium;
        this.czasPodrozy=czasPodrozy;
        this.cena=cena;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getMiejsce() {
        return miejsce;
    }

    public void setMiejsce(String miejsce){
        this.miejsce=miejsce;
    }

    public String getZakwaterowanie() {
        return zakwaterowanie;
    }

    public void setZakwaterowanie(String zakwaterowanie){
        this.zakwaterowanie=zakwaterowanie;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport){
        this.transport=transport;
    }

    public String getAtrakcje() {
        return atrakcje;
    }

    public void setAtrakcje(String atrakcje){
        this.atrakcje=atrakcje;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium){
        this.premium=premium;
    }

    public String getCzasPodrozy() {
        return czasPodrozy;
    }

    public void setCzasPodrozy(String czasPodrozy){
        this.czasPodrozy=czasPodrozy;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena){
        this.cena=cena;
    }
}
