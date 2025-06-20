import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PartReader {
    public static ArrayList<part> readParts(String filePath) {
        ArrayList<part> partList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Parse values from CSV
                String partID = values[0];
                String lifeCycleStatus = values[1];
                String commercialEOL = values[2];
                String orderDate = values[3];
                String receivedDate = values[4];
                double unitCost = Double.parseDouble(values[5]);
                int annualUsage = Integer.parseInt(values[6]);
                boolean requiresRedesign = Boolean.parseBoolean(values[7]);
                int criticalityScore = Integer.parseInt(values[8]);
                double failRate = Double.parseDouble(values[9]);
                int totalPopulation = Integer.parseInt(values[10]);
                int totalSuppliers = Integer.parseInt(values[11]);
                boolean isItar = Boolean.parseBoolean(values[12]);

                // Create part object
                part p = new part(partID, lifeCycleStatus, commercialEOL, orderDate, receivedDate,
                                  unitCost, annualUsage, requiresRedesign, criticalityScore,
                                  failRate, totalPopulation, totalSuppliers, isItar);

                partList.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error reading Part CSV: " + e.getMessage());
            e.printStackTrace();
        }

        return partList;
    }
}
