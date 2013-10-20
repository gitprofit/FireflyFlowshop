
import java.util.*;

public class Job {
	
	private List<Integer> processingTimes;
	
	public Job(int machinesConut) {
		
		processingTimes = new ArrayList<>();
		
		for(int i=0; i<machinesConut; ++i)
			processingTimes.add(Random.fromRange(1, 10));
	}
	
	public int getTime(int machineNumber) {
		return processingTimes.get(machineNumber);
	}
}
