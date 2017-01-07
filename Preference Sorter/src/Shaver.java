import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shaver {
	
	public static void main(String[] args) throws IOException
	{
		File list = General.getAFile();
		BufferedReader r = General.getReader(list);
		
		List<String> order = General.listToArray(r, false);
		List<String> shaved = new ArrayList<String>();
		
		for (int i = 0; i < order.size();i++) {
			shaved.add(order.get(i).substring(order.get(i).indexOf("- ") + 2));	
		}
		
		General.writeTo(list, shaved);
	}

}
