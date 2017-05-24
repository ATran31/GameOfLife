/*
Pattern.java

Handles the creation and naming of new pattern objects
*/

public class Pattern {
    // default states
    private String patternName = "None";

    // constructors
    public Pattern() {}

    public Pattern(String s){
        this.patternName = s;
    }

    public String getPatternName(){
        return this.patternName;
    }
}