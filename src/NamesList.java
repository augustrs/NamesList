import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NamesList {

    private ArrayList<String> names;


    public NamesList() {
        names = new ArrayList<>();
    }

    public void startUserInterface()  {
        System.out.println("""
                Welcome to the NamesList - enterprise edition.
                ----------------------------------------------
                """);


        Scanner sc = new Scanner(System.in);
        int choice = 99;
        while (choice != 0) {
            showMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> displayListOfNames();
                case 2 -> loadListOfNames();
                case 3 -> saveListOfNames();
                case 4 -> enterNames();
                case 0 -> exit();
                default -> System.out.println("Unknown command - please use 0-4");
            }

        }
    }

    private void showMenu() {
        System.out.println("""
                1) Display list of names
                2) Load list of names
                3) Save list of names
                4) Enter names
                0) Exit
                """);
    }

    private void enterNames() {
        System.out.println("""
                Enter names
                -----------
                Enter each name you want to add to the list. End by entering an empty name.
                """);
        Scanner sc = new Scanner(System.in);
        String name = "-nothing yet-";
        while (!name.isBlank() && sc.hasNextLine()) {
            name = sc.nextLine();
            if (!name.isBlank()) {
                names.add(name);
                System.out.println(name + " added to the list, enter another, or empty to quit");
            }
        }
        System.out.println("Done");
    }

    private void saveListOfNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename you want to save to: ");
        String filename = scanner.nextLine().trim();
        try {
            PrintStream output = new PrintStream(new File(filename));

            for (String name : names) {

                output.println(name);
            }
            loadingtext("Saving");

            System.out.println(names.size() + " names saved to names.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            saveListOfNames();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadingtext(String nameOrLoading) throws InterruptedException {
        System.out.print(nameOrLoading);
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.println(".");
    }
    private void loadListOfNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename you want to load from: ");
        String filename = scanner.nextLine().trim();

        try {
            Scanner loader = new Scanner(new File(filename));
            String name = "-nothing yet-";
            while (!name.isBlank() && loader.hasNextLine()) {
                name = loader.nextLine();
                if (!name.isBlank())
                    names.add(name);

            }
            loadingtext("Loading");
            System.out.println(names.size() + " names loaded from file.");
            System.out.println("\u2500".repeat(50) + " ");
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found");
            loadListOfNames();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void displayListOfNames() {

        for (String name : names) {
            System.out.println(name);
        }
        String isAre = "are";
        String s = "s";
        if (names.size() == 1) {
            isAre = "is";
            s = "";
        }
        System.out.println("There " + isAre + " " + names.size() + " name" + s + " in the system");
        System.out.println("\u2500".repeat(50) + " ");

    }

    private void exit() {
        System.out.println("""
                ...
                Thank you for using NamesList - enterprise edition.
                Don't forget to subscribe to our newsletter!""");

    }


    public static void main(String[] args)  {

        NamesList app = new NamesList();
        app.startUserInterface();
    }
}
