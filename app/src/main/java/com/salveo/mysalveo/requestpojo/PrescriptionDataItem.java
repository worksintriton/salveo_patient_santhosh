package com.salveo.mysalveo.requestpojo;

public class PrescriptionDataItem{
	private String tabletName;
	private String quantity;
	private String consumption;

	public void setTabletName(String tabletName){
		this.tabletName = tabletName;
	}

	public String getTabletName(){
		return tabletName;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setConsumption(String consumption){
		this.consumption = consumption;
	}

	public String getConsumption(){
		return consumption;
	}
}
