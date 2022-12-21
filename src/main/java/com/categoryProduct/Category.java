package com.categoryProduct;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class Category {
	@Id
	int category_id;
	String name;
	
	@OneToMany(targetEntity = Product.class,cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	List<Product> products;
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category(int category_id, String name, List<Product> products) {
		super();
		this.category_id = category_id;
		this.name = name;
		this.products = products;
	}


	public int getCategory_id() {
		return category_id;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", name=" + name + ", products=" + products + "]";
	}


	

}
