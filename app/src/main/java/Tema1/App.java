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
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                String comanda = scanner.nextLine();
                String id = comanda.split(" ", 2)[0];
                String nume = comanda.split(" ", 2)[1];
                Alegeri.CreareAlegeri(alegeri, id, nume);
            } else if (input.equals("1")) {
                String id = scanner.nextLine();
                Alegeri.PornireAlegeri(alegeri, id);

            }
        }
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}