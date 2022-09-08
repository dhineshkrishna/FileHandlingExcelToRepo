package com.file.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Userexcel {
	@Id
	
	private Long id;
	private String name;
	private String address;
	private boolean value;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public Userexcel() {
		super();
	}
	public Userexcel(String name, String address, boolean value) {
		super();
		this.name = name;
		this.address = address;
		this.value = value;
	}
	@Override
	public String toString() {
		return "Userexcel [id=" + id + ", name=" + name + ", address=" + address + ", value=" + value + "]";
	}
	

}
