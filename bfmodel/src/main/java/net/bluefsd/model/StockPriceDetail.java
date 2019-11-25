package net.bluefsd.model;

public class StockPriceDetail {

	private String companyCd;
	private String stockCd;
	private String periodType;
	private String[] dates;
	private double[] values;

	public String getCompanyCd() {
		return companyCd;
	}

	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	public String getStockCd() {
		return stockCd;
	}

	public void setStockCd(String stockCd) {
		this.stockCd = stockCd;
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
