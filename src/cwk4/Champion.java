package cwk4;

import java.io.*;

/**
 * This class represents a Champion
 * 
 * @author 
 * @version 
 */
public class Champion implements Serializable {
    private String name;
    private int entryFee;
    private int skillLevel;
    
    public Champion(String name, int entryFee, int skillLevel, ChampionState state) {
		super();
		this.name = name;
		this.entryFee = entryFee;
		this.skillLevel = skillLevel;
		this.state = state;
	}

	private ChampionState state;

    /**
     * Constructor for objects of class Champion
     * @param name - the name of the champion
     * @param state - the state of the champion
     * @param entryFee - the entry fee for the champion
     */
    public Champion(String name, ChampionState state, int entryFee) {
        this.name = name;
        this.state = state;
        this.entryFee = entryFee;
        this.skillLevel = 0;
    }

    /**
     * Get the name of the champion
     * @return the name of the champion
     */
    public String getName() {
        return name;
    }

    /**
     * Get the state of the champion
     * @return the state of the champion
     */
    public ChampionState getState() {
        return state;
    }

    /**
     * Set the state of the champion
     * @param state the state to set
     */
    public void setState(ChampionState state) {
        this.state = state;
    }

    /**
     * Get the entry fee for the champion
     * @return the entry fee for the champion
     */
    public int getEntryFee() {
        return entryFee;
    }

    /**
     * Get the skill level of the champion
     * @return the skill level of the champion
     */
    public int getSkillLevel() {
        return skillLevel;
    }

    /**
     * Set the skill level of the champion
     * @param skillLevel the skill level to set
     */
    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    /**
     * Returns a string representation of the champion
     * @return a string representation of the champion
     */
    @Override
    public String toString() {
        return "Name: " + name + ", State: " + state + ", Entry Fee: " + entryFee + ", Skill Level: " + skillLevel;
    }
}
