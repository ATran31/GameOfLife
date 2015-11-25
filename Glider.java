/*
Glider.java

Handles creation of glider objects
Child of Pattern
*/

public class Glider extends Pattern {
    
    // default states
    private String [][] gliderPattern = {{" ", "*", " "}, {" ", " ", "*"}, {"*","*","*"}};

    // constructors
    public Glider(){
        super("Glider");
    }
    
    public String [][] getPattern(){
        return gliderPattern;
    }
}