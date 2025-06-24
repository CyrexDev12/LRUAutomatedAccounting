import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class LRU {
    private String lruID; 
    private String name; 
    private String platform; 
    private String installDate; 
    private int expectedLife; 
    private Boolean status; 
    private String location; 
    private String notes; 
    private ArrayList<part> parts;

    public LRU(String lruID, String name, String platform, String installDate, int expectedLife, Boolean status, 
               String location, String notes) {
        this.lruID = lruID; 
        this.name = name; 
        this.platform = platform; 
        this.installDate = installDate;
        this.expectedLife = expectedLife; 
        this.status = status; 
        this.location = location; 
        this.notes = notes; 
        this.parts = new ArrayList<>(); // Initialize as empty list
    }

    // Getters
    public String getLruID() {
        return lruID;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getInstallDate() {
        return installDate;
    }

    public int getExpectedLife() {
        return expectedLife;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getNotes() {
        return notes;
    }

    public ArrayList<part> getParts() {
        return parts;
    }

    // Setters
    public void setLruID(String lruID) {
        this.lruID = lruID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public void setExpectedLife(int expectedLife) {
        this.expectedLife = expectedLife;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setParts(ArrayList<part> parts) {
        this.parts = parts;
    }

    // Utility method to add a single part object
    public void addPart(part p) {
        this.parts.add(p);
    }

    // Below are the calculations used to perform for finding the metrics for the user 


      public double calculateRUL() {
        LocalDate installDateToLocal = LocalDate.parse(installDate); // format: YYYY-MM-DD
        LocalDate today = LocalDate.now();
        long daysUsed = ChronoUnit.DAYS.between(installDateToLocal, today);
        double yearsUsed = daysUsed / 365.25; // account for leap years
        
        double rul = expectedLife - yearsUsed;
        
        return Math.round(rul * 100.0) / 100.00;
    }

    public String calculateObsolescenceRiskScore() {
        if (parts.isEmpty()) {
            return "Error! Parts list is empty for " + name;
        }

        double totalScore = 0.0;

        for (part p : parts) {
            String status = p.getLifeCycleStatus().toLowerCase();

            switch (status) {
                case "active":
                    totalScore += 0;
                    break;
                case "nrnd":
                    totalScore += 1;
                    break;
                case "eol":
                    totalScore += 2;
                    break;
            
                default:
                    totalScore += 1.5;
                    break;
            }
        }
        Double calculatedScore = (double) Math.round( totalScore / parts.size());
        // Determine risk based on calculated score 

        if (calculatedScore == 0) {
            return "Low risk (all parts active), calculated score : " + calculatedScore;
        } else if (calculatedScore > 0 && calculatedScore <= 0.9) {
            return "Low risk (mostly active parts, maybe one NRND)" + calculatedScore;
        } else if (calculatedScore > 0.9 && calculatedScore <= 1.4) {
            return "Moderate risk, (Mix of active and NRND, or a few EOL parts), calculated score " + calculatedScore;
        } else if (calculatedScore > 1.4 && calculatedScore <= 1.9) {
            return "High Risk (Several EOL parts or many NRND), calculated score " + calculatedScore;
        }
        return "Critical Risk (All parts are EOL), calculated score " + calculatedScore;
        
    }


}
