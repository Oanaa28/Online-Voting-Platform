package Tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clasa abstracta Persoana contine proprietatile de baza ale unei persoane.
 * Aceasta e utilizata drept clasa parinte pentru clasele 'Candidat' si 'Votant'.
 */
public abstract class Persoana {
    private final String cnp; // cod numeric personal
    private final int varsta; // varsta persoanei
    private final String nume; // numele persoanei

    /**
     * Constructorul cu parametri al clasei
     * @param cnp CNP-ul persoanei
     * @param varsta Varsta persoanei
     * @param nume Numele persoanei
     */
    public Persoana(String cnp, int varsta, String nume) {
        this.nume = nume;
        this.varsta = varsta;
        this.cnp = cnp;
    }

    /**
     * Returneaza numele persoanei
     * @return nume persoana
     */
    public String getNume() {
        return nume;
    }

    /**
     * Returneaza varsta persoanei.
     * @return varsta persoana
     */
    public int getVarsta() {
        return varsta;
    }

    /**
     * Returneaza cnp-ul persoanei.
     * @return cnp persoana
     */
    public String getCnp() {
        return cnp;
    }
}

/**
 * Clasa 'Candidat' extinde clasa 'Persoana' si adauga functionalitati specifice unui candidat,
 * precum gestionarea voturilor.
 */
class Candidat extends Persoana {
    private final ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie;
    int nrVoturi; // numarul total de voturi
    int nrVoturiPerRegiune; // numarul de voturi pe regiune

    /**
     * Constructorul cu parametri al clasei Candidat.
     * @param cnp CNP-ul candidatului
     * @param varsta Varsta candidatului
     * @param nume Numele candidatului
     * @param nrVoturi numarul total de voturi
     */
    public Candidat(String cnp, int varsta, String nume, int nrVoturi) {
        super(cnp, varsta, nume);
        this.voturiPerCircumscriptie = new ArrayList<>();
        this.nrVoturi = nrVoturi;
    }

    public void setNrVoturi(int nrVoturi) {
        this.nrVoturi = nrVoturi;
    }

    public int getNrVoturi() {
        return nrVoturi;
    }

    public int getNrVoturiPerRegiune() {
        return nrVoturiPerRegiune;
    }

    public void setNrVoturiPerRegiune(int nrVoturiPerRegiune) {
        this.nrVoturiPerRegiune = nrVoturiPerRegiune;
    }

    public void AdaugaVoturiPerRegiune(int nrVoturiAdaugate) {
        this.nrVoturiPerRegiune += nrVoturiAdaugate;
    }

    /**
     * Adauga un vot in Circumscriptia specificata prin nume.
     * Daca aceasta nu exista, o creeaza.
     * @param numeCircumscriptie numele circumscriptiei
     */
    public void adaugaVotInCircumscriptie(String numeCircumscriptie) {
        for (VoturiCircumscriptie v : voturiPerCircumscriptie) {
            if (v.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                v.setNumarVoturi(v.getNumarVoturi() + 1);
                return;
            }
        }
        voturiPerCircumscriptie.add(new VoturiCircumscriptie(numeCircumscriptie, 1));
    }

    /**
     * Returneaza numarul de voturi pentru circumscriptia specificata.
     * @param numeCircumscriptie Numele circumscriptiei
     * @return numarul de voturi din circumscriptie
     */
    public int getVotDinCircumscriptie(String numeCircumscriptie) {
        for (VoturiCircumscriptie v : voturiPerCircumscriptie) {
            if (v.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                return v.getNumarVoturi();
            }
        }
        return 0;
    }

