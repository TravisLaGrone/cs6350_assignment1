package question2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.ArrayPrimitiveWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class MutualFriendList implements Writable, WritableComparable<MutualFriendList>
{
	private static final int[] EMPTY_ARRAY = new int[] { };
	
	private final ArrayPrimitiveWritable arrayWritable = new ArrayPrimitiveWritable(int[].class);
	
	private int[] mutualFriends;
	
	/**
	 * Initializes the this mutual friends list value to the empty array. 
	 */
	public MutualFriendList()
	{
		this.mutualFriends = EMPTY_ARRAY;  // to avoid accidental NullPointerException
	}
	
	/**
	 * Compares by count of mutual friends.  Assumes the length of the data array represents the count of
	 * mutual friends.
	 */
	@Override
	public int compareTo(MutualFriendList other)
	{
		return Integer.compare(this.mutualFriends.length, other.mutualFriends.length);
	}

	@Override
	public void write(DataOutput out) throws IOException
	{
		arrayWritable.set(this.mutualFriends);
		arrayWritable.write(out);
		
		// to avoid preventing the GC from collecting the array object if its reference is removed from this
		arrayWritable.set(EMPTY_ARRAY);
	}

	@Override
	public void readFields(DataInput in) throws IOException
	{
		arrayWritable.readFields(in);
		this.mutualFriends = (int[]) arrayWritable.get();
		
		// to avoid preventing the GC from collecting the array object if its reference is removed from this
		arrayWritable.set(EMPTY_ARRAY);
	}
	
	/**
	 * Sets the mutual friends list value without making a copy.  Client should not pass <code>null</code>,
	 * although this method does not enforce as such.
	 * @param mutualFriends
	 */
	public void setMutualFriendsShallow(int[] mutualFriends)
	{
		this.mutualFriends = mutualFriends;
	}
	
	/**
	 * Returns the mutual friends list value without making a copy.  Will not return <code>null</code>
	 * unless client had previously set the value to <code>null</code>.
	 * @return
	 */
	public int[] getMutualFriendsShallow()
	{
		return this.mutualFriends;
	}

}
