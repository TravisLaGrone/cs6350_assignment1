package question1;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class Q1Partitioner extends Partitioner<Q1IntermediateKey, NullWritable>
{

	@Override
	public int getPartition(Q1IntermediateKey key, NullWritable value, int numPartitions)
	{
		return (key.getUser1() + key.getUser2()) % numPartitions;
	}

}
