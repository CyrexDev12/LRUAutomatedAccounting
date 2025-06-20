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
}
