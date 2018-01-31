package gedcomlint;

public class GedcomLint {

	public static void main(String args[]) {
		// print command line arguments
        for (String s: args) {
            System.out.println(s);
        }
        // open filename from argument
        String filename = args[0];
        System.out.println(filename);
	}
}
