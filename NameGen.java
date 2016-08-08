//NMS Name Gen. V0.1.3
//Code by Ethan MacDonald 8/6/16 . 8/7/16 . 8/7/16
//Uses a list of prefixes, roots, and suffixes to generate random names which are
//displayed in a simple GUI.

package nmsNomenclature;
import java.io.*;
public class NameGen extends JFrame {

	private JPanel contentPane;
	//Loads the parts from their respective .txt files.
	static String[] prefix = encode("prefix.txt");
	static String[] root = encode("root.txt");
	static String[] suffix = encode("suffix.txt");
	static String[] mod = encode("mod.txt");
	static String[] words ={"NMS Name Gen","","Ethan MacDonald","8/7/16","V0.1.3", ""};
	
	public static void main(String[] args) {
		NameGen frame = new NameGen();
		frame.setVisible(true);
	} 

	public NameGen() {
		//Sets up the frame
		setTitle("NMS Name Gen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 312);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Creates the button
		JButton b = new JButton("Regenerate List");
		b.setFont(new Font("Century", Font.PLAIN, 20));
		contentPane.add(b, BorderLayout.SOUTH);
		//What the button does
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Creates the text area
				JTextArea ta = new JTextArea();
				contentPane.add(ta, BorderLayout.CENTER);
				ta.setEditable(false);
				ta.setFont(new Font("Century Gothic", Font.PLAIN, 30));
				ta.setText(WordsFormatter(words));
				
				for(int i=0; i<6; i++){
					//Generates a random index to pick from the array
					int p = randomP();
					int r = randomR();
					int s = randomS();
					int m = randomM();
					//Constructs the word and prints it to the console.
					String raw_word = prefix[p] + root[r] + suffix[s];
					String word = "";
					//Adds a modifier to short words (sometimes). 
					if(raw_word.length()==6){
						//30% chance a modifier is added.
						int chance = random10();
						if(chance<=3){
							word = raw_word + " " + mod[m];
						}
						else{
							word = raw_word;
						}
					}
					else if(raw_word.length()<=5){
						//70% chance a modifier is added.
						int chance = random10();
						if(chance>3){
							word = raw_word + " " + mod[m];
						}
						else{
							word = raw_word;
						}
					}
					else{
						word = raw_word;
					}
					//Adds the final word to the array in each iteration of the loop.
					words[i] = word;
				}
				for(int j=0; j<6; j++){
					System.out.println(words[j]);
				}
				System.out.println();
				//System.out.println("test");
				
			}
		});
		
	}
	//Methods
	//Encodes a .txt file into an array
	public static String[] encode(String file){
		String f = file;
		int step = 0;
		try {
			Scanner s1 = new Scanner(new File(f));
			while(s1.hasNextLine()){
				step++;
				s1.next();
			}
			String[] returnArray = new String[step];
			Scanner s2 = new Scanner(new File(f));
			for(int i =0; i<step; i++){
				returnArray[i] = s2.next();
			}
			return returnArray;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Formats the indexes from the words array in NameGen for display in the ta text area.
	public static String WordsFormatter(String[] words){
		String list = "";
		for(int i=0; i<6; i++){
			list = list + words[i] + "\n";
		}
		return list;
	}
	//Selects a random prefix
	public static int randomP(){
		int rp = new Random().nextInt(prefix.length);
		return rp;
	}
	//Selects a random root
	public static int randomR(){
		int rr = new Random().nextInt(root.length);
		return rr;
	}
	//Selects a random suffix
	public static int randomS(){
		int rs = new Random().nextInt(suffix.length);
		return rs;
	}
	//Selects a random suffix
		public static int randomM(){
			int rm = new Random().nextInt(mod.length);
			return rm;
		}
	//Generates a random int between 1-10 for mod addition chance
	public static int random10(){
		int r10 =  new Random().nextInt(10);
		r10++;
		return r10;
	}
}
	/* Legacy arrays
	 * static String[] prefix = {"","Pro","Pre","Re","Ex","De","Ab","Ad","Iso","Sil","Lex","Lux","Wi","Pi","Eth","Ae","Sol","No","Nu"};
	 * static String[] root = {"","air","ba","sky","azul","mire","il","con","uu","tail","d","gen","rac","ud","ff","rth","bar"};
	 * static String[] suffix = {"","ly","ioux","ii", "ium", "mawr","a","ae","am","arum","is","as","is","ux","mn","us","i","es"};
	 */
