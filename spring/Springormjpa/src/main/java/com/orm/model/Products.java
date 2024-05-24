package com.orm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "i185_productss")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prod_id;
	private String prod_name;
	private Double prod_price;

	public Integer getProd_id() {
		return prod_id;
	}

	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public Double getProd_price() {
		return prod_price;
	}

	public void setProd_price(Double prod_price) {
		this.prod_price = prod_price;
	}

}
