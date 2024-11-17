package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Analiza {
    private String id_alegeri;
    private String nume_circumscriptie;

    public Analiza(String id_alegeri, String nume_circumscriptie) {
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
    static void AnalizaPerCircumscriptie(ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie, ArrayList<Candidat> candidati, ArrayList<Circumscriptie> circumscriptii, ArrayList<Alegeri> alegeri, String id_alegeri, String nume_circumscriptie) {

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
        for (VoturiCircumscriptie v : voturiPerCircumscriptie) {
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

        int nrVoturiCircumscriptie = 0, nrVoturiNational = 0;

        for (Circumscriptie c : circumscriptii) {
            for (Candidat cand: candidati) {
                nrVoturiNational += cand.getVotDinCircumscriptie(c.numeCircumscriptie);
            }
        }
        boolean gasitCircumscriptie = false;
        int maxim_voturiCircumscriptie = 0;
        Candidat candidatMaximVoturi = null;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(nume_circumscriptie)) {
                nrVoturiCircumscriptie = 0;
                for (Candidat cand : candidati) {
                    int nrVoturiCandidat = cand.getVotDinCircumscriptie(nume_circumscriptie);
                    nrVoturiCircumscriptie += nrVoturiCandidat;
                    if (nrVoturiCandidat > maxim_voturiCircumscriptie) {
                        maxim_voturiCircumscriptie = nrVoturiCandidat;
                        candidatMaximVoturi = cand;
                    }
                }
                gasitCircumscriptie = true;
                break;
            }
        }
        int procentaj = 0, procentajVoturiMaxime = 0;
        if (nrVoturiNational != 0) {
            procentaj = (int) ((float) nrVoturiCircumscriptie * 100 / nrVoturiNational);
        }
        if (nrVoturiCircumscriptie != 0) {
            procentajVoturiMaxime = (int)(float)maxim_voturiCircumscriptie * 100 / nrVoturiCircumscriptie;
        }
        if (gasitCircumscriptie) {
            System.out.println("in " + nume_circumscriptie + " au fost " + nrVoturiCircumscriptie + " voturi din " + nrVoturiNational + ". Adica " + procentaj +"%. Cele mai multe voturi au fost stranse de " + candidatMaximVoturi.getCnp() + " " + candidatMaximVoturi.getNume() + ". Acestea constituie " + procentajVoturiMaxime + "% din voturile circumscriptiei.");
        }
    }
    static void AnalizaPePlanNational (ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie, ArrayList<Candidat> candidati, ArrayList<Circumscriptie> circumscriptii, ArrayList<Alegeri> alegeri, String id_alegeri) {
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
        //System.out.println("");
    }
}
