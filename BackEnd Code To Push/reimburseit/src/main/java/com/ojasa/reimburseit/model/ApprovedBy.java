package com.ojasa.reimburseit.model;

public class ApprovedBy {
	private int expReqId;
	private int junior;
	private int hr;
	private int pm;
	private int buh;

	public ApprovedBy() {
	}

	public ApprovedBy(int expReqId, int junior, int hr, int pm, int buh) {
		super();
		this.expReqId = expReqId;
		this.junior = junior;
		this.hr = hr;
		this.pm = pm;
		this.buh = buh;
	}

	public int getExpReqId() {
		return expReqId;
	}

	public void setExpReqId(int expReqId) {
		this.expReqId = expReqId;
	}

	public int getJunior() {
		return junior;
	}

	public void setJunior(int junior) {
		this.junior = junior;
	}

	public int getHr() {
		return hr;
	}

	public void setHr(int hr) {
		this.hr = hr;
	}

	public int getPm() {
		return pm;
	}

	public void setPm(int pm) {
		this.pm = pm;
	}

	public int getBuh() {
		return buh;
	}

	public void setBuh(int buh) {
		this.buh = buh;
	}

	@Override
	public String toString() {
		return "ApprovedBy [expReqId=" + expReqId + ", junior=" + junior + ", hr=" + hr + ", pm=" + pm + ", buh=" + buh
				+ "]";
	}

}
