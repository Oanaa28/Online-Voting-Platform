package Tema1;

import java.util.ArrayList;

/**
 * Clasa 'Vot' reprezinta procesul de votare intr-o circumscriptie pentru un anumit candidat.
 * Aceasta contine metode pentru validarea si gestionarea procesului de vot.
 */
public class Vot {
    String idAlegeri;
    String numeCircumscriptie;
    String cnpVotant;
    String cnpCandidat;

    /**
     * Constructorul cu parametri al clasei.
     * @param idAlegeri ID-ul alegerilor.
     * @param numeCircumscriptie Numele circumscriptiei.
     * @param cnpVotant CNP-ul votantului
     * @param cnpCandidat CNP-ul candidatului
     */
    public Vot(String idAlegeri, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        this.idAlegeri = idAlegeri;
        this.numeCircumscriptie = numeCircumscriptie;
        this.cnpVotant = cnpVotant;
        this.cnpCandidat = cnpCandidat;
    }

    /**
     * Metoda principala pentru procesul de votare.
     * Aceasta verifica validitatea votului, inregistreaza voturile
     * si detecteaza eventualele incercari de frauda.
     * @param alegeri Lista alegerilor.
     * @param candidati Lista candidati.
     * @param votanti Lista votanti.
     * @param circumscriptii Lista de circumscriptii.
     * @param voturiCircumscriptie Lista voturilor pe circumscriptii.
     * @param idAlegeri ID-ul alegerilor.
     * @param numeCircumscriptie Numele circumscriptiei unde se voteaza.
     * @param cnpVotant CNP-ul votantului
     * @param cnpCandidat CNP-ul candidatului votat
     */
    static void Votare(ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati,
                       ArrayList<Votant> votanti, ArrayList<Circumscriptie> circumscriptii,
                       ArrayList<VoturiCircumscriptie> voturiCircumscriptie, String idAlegeri,
                       String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        // verificare daca alegerile exista, sunt valide si salvam stagiul acestora
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
        }
        // verificare daca votantul exista
        boolean existaCnpVotant = false;
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnpVotant)) {
                existaCnpVotant = true;
                break;
            }
        }

        if (!existaCnpVotant) {
            System.out.println("EROARE: Nu exista un votant cu CNP-ul " + cnpVotant);
            return;
        }
        // verificare daca candidatul exista
        boolean existaCnpCandidat = false;
        for (Candidat c : candidati) {
            if (c.getCnp().equals(cnpCandidat)) {
                existaCnpCandidat = true;
                break;
            }
        }
        if (!existaCnpCandidat) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
            return;
        }
        // verificare daca circumscriptia exista
        boolean existaCircumscriptie = false;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                existaCircumscriptie = true;
                break;
            }
        }
        if (!existaCircumscriptie) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        // verificare daca votantul este inregistrat in circumscriptia respectiva
        boolean esteInregistrat = false;
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnpVotant) && v.getCircumscriptieVotant().equals(numeCircumscriptie)) {
                esteInregistrat = true;
                break;
            }
        }
        if (!esteInregistrat) {
            // daca nu este inregistrat, se marcheaza ca frauda
            for (Votant v : votanti) {
                if (v.getCnp().equals(cnpVotant) && !v.getCircumscriptieVotant().equals(numeCircumscriptie)) {
                    v.setFrauda(true);
                }
            }
            System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
            return;
        }
        // procesare vot (daca nu au existat erori)
        String numeVotant = "";
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnpVotant)) {
                if (v.getNeindemanatic()) {
                    v.setFrauda(true);
                    System.out.println("FRAUDa: Votantul cu CNP-ul " + v.getCnp() + " a incercat sa comita o frauda. Votul a fost anulat");
                    return;
                } else if (!v.getVot()) {
                    numeVotant = v.getNume();
                    v.setVot(true);
                    break;
                } else {
                    v.setFrauda(true);
                    System.out.println("FRAUDa: Votantul cu CNP-ul " + v.getCnp() + " a incercat sa comita o frauda. Votul a fost anulat");
                    return;
                }
            }
        }
        // adaugare vot pentru candidat
        Candidat candidat = null;
        for (Candidat c : candidati) {
            if (c.getCnp().equals(cnpCandidat)) {
                candidat = c;
                break;
            }
        }
        if (candidat != null) {
            candidat.adaugaVotInCircumscriptie(numeCircumscriptie);
        }
        // actualizare numar voturi pentru circumscriptie
        for (VoturiCircumscriptie v : voturiCircumscriptie) {
            if (v.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                v.adaugaVot();
                break;
            }
        }
        if (candidat != null) {
            System.out.println(numeVotant + " a votat pentru " + candidat.getNume());
        }
    }
}

/**
 * Clasa 'VoturiCircumscriptie' gestioneaza numarul total de voturi dintr-o circumscriptie.
 */
class VoturiCircumscriptie {

    private final String numeCircumscriptie;
    private int numarVoturi;

    /**
     * Constructor cu parametri pentru initializarea voturilor dintr-o circumscriptie.
     * @param numeCircumscriptie Numele circumscriptiei.
     * @param numarVoturi Numarul total de voturi din circumscriptie
     */
    public VoturiCircumscriptie(String numeCircumscriptie, int numarVoturi) {
        this.numeCircumscriptie = numeCircumscriptie;
        this.numarVoturi = numarVoturi;
    }

    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }

    public int getNumarVoturi() {
        return numarVoturi;
    }

    public void setNumarVoturi(int numarVoturi) {
        this.numarVoturi = numarVoturi;
    }

    /**
     * Metoda creste numarul de voturi din circumscriptie cu unul.
     */
    public void adaugaVot() {
        this.numarVoturi++;
    }
}