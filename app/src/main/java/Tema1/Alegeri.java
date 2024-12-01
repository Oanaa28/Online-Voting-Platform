package Tema1;

import java.util.ArrayList;

/**
 * Clasa alegeri contine multimea alegerilor.
 * Fiecare alegere are un anumit ID, un nume si un stagiu.
 * Clasa dispunde de urmatoarele functionalitati: crearea, pornirea, oprirea, stergerea si listarea alegerilor.
 */
public class Alegeri {
    private final String idAlegeri;
    private final String numeAlegeri;
    private int stagiu;

    /**
     * Constructorul clasei Alegeri.
     * @param idAlegeri ID-ul alegerilor
     * @param numeAlegeri Numele alegerilor
     * @param stagiu Stagiul curent al alegerilor ( 0 - neinceput, 1 - in curs, 2 - finalizat)
     */
    public Alegeri(String idAlegeri, String numeAlegeri, int stagiu) {
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        this.stagiu = stagiu;
    }

    /**
     * Returneaza ID-ul alegerilor.
     * @return ID-ul alegerilor
     */
    String getIdAlegeri() {
        return idAlegeri;
    }

    /**
     * Returneaza numele alegerilor.
     * @return Numele alegerilor.
     */
    String getNumeAlegeri() {
        return numeAlegeri;
    }

    /**
     * Returneaza stagiul curent al alegerilor.
     * @return Stagiul curent al alegerilor ( 0 - neinceput, 1 - in curs, 2 - finalizat)
     */
    int getStagiu() {
        return stagiu;
    }

    /**
     * Seteaza stagiul curent al alegerilor.
     * @param stagiu Noua valoare a stagiului.
     */
    void setStagiu(int stagiu) {
        this.stagiu = stagiu;
    }

    /**
     * Creeaza un nou set de alegeri.
     * @param alegeri Lista de alegeri existente.
     * @param idAlegeri ID-UL noilor alegeri.
     * @param numeAlegeri Numele noilor alegeri.
     */

    static void CreareAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri, String numeAlegeri) {
        // Validare ID alegeri
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                System.out.println("EROARE: Deja exista alegeri cu id " + a.getIdAlegeri());
                return;
            }
        }
        // Creare alegere noua
        Alegeri alegereNoua = new Alegeri(idAlegeri, numeAlegeri, 0);
        alegeri.add(alegereNoua);

        System.out.println("S-au creat alegerile " + numeAlegeri);
    }

    /**
     * Porneste alegerile cu ID-ul corespunzator.
     * @param alegeri Lista de alegeri existente.
     * @param idAlegeri ID-UL noilor alegeri.
     */
    static void PornireAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri) {

        boolean valid = false;
        Alegeri alegereDePornit = null;

        // Cautare alegeri
        // valid = 0 -> alegerea nu a fost gasita
        // valit = 1 -> alegerea a fost gasita

        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                alegereDePornit = a;
                break;
            }
        }
        if (!valid) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        // Pornire alegeri
        for (Alegeri a : alegeri) {
            if(a.getIdAlegeri().equals(idAlegeri)) {
                if (alegereDePornit.getStagiu() == 0) {
                    System.out.println("Au pornit alegerile " + alegereDePornit.getNumeAlegeri());
                    a.setStagiu(1);
                } else {
                    System.out.println("EROARE: Alegerile deja au inceput");
                }
            }
        }
    }

    /**
     * Opreste alegerile cu ID-ul corespunzator.
     * @param alegeri Lista de alegeri existente.
     * @param idAlegeri ID-ul alegerilor ce vor fi oprite.
     */
    static void OprireAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri) {

        int valid = 0;
        Alegeri alegereDeOprit = null;

        // Cautare alegeri
        // valid = 0 -> alegerea nu a fost gasita
        // valit = 1 -> alegerea a fost gasita
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                alegereDeOprit = a;
                valid = 1;
            }
        }

        if (valid == 0) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (alegereDeOprit.getStagiu() == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        System.out.println("S-au terminat alegerile " + alegereDeOprit.getNumeAlegeri());
        alegereDeOprit.setStagiu(2);
    }

    /**
     * Sterge alegerile specificate prin ID, impreuna cu toti candidatii si circumscriptiile asociate.
     * @param alegeri Lista de alegeri existente.
     * @param candidati Lista de candidati existenti.
     * @param circumscriptii Lista de circumscriptii existente.
     * @param idAlegeri ID-ul alegerilor ce vor fi sterse.
     */
    static void StergereAlegeri(ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati, ArrayList<Circumscriptie> circumscriptii, String idAlegeri) {

        int valid = 0;
        Alegeri alegereDeSters = null;

        // Cautare alegeri
        // valid = 0 -> alegerea nu a fost gasita
        // valid = 1 -> alegerea a fost gasita
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = 1;
                alegereDeSters = a;
            }
        }

        if (valid == 0) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        alegeri.remove(alegereDeSters);
        System.out.println("S-au sters alegerile " + alegereDeSters.getNumeAlegeri());

        // Stergere candidati
        for (Candidat c : candidati) {
            Candidat.EliminareCandidat(alegeri, candidati, idAlegeri, c.getCnp());
        }
        // Stergere circumscriptii
        for (Circumscriptie c : circumscriptii) {
            Circumscriptie.EliminareCircumscriptie(alegeri, circumscriptii, idAlegeri, c.getNumeCircumscriptie());
        }
    }

    /**
     * Listeaza toate alegerile.
     * @param alegeri Lista de alegeri existente.
     */
    static void ListareAlegeri(ArrayList<Alegeri> alegeri) {

        if (alegeri.isEmpty()) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        }

        System.out.println("Alegeri:");
        for (Alegeri a : alegeri) {
            System.out.println(a.getIdAlegeri() + " " + a.getNumeAlegeri());
        }
    }
}
