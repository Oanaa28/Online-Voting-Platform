package Tema1;

import java.util.ArrayList;

/**
 * Clasa Circumscriptie contine multimea circumscriptiilor.
 * Fiecare circumscriptie are un anumit nume si o regiune din care face parte.
 * Clasa dispunde de urmatoarele functionalitati: crearea si eliminarea circumscriptiilor, precum si
 * contorizarea regiunilor din care fiecare circumscriptie face parte.
 */
public class Circumscriptie {

    String numeCircumscriptie;
    String regiune;

    /**
     * Constructorul cu parametri al clasei Circumscriptie
     * @param numeCircumscriptie numele Circumscriptiei
     * @param regiune Regiunea din care face parte circumscriptia.
     */
    public Circumscriptie(String numeCircumscriptie, String regiune) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.regiune = regiune;
    }

    /**
     * Returneaza numele circumscriptiei
     * @return numele circumscriptiei
     */
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    /**
     * Returneaza regiunea din care circumscriptia face parte.
     * @return regiunea
     */
    public String getRegiune() {
        return regiune;
    }

    /**
     * Creeaza o noua circumscriptie si o adauga in lista existenta.
     * @param alegeri Lista alegerilor.
     * @param circumscriptii Lista circumscriptiilor.
     * @param voturiCircumscriptie Lista voturilor din circumscriptie.
     * @param idAlegeri ID-ul alegerilor in care se aduaga circumscriptiile.
     * @param numeCircumscriptie Numele circumscriptiei ce va fi adaugata.
     * @param regiune Regiunea in care se afla circumscriptia.
     */
    static void CreareCircumscriptie(ArrayList<Alegeri> alegeri, ArrayList<Circumscriptie> circumscriptii,
                                     ArrayList<VoturiCircumscriptie> voturiCircumscriptie,
                                     String idAlegeri, String numeCircumscriptie, String regiune) {

        boolean valid = false;
        int stagiu = 0;
        // cautare a alegerilor si retinere stagiu
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }
        if (!valid) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }

        // verificare daca exista deja o circumscriptie cu acelasi nume
        for (Circumscriptie a : circumscriptii) {
            if (a.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                System.out.println("EROARE: Deja exista o circumscriptie cu numele " + a.getNumeCircumscriptie());
                return;
            }
        }
        // creare si adaugare a noii circumscriptii in lista de circumscriptii
        Circumscriptie circumscriptieNoua = new Circumscriptie(numeCircumscriptie, regiune);
        circumscriptii.add(circumscriptieNoua);
        // creare a unui nou obiect pentru inregistrarea voturilor din circumscriptie
        VoturiCircumscriptie v = new VoturiCircumscriptie(numeCircumscriptie, 0);
        voturiCircumscriptie.add(v);
        System.out.println("S-a adaugat circumscriptia " + circumscriptieNoua.getNumeCircumscriptie());
    }

    /**
     * Elimina o circumscriptie existenta din lista de circumscriptii.
     * @param alegeri Lista alegerilor.
     * @param circumscriptii Lista circumscriptiilor existente.
     * @param idAlegeri ID-ul alegerilor din circumscriptie.
     * @param numeCircumscriptie Numele circumsccriptiei ce va fi eliminata.
     */
    static void EliminareCircumscriptie(ArrayList<Alegeri> alegeri,
                                        ArrayList<Circumscriptie> circumscriptii,
                                        String idAlegeri, String numeCircumscriptie) {

        // cautare a alegerilor si retinere stagiu
        boolean valid = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }
        if (!valid) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        // verificare daca circumscriptia exista
        boolean existaCircumscriptie = false;
        Circumscriptie circumscriptieEliminata = null;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                existaCircumscriptie = true;
                circumscriptieEliminata = c;
            }
        }
        if (!existaCircumscriptie) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }
        // eliminare circumscriptie din lista
        circumscriptii.remove(circumscriptieEliminata);
        System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
    }

    /**
     * Contorizeaza regiunile din lista de circumscriptii.
     * @param circumscriptii Lista circumscriptiilor.
     * @return Lista de regiuni.
     */
    static ArrayList<String> ContorizareRegiuniDinCircumscriptii(ArrayList<Circumscriptie> circumscriptii) {

        ArrayList<String> regiuni = new ArrayList<>();
        // parcurgere circumscriptii
        for (Circumscriptie c : circumscriptii) {
            boolean existaRegiune = false;
            // verificare daca regiunea exista deja in lista de regiuni
            for (String r : regiuni) {
                if (r.equals(c.getRegiune())) {
                    existaRegiune = true;
                    break;
                }
            }
            // daca nu exista, se adauga in lista
            if (!existaRegiune) {
                regiuni.add(c.getRegiune());
            }
        }
        // returnare lista de regiuni
        return regiuni;
    }
}
