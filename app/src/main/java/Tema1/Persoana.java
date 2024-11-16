package Tema1;

import java.util.ArrayList;

public abstract class Persoana {
    String cnp;
    int varsta;
    String nume;
    protected Persoana() {

    }
    public Persoana(String nume, int varsta, String cnp) {
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
    public Candidat(String nume, int varsta, String cnp) {
        super(nume, varsta, cnp);
    }
    static void AdaugareCandidat (ArrayList<Candidat> candidati, ArrayList<Alegeri> alegeri, String idAlegeri, String nume, int varsta, String cnp) {
        boolean valid = false;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                break;
            }
        }
        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
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
            if (c.getCnp().equals(cnp)) {
                existaCNP = true;
                numeGasit = c.getNume();
                break;
            }
        }

        if (!existaCNP) {
            Candidat candidatNou = new Candidat(cnp, varsta, nume);
            candidati.add(candidatNou);
            System.out.println("S-a adaugat candidatul " + nume);
        } else {
            System.out.println("EROARE: Candidatul " + numeGasit + " are deja acelasi CNP");
            return;
        }
    }
}
