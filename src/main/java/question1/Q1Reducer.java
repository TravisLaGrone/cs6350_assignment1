package question1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1Reducer extends Reducer<Q1IntermediateKey, VIntWritable, Text, Text>
{
	private List<VIntWritable> mutualFriends = new ArrayList<>();
	private Text outKey = new Text();
	private Text outValue = new Text();
	
	@Override
	protected void reduce(Q1IntermediateKey key, Iterable<VIntWritable> values, Context context)
			throws IOException, InterruptedException
	{
		mutualFriends.clear();
		
		// Reduce.
		Iterator<VIntWritable> friends = values.iterator();
		VIntWritable prev = friends.next();
		while (friends.hasNext()) {
			VIntWritable next = friends.next();
			if (prev.equals(next)) {
				mutualFriends.add(next);
			}
			prev = next;
		}
		
		// Prepare output.
		String strKey = new StringBuilder()
				.append(key.getUser1())
				.append(',')
				.append(key.getUser2())
				.toString();
		outKey.set(strKey);

		String strValue = mutualFriends.stream()
				.map(mF -> mF.toString())
				.collect(Collectors.joining(","));
		outValue.set(strValue);
		
		context.write(outKey, outValue);
	}

}