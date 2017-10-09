package question2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q2Mapper extends Mapper<Text, Text, UserPair, User>
{
	private User mutualFriend = new User();
	private UserPair userPair = new UserPair();

	@Override
	protected void map(Text rawKey, Text rawValue, Context context)
			throws IOException, InterruptedException
	{
		Integer friend = Integer.valueOf(rawKey.toString());
		List<Integer> users = Arrays.stream(rawValue.toString().split(","))
				.map(Integer::valueOf).collect(Collectors.toList());
		
		this.mutualFriend.set(friend);
		
		for (int i = 0; i < users.size(); ++i) {
			Integer user1 = users.get(i);
			
			for (int j = i+1; j < users.size(); ++j) {
				Integer user2 = users.get(j);
				
				userPair.setUsers(user1, user2);
				context.write(this.userPair, this.mutualFriend);
			}
		}
	}

}
