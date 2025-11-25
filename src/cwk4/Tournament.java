package cwk4;

import java.util.*;
import java.io.*;

/**
 * This class represents a Tournament for the CARE project.
 * It implements the CARE interface.
 * 
 * @author 
 * @version 
 */

public class Tournament implements CARE {

    private String vizier;
    private int treasury;
    private Map<String, Champion> champions;
    private Map<Integer, Challenge> challenges;

    //**************** Constructor ************************** 
    /** Constructor requires the name of the vizier
     * @param viz the name of the vizier
     */  
    public Tournament(String viz) {
        this.vizier = viz;
        treasury = 0;
        champions = new HashMap<>();
        challenges = new HashMap<>();
        setupChampions();
        setupChallenges();
    }

    /** Constructor requires the name of the vizier and the
     * name of the file storing challenges
     * @param viz the name of the vizier
     * @param filename name of file storing challenges
     */  
    public Tournament(String viz, String filename) {
        this(viz);
        readChallenges(filename);
    }

    //**************** CARE ************************** 

    /**Returns a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     * 
     * @return a String representation of the state of the game,
     * including the name of the vizier, state of the treasury,
     * whether defeated or not, and the champions currently in the 
     * team,(or, "No champions" if team is empty)
     **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vizier: ").append(vizier).append("\n");
        sb.append("Treasury: ").append(treasury).append("\n");
        sb.append("Champions in reserve:\n");
        sb.append(getReserve()).append("\n");
        sb.append("Champions in Vizier's team:\n");
        sb.append(getTeam()).append("\n");
        return sb.toString();
    }

    /** returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     * @returns true if Treasury <=0 and the vizier's team has no 
     * champions which can be retired. 
     */
    @Override
    public boolean isDefeated() {
        return (treasury <= 0 && champions.isEmpty());
    }

    /** returns the amount of money in the Treasury
     * @returns the amount of money in the Treasury
     */
    @Override
    public int getMoney() {
        return treasury;
    }

    /**Returns a String representation of all champions in the reserves
     * @return a String representation of all champions in the reserves
     **/
    @Override
    public String getReserve() {
        StringBuilder sb = new StringBuilder();
        for (Champion champ : champions.values()) {
            if (champ.getState().equals(ChampionState.WAITING)) {
                sb.append(champ.getName()).append(": £").append(champ.getEntryFee()).append("\n");
            }
        }
        if (sb.length() == 0) {
            return "No champions in reserve";
        }
        return sb.toString();
    }

    /** Returns details of the champion with the given name. 
     * Champion names are unique.
     * @return details of the champion with the given name
     **/
    @Override
    public String getChampionDetails(String name) {
        Champion champ = champions.get(name);
        if (champ == null) {
            return "No champion found with name: " + name;
        } else {
            return champ.toString();
        }
    }

    /** returns whether champion is in reserve
    * @param name champion's name
    * @return true if champion in reserve, false otherwise
    */
    @Override
    public boolean isInReserve(String name) {
        Champion champ = champions.get(name);
        return (champ != null && champ.getState().equals(ChampionState.WAITING));
    }

    // ***************** Viziers Team ************************   

    /** Allows a champion to be entered for the vizier's team, if there 
     * is enough money in the Treasury for the entry fee.The champion's 
     * state is set to "entered"
     * 0 if champion is entered in the vizier's team, 
     * 1 if champion is not in reserve, 
     * 2 if not enough money in the treasury, 
     * -1 if there is no such champion 
     * @param name represents the name of the champion
     * @return as shown above
     **/        
    @Override
    public int enterChampion(String name) {
        Champion champ = champions.get(name);
        if (champ == null) {
            return -1; // No such champion
        } else if (!isInReserve(name)) {
            return 1; // Champion not in reserve
        } else if (champ.getEntryFee() > treasury) {
            return 2; // Not enough money in the treasury
        } else {
            champ.setState(ChampionState.ENTERED);
            treasury -= champ.getEntryFee();
            return 0; // Champion entered in the vizier's team
        }
    }

