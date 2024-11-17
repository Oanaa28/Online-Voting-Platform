package Tema1;

import java.util.ArrayList;

public class Alegeri {
    private String idAlegeri;
    private String numeAlegeri;
    private int stagiu;

    public Alegeri(String idAlegeri, String numeAlegeri, int stagiu) {
        this.idAlegeri = idAlegeri;
        this.numeAlegeri = numeAlegeri;
        this.stagiu = stagiu;
    }
    String getIdAlegeri() {
        return idAlegeri;
    }
    void setIdAlegeri(String idAlegeri) {
        this.idAlegeri = idAlegeri;
    }
    String getNumeAlegeri() {
        return numeAlegeri;
    }
    void setNumeAlegeri(String numeAlegeri) {
        this.numeAlegeri = numeAlegeri;
    }
    int getStagiu() {
        return stagiu;
    }
    void setStagiu(int stagiu) {
        this.stagiu = stagiu;
    }

    static void CreareAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri, String numeAlegeri) {
        //validare id alegeri
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                System.out.println("EROARE: Deja exista alegeri cu id " + a.getIdAlegeri());
                return;
            }
        }
        Alegeri alegereNoua = new Alegeri(idAlegeri, numeAlegeri, 0);
        alegeri.add(alegereNoua);
        System.out.println("S-au creat alegerile " + numeAlegeri);
    }
    static void PornireAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri) {
        boolean valid = false;
        int stagiu = 0;
        String numeAlegere = "";
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = true;
                numeAlegere = a.getNumeAlegeri();
                stagiu = a.getStagiu();
                break;
            }
        }
        if (valid == false) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        for (Alegeri a : alegeri) {
            if(a.getIdAlegeri().equals(idAlegeri)) {
                if (stagiu == 0) {
                    System.out.println("Au pornit alegerile " + numeAlegere);
                    a.setStagiu(1);
                } else {
                    System.out.println("EROARE: Alegerile deja au inceput");
                }
            }
        }
    }
    static void OprireAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri) {
        int stagiu = 0, valid = 0;
        String numeAlegere = "";
        Alegeri alegere = null;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                alegere = a;
                numeAlegere = a.getNumeAlegeri();
                valid = 1;
                stagiu = a.getStagiu();
            }
        }
        if (valid == 0) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        if (stagiu == 0) {
            System.out.println("EROARE: Nu este perioada de votare");
            return;
        }
        System.out.println("S-au terminat alegerile " + numeAlegere);
        alegere.setStagiu(2);
    }
    static void StergereAlegeri(ArrayList<Alegeri> alegeri, ArrayList<Candidat> candidati, ArrayList<Circumscriptie> circumscriptii, String idAlegeri) {
        int valid = 0;
        Alegeri alegereStergere = null;
        for (Alegeri a : alegeri) {
            if (a.getIdAlegeri().equals(idAlegeri)) {
                valid = 1;
                alegereStergere = a;
            }
        }
        if (valid == 0) {
            System.out.println("EROARE: Nu exista alegeri cu acest id");
            return;
        }
        alegeri.remove(alegereStergere);
        System.out.println("S-au sters alegerile " + alegereStergere.getNumeAlegeri());

        for (Candidat c : candidati) {
            Candidat.EliminareCandidat(alegeri, candidati, idAlegeri, c.getCnp());
        }
        for (Circumscriptie c : circumscriptii) {
            Circumscriptie.EliminareCircumscriptie(alegeri, circumscriptii, idAlegeri, c.getNumeCircumscriptie());
        }
    }
    static void ListareAlegeri(ArrayList<Alegeri> alegeri) {
        if (alegeri == null || alegeri.isEmpty()) {
            System.out.println("GOL: Nu sunt alegeri");
            return;
        }
        System.out.println("Alegeri:");
        for (Alegeri a : alegeri) {
            System.out.println(a.getIdAlegeri() + " " + a.getNumeAlegeri());
        }
    }
}
