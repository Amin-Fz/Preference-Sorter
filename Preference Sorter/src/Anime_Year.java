import java.io.*;

//An app to turn a numbered anime list into a numbered list of anime seen before a specific year.
import java.util.*;

public class Anime_Year {
	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		File list = General.getAFile();
		if (list == null)
			return;
		BufferedReader r = General.getReader(list);
		
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
		
		File daFile = General.getFile(scan);
		if (daFile == null)
			return;
		
		List<String> year = new ArrayList<String>();
		
		
		for (int j = 0; j < order.size(); j++) {
			String element = String.format("%s", order.get(j).name);
			Anime_Year exists = order.get(j);
			if (exists.year <= y) {
				year.add(element);
			}
		}
		
		year = General.numberList(year);
		General.writeTo(daFile, year);
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