    /** Returns true if the champion with the name is in 
     * the vizier's team, false otherwise.
     * @param name is the name of the champion
     * @return returns true if the champion with the name
     * is in the vizier's team, false otherwise.
     **/
    @Override
    public boolean isInViziersTeam(String name) {
        Champion champ = champions.get(name);
        return (champ != null && champ.getState().equals(ChampionState.ENTERED));
    }

    /** Removes a champion from the team back to the reserves (if they are in the team)
     * Pre-condition: isChampion()
     * 0 - if champion is retired to reserves
     * 1 - if champion not retired because disqualified
     * 2 - if champion not retired because not in team
     * -1 - if no such champion
     * @param name is the name of the champion
     * @return as shown above 
     **/
    @Override
    public int retireChampion(String name) {
        Champion champ = champions.get(name);
        if (champ == null) {
            return -1; // No such champion
        } else if (!isInViziersTeam(name)) {
            return 2; // Champion not retired because not in team
        } else {
            champ.setState(ChampionState.WAITING);
            return 0; // Champion retired to reserves
        }
    }

    /**Returns a String representation of the champions in the vizier's team
     * or the message "No champions entered"
     * @return a String representation of the champions in the vizier's team
     **/
    @Override
    public String getTeam() {
        StringBuilder sb = new StringBuilder();
        for (Champion champ : champions.values()) {
            if (champ.getState().equals(ChampionState.ENTERED)) {
                sb.append(champ.getName()).append(": £").append(champ.getEntryFee()).append("\n");
            }
        }
        if (sb.length() == 0) {
            return "No champions entered";
        }
        return sb.toString();
    }

    /**Returns a String representation of the disqualified champions in the vizier's team
     * or the message "No disqualified champions "
     * @return a String representation of the disqualified champions in the vizier's team
     **/
    @Override
    public String getDisqualified() {
        StringBuilder sb = new StringBuilder();
        for (Champion champ : champions.values()) {
            if (champ.getState().equals(ChampionState.DISQUALIFIED)) {
                sb.append(champ.getName()).append(": £").append(champ.getEntryFee()).append("\n");
            }
        }
        if (sb.length() == 0) {
            return "No disqualified champions";
        }
        return sb.toString();
    }

    //**********************Challenges************************* 

    /** returns true if the number represents a challenge
     * @param num is the number of the challenge
     * @return true if the number represents a challenge
     **/
    @Override
    public boolean isChallenge(int num) {
        return challenges.containsKey(num);
    }    

    /** Provides a String representation of a challenge given by 
     * the challenge number
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    @Override
    public String getChallenge(int num) {
        Challenge chal = challenges.get(num);
        if (chal == null) {
            return "No such challenge";
        } else {
            return "Challenge " + num + ": Requires skill level " + chal.getSkillLevel() + " Reward: £" + chal.getReward();
        }
    }

    /** Provides a String representation of all challenges 
     * @return returns a String representation of all challenges
     **/
    @Override
    public String getAllChallenges() {
        StringBuilder sb = new StringBuilder();
        for (Challenge chal : challenges.values()) {
            sb.append("Challenge ").append(chal.getNumber()).append(": Requires skill level ").append(chal.getSkillLevel()).append(" Reward: £").append(chal.getReward()).append("\n");
        }
        return sb.toString();
    }

