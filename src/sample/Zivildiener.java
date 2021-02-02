package sample;

public class Zivildiener {
    private String name;
    private int stunden;
    private boolean fahrer;
    private boolean tag;
    private boolean nachtdienst;
    private boolean eingesetzt;

    public Zivildiener(String name, int stunden, String fahrer) {
        this.name = name;
        this.stunden = stunden;
        if (fahrer.equals("ja") || fahrer.equals("Ja") || fahrer.equals("JA")){
            this.fahrer = true;
        } else {
            this.fahrer = false;
        }
    }

    public void subStunden(int stunden) {
        this.stunden -= stunden;
    }

    public boolean isEingesetzt() {
        return eingesetzt;
    }

    public void setEingesetzt(boolean eingesetzt) {
        this.eingesetzt = eingesetzt;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStunden() {
        return stunden;
    }

    public void setStunden(int stunden) {
        this.stunden = stunden;
    }

    public boolean isFahrer() {
        return fahrer;
    }

    public void setFahrer(boolean fahrer) {
        this.fahrer = fahrer;
    }

    public boolean isNachtdienst() {
        return nachtdienst;
    }

    public void setNachtdienst(boolean nachtdienst) {
        this.nachtdienst = nachtdienst;
    }
}
