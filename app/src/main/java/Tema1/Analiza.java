package Tema1;

import java.util.ArrayList;

/**
 * Clasa 'Analiza' este responsabila pentru efectuarea analizelor asupra voturilor
 * dintr-o anumita circumscriptie sau la nivel national.
 */
public class Analiza {
    /**
     * Analizeaza voturile unei anumite circumscriptii pentru o alegere specifica.
     * @param voturiPerCircumscriptie Lista voturi pe fiecare circumscriptie
     * @param candidati Lista candidatilor
     * @param circumscriptii Lista circumscriptiilor
     * @param alegeri Lista alegerilor
     * @param id_alegeri ID-ul alegerilor pentru care se face analiza
     * @param nume_circumscriptie Numele circumscriptiei pentru care se face analiza
     */
    static void AnalizaPerCircumscriptie(ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie,
                                         ArrayList<Candidat> candidati,
                                         ArrayList<Circumscriptie> circumscriptii, ArrayList<Alegeri> alegeri,
                                         String id_alegeri, String nume_circumscriptie) {
        Alegeri alegereDeAnalizat = null;
        boolean idValid = false;
        // verificare daca exista alegerile cautate
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(id_alegeri)) {
                alegereDeAnalizat = a;
                idValid = true;
                break;
            }
        }

        if (!idValid) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        int stagiu = alegereDeAnalizat.getStagiu();
        // verificare daca s-a terminat votarea
        if (stagiu == 0 || stagiu == 1) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }
        // verificare daca exista circumscriptia
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
        // cautare a voturilor din circumscriptia data
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
        // sortare candidati descrescator in functie de numarul de voturi
        // daca au acelasi nr. de voturi, descrescator dupa cnp
        candidati.sort((c1, c2) -> {
            int dif = c2.getNrVoturi() - c1.getNrVoturi();
            if (dif != 0) {
                return dif;
            }
            return c2.getCnp().compareTo(c1.getCnp());
        });

        // calculare a numarului total de voturi pe plan national
        int nrVoturiNational = 0;

        for (Circumscriptie c : circumscriptii) {
            for (Candidat cand : candidati) {
                nrVoturiNational += cand.getVotDinCircumscriptie(c.getNumeCircumscriptie());
            }
        }
        // determinare a numarului de voturi din circumscriptie si a candidatului cu cele mai multe voturi
        int maximVoturiCircumscriptie = 0, nrVoturiCircumscriptie = 0;
        Candidat candidatMaximVoturi = null;

        for (Candidat cand : candidati) {
            // numarul de voturi ale candidatului in circumscriptia specificata
            int nrVoturiCandidat = cand.getVotDinCircumscriptie(nume_circumscriptie);
            nrVoturiCircumscriptie += nrVoturiCandidat;

            // verificare daca acest candidat are cele mai multe voturi din circumscriptie
            if (nrVoturiCandidat > maximVoturiCircumscriptie) {
                maximVoturiCircumscriptie = nrVoturiCandidat;
                candidatMaximVoturi = cand;
            }
        }
        int procentaj = 0, procentajVoturiMaxime = 0;
        // determinare a procentajului voturilor din circumscriptie in raport cu numatul total de voturi nationale
        if (nrVoturiNational != 0) {
            procentaj = (int) ((float) nrVoturiCircumscriptie * 100 / nrVoturiNational);
        }
        // determinare a procentajului voturilor maxime in raport cu numarul total de voturi din circumscriptie
        if (nrVoturiCircumscriptie != 0) {
            procentajVoturiMaxime = (int) (float) maximVoturiCircumscriptie * 100 / nrVoturiCircumscriptie;
        }

        if (candidatMaximVoturi != null) {
            System.out.println("in " + nume_circumscriptie + " au fost " + nrVoturiCircumscriptie
                    + " voturi din " + nrVoturiNational + ". Adica " + procentaj
                    + "%. Cele mai multe voturi au fost stranse de " + candidatMaximVoturi.getCnp()
                    + " " + candidatMaximVoturi.getNume() + ". Acestea constituie "
                    + procentajVoturiMaxime + "% din voturile circumscriptiei.");
        }
    }

    /**
     * Analizeaza voturile pe plan national pentru o alegere specifica.
     * @param candidati Lista candidatilor
     * @param circumscriptii Lista circumscriptiilor
     * @param alegeri Lista alegerilor
     * @param id_alegeri ID-ul alegerilor pentru care se face analizq
     */
    static void AnalizaPePlanNational (ArrayList<Candidat> candidati,
                                       ArrayList<Circumscriptie> circumscriptii,
                                       ArrayList<Alegeri> alegeri, String id_alegeri) {
        // verificare daca alegerile exista in lista de alegeri
        boolean valid = false;
        Alegeri alegereDeAnalizat = null;

        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(id_alegeri)) {
                alegereDeAnalizat = a;
                valid = true;
                break;
            }
        }
        if (!valid) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        int stagiu = alegereDeAnalizat.getStagiu();
        // verificare daca s-a terminat votarea
        if (stagiu == 0 || stagiu == 1) {
            System.out.println("EROARE: Inca nu s-a terminat votarea");
            return;
        }

        boolean gol = true;
        // determinare numar de voturi pentru fiecare candidat
        // verificare daca exista voturi inregistrate
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

        int nrVoturiNational = 0;
        for (Candidat cand : candidati) {
            nrVoturiNational += cand.getNrVoturi();
        }

        ArrayList<String> regiuni = Circumscriptie.ContorizareRegiuniDinCircumscriptii(circumscriptii);

        // ordonare alfabetica a regiunilor
        regiuni.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("in Romania au fost " + nrVoturiNational + " voturi.");

        Candidat candidatMaximVoturi = null;
        for (String r : regiuni) {
            int nrVoturiRegiune = 0, nrMaximVoturiRegiune = 0;
            // resetare numar de voturi la 0 pentru regiunea curenta
            for (Candidat cand : candidati) {
                cand.setNrVoturiPerRegiune(0);
            }

            for (Circumscriptie c : circumscriptii) {
                // verificare daca circumscriptia face parte din regiunea curenta
                if (c.getRegiune().equals(r)) {

                    int nrVoturiCircumscriptie = 0;
                    String numeCircumscriptie = c.getNumeCircumscriptie();

                    // determinare numar voturi pentru fiecare candidat din circumscriptia curenta

                    for (Candidat cand : candidati) {
                        int nrVoturiCandidat = cand.getVotDinCircumscriptie(numeCircumscriptie);

                        cand.AdaugaVoturiPerRegiune(nrVoturiCandidat);

                        nrVoturiCircumscriptie += nrVoturiCandidat;

                        // verificare daca curentul candidat are cele mai multe voturi din regiune
                        if (cand.getNrVoturiPerRegiune() >= nrMaximVoturiRegiune) {
                            nrMaximVoturiRegiune = cand.getNrVoturiPerRegiune();
                            candidatMaximVoturi = cand;
                        }
                    }
                    nrVoturiRegiune += nrVoturiCircumscriptie;
                }
            }

            int procentaj = 0, procentajVoturiMaxime = 0;
            // determinare procentaj pentru regiunea curenta in raport cu numarul de voturi nationale
            if (nrVoturiNational != 0) {
                procentaj = (int) ((float) nrVoturiRegiune * 100 / nrVoturiNational);
            }
            // determinare procentaj pentru cele mai multe voturi din regiune
            if (nrVoturiRegiune != 0) {
                procentajVoturiMaxime = (int) ((float) nrMaximVoturiRegiune * 100 / nrVoturiRegiune);
            }
            if (candidatMaximVoturi != null) {
                System.out.println("in " + r + " au fost " + nrVoturiRegiune + " voturi din " + nrVoturiNational + ". " + "Adica " + procentaj + "%. Cele mai multe voturi au fost stranse de " + candidatMaximVoturi.getCnp() + " " + candidatMaximVoturi.getNume() + ". Acestea constituie " + procentajVoturiMaxime + "% din voturile regiunii.");
            }
        }
    }
}
