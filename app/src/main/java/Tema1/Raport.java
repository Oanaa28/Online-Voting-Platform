package Tema1;

import java.util.ArrayList;

/**
 * Clasa 'Raport' genereaza rapoarte pentru voturi per circumsriptie si la nivel national.
 * Aceasta este utilizata pentru a analiza rezultatele dupa finalizarea votarii.
 */
public class Raport {
    String id_alegeri;
    String nume_circumscriptie;

    /**
     * Constructorul cu parametri al clasei Raport.
     * @param id_alegeri ID-ul alegerilor asociate raportului
     * @param nume_circumscriptie numele circumscriptiei asociate raportului
     */
    public Raport(String id_alegeri, String nume_circumscriptie) {
        this.id_alegeri = id_alegeri;
        this.nume_circumscriptie = nume_circumscriptie;
    }

    /**
     * Creeaza un raport pentru voturile dintr-o circumscriptie specificata prin nume.
     * @param voturiCircumscriptie Lista voturi pe circcumscriptie.
     * @param circumscriptii Lista circumscriptiilor existente.
     * @param candidati Lista canditatilor.
     * @param alegeri Lista alegerilor.
     * @param id_alegeri ID-ul alegerilor pentru care se genereaza raportul.
     * @param nume_circumscriptie Numele circumcriptiei pentru care se genereaza raportul.
     */
    static void CreeazaRaportPerCircumscriptie(ArrayList<VoturiCircumscriptie> voturiCircumscriptie,
                                               ArrayList<Circumscriptie> circumscriptii,
                                               ArrayList<Candidat> candidati,
                                               ArrayList<Alegeri> alegeri, String id_alegeri,
                                               String nume_circumscriptie) {
        // verificare daca alegerile specificate prin id exista si salvare a stagiului lor
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
        // verificare daca circumscriptia exista
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
        // cautare voturi pentru circumscriptia specificata
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
        // sortarea candidatilor descrescator dupa numarul de voturi si ulterior dupa CNP
        candidati.sort((c1, c2) -> {
            int dif = c2.getNrVoturi() - c1.getNrVoturi();
            if (dif != 0) {
                return dif;
            }
            return c2.getCnp().compareTo(c1.getCnp());
        });

        // generare raport pentru circcumscriptie
        System.out.println("Raport voturi " + nume_circumscriptie + ":");
        for (Candidat cand : candidati) {
            int voturi = cand.getVotDinCircumscriptie(nume_circumscriptie);
            System.out.println(cand.getNume() + " " + cand.getCnp() + " - " + voturi);
        }
    }

    /**
     * Creeaza un raport pentru voturile la nivel national.
     * @param circumscriptii Lista circumscriptiilor existente.
     * @param candidati Lista candidatilor.
     * @param alegeri Lista alegerilor.
     * @param id_alegeri ID-ul alegerilor pentru care se genereaza raportul.
     */
    static void CreeazaRaportNational (ArrayList<Circumscriptie> circumscriptii,
                                       ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri,
                                       String id_alegeri) {
        // verificare daca alegerile specificate prin ID exista si salvare a stagiului acestora
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
        // calculare a numarului total de voturi pentru fiecare candidat
        boolean gol = true;
        for (Candidat cand : candidati) {
            int voturi = 0;
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
        // sortarea candidatilor descrescator dupa numarul de voturi si ulterior dupa CNP
        candidati.sort((c1, c2) -> {
            int dif = c2.getNrVoturi() - c1.getNrVoturi();
            if (dif != 0) {
                return dif;
            }
            return c2.getCnp().compareTo(c1.getCnp());
        });
        // generare raport national
        System.out.println("Raport voturi Romania:");
        for (Candidat cand : candidati) {
            System.out.println(cand.getNume() + " " + cand.getCnp() + " - " + cand.getNrVoturi());
        }
    }
}
