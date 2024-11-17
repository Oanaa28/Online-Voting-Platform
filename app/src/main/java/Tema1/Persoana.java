package Tema1;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public abstract class Persoana {
    private String cnp;
    private int varsta;
    private String nume;
    protected Persoana() {

    }
    public Persoana(String cnp, int varsta, String nume) {
        this.nume = nume;
        this.varsta = varsta;
        this.cnp = cnp;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public int getVarsta() {
        return varsta;
    }
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }
    public String getCnp() {
        return cnp;
    }
    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
}

class Candidat extends Persoana {
    private ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie;
    int nrVoturi;
    public Candidat() {

    }
    public void setVoturiPerCircumscriptie() {
        voturiPerCircumscriptie = new ArrayList<>();
    }
    public ArrayList getVoturiPerCircumscriptie() {
        return voturiPerCircumscriptie;
    }

    public void setNrVoturi(int nrVoturi) {
        this.nrVoturi = nrVoturi;
    }

    public int getNrVoturi() {
        return nrVoturi;
    }

    public Candidat(String cnp, int varsta, String nume, ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie, int nrVoturi) {
        super(cnp, varsta, nume);
        this.voturiPerCircumscriptie = new ArrayList<>();
        this.nrVoturi = nrVoturi;
    }

    public void adaugaVotInCircumscriptie(String numeCircumscriptie) {
        for (VoturiCircumscriptie v : voturiPerCircumscriptie) {
            if (v.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                v.setNumarVoturi(v.getNumarVoturi() + 1);
                return;
            }
        }
        voturiPerCircumscriptie.add(new VoturiCircumscriptie(numeCircumscriptie, 1));
    }

    public int getVotDinCircumscriptie(String numeCircumscriptie) {
        for (VoturiCircumscriptie v : voturiPerCircumscriptie) {
            if (v.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                return v.getNumarVoturi();
            }
        }
        return 0;
    }

    static void AdaugareCandidat (ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String idAlegeri, String cnp, int varsta, String nume, ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie) {

        boolean valid = false;
        int stagiu = 1;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                stagiu = a.getStagiu();
                valid = true;
                break;
            }
        }

        if (valid == false) {
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

        String numeGasit = "";
        boolean existaCNP = false;
        for (Candidat c : candidati) {
            //System.out.println("lalal" + c.getCnp() + c.getNume() + c.getVarsta());
            if (c.getCnp().equals(cnp)) {
                existaCNP = true;
                numeGasit = c.getNume();
                break;
            }
        }

        if (existaCNP == false) {
            Candidat candidatNou = new Candidat(cnp, varsta, nume, voturiPerCircumscriptie, 0);
            candidati.add(candidatNou);
            System.out.println("S-a adaugat candidatul " + nume);
        } else {
            System.out.println("EROARE: Candidatul " + numeGasit + " are deja acelasi CNP");
            return;
        }
    }
    static void EliminareCandidat (ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati, String idAlegeri, String cnp) {
        boolean valid = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }

        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
        }

        boolean existaCNP = false;
        Candidat candidatEliminat = null;
        for (Candidat c : candidati) {
            //System.out.println("lalal" + c.getCnp() + c.getNume() + c.getVarsta());
            if (c.getCnp().equals(cnp)) {
                candidatEliminat = c;
                existaCNP = true;
                break;
            }
        }

        if (existaCNP == false) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);
        } else {
            candidati.remove(candidatEliminat);
            System.out.println("S-a sters candidatul " + candidatEliminat.getNume());
            return;
        }
    }
    static void ListareCandidati (ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String idAlegeri) {
        boolean valid = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }

        if (valid == false) {
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

        Collections.sort(candidati, new Comparator<Candidat>() {
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

class Votant extends Persoana {

    String circumscriptieVotant;
    boolean vot;
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

    public Votant() {

    }
    public Votant(String cnp, int varsta, String nume, String circumscriptieVotant, boolean vot) {
        super(cnp, varsta, nume);
        this.circumscriptieVotant = circumscriptieVotant;
        this.vot = vot;
    }

    static void AdaugareVotant (ArrayList<Alegeri> alegeri, ArrayList<Votant> votanti, ArrayList<Circumscriptie> circumscriptii, String idAlegeri, String numeCircumscriptie, String cnp, int varsta, boolean indemanare, String nume ) {
        boolean valid = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }
        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
        }
        boolean existaCircumscriptie = false;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                existaCircumscriptie = true;
                break;
            }
        }
        if (existaCircumscriptie == false) {
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

        String numeGasit = "";
        boolean existaCNP = false;
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnp)) {
                existaCNP = true;
                numeGasit = v.getNume();
                break;
            }
        }

        if (existaCNP == true) {
            System.out.println("EROARE: Votantul " + numeGasit + " are deja acelasi CNP");
            return;
        }

        Votant votantNou = new Votant(cnp, varsta, nume, numeCircumscriptie, false);
        votanti.add(votantNou);
        System.out.println("S-a adaugat votantul " + nume);
    }
    static void ListareVotantiCircumscriptie (ArrayList<Alegeri> alegeri, ArrayList<Votant> votanti, ArrayList<Circumscriptie> circumscriptii, String idAlegeri, String numeCircumscriptie) {
        boolean valid = false;
        int stagiu = 0;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                stagiu = a.getStagiu();
                break;
            }
        }

        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }

        if (stagiu != 1 && stagiu != 2) {
            System.out.println("EROARE: Inca nu au inceput alegerile");
            return;
        }
        boolean existaCircumscriptie = false;
        for (Circumscriptie c : circumscriptii) {
            if(c.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                existaCircumscriptie = true;
            }
        }
        if (existaCircumscriptie == false) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        ArrayList<Votant> votantiCircumscriptie = new ArrayList<Votant>();

        boolean existaVotanti = false;

        for (Votant v : votanti) {
            if (v.getCircumscriptieVotant().equals(numeCircumscriptie)) {
                votantiCircumscriptie.add(v);
                existaVotanti = true;
            }
        }

        if (existaVotanti == false) {
            System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
            return;
        }

        Collections.sort(votantiCircumscriptie, new Comparator<Votant>() {
            public int compare(Votant v1, Votant v2) {
                return v1.getCnp().compareTo(v2.getCnp());
            }
        });
        System.out.println("Votantii din " + numeCircumscriptie + ":");
        for (Votant v : votantiCircumscriptie) {
            System.out.println(v.getNume() + " " + v.getCnp() + " " + v.getVarsta());
        }
    }
}