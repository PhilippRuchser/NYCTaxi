package bda;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NYCTaxiMapper
  extends Mapper<LongWritable, Text, Text, DoubleWritable> {
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String line = value.toString();
    String pickup = parseCSV(line, 15);
    String trip = pickup + ",";
  
    // pure fare, no tolls or other surcharges
    double fareAmount = Double.parseDouble(parseCSV(line, 11));	
    
    context.write(new Text(trip), new DoubleWritable(fareAmount));
    
  }
  
  public String parseCSV(String line, int pos){
	  List<String> items = Arrays.asList(line.split("\\s*,\\s*"));
	  return items.get(pos);
  }
}
