package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import java.util.Date;

public class DaneDoFaktur {
    String imie, nazwisko,email,nazwa,platnosc,rodzajWycieczki;
    int id;
    Date data;
    float kwota;

    public DaneDoFaktur(int id, String imie, String nazwisko, String email, String platnosc, Date data, float kwota, String nazwa, String rodzajWycieczki){
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.platnosc=platnosc;
        this.data=data;
        this.kwota=kwota;
        this.nazwa=nazwa;
        this.rodzajWycieczki=rodzajWycieczki;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getImie(){
        return this.imie;
    }

    public void setImie(String imie){
        this.imie=imie;
    }

    public String getNazwisko(){
        return this.nazwisko;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getPlatnosc(){
        return this.platnosc;
    }

    public void setPlatnosc(String platnosc){
        this.platnosc=platnosc;
    }

    public Date getData(){
        return this.data;
    }

    public void setData(Date data){
        this.data=data;
    }

    public String getRodzajWycieczki(){
        return this.rodzajWycieczki;
    }

    public void setRodzajWycieczki(String rodzajWycieczki){
        this.rodzajWycieczki=rodzajWycieczki;
    }

    public float getKwota() {
        return kwota;
    }

    public void setKwota(float kwota) {
        this.kwota = kwota;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }
}
