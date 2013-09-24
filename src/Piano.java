import java.awt.*;
import java.awt.event.*;
import javax.sound.midi.*;
import javax.swing.*;

public class Piano extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel VirtualPianoPane;
	JButton btnOctaveUp, btnOctaveDown;
	Sequencer player;
	JLabel curNote;
	
	int octave = 0;
	private final JPanel ButtonPanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Piano frame = new Piano();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Piano() {
		setTitle("Kos Virtual Piano");
		setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/Kaustubh/Documents/workspace/Virtual Piano/pics/grand-piano.png"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 466);
		VirtualPianoPane = new JPanel();
		setContentPane(VirtualPianoPane);
		
		VirtualPianoPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 761, 438);
		VirtualPianoPane.add(tabbedPane);
		tabbedPane.addTab("Buttons", null, ButtonPanel, null);
		ButtonPanel.setBackground(new Color(245, 245, 245));
		ButtonPanel.setLayout(null);
		
		JLabel lblYouArePlaying = new JLabel("You are playing:");
		lblYouArePlaying.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblYouArePlaying.setBounds(543, 40, 165, 29);
		ButtonPanel.add(lblYouArePlaying);
		
		JButton btnC_1 = new JButton("C#");
		btnC_1.setBounds(77, 25, 34, 134);
		ButtonPanel.add(btnC_1);
		btnC_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnC_1.setForeground(UIManager.getColor("Button.darkShadow"));
		btnC_1.setBackground(UIManager.getColor("Button.light"));
		btnC_1.addActionListener(new MyButtonListener(btnC_1));
		
		JButton btnD_1 = new JButton("D#");
		btnD_1.setBounds(149, 25, 34, 134);
		ButtonPanel.add(btnD_1);
		btnD_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnD_1.setForeground(UIManager.getColor("Button.darkShadow"));
		btnD_1.setBackground(UIManager.getColor("Button.light"));
		btnD_1.addActionListener(new MyButtonListener(btnD_1));
		
		JButton btnF_1 = new JButton("F#");
		btnF_1.setBounds(294, 25, 34, 134);
		ButtonPanel.add(btnF_1);
		btnF_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnF_1.setForeground(UIManager.getColor("Button.darkShadow"));
		btnF_1.setBackground(UIManager.getColor("Button.light"));
		btnF_1.addActionListener(new MyButtonListener(btnF_1));
		
		JButton btnG_1 = new JButton("G#");
		btnG_1.setBounds(374, 25, 34, 134);
		ButtonPanel.add(btnG_1);
		btnG_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnG_1.setForeground(UIManager.getColor("Button.darkShadow"));
		btnG_1.setBackground(UIManager.getColor("Button.light"));
		btnG_1.addActionListener(new MyButtonListener(btnG_1));
		
		JButton btnB_1 = new JButton("B\u266D");
		btnB_1.setBounds(446, 25, 34, 134);
		ButtonPanel.add(btnB_1);
		btnB_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnB_1.setForeground(UIManager.getColor("Button.darkShadow"));
		btnB_1.setBackground(UIManager.getColor("Button.light"));
		btnB_1.addActionListener(new MyButtonListener(btnB_1));
		
		JButton btnC = new JButton("C");
		btnC.setVerticalAlignment(SwingConstants.BOTTOM);
		btnC.setBounds(24, 25, 75, 256);
		ButtonPanel.add(btnC);
		btnC.addActionListener(new MyButtonListener(btnC));
		
		JButton btnD = new JButton("D");
		btnD.setVerticalAlignment(SwingConstants.BOTTOM);
		btnD.setBounds(95, 25, 75, 256);
		ButtonPanel.add(btnD);
		btnD.addActionListener(new MyButtonListener(btnD));
		
		JButton btnE = new JButton("E");
		btnE.setVerticalAlignment(SwingConstants.BOTTOM);
		btnE.setBounds(167, 25, 75, 256);
		ButtonPanel.add(btnE);
		btnE.addActionListener(new MyButtonListener(btnE));
		
		JButton btnF = new JButton("F");
		btnF.setVerticalAlignment(SwingConstants.BOTTOM);
		btnF.setBounds(238, 25, 75, 256);
		ButtonPanel.add(btnF);
		btnF.addActionListener(new MyButtonListener(btnF));
		
		JButton btnG = new JButton("G");
		btnG.setVerticalAlignment(SwingConstants.BOTTOM);
		btnG.setBounds(311, 25, 78, 256);
		ButtonPanel.add(btnG);
		btnG.addActionListener(new MyButtonListener(btnG));
		
		JButton btnA = new JButton("A");
		btnA.setVerticalAlignment(SwingConstants.BOTTOM);
		btnA.setBounds(384, 25, 75, 256);
		ButtonPanel.add(btnA);
		btnA.addActionListener(new MyButtonListener(btnA));
		
		JButton btnB = new JButton("B");
		btnB.setVerticalAlignment(SwingConstants.BOTTOM);
		btnB.setBounds(456, 25, 75, 256);
		ButtonPanel.add(btnB);
		btnB.addActionListener(new MyButtonListener(btnB));
		
		btnOctaveUp = new JButton("Octave Up");
		btnOctaveUp.setBounds(564, 305, 108, 29);
		btnOctaveUp.addActionListener(new MyOctaveListener(true));
		ButtonPanel.add(btnOctaveUp);
		
		btnOctaveDown = new JButton("Octave Down");
		btnOctaveDown.setBounds(24, 305, 127, 29);
		btnOctaveDown.addActionListener(new MyOctaveListener(false));
		ButtonPanel.add(btnOctaveDown);
		
		curNote = new JLabel("");
		curNote.setFont(new Font("Times New Roman", Font.BOLD, 97));
		curNote.setBounds(559, 25, 175, 218);
		ButtonPanel.add(curNote);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(189, 298, 322, 36);
		
		ButtonPanel.add(scrollBar);
		
		JPanel Keys = new JPanel();
		Keys.setLayout(null);
		Keys.setBackground(Color.WHITE);
		tabbedPane.addTab("Keys", null, Keys, null);
		
		startPlayer();
	}
	
	public void startPlayer() {
		// TODO Auto-generated method stub
		
		try  
		{  
			player = MidiSystem.getSequencer();  
			player.open();
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
		
	}

	public void play(int note)  
    {  
    		try  
    		{  
    			player.stop();
       
    			Sequence seq= new Sequence(Sequence.PPQ, 4);  
    			Track track = seq.createTrack(); 

    			int instrument = 0;
    			track.add(makeEvent(192, 1, instrument, 0, 5));
    			
    			note = note + 12*octave;
    			
    			track.add(makeEvent(144, 1, note, 100, 0));
    			track.add(makeEvent(128, 1, note, 100, 4));
    			
    			player.setSequence(seq);  
    			player.start();  
    		}  
    		catch(Exception e)  
    		{  
    			e.printStackTrace();  
    		}  
    		
    	}

	public MidiEvent makeEvent(int comd, int chan, int note, int vel, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, note, vel);
			event = new MidiEvent(a, tick);
		}
		
		catch (Exception ex) { }
		
		return event;
	}
	
	public class MyButtonListener implements ActionListener {
		
		String key;
		int note;
		
		char[] notesChars = {'C', 'D', 'E', 'F', 'G', 'A', 'B'};
		int[] notesMidiWhite = {60, 62, 64, 65, 67, 69, 71};
		int[] notesMidiBlack = {61, 63, 64, 66, 68, 69, 70};
		
		public MyButtonListener(JButton btn) {
			key = btn.getText();
			if (key.length() == 2) note = getBlack();
			else note = getWhite();
		}
		
        public int getWhite() {
			// TODO Auto-generated method stub
        	for (int i = 0; i < notesChars.length; i++)
        		if (key.charAt(0) == notesChars[i]) return notesMidiWhite[i];
			return 0;
		}
        
        public int getBlack() {
			// TODO Auto-generated method stub
        	for (int i = 0; i < notesChars.length; i++)
        		if (key.charAt(0) == notesChars[i]) return notesMidiBlack[i];
			return 0;
		}

		public void actionPerformed(ActionEvent a) {
           curNote.setText(key);
           play(note);
        }
    } // close inner class
	
public class MyOctaveListener implements ActionListener {
		
		Boolean up;
		public MyOctaveListener(Boolean up) {
			this.up = up;
		}

		public void actionPerformed(ActionEvent a) {
			
			if (up && btnOctaveUp.isVisible()) octave ++;
			else if (btnOctaveDown.isVisible()) octave --;
			
			if (octave == -3) btnOctaveDown.setVisible(false);
			else btnOctaveDown.setVisible(true);
			
			if (octave == 3) btnOctaveUp.setVisible(false);
			else btnOctaveUp.setVisible(true);
        }
    } // close inner class
}