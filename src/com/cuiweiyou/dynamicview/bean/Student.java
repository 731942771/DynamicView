package com.cuiweiyou.dynamicview.bean;

/**
 * 人员Bean
 * @author Administrator
 */
public class Student {

	private String name;
	private Integer age;
	private Integer sex;

	public Student() {
		super();
	}

	/**
	 * 人员bean
	 * @param name 姓名
	 * @param age 年龄
	 * @param sex 性别，0女/1男
	 */
	public Student(String name, Integer age, Integer sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

}
