package cwk4;

import java.io.*;

/**
 * Enumeration class ChampionState 
 * Specifies all possible states of a champion
 * 
 * @author 
 * @version 
 */
public enum ChampionState implements Serializable {
    WAITING(" Waiting in reserve"), ENTERED(" Entered"),  DISQUALIFIED (" Disqualified");
    private String state;
    
    private ChampionState(String st) {
        state = st;
    }
    
    public String toString() {
        return state;
    }
}
