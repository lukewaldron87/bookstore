package com.waldronprojects.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="description")
	private String description;

	@Column(name="units_in_stock")
	private int unitsInStock;
	
	// Products will have many OrderDetails so create a list to store those OrderDetails
	/*@OneToMany(mappedBy="product",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<OrderDetail> orderDetails;*/
	
	public Product() {
	}

	public Product(String productName, double unitPrice, String description, int unitsInStock) {
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.description = description;
		this.unitsInStock = unitsInStock;
	}

	public Product(int id, String productName, double unitPrice, String description, int unitsInStock) {
		this.id = id;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.description = description;
		this.unitsInStock = unitsInStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this .description = description;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Product [id=null, productName=").append(productName)
					 .append(", unitPrice=").append(unitPrice)
					 .append(", description=").append(description)
					 .append(", unitsInStock=").append(unitsInStock)
					 .append("]");
		return stringBuilder.toString();
	}
}
