package Tema1;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.stream.Collectors;
import java.text.*;

public class App {
    private Scanner scanner;

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
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (input.equals("0")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 2)[0];
                String nume_alegeri = comanda.split(" ", 2)[1];
                Alegeri.CreareAlegeri(alegeri, id_alegeri, nume_alegeri);

            } else if (input.equals("1")) {

                String id_alegeri = scanner.nextLine();
                Alegeri.PornireAlegeri(alegeri, id_alegeri);

            } else if (input.equals("2")) {

                String comanda = scanner.nextLine();
                String id_circumscriptie = comanda.split(" ", 3)[0];
                String nume_circumscriptie = comanda.split(" ", 3)[1];
                String regiune = comanda.split(" ", 3)[2];
                Circumscriptie.CreareCircumscriptie(alegeri, circumscriptii, id_circumscriptie, nume_circumscriptie, regiune);

            } else if (input.equals("3")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 2)[0];
                String nume_circumscriptie = comanda.split(" ", 2)[1];
                Circumscriptie.EliminareCircumscriptie(alegeri, circumscriptii, id_alegeri, nume_circumscriptie);
            } else if (input.equals("4")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 4)[0];
                String cnp = comanda.split(" ", 4)[1];
                String varsta = comanda.split(" ", 4)[2];
                String nume = comanda.split(" ", 4)[3];
                int varstaInt = Integer.parseInt(varsta);

                //System.out.println(id_alegeri + "," + nume + "," + cnp + "," + varstaInt);

                Candidat.AdaugareCandidat(candidati, alegeri, id_alegeri, cnp, varstaInt, nume);
            } else if (input.equals("5")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 2)[0];
                String cnp = comanda.split(" ", 2)[1];

                Candidat.EliminareCandidat(alegeri, candidati, id_alegeri, cnp);
            } else if (input.equals("6")) {

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

            } else if (input.equals("7")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 1)[0];
                Candidat.ListareCandidati(candidati, alegeri, id_alegeri);
            } else if (input.equals("8")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 2)[0];
                String nume_circumscriptie = comanda.split(" ", 2)[1];
                Votant.ListareVotantiCircumscriptie(alegeri, votanti, circumscriptii, id_alegeri, nume_circumscriptie);
            } else if (input.equals("9")) {

                String comanda = scanner.nextLine();
                String id_alegeri = comanda.split(" ", 5)[0];
                String nume_circumscriptie = comanda.split(" ", 5)[1];
                String CNP_votant = comanda.split(" ", 5)[2];
                String CNP_candidat = comanda.split(" ", 5)[3];

                Vot.Votare(alegeri, candidati, votanti, circumscriptii, id_alegeri, nume_circumscriptie,CNP_votant, CNP_candidat);

            } else if (input.equals("10")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("11")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("12")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("13")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("14")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("15")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("16")) {

                String comanda = scanner.nextLine();

            } else if (input.equals("17")) {

                String comanda = scanner.nextLine();

            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}