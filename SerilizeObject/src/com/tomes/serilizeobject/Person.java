package com.tomes.serilizeobject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**把要传递的参数封装到一个对象中，去实现序列化接口
 * @author Tomes
 * 
 */
public class Person implements Serializable {

	int age;
	String name;
	Map student = new HashMap<>();

	public int getAge() {
		return age;
	}

	public Person() {

	}

	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
