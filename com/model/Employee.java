package com.model;

import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;

public class Employee {
int id;
String ename;
String city;
String dept;
String designation;
Date doj;
Date dob;
double salary;
String address;
byte[] image;


public Employee(int id, String ename, String city, String dept, String designation, Date doj, Date dob, double salary,String address,byte[] image) {
	super();
	this.id = id;
	this.ename = ename;
	this.city = city;
	this.dept = dept;
	this.designation = designation;
	this.doj = doj;
	this.dob = dob;
	this.salary = salary;
	this.address=address;
	this.image=image;
}


public Employee() {
	super();
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEname() {
	return ename;
}
public void setEname(String ename) {
	this.ename = ename;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
public Date getDoj() {
	return doj;
}
public void setDoj(Date doj) {
	this.doj = doj;
}
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}


public byte[] getImage() {
	return image;
}


public void setImage(byte[] bs) {
	this.image = bs;
}


}