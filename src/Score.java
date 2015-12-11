import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Score {
	private static List<String> line;
	private static int index = 0;
	static{
		line=new ArrayList<String>();
	}
	public static void main(String[] args) throws IOException{
		String path = "C:/Users/UEFI/Documents/GitHub/Project/ABC.txt";
		
		try {
			Scanner in = new Scanner(new File(path));
		//	BufferedReader br = new BufferedReader(new FileReader(file));
			//while ((line = br.readLine()) != null) {
			while (in.hasNext()) {
				line.add(in.nextLine());
				index++;
			}
			in.close();
			getNameAndScore();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	
	
	public static void getNameAndScore(){
		for(int i = 0;i<line.size();i++){
			System.out.println(line.get(i)+"\n");
		}
	}
	
	public static boolean findName(String name){
		for(int i = 0; i<line.size();i++)
			if(line.get(i).indexOf(name) > 0) return true;
		return false;
	}
	
	public static int findIndex(String name){
		for(int i = 0; i<line.size();i++)
			if(line.get(i).indexOf(name) > 0) return i;
		return line.size();
	}
}
