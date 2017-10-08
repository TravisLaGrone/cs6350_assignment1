package question1;

import org.apache.hadoop.mapreduce.Partitioner;

public class Q1Partitioner extends Partitioner<Q1IntermediateKey, Q1IntermediateValue>
{

	@Override
	public int getPartition(Q1IntermediateKey key, Q1IntermediateValue value, int numPartitions)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
