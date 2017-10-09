package question1;

import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Q1SortComparator extends WritableComparator
{
	private static final ComparatorChain<Q1IntermediateKey> comparator;
	
	static {
		comparator = new ComparatorChain<>();
		comparator.addComparator((a,b) -> Integer.compare(a.getUser1(), b.getUser1()));
		comparator.addComparator((a,b) -> Integer.compare(a.getUser2(), b.getUser2()));
		comparator.addComparator((a,b) -> Integer.compare(a.getFriend(), b.getFriend()));
	}
	
	public Q1SortComparator()
	{
		super(Q1IntermediateKey.class, true);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable a, WritableComparable b)
	{
		Q1IntermediateKey key1 = (Q1IntermediateKey) a;
		Q1IntermediateKey key2 = (Q1IntermediateKey) b;
		
		return comparator.compare(key1, key2);
	}
	
}