    /** Retrieves the challenge represented by the challenge 
     * number. Finds a champion from the team who can meet the 
     * challenge. The results of meeting a challenge will be 
     * one of the following:  
     * 0 - challenge won by champion, add reward to the treasury, 
     * 1 - challenge lost on skills  - deduct reward from
     * treasury and record champion as "disqualified"
     * 2 - challenge lost as no suitable champion is  available, deduct
     * the reward from treasury 
     * 3 - If a challenge is lost and vizier completely defeated (no money and 
     * no champions to withdraw) 
     * -1 - no such challenge 
     * @param chalNo is the number of the challenge
     * @return an int showing the result(as above) of fighting the challenge
     */ 
    @Override
    public int meetChallenge(int chalNo) {
        if (!isChallenge(chalNo)) {
            return -1; // No such challenge
        }
        Champion suitableChamp = findChampionForChallenge(chalNo);
        if (suitableChamp == null) {
            return 2; // Challenge lost as no suitable champion is available
        }
        if (suitableChamp.getSkillLevel() < challenges.get(chalNo).getSkillLevel()) {
            suitableChamp.setState(ChampionState.DISQUALIFIED);
            treasury -= challenges.get(chalNo).getReward();
            if (treasury <= 0 && champions.isEmpty()) {
                return 3; // Vizier completely defeated
            }
            return 1; // Challenge lost on skills
        }
        treasury += challenges.get(chalNo).getReward();
        return 0; // Challenge won by champion, add reward to the treasury
    }

    //****************** Private methods for Tournament functionality *******************

    private void setupChampions() {
        champions.put("Champ1", new Champion("Champ1", 50, 500, ChampionState.ENTERED));
        champions.put("Champ2", new Champion("Champ2",20, 300, ChampionState.WAITING));
        champions.put("Champ3", new Champion("Champ3", ChampionState.WAITING, 370));
        champions.put("Champ4", new Champion("Champ4", ChampionState.WAITING, 80));
        champions.put("Champ5", new Champion("Champ5", ChampionState.WAITING, 90));
    }

    private void setupChallenges() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("challengesAM.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                ChallengeType type = ChallengeType.valueOf(parts[0].toUpperCase());
                String championName = parts[1];
                int skillLevel = Integer.parseInt(parts[2]);
                int reward = Integer.parseInt(parts[3]);
                challenges.put(challenges.size() + 1, new Challenge(type, championName, skillLevel, reward));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Champion findChampionForChallenge(int chalNo) {
        System.out.println("Finding a champion for challenge: " + chalNo);
        Challenge challenge = challenges.get(chalNo);
        if (challenge == null) {
            System.out.println("Challenge not found.");
            return null;
        }
        
        int requiredSkillLevel = challenge.getSkillLevel();
        System.out.println("Required skill level: " + requiredSkillLevel);
        
        for (Champion champ : champions.values()) {
            if (champ.getState().equals(ChampionState.ENTERED)) {
                System.out.println("Champion found: " + champ.getName() + ", Skill level: " + champ.getSkillLevel());
                if (champ.getSkillLevel() >= requiredSkillLevel) {
                    System.out.println("Suitable champion found: " + champ.getName());
                    return champ;
                }
            }
        }
        System.out.println("No suitable champion found.");
        return null;
    }


    //*******************************************************************************

    /************************ Task 3.5 ************************************************/  
    
    // ***************   file write/read  *********************
    /**
     * Reads challenges from a comma-separated textfile and stores them in the game
     * @param filename of the comma-separated textfile storing information about challenges
     */
    @Override
    public void readChallenges(String filename) { 
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int num = Integer.parseInt(parts[0]);
                int skillLevel = Integer.parseInt(parts[1]);
                int reward = Integer.parseInt(parts[2]);
                challenges.put(num, new Challenge(num, skillLevel, reward));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
    
    /** Reads all information about the game from the specified file 
     * and returns a CARE reference to a Tournament object, or null
     * @param fname name of file storing the game
     * @return the game (as a Tournament object)
     */
    public Tournament loadGame(String fname) {   // uses object serialisation 
        try {
            FileInputStream fileIn = new FileInputStream(fname);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Tournament game = (Tournament) in.readObject();
            in.close();
            fileIn.close();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    } 
    
    /**
     * Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fname);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
