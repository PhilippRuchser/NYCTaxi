package bda;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NYCTaxi {
  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: NYCTaxi <input path> <output path>");
      System.exit(-1);
    }
    long startTime = System.currentTimeMillis();  
    @SuppressWarnings("deprecation")
	Job job = new Job();
    job.setJarByClass(NYCTaxi.class);
    job.setJobName("NYCTaxi Example");
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    job.setMapperClass(NYCTaxiMapper.class);
    job.setReducerClass(NYCTaxiReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(DoubleWritable.class);
  
    job.waitForCompletion(true);  
    
    long endTime   = System.currentTimeMillis();
    long totalTime = endTime - startTime;
    System.out.println("Runtime: " + totalTime/1000 + " seconds");
  }
}