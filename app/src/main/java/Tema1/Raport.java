package Tema1;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class Raport {
    String id_alegeri;
    String nume_circumscriptie;
    public Raport() {

    }
    public Raport(String id_alegeri, String nume_circumscriptie) {
        this.id_alegeri = id_alegeri;
        this.nume_circumscriptie = nume_circumscriptie;
    }
    public String getId_alegeri() {
        return id_alegeri;
    }
    public void setId_alegeri(String id_alegeri) {
        this.id_alegeri = id_alegeri;
    }
    public String getNume_circumscriptie() {
        return nume_circumscriptie;
    }
    public void setNume_circumscriptie(String nume_circumscriptie) {
        this.nume_circumscriptie = nume_circumscriptie;
    }
    static void CreeazaRaport(ArrayList<VoturiCircumscriptie> voturiCircumscriptie, ArrayList<Circumscriptie> circumscriptii, ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String id_alegeri, String nume_circumscriptie) {
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
        boolean existaCircumscriptie = false;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(nume_circumscriptie)) {
                existaCircumscriptie = true;
                break;
            }
        }
        if (!existaCircumscriptie) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + nume_circumscriptie);
            return;
        }
        VoturiCircumscriptie voturiInCircumscriptie = null;
        for (VoturiCircumscriptie v : voturiCircumscriptie) {
            if (v.getNumeCircumscriptie().equals(nume_circumscriptie)) {
                voturiInCircumscriptie = v;
                break;
            }
        }
        if (voturiInCircumscriptie == null || voturiInCircumscriptie.getNumarVoturi() == 0) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + nume_circumscriptie);
            return;
        }
        System.out.println("Raport voturi " + nume_circumscriptie + ":");
        for (Candidat cand : candidati) {
            int voturi = cand.getVotDinCircumscriptie(nume_circumscriptie);
            System.out.println(cand.getNume() + " " + cand.getCnp() + " - " + voturi + " voturi");
        }
    }
}
