package Tema1;

import java.util.ArrayList;

public class Frauda {
    private String id_alegeri;

    public Frauda(String id_alegeri) {
        this.id_alegeri = id_alegeri;
    }
    public String getId_alegeri() {
        return id_alegeri;
    }
    public void setId_alegeri(String id_alegeri) {
        this.id_alegeri = id_alegeri;
    }
        static void RapoarteFraude (ArrayList<Alegeri> alegeri, ArrayList<Votant> votanti, ArrayList<Candidat> candidati, ArrayList<Circumscriptie> circumscriptii, String id_alegeri) {
        int stagiu = 0;
        boolean valid = false;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(id_alegeri)) {
                stagiu = a.getStagiu();
                valid = true;
                break;
            }
        }
        if (!valid) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0 || stagiu == 1) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }
        boolean frauda = false;
        for (Votant v : votanti) {
            if (v.getFrauda() == true) {
                frauda = true;
                break;
            }
        }
        if (!frauda) {
            System.out.println("GOL: Romanii sunt cinstiti");
            return;
        }
        System.out.println("Fraude comise:");
        for (Votant v : votanti) {
            if (v.getFrauda() == true) {
                System.out.println("in " + v.getCircumscriptieVotant() + ": " + v.getCnp() + " " + v.getNume());
            }
        }
    }
}
