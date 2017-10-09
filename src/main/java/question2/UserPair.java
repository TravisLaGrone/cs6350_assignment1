package question2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 * A writable pair of users.  The constructor ensures the order of users is set internally such that
 * <code>(user1 <= user2)</code>.
 */
public class UserPair implements Writable, WritableComparable<UserPair>
{
	private static final ComparatorChain<UserPair> comparator;
	
	private int user1;
	private int user2;
	
	static {
		comparator = new ComparatorChain<>();
		comparator.addComparator((a,b) -> Integer.compare(a.user1, b.user1));
		comparator.addComparator((a,b) -> Integer.compare(a.user2, b.user2));
	}
	
	public UserPair() { }
	
	@Override
	public int compareTo(UserPair other)
	{
		return comparator.compare(this, other);
	}

	@Override
	public void write(DataOutput out) throws IOException
	{
		out.write(user1);
		out.write(user2);
	}

	@Override
	public void readFields(DataInput in) throws IOException
	{
		user1 = in.readInt();
		user2 = in.readInt();
	}
	
	public void setUsers(int user1, int user2)
	{
		if (user1 <= user2) {
			this.user1 = user1;
			this.user2 = user2;
		}
		else {
			this.user1 = user2;
			this.user2 = user1;
		}
	}
	
	public int getUser1()
	{
		return user1;
	}
	
	public int getUser2()
	{
		return user2;
	}

}
