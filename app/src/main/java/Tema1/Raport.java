package Tema1;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
    static void CreeazaRaportPerCircumscriptie(ArrayList<VoturiCircumscriptie> voturiCircumscriptie, ArrayList<Circumscriptie> circumscriptii, ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String id_alegeri, String nume_circumscriptie) {
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

        Collections.sort(candidati, new Comparator<Candidat>() {
            public int compare(Candidat c1, Candidat c2) {
                int dif = c2.getNrVoturi() - c1.getNrVoturi();
                if (dif != 0) {
                    return dif;
                }
                return c2.getCnp().compareTo(c1.getCnp());
            }
        });

        System.out.println("Raport voturi " + nume_circumscriptie + ":");
        for (Candidat cand : candidati) {
            int voturi = cand.getVotDinCircumscriptie(nume_circumscriptie);
            System.out.println(cand.getNume() + " " + cand.getCnp() + " - " + voturi);
        }
    }
    static void CreeazaRaportNational (ArrayList<VoturiCircumscriptie> voturiCircumscriptie, ArrayList<Circumscriptie> circumscriptii, ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String id_alegeri) {
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

        int voturi = 0;
        boolean gol = true;
        for (Candidat cand : candidati) {
            voturi = 0;
            for (Circumscriptie c : circumscriptii) {
                voturi += cand.getVotDinCircumscriptie(c.getNumeCircumscriptie());
            }
            if (voturi != 0) {
                gol = false;
            }
            cand.setNrVoturi(voturi);
        }
        if (gol) {
            System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
            return;
        }

        Collections.sort(candidati, new Comparator<Candidat>() {
            public int compare(Candidat c1, Candidat c2) {
                int dif = c2.getNrVoturi() - c1.getNrVoturi();
                if (dif != 0) {
                    return dif;
                }
                return c2.getCnp().compareTo(c1.getCnp());
            }
        });

        System.out.println("Raport voturi Romania:");
        for (Candidat cand : candidati) {
            System.out.println(cand.getNume() + " " + cand.getCnp() + " - " + cand.getNrVoturi());
        }
    }
}
