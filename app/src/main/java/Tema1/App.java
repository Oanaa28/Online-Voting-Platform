package Tema1;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private final Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public void run() {
        // Implementați aici cerințele din enunț
        // Pentru citirea datelor de la tastatura se folosește câmpul scanner.
        ArrayList<Alegeri> alegeri = new ArrayList<>();
        ArrayList<Circumscriptie> circumscriptii = new ArrayList<>();
        ArrayList<Candidat> candidati = new ArrayList<>();
        ArrayList<Votant> votanti = new ArrayList<>();
        ArrayList<VoturiCircumscriptie> voturiPerCircumscriptie = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input);
            switch (input) {
                case "0": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 2)[0];
                    String nume_alegeri = comanda.split(" ", 2)[1];

                    Alegeri.CreareAlegeri(alegeri, id_alegeri, nume_alegeri);
                    break;
                }
                case "1": {

                    String id_alegeri = scanner.nextLine();
                    Alegeri.PornireAlegeri(alegeri, id_alegeri);
                    break;
                }
                case "2": {

                    String comanda = scanner.nextLine();
                    String id_circumscriptie = comanda.split(" ", 3)[0];
                    String nume_circumscriptie = comanda.split(" ", 3)[1];
                    String regiune = comanda.split(" ", 3)[2];
                    Circumscriptie.CreareCircumscriptie(alegeri, circumscriptii, voturiPerCircumscriptie, id_circumscriptie, nume_circumscriptie, regiune);
                    break;
                }
                case "3": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 2)[0];
                    String nume_circumscriptie = comanda.split(" ", 2)[1];
                    Circumscriptie.EliminareCircumscriptie(alegeri, circumscriptii, id_alegeri, nume_circumscriptie);
                    break;
                }
                case "4": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 4)[0];
                    String cnp = comanda.split(" ", 4)[1];
                    String varsta = comanda.split(" ", 4)[2];
                    String nume = comanda.split(" ", 4)[3];
                    int varstaInt = Integer.parseInt(varsta);

                    Candidat.AdaugareCandidat(candidati, alegeri, id_alegeri, cnp, varstaInt, nume);
                    break;
                }
                case "5": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 2)[0];
                    String cnp = comanda.split(" ", 2)[1];

                    Candidat.EliminareCandidat(alegeri, candidati, id_alegeri, cnp);
                    break;
                }
                case "6": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 6)[0];
                    String nume_circumscriptie = comanda.split(" ", 6)[1];
                    String cnp = comanda.split(" ", 6)[2];
                    String varsta = comanda.split(" ", 6)[3];
                    String indemanare = comanda.split(" ", 6)[4];
                    String nume = comanda.split(" ", 6)[5];
                    int varstaInt = Integer.parseInt(varsta);
                    boolean indemanareBoolean = Boolean.parseBoolean(indemanare);
                    Votant.AdaugareVotant(alegeri, votanti, circumscriptii, id_alegeri, nume_circumscriptie, cnp, varstaInt, indemanareBoolean, nume);
                    break;
                }
                case "7": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 1)[0];
                    Candidat.ListareCandidati(candidati, alegeri, id_alegeri);
                    break;
                }
                case "8": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 2)[0];
                    String nume_circumscriptie = comanda.split(" ", 2)[1];
                    Votant.ListareVotantiCircumscriptie(alegeri, votanti, circumscriptii, id_alegeri, nume_circumscriptie);
                    break;
                }
                case "9": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 5)[0];
                    String nume_circumscriptie = comanda.split(" ", 5)[1];
                    String CNP_votant = comanda.split(" ", 5)[2];
                    String CNP_candidat = comanda.split(" ", 5)[3];

                    Vot.Votare(alegeri, candidati, votanti, circumscriptii, voturiPerCircumscriptie, id_alegeri, nume_circumscriptie, CNP_votant, CNP_candidat);
                    break;
                }
                case "10": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 1)[0];
                    Alegeri.OprireAlegeri(alegeri, id_alegeri);
                    break;
                }
                case "11": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 2)[0];
                    String nume_circumscriptie = comanda.split(" ", 2)[1];
                    Raport.CreeazaRaportPerCircumscriptie(voturiPerCircumscriptie, circumscriptii, candidati, alegeri, id_alegeri, nume_circumscriptie);
                    break;
                }
                case "12": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 1)[0];
                    Raport.CreeazaRaportNational(circumscriptii, candidati, alegeri, id_alegeri);
                    break;

                }
                case "13": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 2)[0];
                    String nume_circumscriptie = comanda.split(" ", 2)[1];
                    Analiza.AnalizaPerCircumscriptie(voturiPerCircumscriptie, candidati, circumscriptii, alegeri, id_alegeri, nume_circumscriptie);
                    break;
                }
                case "14": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 1)[0];
                    Analiza.AnalizaPePlanNational(candidati, circumscriptii, alegeri, id_alegeri);
                    break;
                }
                case "15": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 1)[0];
                    Frauda.RapoarteFraude(alegeri, votanti, id_alegeri);
                    break;
                }
                case "16": {

                    String comanda = scanner.nextLine();
                    String id_alegeri = comanda.split(" ", 1)[0];
                    Alegeri.StergereAlegeri(alegeri, candidati, circumscriptii, id_alegeri);
                    break;

                }
                case "17": {
                    Alegeri.ListareAlegeri(alegeri);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}