    /**
     * Adauga un candidat nou in lista de candidati (implicit si in circumscriptie)
     * @param candidati Lista de candidati.
     * @param alegeri Lista de alegeri.
     * @param idAlegeri ID-ul alegerilor specificate.
     * @param cnp CNP-ul candidatului ce va fi adaugat.
     * @param varsta Varsta candidatului.
     * @param nume Numele candidatului.
     */
    static void AdaugareCandidat (ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri,
                                  String idAlegeri, String cnp, int varsta, String nume) {

        boolean valid = false;
        int stagiu = 1;
        // cautare alegeri si veriifcare daca exist
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                stagiu = a.getStagiu();
                valid = true;
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

        if (varsta < 35) {
            System.out.println("EROARE: Varsta invalida");
            return;
        }
        if (cnp.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        // verificare daca exista deja un candidat cu acelasi cnp in lista de candidati
        String numeGasit = "";
        boolean existaCNP = false;
        for (Candidat c : candidati) {
            if (c.getCnp().equals(cnp)) {
                existaCNP = true;
                numeGasit = c.getNume();
                break;
            }
        }

        if (!existaCNP) {
            // aduagam in lista candidatul daca datele lui sunt valide
            Candidat candidatNou = new Candidat(cnp, varsta, nume, 0);
            candidati.add(candidatNou);

            System.out.println("S-a adaugat candidatul " + nume);
        } else {
            System.out.println("EROARE: Candidatul " + numeGasit + " are deja acelasi CNP");
        }
    }

    /**
     * Elimina un candidat din lista de candidati, daca acesta exista deja in lista.
     * @param alegeri Lista de alegeri.
     * @param candidati Lista de candidati.
     * @param idAlegeri ID-ul alegerilor specificate.
     * @param cnp CNP-ul candidatului ce va fi eliminat.
     */
    static void EliminareCandidat (ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati,
                                   String idAlegeri, String cnp) {
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

        // verificare daca exista candidatul cu CNP-ul specificat
        boolean existaCNP = false;
        Candidat candidatEliminat = null;
        for (Candidat c : candidati) {
            if (c.getCnp().equals(cnp)) {
                candidatEliminat = c;
                existaCNP = true;
                break;
            }
        }

        if (!existaCNP) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);
        } else {
            // eliminare candidat
            candidatEliminat.nrVoturi = 0;
            candidatEliminat.nrVoturiPerRegiune = 0;
            candidatEliminat.voturiPerCircumscriptie.remove(candidatEliminat);
            candidati.remove(candidatEliminat);
            System.out.println("S-a sters candidatul " + candidatEliminat.getNume());
        }
    }

    /**
     * Listeaza toti candidatii existenti in alegerea specificata, ordonati crescator dupa CNP.
     * @param candidati Lista de candidati.
     * @param alegeri Lista alegerilor.
     * @param idAlegeri ID-ul alegerilor specificate.
     */
    static void ListareCandidati (ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String idAlegeri) {
        // validare ID si determinarea stagiului alegerilor
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

        if (stagiu != 1 && stagiu != 2) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }

        if (candidati.isEmpty()) {
            System.out.println("GOL: Nu sunt candidati");
            return;
        }
        // sortare candidati crescator dupa CNP-ul fiecaruia
        candidati.sort(new Comparator<Candidat>() {
            @Override
            public int compare(Candidat c1, Candidat c2) {
                return c1.getCnp().compareTo(c2.getCnp());
            }
        });

        System.out.println("Candidatii:");
        for (Candidat c : candidati) {
            System.out.println(c.getNume() + " " + c.getCnp() + " " + c.getVarsta());
        }
    }
}

/**
 * Clasa 'Votant' extinde clasa 'Persoana' si adauga functionalitati specifice unui votant,
 * precum frauda, indemanarea si posibilele fraude comise.
 * Metodele clasei adauga si listeaza votantii dintr-o circumscriptie specificata.
 */
class Votant extends Persoana {
    String circumscriptieVotant;
    boolean vot, frauda, neindemanatic;

    /**
     * Constructorul cu parametri al clasei 'Votant'.
     * @param cnp CNP-ul votantului
     * @param varsta Varsta votantului
     * @param nume Numele votantului
     * @param circumscriptieVotant Circumscriptia unde va vota
     * @param neindemanatic (da/nu)
     * @param vot (da/nu)
     */
    public Votant(String cnp, int varsta, String nume, String circumscriptieVotant, boolean neindemanatic, boolean vot) {
        super(cnp, varsta, nume);
        this.circumscriptieVotant = circumscriptieVotant;
        this.neindemanatic = neindemanatic;
        this.vot = vot;
    }

    public void setCircumscriptieVotant(String circumscriptieVotant) {
        this.circumscriptieVotant = circumscriptieVotant;
    }

    public String getCircumscriptieVotant() {
        return circumscriptieVotant;
    }

