package question1;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class Q1IntermediateKey implements Writable
{
	private int user1;
	private int user2;
	private int friend;

	public Q1IntermediateKey() { }
	
	public Q1IntermediateKey(int user1, int user2, int friend)
	{
		this.user1 = user1;
		this.user2 = user2;
		this.friend = friend;
	}

	@Override
	public void write(DataOutput out) throws IOException
	{
		WritableUtils.writeVInt(out, user1);
		WritableUtils.writeVInt(out, user2);
		WritableUtils.writeVInt(out, friend);
	}

	@Override
	public void readFields(DataInput in) throws IOException
	{
		user1 = WritableUtils.readVInt(in);
		user2 = WritableUtils.readVInt(in);
		friend = WritableUtils.readVInt(in); 
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder()
				.append("user1: ").append(user1)
				.append("user2: ").append(user2)
				.append("friend: ").append(friend)
				.toString();
	}

	public int getUser1()
	{
		return user1;
	}

	public void setUser1(int user1)
	{
		this.user1 = user1;
	}

	public int getUser2()
	{
		return user2;
	}

	public void setUser2(int user2)
	{
		this.user2 = user2;
	}

	public int getFriend()
	{
		return friend;
	}

	public void setFriend(int friend)
	{
		this.friend = friend;
	}
	
}
