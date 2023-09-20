package com.camunda.activetasklist.model;

public class Shards {
	  private int total;
	  public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Shards [total=" + total + ", successful=" + successful + ", skipped=" + skipped + ", failed=" + failed
				+ "]";
	}
	public int getSuccessful() {
		return successful;
	}
	public void setSuccessful(int successful) {
		this.successful = successful;
	}
	public int getSkipped() {
		return skipped;
	}
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
	}
	private int successful;
	  private int skipped;
	  private int failed;
}
