package question1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Q1Driver
{
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		Configuration conf = new Configuration();
		conf.set("mapreduce.input.keyvaluelinerecordreader.key.value.separator", "\t");
		conf.set("mapreduce.output.textoutputformat.separator", "\t");
		
		Job job = Job.getInstance(conf);
		job.setJobName("Question 1");
		job.setJarByClass(Q1Driver.class);
		
		Path inputDir = new Path(args[0]);
		Path outputDir = new Path(args[1]);
		
		FileInputFormat.addInputPath(job, inputDir);
		FileOutputFormat.setOutputPath(job, outputDir);
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(Q1Mapper.class);
		job.setMapOutputKeyClass(Q1IntermediateKey.class);
		job.setMapOutputValueClass(VIntWritable.class);
		
		job.setPartitionerClass(Q1Partitioner.class);
		job.setSortComparatorClass(Q1SortComparator.class);
		job.setGroupingComparatorClass(Q1GroupingComparator.class);
		
		job.setReducerClass(Q1Reducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
