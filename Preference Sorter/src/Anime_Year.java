import java.io.*;
import java.util.*;

public class Anime_Year {
	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		File animeList = new File("C:/Users/Amin/Documents/Anime_List_Years.txt");
		FileInputStream animu = new FileInputStream(animeList);
		InputStreamReader rank = new InputStreamReader(animu);
		BufferedReader r = new BufferedReader(rank);
		
		List<Anime_Year> order = new ArrayList<Anime_Year>();
		String next = r.readLine();
		int i = 0;
		while (next != null) {
			i++;
			int offput = 3;
			if (i>9)
				offput = 4;
			int size = next.length();
			String title = next.substring(offput, size-7);
			Anime_Year now = new Anime_Year(title, i);
			now.addYear(Integer.parseInt(next.substring(size-5, size-1)));
			order.add(now);
			next = r.readLine();
		}
		r.close();
		
		System.out.println("Year?");
		int y = Integer.parseInt(scan.nextLine());
		
		String thefile = String.format("C:/Users/Amin/Documents/Anime_List_%d.txt", y);
		File dafile = new File(thefile);
		if (!dafile.exists())
			dafile.createNewFile();
		FileOutputStream anime = new FileOutputStream(dafile);
		OutputStreamWriter list = new OutputStreamWriter(anime);
		BufferedWriter w = new BufferedWriter(list);
		int k = 1;
		for (int j = 0; j < order.size(); j++) {
			String element = String.format("%d. %s", k, order.get(j).name);
			Anime_Year exists = order.get(j);
			if (exists.year <= y) {
				w.write(element);
				w.newLine();
				k++;
			}
		}
		w.close();
		scan.close();
	}
	
	private int upper;
	private int lower;
	private String name;
	private int year;
	
	public Anime_Year(String title, int length) {
		upper = 0;
		lower = length;
		this.name = new String(title);
	}
	
	public void addYear(int year){
		this.year = year;
	}
	
	public void sort(List<String> order, Scanner input){
		for(;;){
			if (this.lower == this.upper) {
				order.add(this.upper, this.name);
				return;
			}
			else if (this.lower - this.upper < 0)
				System.exit(1);
			
			int average = (lower + upper)/2;

			String compare = order.get(average);
			System.out.printf("Do you choose: \n A. %s or\n B. %s ?", compare,
					this.name);
			String answer = input.nextLine();
			while (!answer.equals("A") && !answer.equals("B") && 
					!answer.equals("Stop.")) {
				System.out.println("Try again.");
				answer = input.nextLine();
			}
			if (answer.equals("A")) {
				upper = average + 1;
				
			}
			if (answer.equals("B")) {
				lower = average;
			}
			if (answer.equals("Stop."))
				return;
		}
	}

}
