package Models;

public class PercentSplit extends Split{
	double percent;
	public PercentSplit(User payingUser, double percent){
		super(payingUser);
		this.setPercent(percent);
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public double getPercent() {
		return this.percent;
	}
}