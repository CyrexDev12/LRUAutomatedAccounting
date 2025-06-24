import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // File paths
        String LRUdataFilePath = "lru_data.csv";
        String PartDataFilepath = "part_data.csv";

        // Load part data and build part map
        ArrayList<part> partList = PartReader.readParts(PartDataFilepath);
        Map<String, part> partMap = new HashMap<>();
        for (part p : partList) {
            partMap.put(p.getPartID(), p);
        }

        // Load LRU data with part linkage
        ArrayList<LRU> LRUList = LRUreader.readLRUs(LRUdataFilePath, partMap);

        // Start main menu
        promptUserMainMenu(scnr, LRUList, partList);
    }

    public static void promptUserMainMenu(Scanner scnr, ArrayList<LRU> LRUList, ArrayList<part> partList) {
        int userSelectMain = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println("Welcome to the aircraft Avionics COE Obsolescence area");
            System.out.println("1. Obsolescence Calculator");
            System.out.println("2. Add new LRU to database");
            System.out.println("3. Exit");

            userSelectMain = scnr.nextInt();
            scnr.nextLine(); // consume newline

            switch (userSelectMain) {
                case 1:
                    promptCalculatorMenu(scnr, LRUList, partList);
                    valid = true;
                    break;
                case 2:
                    addNewLRU(scnr, LRUList);
                    valid = true;
                    break;
                case 3:
                    System.out.println("Exiting...");
                    valid = true;
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
            }
        }
    }

    public static void promptCalculatorMenu(Scanner scnr, ArrayList<LRU> LRUList, ArrayList<part> partList) {
        if (LRUList.isEmpty()) {
            System.out.println("No LRUs found in the database.");
            return;
        }

        System.out.println("Here's the list of the current LRUs in the database:");
        for (int i = 0; i < LRUList.size(); i++) {
            LRU lru = LRUList.get(i);
            System.out.println((i + 1) + ". " + lru.getName() + " (ID: " + lru.getLruID() + ")");
        }

        System.out.println("Please enter the number of the LRU you'd like to perform metrics on:");
        int selection = -1;

        while (selection < 1 || selection > LRUList.size()) {
            if (scnr.hasNextInt()) {
                selection = scnr.nextInt();
                scnr.nextLine(); // consume newline
                if (selection < 1 || selection > LRUList.size()) {
                    System.out.println("Invalid selection. Please enter a number between 1 and " + LRUList.size() + ":");
                }
            } else {
                System.out.println("Invalid input. Please enter a number:");
                scnr.next(); // consume invalid input
            }
        }

        LRU selectedLRU = LRUList.get(selection - 1);
        System.out.println("You selected: " + selectedLRU.getName() + " (ID: " + selectedLRU.getLruID() + ")");

        System.out.println("Parts associated with this LRU:");
        for (part p : selectedLRU.getParts()) {
            System.out.println("- " + p.getPartID() + " | Status: " + p.getLifeCycleStatus() + " | EOL: " + p.getCommercialEOL());
        }

        // TODO: Provide more data to show the user on the parts, for example the inventory, etc...

        // TODO: Add metric calculations here
        // Calculations: Remaning Useful Life, Obsolescence Risk Score

        System.out.println("Would you like to run metrics on the LRU? Or a specific part in the LRU?\n Enter 1 to continue");
        
        int userSelectionMetric = scnr.nextInt(); 

        switch (userSelectionMetric) {
            case 1:
                runMetricsLRU(scnr, selectedLRU);
                break;
            case 2: 
                System.out.println("This feature has not been implented yet");
                break;
        
            default:
                System.out.println("Error: Invalid selection");
                break;
        }




    }



    public static void runMetricsLRU(Scanner scnr, LRU SelectedLRU) {
        System.out.println("What metrics would you like to run on " + SelectedLRU.getName());

        // Remaining useful life = Expected Life - (Current Year - Install Year)
        System.out.println("1. (RUL) Remaning Useful life\n2. (ORS) Obsolescense Risk Score"); 

        // TODO: Connect to python and integrate visual graphs and representations for visual aid

        int userMetricSelection = scnr.nextInt(); 

        switch (userMetricSelection) {
            case 1:
                System.out.println("The expected Remaining Useful Life of the LRU is: " + SelectedLRU.calculateRUL());
                break;
            case 2: 
                System.out.println("The obsolescense risk score of the LRU calculated is " + SelectedLRU.calculateObsolescenceRiskScore());
        
            default:
                break;
        }

    }

    public static void addNewLRU(Scanner scnr, ArrayList<LRU> lruList) {
        scnr.nextLine(); // Clear buffer

        System.out.println("What is the ID of the LRU?");
        String ID = scnr.nextLine();

        System.out.println("What is the name of the LRU?");
        String name = scnr.nextLine();

        System.out.println("What is the platform (Aircraft) that this is used on?");
        String platform = scnr.nextLine();

        System.out.println("What is the install date of the LRU?");
        String installDate = scnr.nextLine();

        System.out.println("What is the expected life of the LRU (in years)?");
        int expectedLife = scnr.nextInt();
        scnr.nextLine(); // Clear buffer

        System.out.println("Is the LRU currently operational? (Y/N)");
        boolean status = promptIsOperational(scnr);

        System.out.println("What is the managing location of this LRU? (e.g. AZ75)");
        String location = scnr.nextLine();

        System.out.println("Enter any notes or a description of the LRU:");
        String notes = scnr.nextLine();

        // Create the LRU object
        LRU newLRU = new LRU(ID, name, platform, installDate, expectedLife, status, location, notes);

        // NOTE: You could add part selection here in the future
        lruList.add(newLRU);
        System.out.println("LRU added successfully!");

        // TODO: When a new LRU is added we need to ensure that it is written to the database, along with the parts that go along with it. 
    }

    public static boolean promptIsOperational(Scanner scnr) {
        while (true) {
            String input = scnr.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N':");
            }
        }
    }
}
