import java.io.*;
import java.util.Collections;
import java.util.List;

public class Shuffler {

	
	public static void main(String[] args) throws IOException 
	{
		File list;
		list = General.getFile();
		
		BufferedReader r = General.getReader(list);
		
		
		List<String> changed = General.listToArray(r,false);
		Collections.shuffle(changed);
		
		General.writeTo(list,changed);
	}
}
