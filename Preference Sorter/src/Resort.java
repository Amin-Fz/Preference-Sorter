import java.io.*;
import java.util.*;
import javax.swing.*;

public class Resort {
	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		File list;
		list = General.getAFile();
		if (list == null)
			return;
		
		BufferedReader r = General.getReader(list);
		
		
		List<String> changed = General.listToArray(r,false);
		Collections.shuffle(changed);
		
		System.out.println("Choose a place to store the new order");
		
		File storage = General.getFile(scan);
		if (storage == null)
			return;
		
		General.writeTo(storage, changed);
		
		
		r = General.getReader(storage); 
		
		List<String> order = new ArrayList<String>();
		
		Preference.addList(storage, order, scan);
		
		System.out.println("Input complete!");
		r.close();
		
		General.writeTo(list, order);
		scan.close();
	}
	
	

}