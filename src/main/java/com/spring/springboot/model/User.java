package com.spring.springboot.model;

/**
 * Created by zl on 2015/8/27.
 */
public class User {
	private String id;
    private String name;
    private Integer age;
    private String password;
    private String revision;
    private String area;
    
    public String getArea() {
		return area;
	}
    
    public void setArea(String area) {
		this.area = area;
	}
    
    public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setId(String id) {
		this.id = id;
	}
    
    public String getId() {
		return id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
