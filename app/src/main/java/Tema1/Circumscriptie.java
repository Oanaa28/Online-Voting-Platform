package Tema1;

import java.awt.*;
import java.util.ArrayList;

public class Circumscriptie {
    String numeCircumscriptie;
    String regiune;
    public Circumscriptie(String numeCircumscriptie, String regiune) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiune = regiune;
    }
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }
    public String getRegiune() {
        return regiune;
    }
    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }
    static void CreareCircumscriptie(ArrayList<Alegeri> alegeri, ArrayList<Circumscriptie> circumscriptii, String idAlegeri, String numeCircumscriptie, String regiune) {

        boolean valid = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }
        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        for (Circumscriptie a : circumscriptii) {
            if (a.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + a.getNumeCircumscriptie());
                return;
            }
        }
        Circumscriptie circumscriptieNoua = new Circumscriptie(numeCircumscriptie, regiune);
        circumscriptii.add(circumscriptieNoua);
        System.out.println("S-a adaugat circumscriptia " + circumscriptieNoua.getNumeCircumscriptie());
    }
    static void EliminareCircumscriptie(ArrayList<Alegeri> alegeri, ArrayList<Circumscriptie> circumscriptii, String idAlegeri, String numeCircumscriptie) {

        boolean valid = false, existaCircumscriptie = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }
        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        Circumscriptie circumscriptieEliminata = null;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(numeCircumscriptie) == true) {
                existaCircumscriptie = true;
                circumscriptieEliminata = c;
            }
        }
        if (existaCircumscriptie == false) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }
        circumscriptii.remove(circumscriptieEliminata);
        System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
    }
}
