package cwk4;

import java.io.*;

/**
 * This class represents a Challenge
 * 
 * @author 
 * @version 
 */
public class Challenge implements Serializable {
	private String championName;
	private ChallengeType type;
    private int number;
    private int skillLevel;
    private int reward;

    /**
     * Constructor for objects of class Challenge
     * @param number - the number of the challenge
     * @param skillLevel - the skill level required for the challenge
     * @param reward - the reward for the challenge
     */
    public Challenge(int number, int skillLevel, int reward) {
        this.number = number;
        this.skillLevel = skillLevel;
        this.reward = reward;
    }

    public Challenge(ChallengeType type, String championName, int skillLevel, int reward) {
    	this.type = type;
	    this.championName = championName;
	    this.reward = reward;
	    this.skillLevel = skillLevel;
	    
	}

	/**
     * Get the number of the challenge
     * @return the number of the challenge
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get the skill level required for the challenge
     * @return the skill level required for the challenge
     */
    public int getSkillLevel() {
        return skillLevel;
    }

    /**
     * Get the reward for the challenge
     * @return the reward for the challenge
     */
    public int getReward() {
        return reward;
    }

    /**
     * Returns a string representation of the challenge
     * @return a string representation of the challenge
     */
    @Override
    public String toString() {
        return "Number: " + number + ", Skill Level: " + skillLevel + ", Reward: " + reward;
    }
}
