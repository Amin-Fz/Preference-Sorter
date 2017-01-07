import java.io.*;
import java.util.*;
import javax.swing.*;

public class Preference {
	
	final static String COMMAND_LIST = "Move. - Re-sort an entry by number\n"
			+ "Remove. - Delete an entry by number\n"
			+ "Print. - Print the order of list entries (is not necessarily the final list format)\n"
			+ "List. - Add the members of another list to this list\n"
			+ "Re-sort. - Create a new list with all the same members."
			+ "Quit. - Exit the program and don't change the list."
			;
	
	public static void main(String[] args) throws IOException
	{
	    //File list = new File("C:/Users/Amin/Documents/Anime_List_Years.txt");
		Scanner scan = new Scanner(System.in);
		String next;
		
		File list = General.getFile();
		
		BufferedReader r = General.getReader(list);
		
		System.out.println("Numbered list? Y/N");
		boolean t = General.confirm(scan);
		
		
		List<String> order = General.listToArray(r,t);
		r.close();
		
		System.out.println("Entry " + (order.size() +1)  + "? Type \"Commands.\" for more info.");
		next = scan.nextLine();
		while (!next.equals("Done."))
		{
			if (next.equals("Move.")) {
				System.out.println("Choose the number to be moved");
				move(order, scan);
				
			}
			else if (next.equals("Remove.")) {
				System.out.println("Choose the number to be removed");
				remove(order, scan);
				
			}
			else if (next.equals("Print.")){
				for (int i = 0; i < order.size();i++)
					System.out.printf("%d. %s\n", i+1,order.get(i));
				
			}
			else if (next.equals("List.")) {
				File additions = General.getFile();
				addList(additions,order,scan);
				
			}
			else if (next.equals("Re-sort."))
				order = reSort(order, scan);
			else if (next.equals("Quit."))
				return;
			else if (next.equals("Commands."))
				System.out.println(COMMAND_LIST);
			else {
				System.out.println("Entry " + (order.size() +1)  + ": " + next);
				Preference q = new Preference(next, order.size());
				q.sort(order,scan);
			}
			System.out.println("What next?");
			next = scan.nextLine();
			
		}
		
		System.out.println("Input complete!");
				
		
		if (t)
			order = General.numberList(order);
			
	    General.writeTo(list, order);

		
		
		scan.close();
	}
	
	private int upper;
	private int lower;
	private String name;
	
	public Preference(String title, int length) {
		upper = 0;
		lower = length;
		this.name = new String(title);
	}
	

	public static void addList(File list, List<String> order, Scanner scan) throws IOException
	{
		
		BufferedReader r = General.getReader(list);
		
		String next = r.readLine();
		
		int i = 0;
		while (next != null) {
			Preference q = new Preference(next, order.size());
			if (q.sort(order,scan))
				next = r.readLine();
			
			if (++i % 10 == 0) {
				System.out.println("Continue? Y/N");
				if (!General.confirm(scan))
					return;
			}
			
		}
		
	}
	
	public static void move(List<String> order, Scanner input) throws IndexOutOfBoundsException
	{ 
		String title = remove(order,input);
		if (title != null) {
			Preference q = new Preference(title, order.size());
			
			while (!q.sort(order, input)) 
			{
				System.out.println("Try again? If not, the entry will be deleted. Y/N");
				
				if (!General.confirm(input))	
					return;
				
				q = new Preference(title, order.size());
				
			}

		}
		
	}
	
	public static String remove(List<String> order, Scanner input) throws IndexOutOfBoundsException, NumberFormatException
	{ 
		boolean found = false;
		String goner = null;
		
		while (!found) {
			
			int next = 0;
			
			while (next == 0) {
				
				String pref = input.nextLine();
				
				if (pref.equals("Stop."))
					return null;
				
				try {
					next = Integer.parseInt(pref);
				} catch (NumberFormatException n) {
					next = 0;
					System.out.println("That's not a number!");
				}
			}
			
			try {
				goner = order.get(next-1);
				System.out.println("You selected " + goner + "? Y/N");
				
				if (!General.confirm(input)) {
					System.out.println("Try again?");
					continue;
				}
				
				found = true;
				
				order.remove(next-1);
			} catch (IndexOutOfBoundsException e){
				System.out.println("There is no member #" + next + "\nTry again.");
				continue;
			}
			
			
		}
		
		return goner;
		
	}
	
	public static List<String> reSort(List<String> changed, Scanner scan) throws IOException
	{
		Collections.shuffle(changed);
		
		System.out.println("Choose a place to store the new order");
		
		File storage = General.getFile();
		
		General.writeTo(storage, changed);
		
		
		BufferedReader r = General.getReader(storage); 
		
		List<String> order = new ArrayList<String>();
		
		Preference.addList(storage, order, scan);
		
		System.out.println("Input complete!");
		r.close();
		
		return order;
	}
	
	public boolean sort(List<String> order, Scanner input){
		for(;;){
			if (this.lower == this.upper) {
				order.add(this.upper, this.name);
				return true;
			}
			else if (this.lower - this.upper < 0)
				System.exit(1);
			
			int average = (lower + upper)/2;

			String compare = order.get(average);
			CharSequence score = " - ";
			if (this.name.contains(score))
				System.out.printf("Do you choose: \n A. %s or\n B. %s ?", General.shave(compare),
						General.shave(this.name));
			else
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
				return false;
		}
	}
	
	

}
