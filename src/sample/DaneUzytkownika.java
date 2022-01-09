package sample;/*
 * @project BiuroPodróży.iml
 * @author kola
 */

public class DaneUzytkownika {
    String imie, nazwisko, email, login, haslo,rola;
    int id;
    DaneUzytkownika(int id,String imie, String nazwisko, String email, String login, String haslo, String rola){
        this.id=id;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.email=email;
        this.login=login;
        this.haslo=haslo;
        this.rola=rola;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getImie() {
        return imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }
}
