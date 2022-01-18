package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

import java.sql.Date;

public class DaneDoRezerwacji {
    int id,ileDzieci,ileDoroslych;
    String nazwisko,wycieczka,platnosc,status;
    Date termin;
    Float cena;
    DaneDoRezerwacji(int id,String nazwisko,String wycieczka,Date termin, int ileDoroslych, int ileDzieci, Float cena, String platnosc, String status){
        this.id=id;
        this.nazwisko=nazwisko;
        this.wycieczka=wycieczka;
        this.termin=termin;
        this.ileDzieci=ileDzieci;
        this.ileDoroslych=ileDoroslych;
        this.cena=cena;
        this.platnosc=platnosc;
        this.status=status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getWycieczka() {
        return wycieczka;
    }

    public void setWycieczka(String wycieczka) {
        this.wycieczka = wycieczka;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }

    public Date getTermin() {
        return termin;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public Float getCena() {
        return cena;
    }

    public int getIleDzieci() {
        return ileDzieci;
    }

    public void setIleDzieci(int ileDzieci) {
        this.ileDzieci = ileDzieci;
    }

    public void setIleDoroslych(int ileDoroslych) {
        this.ileDoroslych = ileDoroslych;
    }

    public int getIleDoroslych() {
        return ileDoroslych;
    }

    public void setPlatnosc(String platnosc) {
        this.platnosc = platnosc;
    }

    public String getPlatnosc() {
        return platnosc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
