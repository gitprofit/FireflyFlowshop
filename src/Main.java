import java.util.Arrays;

import firefly.FireflySolver;
import flowshop.*;
import flowshop.util.*;

public class Main {

	public static void main(String[] args) {
		
		flowshop.util.Random.fromRange(1, 10);
		System.out.println("sfsdfd");
		
		TaillardLoader loader = new TaillardLoader();
		loader.loadFile("D:\\tai20_5.txt");

		TestInstance inst = loader.getInstances().get(1);
		
		/*
		for(int i=0; i<inst.numOfMachines; ++i) {
			for(int j=0; j<inst.numOfJobs; ++j)
				System.out.print(inst.processingTimes[j][i] + " ");
			System.out.println("");
		}
		*/

		
		ISolverInput input = new SolverInput();
		input.setTestInstance(inst);
		input.setParam("firefliesCount", 50);
		//input.setParam("absorption", 0.0000002);
		//input.setParam("maxRandomStep", 10.0);
		input.setParam("iterations", 100);
		
		input.setParam("absorption", 0.00002);
		input.setParam("maxRandomStep", 3.0);
		
		FireflySolver solver = new FireflySolver();
		ISolverOutput solution = solver.solve(input);
		
		System.out.println("order: " + Arrays.toString(solution.getOrder().toArray()));
		System.out.println("makespan: " + solution.getMakespan());
	}

}
