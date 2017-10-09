package question2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Q2Driver
{
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);
		job.setJobName("Question 2");
		job.setJarByClass(Q2Driver.class);
		
		Path inputDir = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		
		FileInputFormat.addInputPath(job, inputDir);		
		FileOutputFormat.setOutputPath(job, outputDir);
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(Q2Mapper.class);
		job.setMapOutputKeyClass(UserPair.class);
		job.setMapOutputValueClass(User.class);
		
		job.setReducerClass(Q2Reducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setNumReduceTasks(1);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
