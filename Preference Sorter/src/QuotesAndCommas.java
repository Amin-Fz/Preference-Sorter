import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

import javax.swing.JFileChooser;

public class QuotesAndCommas {
	
	public static void main(String[] args) throws IOException
	{
	    //File animeList = new File("C:/Users/Amin/Documents/Anime_List_Years.txt");
		
		
		File list = General.getFile();

		BufferedReader r = General.getReader(list);
		
		List<String> orig = General.listToArray(r,false);
		r.close();
		
		List<String> changed = new ArrayList<String>();
		
		for (int i = 0; i < orig.size(); i++) {
			changed.add("\"" + orig.get(i) + "\""+",");			
		}
		
		General.writeTo(list, changed);
	}

	

}
