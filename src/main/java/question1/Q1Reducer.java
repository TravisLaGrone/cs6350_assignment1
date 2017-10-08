package question1;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1Reducer extends Reducer<Q1IntermediateKey, NullWritable, Text, Text>
{
	private Integer user1;
	private Integer user2;
	private List<Integer> mutualFriends; 
	
	@Override
	protected void setup(Reducer<Q1IntermediateKey, NullWritable, Text, Text>.Context context)
			throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected void reduce(Q1IntermediateKey arg0, Iterable<NullWritable> arg1,
			Reducer<Q1IntermediateKey, NullWritable, Text, Text>.Context arg2) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		super.reduce(arg0, arg1, arg2);
	}
	
	@Override
	protected void cleanup(Reducer<Q1IntermediateKey, NullWritable, Text, Text>.Context context)
			throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		super.cleanup(context);
	}

}