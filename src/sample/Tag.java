package sample;

import java.util.zip.DeflaterInputStream;

public class Tag {
    private Dienst kurz;
    private Dienst lang1;
    private Dienst lang2;
    private Dienst nacht;

    public Tag(Dienst kurz, Dienst lang1, Dienst lang2, Dienst nacht) {
        this.kurz = kurz;
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.nacht = nacht;
    }

    public Dienst getKurz() {
        return kurz;
    }

    public void setKurz(Dienst kurz) {
        this.kurz = kurz;
    }

    public Dienst getLang1() {
        return lang1;
    }

    public void setLang1(Dienst lang1) {
        this.lang1 = lang1;
    }

    public Dienst getLang2() {
        return lang2;
    }

    public void setLang2(Dienst lang2) {
        this.lang2 = lang2;
    }

    public Dienst getNacht() {
        return nacht;
    }

    public void setNacht(Dienst nacht) {
        this.nacht = nacht;
    }
}
