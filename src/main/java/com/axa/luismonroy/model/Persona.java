package com.axa.luismonroy.model;

public class Persona {
	private String name;
	private int age;
	private String phoneNumber;
	private String address;

	public Persona() {
	}

	public Persona(String name, int age, String phoneNumber, String address) {
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("{\"name\":\"%s\",\"age\":%s,\"phoneNumber\":\"%s\",\"address\":\"%s\"}",
                name, age, phoneNumber, address);
	}
}
