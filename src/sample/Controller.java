package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Controller {
    private static final String DEFAULT_PFAD_DIENSTPLAN = "./Excel/Dienstplan.csv";
    private static final String DEFAULT_PFAD_PERSONAL = "./Excel/Personal.csv";
    private static final String DELIM = ";";
    private static final int NAME = 0;
    private static final int STUNDEN = 1;
    private static final int FAHRER = 2;
    private static final int TAG = 0;
    private static final int TYP = 1;
    private static final int KURZ = 2;
    private static final int LANG1 = 3;
    private static final int LANG2 = 4;
    private static final int NACHT = 5;
    private static final int ZEITKURZ = 6;
    private static final int ZEITLANG = 12;
    private static final String KURZDIENST = "kurz";
    private static final String LANGDIENST1 = "lang1";
    private static final String LANGDIENST2 = "lang2";
    private static final String TAGDIENST = "tag";
    private static final String NACHTDIENST = "nacht";
    private static final String EINGESETZT = "eingesetzt";
    private static final String VARIABLE = "x";
    private static final boolean FAHRERDIENST = true;
    private static final boolean KEINFAHRERDIENST = false;
    private static final int VERSUCHE = 100;

    public Button btn_dienstplan;
    public Button btn_personal;
    public TextField tf_dienstplan;
    public TextField tf_personal;
    public Button btn_start;
    private File file_dienstplan;
    private File file_personal;
    ArrayList<Zivildiener> personal = new ArrayList<>();
    ArrayList<Tag> woche = new ArrayList<>();

    public void initialize() {
        tf_dienstplan.setText(DEFAULT_PFAD_DIENSTPLAN);
        tf_personal.setText(DEFAULT_PFAD_PERSONAL);
    }

    public void ChooseDienstplan(ActionEvent actionEvent) {
        file_dienstplan = GetFile(DEFAULT_PFAD_DIENSTPLAN);
        tf_dienstplan.setText(file_dienstplan.getPath());
    }

    public void ChoosePersonal(ActionEvent actionEvent) {
        file_personal = GetFile(DEFAULT_PFAD_PERSONAL);
        tf_personal.setText(file_personal.getPath());
    }

    public File GetFile(String pfad) {
        JFileChooser fileChooser = new JFileChooser(pfad);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("nur CSV-Dateien", "csv");
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        return file;
    }

    public void StartProcessing(ActionEvent actionEvent) throws CustomException {
        if (tf_dienstplan.getText() == null || tf_dienstplan.getText().equals("") || tf_personal.getText() == null || tf_personal.getText().equals("")) {
            throw new CustomException("Pfade müssen angegeben werden!");
        } else {
            try {
                ReadPersonal();
                ReadDienstplan();
                String replace;
                Zivildiener zivildiener = null;
                for (Tag tag : woche) {
                    ResetDienstFlag(TAGDIENST);
                    if (tag.getKurz().getEf().equals(VARIABLE)) {
                        zivildiener = GetRandom(FAHRERDIENST, ZEITKURZ, TAGDIENST);
                        tag.getKurz().setEf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }
                    if (tag.getLang1().getEf().equals(VARIABLE)) {
                        zivildiener = GetRandom(FAHRERDIENST, ZEITLANG, TAGDIENST);
                        tag.getLang1().setEf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }
                    if (tag.getLang2().getEf().equals(VARIABLE)) {
                        zivildiener = GetRandom(FAHRERDIENST, ZEITLANG, TAGDIENST);
                        tag.getLang2().setEf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }

                    if (tag.getKurz().getTf().equals(VARIABLE)) {
                        zivildiener = GetRandom(KEINFAHRERDIENST, ZEITKURZ, TAGDIENST);
                        tag.getKurz().setTf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }
                    if (tag.getLang1().getTf().equals(VARIABLE)) {
                        zivildiener = GetRandom(KEINFAHRERDIENST, ZEITLANG, TAGDIENST);
                        tag.getLang1().setTf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }

                    if (tag.getLang2().getTf().equals(VARIABLE)) {
                        zivildiener = GetRandom(KEINFAHRERDIENST, ZEITLANG, TAGDIENST);
                        tag.getLang2().setTf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }

                    ResetDienstFlag(NACHTDIENST);
                    if (tag.getNacht().getEf().equals(VARIABLE)) {
                        zivildiener = GetRandom(FAHRERDIENST, ZEITLANG, NACHTDIENST);
                        tag.getNacht().setEf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }

                    if (tag.getNacht().getTf().equals(VARIABLE)) {
                        zivildiener = GetRandom(KEINFAHRERDIENST, ZEITLANG, NACHTDIENST);
                        tag.getNacht().setTf(zivildiener.getName());
                        System.out.println(zivildiener.getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException("File kann nicht gefunden werden!");
            }
            for (Tag tag : woche) {
                System.out.println("Kurz - EF: " + tag.getKurz().getEf());
                System.out.println("Kurz - TF: " + tag.getKurz().getTf());
                System.out.println("Lang1 - EF: " + tag.getLang1().getEf());
                System.out.println("Lang1 - TF: " + tag.getLang1().getTf());
                System.out.println("Lang2 - EF: " + tag.getLang2().getEf());
                System.out.println("Lang2 - TF: " + tag.getLang2().getTf());
                System.out.println("Nacht - EF: " + tag.getNacht().getEf());
                System.out.println("Nacht - TF: " + tag.getNacht().getTf());
            }
            for (Zivildiener zivildiener : personal) {
                System.out.println(zivildiener.getName() + "|" + zivildiener.getStunden());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("SUPER DU!");
            alert.setContentText("Dienstplan wurde erfolgreich erstellt!");
            alert.showAndWait();
        }
    }

    void ResetDienstFlag(String art) {
        for (Zivildiener zivildiener : personal) {
            switch (art) {
                case TAGDIENST:
                    zivildiener.setTag(false);
                    break;

                case NACHTDIENST:
                    zivildiener.setNachtdienst(false);
                    break;

                case EINGESETZT:
                    zivildiener.setEingesetzt(false);
                    break;
            }
        }
    }

    Zivildiener GetRandom(boolean fahrerdienst, int stunden, String art) {
        Random random = new Random();
        int i = 0;
        int versuche = 0;
        Zivildiener rv;

        if (art.equals(TAGDIENST)) {
            if (fahrerdienst) {
                do {
                    i = (int) (Math.random() * ((personal.size() - 1) - 0 + 1) + 0);
                    //i = random.nextInt((personal.size() - 1));
                    versuche++;
                } while (((personal.get(i).isFahrer() != true) || (personal.get(i).getStunden() < stunden) || (personal.get(i).isTag()) || (personal.get(i).isNachtdienst())) && (versuche <= VERSUCHE));
            } else {
                do {
                    i = (int) (Math.random() * (((personal.size() - 1) - 0) + 1) + 0);
                    versuche++;
                } while (((personal.get(i).getStunden() < stunden) || (personal.get(i).isTag()) || (personal.get(i).isNachtdienst())) && (versuche <= VERSUCHE));
            }
        } else {
            if (fahrerdienst) {
                do {
                    i = (int) (Math.random() * (((personal.size() - 1) - 0) + 1) + 0);
                    versuche++;
                } while (((personal.get(i).isFahrer() != true) || (personal.get(i).getStunden() < stunden) || (personal.get(i).isTag()) || (personal.get(i).isNachtdienst())) && (versuche <= VERSUCHE));
            } else {
                do {
                    i = (int) (Math.random() * (((personal.size() - 1) - 0) + 1) + 0);
                    versuche++;
                } while (((personal.get(i).getStunden() < stunden) || (personal.get(i).isTag()) || (personal.get(i).isNachtdienst())) && (versuche <= VERSUCHE));
            }
        }
        if (versuche >= VERSUCHE) {
            Zivildiener keinName = new Zivildiener("NA", 0, "nein");
            rv = keinName;
        } else {
            personal.get(i).subStunden(stunden);
            if (art.equals(TAGDIENST)) {
                personal.get(i).setTag(true);
            } else {
                personal.get(i).setNachtdienst(true);
            }
            rv = personal.get(i);
        }
        System.out.println(rv.getName());
        return rv;
    }

    private void ReadDienstplan() throws IOException {
        FileReader fileReader = new FileReader(tf_dienstplan.getText());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            String[] data_ef = ReadTeam(line);
            line = bufferedReader.readLine();
            System.out.println(line);
            String[] data_tf = ReadTeam(line);
            Dienst kurz = new Dienst(data_ef[KURZ], data_tf[KURZ], ZEITKURZ, KURZDIENST);
            Dienst lang1 = new Dienst(data_ef[LANG1], data_tf[LANG1], ZEITLANG, LANGDIENST1);
            Dienst lang2 = new Dienst(data_ef[LANG2], data_tf[LANG2], ZEITLANG, LANGDIENST2);
            Dienst nacht = new Dienst(data_ef[NACHT], data_tf[NACHT], ZEITLANG, NACHTDIENST);
            Tag tag = new Tag(kurz, lang1, lang2, nacht);
            woche.add(tag);
        }
        bufferedReader.close();
        fileReader.close();
    }

    private String[] ReadTeam(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line, DELIM);
        String[] data = new String[stringTokenizer.countTokens()];
        int count = 0;
        while (stringTokenizer.hasMoreTokens()) {
            data[count] = stringTokenizer.nextToken();
            count++;
        }
        return data;
    }

    public void ReadPersonal() throws IOException {
        FileReader fileReader = new FileReader(tf_personal.getText());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();

        String line = "";

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            StringTokenizer stringTokenizer = new StringTokenizer(line, DELIM);
            String[] data = new String[stringTokenizer.countTokens()];
            int count = 0;
            while (stringTokenizer.hasMoreTokens()) {
                data[count] = stringTokenizer.nextToken();
                count++;
            }
            personal.add(new Zivildiener(data[NAME], Integer.parseInt(data[STUNDEN]), data[FAHRER]));
        }
        bufferedReader.close();
        fileReader.close();

        for (Zivildiener zivildiener : personal) {
            System.out.println(zivildiener.getName() + "|" + zivildiener.getStunden() + "|" + zivildiener.isFahrer());
        }
    }
}
