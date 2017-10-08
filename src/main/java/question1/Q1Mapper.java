package question1;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q1Mapper extends Mapper<Text, Text, Q1IntermediateKey, NullWritable>
{
	private Q1IntermediateKey intermediateKey = new Q1IntermediateKey();
	private static final NullWritable intermediateValue = NullWritable.get();

	@Override
	protected void map(Text rawKey, Text rawValue, Mapper<Text, Text, Q1IntermediateKey, NullWritable>, Context context)
			throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String key = rawKey.toString();
		String value = rawValue.toString();
		
		Integer user1 = Integer.valueOf(key);
		List<Integer> friends = Arrays.stream(value.split(",")).map(s -> Integer.valueOf(s)).collect(Collectors.toList());
		
		intermediateKey.setUser1(user1);
		for (Integer user2 : friends) {
			
			intermediateKey.setUser2(user2);
			for (Integer friend : friends) {
				
				if (!friend.equals(user2)) {
					intermediateKey.setFriend(friend);
					context.write(intermediateKey, intermediateValue);
				}
			}
		}
	}
	
}