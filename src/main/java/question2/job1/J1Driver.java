package question2.job1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import question2.Q2Driver;

public class J1Driver
{
	public static final Path inputDir = Q2Driver.inputDir;
	public static final Path outputDir = Q2Driver.tempDir;
	
	public Job getJob() throws IOException
	{
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);
		job.setJobName("Question 2, Job 1 of 3");
		
		job.setJarByClass(J1Driver.class);
		FileInputFormat.addInputPath(job, inputDir);
		FileOutputFormat.setOutputPath(job, outputDir);
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(J1Mapper.class);
		job.setMapOutputKeyClass(VIntWritable.class);
		job.setMapOutputValueClass(VIntWritable.class);
		
		job.setReducerClass(J1Reducer.class);
		// TODO output key
		// TODO output value
		
		return job;
	}
	
}
