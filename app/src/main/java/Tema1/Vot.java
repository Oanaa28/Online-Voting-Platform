package Tema1;

import java.util.ArrayList;

public class Vot {
    String idAlegeri;
    String numeCircumscriptie;
    String cnpVotant;
    String cnpCandidat;
    public Vot() {

    }
    public Vot(String idAlegeri, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
        this.idAlegeri = idAlegeri;
        this.numeCircumscriptie = numeCircumscriptie;
        this.cnpVotant = cnpVotant;
        this.cnpCandidat = cnpCandidat;
    }
    public String getIdAlegeri() {
        return idAlegeri;
    }
    public void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }
    public String getNumeCircumscriptie() {
        return numeCircumscriptie;
    }
    public void setNumeCircumscriptie(String numeCircumscriptie) {
        this.numeCircumscriptie = numeCircumscriptie;
    }
    public String getCnpVotant() {
        return cnpVotant;
    }
    public void setCnpVotant(String cnpVotant) {
        this.cnpVotant = cnpVotant;
    }
    public String getCnpCandidat() {
        return cnpCandidat;
    }
    public void setCnpCandidat(String cnpCandidat) {
        this.cnpCandidat = cnpCandidat;
    }
    static void Votare(ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati, ArrayList<Votant> votanti, ArrayList<Circumscriptie> circumscriptii, String idAlegeri, String numeCircumscriptie, String cnpVotant, String cnpCandidat) {
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

        boolean existaCnpVotant = false;
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnpVotant)) {
                existaCnpVotant = true;
                break;
            }
        }

        if (existaCnpVotant == false) {
            System.out.println("EROARE: Nu exista un votant cu CNP-ul " + cnpVotant);
            return;
        }

        boolean existaCnpCandidat = false;
        for (Candidat c : candidati) {
            if (c.getCnp().equals(cnpCandidat)) {
                existaCnpCandidat = true;
                break;
            }
        }
        if (existaCnpCandidat == false) {
            System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
            return;
        }

        boolean existaCircumscriptie = false;
        for (Circumscriptie c : circumscriptii) {
            if (c.getNumeCircumscriptie().equals(numeCircumscriptie)) {
                existaCircumscriptie = true;
            }
        }
        if (!existaCircumscriptie) {
            System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
            return;
        }

        boolean esteInregistrat = false;
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnpVotant) && v.getCircumscriptieVotant().equals(numeCircumscriptie)) {
//                System.out.println("lalalalla" + v.getCircumscriptieVotant());
//                System.out.println("lalalalla" + numeCircumscriptie);
                esteInregistrat = true;
                break;
            }
        }
        if (!esteInregistrat) {
            System.out.println("FRAUDA: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
            return;
        }
        String numeVotant = "";
        for (Votant v : votanti) {
            if (v.getCnp().equals(cnpVotant)) {
                if (!v.getVot()) {
                    numeVotant = v.getNume();
                    v.setVot(true);
                    break;
                } else {
                    System.out.println("FRAUDa: Votantul cu CNP-ul " + v.getCnp() + " a incercat sa comita o frauda. Votul a fost anulat");
                    return;
                }
            }
        }
        String numeCandidat = "";
        for (Candidat c : candidati) {
            if (c.getCnp().equals(cnpCandidat)) {
                numeCandidat = c.getNume();
                //int nrVoturi = c.getVoturiPerCircumscriptie()
                break;
            }
        }
        System.out.println(numeVotant + " a votat pentru " + numeCandidat);
    }
}
