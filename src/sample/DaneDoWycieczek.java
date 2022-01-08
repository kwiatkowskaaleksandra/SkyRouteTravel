package sample;/*
 * @project OfertyWycieczek.fxml
 * @author kola
 */

import javax.swing.text.html.ImageView;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class DaneDoWycieczek {
    String nazwa,miejsce, zakwaterowanie,transport,atrakcje,premium,wyzywienie,czasPodrozy,rodzaj;
    float cena, cenaP;
    int id, iloscDni;

    public DaneDoWycieczek(int id,String nazwa, String miejsce, float cena,  String transport, String czasPodrozy, String zakwaterowanie, String wyzywienie, String premium, float cenaP,String atrakcje, String rodzaj, int iloscDni){
        this.id=id;
        this.nazwa=nazwa;
        this.miejsce=miejsce;
        this.zakwaterowanie=zakwaterowanie;
        this.wyzywienie=wyzywienie;
        this.transport=transport;
        this.atrakcje=atrakcje;
        this.premium=premium;
        this.cenaP=cenaP;
        this.czasPodrozy=czasPodrozy;
        this.cena=cena;
        this.rodzaj=rodzaj;
        this.iloscDni=iloscDni;

    }

    public float getCenaP() {
        return cenaP;
    }

    public void setCenaP(float cenaP) {
        this.cenaP = cenaP;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa){
        this.nazwa=nazwa;
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

    public String getWyzywienie() { return wyzywienie; }

    public void setWyzywienie(String wyzywienie) { this.wyzywienie = wyzywienie; }

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

    public int getIloscDni() { return iloscDni; }

    public void setIloscDni(int iloscDni) { this.iloscDni = iloscDni; }

    public String getRodzaj() { return rodzaj; }

    public void setRodzaj(String rodzaj) { this.rodzaj = rodzaj; }


}
