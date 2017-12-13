package by.htp.rent.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Equipment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1011226276252698314L;
	private int id;
	private String title;
	private double price;
	private Date date;
	
	
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Equipment(int id, String title, double price, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date2) {
		this.date = date2;
	}

	@Override
	public String toString() {
		return "id " + this.id + "price " + this.price + "title " + this.title + "date " + this.date;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	

}
