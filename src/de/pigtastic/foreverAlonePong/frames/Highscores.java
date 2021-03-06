package de.pigtastic.foreverAlonePong.frames;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.pigtastic.foreverAlonePong.highscore.Highscore;
import de.pigtastic.foreverAlonePong.highscore.HighscoreReader;

/**
 * Displays the highscores list on this system after the game.
 */

@SuppressWarnings("serial")
public class Highscores extends DefaultJPanel implements ActionListener {

	// Panel and Layout
	private JPanel panel;
	private CardLayout cardLayout;

	// Components
	private JLabel headline = new JLabel();
	private JButton backbtn;

	// Liste zur Highscoreanzeige
	private List<Highscore> scores = new ArrayList<Highscore>();
	private DefaultListModel<String> scoreModel = new DefaultListModel<String>();
	private JList<String> scoreList = new JList<String>(scoreModel);

	/**
	 * Konstruktor des Highscores JPanel.
	 * 
	 * @param panel
	 * @param cardLayout
	 * @param playedHighscore not used
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public Highscores(JPanel panel, CardLayout cardLayout) {
		this.cardLayout = cardLayout;
		this.panel = panel;

		// Panel Settings
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);

		// LayoutSettings
		GridBagLayout gbl = new GridBagLayout();
		setLayout(gbl);

		// Panel Components

		// Headline
		headline = new JLabel("HIGHSCORES");
		headline.setForeground(Color.WHITE);
		headline.setBackground(Color.BLUE);
		headline.setFont(new Font("Helvetica", Font.PLAIN, 50));
		headline.setHorizontalAlignment(SwingConstants.CENTER);

		// Backbutton
		backbtn = new JButton("Go Back to Start");
		backbtn.setBackground(Color.BLACK);
		backbtn.setForeground(Color.WHITE);
		backbtn.setFont(new Font("Helvetica", Font.PLAIN, 20));

		// add Scores to JList
		try {
			scores = HighscoreReader.importScores();
		} catch (NumberFormatException | IOException e) {
			System.out.println("Fehlender Score, wird beim nächsten Spiel erstellt.");
		}
		scores.forEach(a -> {
			scoreModel.addElement(a.toString());
		});
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) scoreList.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		scoreList.setBackground(Color.BLACK);
		scoreList.setForeground(Color.WHITE);
		scoreList.setFont(new Font("Helvetica", Font.PLAIN, 30));

		// add Component
		addComponent(gbl, headline, 0, 1, 3, 1, 0.0, 0.0, new Insets(25, 20, 15, 20));
		addComponent(gbl, scoreList, 0, 2, 3, 2, 0.0, 0.5);
		addComponent(gbl, backbtn, 0, 4, 3, 1, 0.0, 0.0, new Insets(10, 20, 20, 20));

		// add Listeners
		backbtn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(backbtn)) {
			cardLayout.show(panel, "StartMenu");
			MainFrame.setActivePane("StartMenu");
			Main.main.validate();
		}
	}

	/**
	 * Fügt den Score zur Highscoreliste hinzu, wenn dieser in der Top10 ist.
	 * 
	 * @param score
	 */
	public void addNewScore(Highscore score) {
		scores.add(score);
		Collections.sort(scores);
		if (scores.size() > 10) {
			scores = scores.subList(0, 10);
		}
		scoreModel.removeAllElements();
		scores.forEach(a -> {
			scoreModel.addElement(a.toString());
		});

	}

	// Getter and Setter

	/**
	 * Return a list of highscores.
	 * 
	 * @return scores
	 */
	public List<Highscore> getScores() {
		return scores;
	}

	/**
	 * Set a List as Highscores.
	 * 
	 * @param scores
	 */
	public void setScores(List<Highscore> scores) {
		this.scores = scores;
	}

}