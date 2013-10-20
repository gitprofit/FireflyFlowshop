
import java.util.*;

interface ISolver {
	ISolverOutput solve(ISolverInput input);
}


interface ISolverInput {
	Object getParam(String name);
	void setParam(String name, Object value);
}


interface ISolverOutput {
	
	List<Integer> getOrder();
	int getMakespan();
	int getIterations();
	
	void setOrder(List<Integer> order);
	void setMakespan(int makespan);
	void setIterations(int iterations);
}


class SolverInput implements ISolverInput {
	
	private Map<String, Object> data = new HashMap<>();

	@Override
	public Object getParam(String name) {
		return data.get(name);
	}

	@Override
	public void setParam(String name, Object value) {
		data.put(name, value);
	}
}


class SolverOutput implements ISolverOutput {
	
	List<Integer> order;
	int makespan;
	int iterations;
	
	public List<Integer> getOrder() {
		return order;
	}
	public void setOrder(List<Integer> order) {
		this.order = order;
	}
	public int getMakespan() {
		return makespan;
	}
	public void setMakespan(int makespan) {
		this.makespan = makespan;
	}
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
}