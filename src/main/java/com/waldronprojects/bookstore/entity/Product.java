package com.waldronprojects.bookstore.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unit_price")
	private BigDecimal unitPrice;
	
	@Column(name="description")
	private String description;

	@Column(name="units_in_stock")
	private int unitsInStock;

	@ManyToMany
	@JoinTable(name = "product_product_types",
			   joinColumns = @JoinColumn(name = "product_id"),
			   inverseJoinColumns = @JoinColumn(name = "product_type_id"))
	private Collection<ProductType> productTypeCollection;

	@ManyToMany
	@JoinTable(name = "product_genres",
				joinColumns = @JoinColumn(name = "product_id"),
				inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Collection<Genre> genreCollection;

	@OneToMany(mappedBy = "product",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private Collection<Image> imageCollection;
	
	// Products will have many OrderDetails so create a list to store those OrderDetails
	/*@OneToMany(mappedBy="product",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<OrderDetail> orderDetails;*/
	
	public Product() {
	}

	public Product(String productName, BigDecimal unitPrice, String description, int unitsInStock,
				   Collection<ProductType> productTypeCollection, Collection<Genre> genreCollection,
				   Collection<Image> imageCollection) {
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.description = description;
		this.unitsInStock = unitsInStock;
		this.productTypeCollection = productTypeCollection;
		this.genreCollection = genreCollection;
		this.imageCollection = imageCollection;
	}

	public Product(Long id, String productName, BigDecimal unitPrice, String description, int unitsInStock,
				   Collection<ProductType> productTypeCollection, Collection<Genre> genreCollection,
				   Collection<Image> imageCollection) {
		this.id = id;
		this.productName = productName;
		this.unitPrice = unitPrice;
		this.description = description;
		this.unitsInStock = unitsInStock;
		this.productTypeCollection = productTypeCollection;
		this.genreCollection = genreCollection;
		this.imageCollection = imageCollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
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

	public Collection<ProductType> getProductTypeCollection() {
		return productTypeCollection;
	}

	public void setProductTypeCollection(Collection<ProductType> productTypeCollection) {
		this.productTypeCollection = productTypeCollection;
	}

	public Collection<Genre> getGenreCollection() {
		return genreCollection;
	}

	public void setGenreCollection(Collection<Genre> genreCollection) {
		this.genreCollection = genreCollection;
	}

	public Collection<Image> getImageCollection() {
		return imageCollection;
	}

	public void setImageCollection(Collection<Image> imageCollection) {
		this.imageCollection = imageCollection;
	}

	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Product [id=null, productName=").append(productName)
					 .append(", unitPrice=").append(unitPrice)
					 .append(", description=").append(description)
					 .append(", unitsInStock=").append(unitsInStock)
					 .append(", productTypeCollection=").append(productTypeCollection.toString())
					 .append(", genreCollection=").append(genreCollection.toString())
					 .append(", imageCollection=").append(imageCollection.toString())
					 .append("]");
		return stringBuilder.toString();
	}
}
