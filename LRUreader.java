import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LRUreader {
    public static ArrayList<LRU> readLRUs(String filePath, Map<String, part> partMap) {
        ArrayList<LRU> lruList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String lruID = values[0];
                String name = values[1];
                String platform = values[2];
                String installDate = values[3];
                int expectedLife = Integer.parseInt(values[4]);
                Boolean status = Boolean.parseBoolean(values[5]);
                String location = values[6];
                String notes = values[7];

                LRU lru = new LRU(lruID, name, platform, installDate, expectedLife, status, location, notes);

                // Parse partIDs and link actual part objects
                if (values.length > 8) {
                    String[] partIDs = values[8].split(";");
                    for (String partID : partIDs) {
                        partID = partID.trim();
                        if (partMap.containsKey(partID)) {
                            lru.addPart(partMap.get(partID));
                        } else {
                            System.out.println("Warning: Part ID '" + partID + "' not found in part map.");
                        }
                    }
                }

                lruList.add(lru);
            }
        } catch (Exception e) {
            System.out.println("Error reading LRU CSV: " + e.getMessage());
            e.printStackTrace();
        }

        return lruList;
    }
}