    public void setVot(boolean vot) {
        this.vot = vot;
    }

    public boolean getVot() {
        return vot;
    }

    public void setFrauda(boolean frauda) {
        this.frauda = frauda;
    }
    public boolean getFrauda() {
        return frauda;
    }

    public boolean getNeindemanatic() {
        return neindemanatic;
    }

    /**
     * Adauga un votant im lista votantilor, dupa ce verifica validitatea datelor si a circumscriptiei.
     * @param alegeri Lista alegerilor.
     * @param votanti Lista votantilor existenti.
     * @param circumscriptii Lista circumscriptiilor.
     * @param idAlegeri ID-ul alegerilor unde participa votantul.
     * @param numeCircumscriptie Numele circumscriptiei unde va vota.
     * @param cnp CNP-ul votantului
     * @param varsta Varsta votantului
     * @param neindemanatic Indemanarea votantului
     * @param nume Numele votantului
     */
    static void AdaugareVotant (ArrayList<Alegeri> alegeri, ArrayList<Votant> votanti,
                                ArrayList<Circumscriptie> circumscriptii, String idAlegeri,
                                String numeCircumscriptie, String cnp, int varsta,
                                boolean neindemanatic, String nume ) {
        // validare ID si determinarea stagiului alegerilor
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
        // verificare daca exista cirxumscriptia specificata
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
        if (varsta < 18) {
            System.out.println("EROARE: Varsta invalida");
        }

        if (cnp.length() != 13) {
            System.out.println("EROARE: CNP invalid");
            return;
        }
        // verificare daca exista deja un alt votant cu acelasi CNP
        String numeGasit = "";
        boolean existaCNP = false;
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnp)) {
                v.setCircumscriptieVotant(numeCircumscriptie);
                existaCNP = true;
                numeGasit = v.getNume();
                break;
            }
        }

        if (existaCNP) {
            System.out.println("EROARE: Votantul " + numeGasit + " are deja acelasi CNP");
            return;
        }
        // creare si adaugare votant in lista de votanti daca a trecut de toat verificarile
        Votant votantNou = new Votant(cnp, varsta, nume, numeCircumscriptie, neindemanatic, false);
        votanti.add(votantNou);
        System.out.println("S-a adaugat votantul " + nume);
    }

    /**
     * Listeaza toti votantii din circumscriptia specificata prin numeCircumscriptie
     * @param alegeri Lista de alegeri
     * @param votanti Lista de votanti
     * @param circumscriptii Lista de circumscriptii
     * @param idAlegeri ID-ul alegerilor
     * @param numeCircumscriptie Numele circumscriptiiei specificate.
     */
    static void ListareVotantiCircumscriptie (ArrayList<Alegeri> alegeri, ArrayList<Votant> votanti,
                                              ArrayList<Circumscriptie> circumscriptii,
                                              String idAlegeri, String numeCircumscriptie) {
        // verificare validitate id si determinare a stagiului curent
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

        if (stagiu != 1 && stagiu != 2) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }
        // verificare daca exista circumscriptia specificata in lista de circumscriptii
        boolean existaCircumscriptie = false;
        for (Circumscriptie c : circumscriptii) {
            if(c.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                existaCircumscriptie = true;
                break;
            }
        }

        if (!existaCircumscriptie) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }
        // creare lista cu votantii din circumscriptie
        ArrayList<Votant> votantiCircumscriptie = new ArrayList<>();

        boolean existaVotanti = false;

        for (Votant v : votanti) {
            if (v.getCircumscriptieVotant().equals(numeCircumscriptie)) {
                votantiCircumscriptie.add(v);
                existaVotanti = true;
            }
        }

        if (!existaVotanti) {
            System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
            return;
        }
        // sortarea votantilor din lista creata in ordine crescatoare dupa CNP
        Collections.sort(votantiCircumscriptie, new Comparator<Votant>() {
            public int compare(Votant v1, Votant v2) {
                return v1.getCnp().compareTo(v2.getCnp());
            }
        });
        // afisare a votantilor si a detaliilor acestora
        System.out.println("Votantii din " + numeCircumscriptie + ":");
        for (Votant v : votantiCircumscriptie) {
            System.out.println(v.getNume() + " " + v.getCnp() + " " + v.getVarsta());
        }
    }
}