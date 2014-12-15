package bda;
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class NYCTaxiReducer
  extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
  
  @Override
  public void reduce(Text key, Iterable<DoubleWritable> values,
      Context context)
      throws IOException, InterruptedException {	  
	  // Sum up the number trips and the dollarPerHour-ratio for one connection
	  double aggregatedFare = 0.0;
	  
	  for (DoubleWritable v : values){
		  aggregatedFare += v.get();
	  }
	  context.write(key, new DoubleWritable(aggregatedFare));
  }
}
