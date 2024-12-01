package Tema1;

import java.util.ArrayList;

/**
 * Clasa Frauda contine multimea fraudelor raportate in cadrul procesului de votare.
 * Aceasta contine functionalitatea de a genera rapoarte pentru toate fraudele aparute.
 */
public class Frauda {
    /**
     * Metoda genereaza un raport al fraudelor pentru algerea specificata prin ID-ul primit ca paramtru.
     * @param alegeri Lista alegerilor.
     * @param votanti Lista votantilor, ce contin fiecare informatii despre fraude.
     * @param id_alegeri ID-ul alegerilor pentru care se genereaza raportul.
     */
    static void RapoarteFraude (ArrayList<Alegeri> alegeri, ArrayList<Votant> votanti, String id_alegeri) {
        int stagiu = 0;
        boolean valid = false;
        // cautare alegeri dupa ID si verificarea stagiului
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
        // verificare daca exista fraude in lista de votanti.
        boolean frauda = false;
        for (Votant v : votanti) {
            if (v.getFrauda()) {
                frauda = true;
                break;
            }
        }
        if (!frauda) {
            System.out.println("GOL: Romanii sunt cinstiti");
            return;
        }
        // daca exista, afisare a raportului detaliat pentru fraudele comise
        System.out.println("Fraude comise:");
        for (Votant v : votanti) {
            if (v.getFrauda()) {
                System.out.println("in " + v.getCircumscriptieVotant() + ": " + v.getCnp() + " " + v.getNume());
            }
        }
    }
}
