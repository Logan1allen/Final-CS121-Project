public class Choice {
    private String description;
    private Location nextLocation;
    private String event;
    
    public Choice(String description, Location nextLocation) {
        this.description = description;
        this.nextLocation = nextLocation;
        this.event = null;
    }
    
    public Choice(String description, Location nextLocation, String event) {
        this.description = description;
        this.nextLocation = nextLocation;
        this.event = event;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Location getNextLocation() {
        return nextLocation;
    }
    
    public String getEvent() {
        return event;
    }
}
