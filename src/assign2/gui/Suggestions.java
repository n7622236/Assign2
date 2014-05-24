package assign2.gui;

import javax.swing.SwingUtilities;

public class Suggestions {

	/**
	 * produces the GUI
	 * 
	 * @param args
	 * @author Chou,Shu-Hung(n7622236)
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new NGramGUI("SearchSuggestion GUI"));
	}
}
