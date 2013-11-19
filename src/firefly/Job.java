package firefly;

import java.util.*;

class Job {
	
	private List<Integer> processingTimes;
	
	public Job(int[] times) {
		
		processingTimes = new ArrayList<>();
		
		for(int i=0; i<times.length; ++i)
			processingTimes.add(times[i]);
	}
	
	public int getTime(int machineNumber) {
		return processingTimes.get(machineNumber);
	}
}
