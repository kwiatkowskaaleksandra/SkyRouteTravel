package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import java.util.Date;

public class DaneDoFaktur {
    String imie, nazwisko,email,miejscowosc,platnosc,rodzajWycieczki;
    int id,telefon,numer,kod;
    Date data;

    public DaneDoFaktur(int id,String imie, String nazwisko,String email,int telefon,String miejscowosc,int numer,int kod,String platnosc,Date data,String rodzajWycieczki){
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.telefon=telefon;
        this.miejscowosc=miejscowosc;
        this.numer=numer;
        this.kod=kod;
        this.platnosc=platnosc;
        this.data=data;
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

    public int getTelefon(){
        return this.telefon;
    }

    public void setTelefon(int telefon){
        this.telefon=telefon;
    }

    public String getMiejscowosc(){
        return this.miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc){
        this.miejscowosc=miejscowosc;
    }

    public int getNumer(){
        return this.numer;
    }

    public void setNumer(int numer){
        this.numer=numer;
    }

    public int getKod(){
        return this.kod;
    }

    public void setKod(int kod){
        this.kod=kod;
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
}
