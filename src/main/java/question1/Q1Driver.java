package question1;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

public class Q1Driver
{
	
	public static void main(String[] args) throws IOException
	{
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "question 1");
		job.setJarByClass(Q1Driver.class);
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setMapperClass(Q1Mapper.class);
		// job.setSortComparator
		job.setReducerClass(Q1Reducer.class);
	}

}
