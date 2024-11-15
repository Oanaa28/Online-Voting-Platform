package Tema1;

import java.util.ArrayList;

public class Alegeri {
    private String idAlegeri;
    private String numeAlegeri;
    private boolean stagiu;

    public Alegeri(String idAlegeri, String numeAlegeri, boolean stagiu) {
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
    boolean getStagiu() {
        return stagiu;
    }
    void setStagiu(boolean stagiu) {
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
        Alegeri alegereNoua = new Alegeri(idAlegeri, numeAlegeri, false);
        alegeri.add(alegereNoua);
        System.out.println("S-au creat alegerile " + numeAlegeri);
    }
    static void PornireAlegeri (ArrayList<Alegeri> alegeri, String idAlegeri) {
        boolean stagiu = false, valid = false;
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
                if (stagiu == false) {
                    System.out.println("Au pornit alegerile " + numeAlegere);
                    a.setStagiu(true);
                } else {
                    System.out.println("EROARE: Alegerile deja au inceput");
                }
            }
        }
    }
}
