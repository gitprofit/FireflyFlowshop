
public class Firefly {
	
	private JobShop jobShop;
	private JobOrder order;
	private double absorption;
	private double maxRandStep;
	
	public Firefly(JobShop jobShop, double absorption, double maxRandStep) {
		this.jobShop = jobShop;
		this.order = jobShop.createOrder();
		this.absorption = absorption;
		this.maxRandStep = maxRandStep;
		
		order.permute();
	}
	
	public JobOrder getJobOrder() {
		return order;
	}
	
	public double distance(Firefly other) {
		return order.distance(other.order);
	}
	
	public double attractivenessAtSource() {
		return 1.0 / Math.pow(jobShop.getMakespan(order), 2.0);
	}
	
	public double attractivenessToOther(Firefly other) {
		double dist = distance(other);
		return attractivenessAtSource()/(1.0 + absorption*dist*dist);
	}
	
	public boolean isLessAttractiveThan(Firefly other) {
		double currAttr = this.attractivenessAtSource();
		double otherAttr = other.attractivenessToOther(this);
		return currAttr < otherAttr;
	}
	
	public void moveToOther(Firefly other) {
		
		double[][] currentPriorities = order.getPriorities();
		double[][] otherPriorities = other.order.getPriorities();
		
		double currAttr = this.attractivenessAtSource();
		double otherAttr = other.attractivenessToOther(this);
		
		
		int dim = currentPriorities.length;
		
		for(int i=0; i<dim; ++i)
			for(int j=0; j<dim; ++j)
				currentPriorities[i][j] =
						currAttr * currentPriorities[i][j] / otherAttr
						+ (otherAttr - currAttr) * otherPriorities[i][j] / otherAttr
						+ maxRandStep * (Math.random() - 0.5);
		
		order.updateByPriorities(currentPriorities);
	}
	
	public void moveRandomly() {
		
		double[][] currentPriorities = order.getPriorities();
		
		int dim = currentPriorities.length;
		
		for(int i=0; i<dim; ++i)
			for(int j=0; j<dim; ++j)
				currentPriorities[i][j] = maxRandStep * (Math.random() - 0.5);
		
		order.updateByPriorities(currentPriorities);
	}
}
