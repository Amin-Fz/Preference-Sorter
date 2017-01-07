
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class General {

	public static File getFile(){
		
     	JFileChooser chooser = new JFileChooser(new File("c:\\Users\\"));
		chooser.setDialogTitle("Open your list");
		chooser.showSaveDialog(null);
		File list = chooser.getSelectedFile();
		return list;
		
	}
	
	public static BufferedReader getReader(File f) throws IOException
	{
		FileInputStream stream = new FileInputStream(f);
		InputStreamReader sr = new InputStreamReader(stream);
		BufferedReader r = new BufferedReader(sr);
		return r;
	}
	
	public static boolean writeTo(File f, List<String> list) throws IOException
	{
		FileOutputStream output = new FileOutputStream(f);
		OutputStreamWriter writer = new OutputStreamWriter(output);
		BufferedWriter w = new BufferedWriter(writer);
		for (int j = 0; j < list.size(); j++) {
			String element = list.get(j);
			w.write(element);
			w.newLine();
		}
		w.close();
		return true;
	}
	
	public static List<String> numberList(List<String> list)
	{
		List<String> numbered = new ArrayList<String>();
		
		for (int i = 0; i < list.size(); i++) {
			numbered.add((i+1) + ". "+ list.get(i));		
		}
		
		return null;
	}
	
	//Boolean value t is whether the list was numbered
	public static List<String> listToArray(BufferedReader r, boolean t) throws IOException
	{
		List<String> order = new ArrayList<String>();
		String next = r.readLine();
		int i = 0;
		while (next != null) {
			i++;
			int offput = 0;
			if (t)
				offput = getOffput(i);
			
			order.add(next.substring(offput));
			next = r.readLine();
		}
		return order;
	}
	
	private static int getOffput(int i) {
		int offput = 3;
		if (i>999)
			offput = 6;
		else if (i>99)
			offput = 5;
		else if (i>9)
			offput = 4;
		
		return offput;
		
	}
	
	public static boolean confirm(Scanner scan) 
	{
		String next = scan.nextLine();
		while(!next.equals("Y") && !next.equals("N")) {
			System.out.println("Invalid input");
			next = scan.nextLine();
		}
		return next.equals("Y");
	}
	
	public static String shave(String s) {
		return s.substring(s.indexOf("- ") + 2);
	}
	
}
