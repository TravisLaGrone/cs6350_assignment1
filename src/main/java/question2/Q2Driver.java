package question2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class Q2Driver
{
	public static final Path inputDir = new Path("/socNetData/networkdata");
	public static final Path tempDir = Path.mergePaths(inputDir, new Path("/temp"));
	public static final Path outputDir = inputDir;
	public static final Configuration conf = new Configuration();

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
