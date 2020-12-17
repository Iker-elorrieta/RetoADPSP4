package main;

import java.util.ArrayList;

public class Indice {
	private String lastUpdateDate;
	private ArrayList<aggregated> aggregated;

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public ArrayList<aggregated> getAggregated() {
		return aggregated;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setAggregated(ArrayList<aggregated> aggregated) {
		this.aggregated = aggregated;
	}

	@Override
	public String toString() {
		return "Indice [lastUpdateDate=" + lastUpdateDate + ", aggregated=" + aggregated + "]";
	}

}
