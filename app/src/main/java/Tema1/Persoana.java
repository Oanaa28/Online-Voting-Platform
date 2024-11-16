package Tema1;

import java.util.ArrayList;

public abstract class Persoana {
    String cnp;
    int varsta;
    String nume;
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
    public Candidat() {

    }
    public Candidat(String cnp, int varsta, String nume) {
        super(cnp, varsta, nume);
    }
    static void AdaugareCandidat (ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String idAlegeri, String cnp, int varsta, String nume) {

        boolean valid = false, stagiu = false;
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

        if (stagiu == false) {
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
            Candidat candidatNou = new Candidat(cnp, varsta, nume);
            candidati.add(candidatNou);
            System.out.println("S-a adaugat candidatul " + nume);
        } else {
            System.out.println("EROARE: Candidatul " + numeGasit + " are deja acelasi CNP");
            return;
        }
    }
    static void EliminareCandidat (ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati, String idAlegeri, String cnp) {
        boolean valid = false, stagiu = false;
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
        if (stagiu == false) {
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
}
