package question1;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class Q1IntermediateKey implements Writable, WritableComparable<Q1IntermediateKey>
{

	@Override
	public int compareTo(Q1IntermediateKey o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void write(DataOutput out) throws IOException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFields(DataInput in) throws IOException
	{
		// TODO Auto-generated method stub
		
	}

}
