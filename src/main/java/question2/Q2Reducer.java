package question2;

import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Q2Reducer extends Reducer<UserPair, User, Text, Text>
{
	private PriorityQueue<MutualFriendsEntry> topTenQueue;  // min heap

	@Override
	protected void setup(Context context) throws IOException, InterruptedException
	{
		topTenQueue = new PriorityQueue<>();
	}

	@Override
	protected void reduce(UserPair userPair, Iterable<User> values, Context context)
			throws IOException, InterruptedException
	{
		List<User> mutualFriends = StreamSupport.stream(values.spliterator(), false).sorted().collect(Collectors.toList());
		MutualFriendsEntry mutualFriendsEntry = new MutualFriendsEntry(userPair, mutualFriends);
		
		topTenQueue.add(mutualFriendsEntry);
		while (topTenQueue.size() > 10) {
			topTenQueue.poll();  // removes least element
		}
	}
	
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException
	{
		List<MutualFriendsEntry> topTenList = topTenQueue.stream()
				.sorted(MutualFriendsEntry.comparator.reversed())  // most friends first
				.collect(Collectors.toList());
		
		Text outKey = new Text();
		Text outValue = new Text();
		
		for (MutualFriendsEntry mutualFriendsEntry : topTenList) {
			
			outKey.set(
					String.join(","
					, Integer.toString(mutualFriendsEntry.userPair.getUser1())
					, Integer.toString(mutualFriendsEntry.userPair.getUser2()) )
			);
			
			outValue.set(
					mutualFriendsEntry.mutualFriends.stream()
					.map(u -> Integer.toString(u.get()))
					.collect(Collectors.joining(","))
			);
			
			context.write(outKey, outValue);
		}
	}
	
	private static class MutualFriendsEntry implements Comparable<MutualFriendsEntry>
	{
		// least friends first
		public static final ComparatorChain<MutualFriendsEntry> comparator;
		
		public final UserPair userPair;
		public final List<User> mutualFriends;
		
		static {
			comparator = new ComparatorChain<>();
			comparator.addComparator((a,b) -> Integer.compare(a.mutualFriends.size(), b.mutualFriends.size()));
			comparator.addComparator((a,b) -> a.userPair.compareTo(b.userPair), true);
		}
		
		public MutualFriendsEntry(UserPair userPair, List<User> mutualFriends)
		{
			this.userPair = userPair;
			this.mutualFriends = mutualFriends;
		}

		@Override
		public int compareTo(MutualFriendsEntry other)
		{
			return comparator.compare(this, other);
		}
		
	}

}
