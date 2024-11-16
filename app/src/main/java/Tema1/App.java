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
                Candidat.AdaugareCandidat(candidati, alegeri, id_alegeri, nume, varstaInt, cnp);
            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}