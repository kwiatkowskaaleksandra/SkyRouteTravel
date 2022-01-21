package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import java.sql.Date;

public class DaneOpinie {
    String tresc,imie,nazwisko,miejsce,nazwa;
    Date data;
    int idop;
   public DaneOpinie(int idop,String imie, String nazwisko, String miejsce, String nazwa,String tresc, Date data){
        this.data=data;
        this.idop=idop;
        this.tresc=tresc;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.nazwa=nazwa;
        this.miejsce=miejsce;
    }


    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public String getTresc() {
        return tresc;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdop() {
        return idop;
    }

    public void setIdop(int idop) {
        this.idop = idop;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }

    public String getMiejsce() {
        return miejsce;
    }

}
