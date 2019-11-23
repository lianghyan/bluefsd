package net.bluefsd.model;

public class StockPriceDetails {

	public String company_cd;
	public String stock_cd;
	String periodType;
	String[] dates;
	double[] values;

	public String getCompany_cd() {
		return company_cd;
	}

	public void setCompany_cd(String company_cd) {
		this.company_cd = company_cd;
	}

	public String getStock_cd() {
		return stock_cd;
	}

	public void setStock_cd(String stock_cd) {
		this.stock_cd = stock_cd;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String[] getDates() {
		return dates;
	}

	public void setDates(String[] dates) {
		this.dates = dates;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}
}
