import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		
		System.out.println("sfsdfd");
		
		ISolverInput input = new SolverInput();
		input.setParam("machinesCount", 8);
		input.setParam("jobsCount", 10);
		input.setParam("firefliesCount", 20);
		input.setParam("absorption", 0.002);
		input.setParam("maxRandomStep", 2.0);
		
		FireflySolver solver = new FireflySolver();
		ISolverOutput solution = solver.solve(input);
		
		System.out.println("order: " + Arrays.toString(solution.getOrder().toArray()));
		System.out.println("makespan: " + solution.getMakespan());
	}

}
