
import java.util.*;

public class FireflySolver implements ISolver {
	
	private JobShop jobShop;
	private List<Firefly> fireflies;
	
	public FireflySolver() { }
	
	private void initialize(ISolverInput input) {
		
		int machinesCount = (int) input.getParam("machinesCount");
		int jobsCount = (int) input.getParam("jobsCount");
		int firefliesCount = (int) input.getParam("firefliesCount");
		
		double absorption = (double) input.getParam("absorption");
		double maxRandomStep = (double) input.getParam("maxRandomStep");
		
		jobShop = new JobShop(machinesCount, jobsCount);
		
		fireflies = new ArrayList<>();
		for(int i=0; i<firefliesCount; ++i)
			fireflies.add(new Firefly(jobShop, absorption, maxRandomStep));
	}
	
	public ISolverOutput solve(ISolverInput input) {
		
		initialize(input);
		
		int bestResult = Integer.MAX_VALUE;
		JobOrder bestOrder = null;
		
		int iterations = 20;
		// jakis warunek stopu trzeba
		// albo pobrac to z input
		
		for(int i=0; i<iterations; ++i) {
			
			JobOrder order = performIteration();
			int result = jobShop.getMakespan(order);
			
			if(result < bestResult) {
				bestResult = result;
				bestOrder = order;
			}
		}
		
		ISolverOutput out = new SolverOutput();
		
		out.setOrder(bestOrder.toList());
		out.setMakespan(bestResult);
		out.setIterations(iterations);
		
		return out;
	}
	
	private JobOrder performIteration() {
		
		// czy tu wolno posortowac ???
		
		for(Firefly currentFirefly : fireflies) {
			
			boolean currentMoved = false;
			
			for(Firefly otherFirefly : fireflies) {
				
				if(currentFirefly == otherFirefly) continue;
				
				if(currentFirefly.isLessAttractiveThan(otherFirefly)) {
					currentMoved = true;
					currentFirefly.moveToOther(otherFirefly);
				}
				
			}
			
			if(!currentMoved) {
				// local search here
				currentFirefly.moveRandomly();
			}
		}
		
		int bestTime = Integer.MAX_VALUE;
		JobOrder bestOrder = null;
		
		for (Firefly f : fireflies) {
			int currentTime = jobShop.getMakespan(f.getJobOrder());
			if(currentTime < bestTime) {
				bestTime = currentTime;
				bestOrder = f.getJobOrder();
			}
		}
		
		return bestOrder;
	}
}
