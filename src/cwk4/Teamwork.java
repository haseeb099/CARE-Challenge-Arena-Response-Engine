package cwk4;

/**
 * Details of your team
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Teamwork {
	private String[] details = new String[12];

	public Teamwork() {
		// in each line replace the contents of the String
		// with the details of your team member
		// Please list the member details alphabetically by surname
		// i.e. the surname of member1 should come alphabetically
		// before the surname of member 2...etc
		details[0] = "CS34";

		details[1] = "Ahmed";
		details[2] = "Afaq";
		details[3] = "21094291";

		details[4] = "Mehdi Hussain";
		details[5] = "Syed";
		details[6] = "21075743";

		details[7] = "Assam Sharif";
		details[8] = "Muhammad";
		details[9] = "21075844";

		details[10] = "Haseeb Rafique";
		details[11] = "Muhammad";
		details[12] = "21083126";
	}

	public String[] getTeamDetails() {
		return details;
	}

	public void displayDetails() {
		for (String temp : details) {
			System.out.println(temp.toString());
		}
	}
}
