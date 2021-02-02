package sample;

public class Dienst {
    private String ef;
    private String tf;
    private int laenge;
    private String art;

    public Dienst(String ef, String tf, int laenge, String art) {
        this.ef = ef;
        this.tf = tf;
        this.laenge = laenge;
        this.art = art;
    }

    public String getEf() {
        return ef;
    }

    public void setEf(String ef) {
        this.ef = ef;
    }

    public String getTf() {
        return tf;
    }

    public void setTf(String tf) {
        this.tf = tf;
    }

    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
}
