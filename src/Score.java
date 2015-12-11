import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Score {
	private static List<String> line;

	static {
		line = new ArrayList<String>();
	}

	public static void main(String[] args) {
		// String path = "C:/Users/UEFI/Documents/GitHub/Project/ABC.txt";
		Scanner in = null;
		try {
			in = new Scanner(new File("Name.txt"));
			while (in.hasNext()) {
				line.add(in.nextLine());
			}
			in.close();
			// BufferedReader br = new BufferedReader(new FileReader(file));
			// while ((line = br.readLine()) != null) {
		} catch (FileNotFoundException e) {
			createFile(line);
		}

		System.out.println(findName("a"));
		System.out.println(findName("123"));
		System.out.println(findName("b"));
		System.out.println(findName("b33"));
		levelUp("123");
		createFile(line);
	}

	// public static void getNameAndScore(){
	// for(int i = 0;i<line.size();i++){
	// System.out.println(line.get(i)+"\n");
	// }
	// }
	//
	// public static boolean findName(String name){
	// for(int i = 0; i<line.size();i++)
	// if(line.get(i).indexOf(name) > 0) return true;
	// return false;
	// }
	//
	// public static int findIndex(String name){
	// for(int i = 0; i<line.size();i++)
	// if(line.get(i).indexOf(name) > 0) return i;
	// return line.size();
	// }

	public static int findName(String name){
		for(int i = 0; i<line.size();i++)
			if(line.get(i).indexOf(name) >= 0) {
				return getLevel(line.get(i));
			}
		addName(name,1);
		return 1;
	}
	
	public static void addName(String name,int lv){
		line.add(name+":"+lv);
	}

	public static int getLevel(String line) {
		int index = line.indexOf(":");
		return Integer.parseInt(line.substring(index + 1));
	}
	
	public static void levelUp(String name){
		int index=0;
		for(int i = 0; i<line.size();i++)
			if(line.get(i).indexOf(name) >= 0) {
				index=i;
				break;
			}
		int level = getLevel(line.get(index));
		line.remove(index);
		addName(name, level+1);
	}

	public static void createFile(List<String> line) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("Name.txt"));
			String str = "";
			for (String s : line) {
				str += s + "\n";
			}
			str = str.trim();
			out.write(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
