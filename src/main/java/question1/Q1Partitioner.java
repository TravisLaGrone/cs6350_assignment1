package question1;

import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class Q1Partitioner extends Partitioner<Q1IntermediateKey, VIntWritable>
{

	@Override
	public int getPartition(Q1IntermediateKey key, VIntWritable value, int numPartitions)
	{
		return (key.getUser1() + key.getUser2()) % numPartitions;
	}

}
