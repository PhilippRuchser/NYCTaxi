package classic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;


public class SingleProcess {

	public static void main(String[] args) {
		if (args.length != 2) {
	      System.err.println("Usage: SingleProcess <input path> <output path>");
	      System.exit(-1);
		}
	    long startTime = System.currentTimeMillis();

		BufferedReader br;
		String line, pickup;
		double fareAmount;
		StorageType temp, temp2;
		ArrayList<StorageType> list = new ArrayList<StorageType>();
		 
		try {
			br = new BufferedReader(new FileReader(args[0]));
			while ((line = br.readLine()) != null) {
				 pickup = parseCSV(line, 15);
				 fareAmount = Double.parseDouble(parseCSV(line, 11));
				 temp = new StorageType(pickup, fareAmount);
				 
				 if (!list.contains(temp))
				 {
					 // Pickup-Code does not exist yet, add temp to list
					 list.add(temp);
				 } else {
					 // Pickup-Code does exist in list, add fare of temp to existing entry
					 int idx = list.indexOf(temp);
					 temp2 = list.remove(idx);
					 temp.add(temp2);
					 list.add(temp);					 
				 } 
			}
			br.close();		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		// in order to provide a fair comparison, sort list since MR does the same
		Collections.sort(list);
		
		// Write to file
		try {		
			FileWriter writer = new FileWriter(args[1]); 
			for(StorageType st: list) {
				writer.write(st.getPickup() + ", " + st.getFare() + "\n");
			}
			writer.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	    long endTime   = System.currentTimeMillis();
	    long totalTime = endTime - startTime;
	    System.out.println("Runtime: " + totalTime/1000 + " seconds");
	}
	
	public static String parseCSV(String line, int pos){
		  List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
		  return items.get(pos);
	}
}
