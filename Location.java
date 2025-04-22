import java.util.ArrayList;
import java.util.List;

public class Location {
    private String name;
    private String description;
    private List<Choice> choices;
    
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.choices = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public List<Choice> getChoices() {
        return choices;
    }
    
    public void addChoice(Choice choice) {
        choices.add(choice);
    }
}
