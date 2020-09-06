package io.github.edgardobarriam.springgcpchallenge.dto;

import java.io.Serializable;

public class StudentDTO implements Serializable {
  
  private int id;
  private String rut;
  private String name;
  private String lastName;
  private Integer age;
  private CourseDTO course;
  
  public StudentDTO() {
  }
  
  public StudentDTO(int id, String rut, String name, String lastName, int age, CourseDTO course) {
    this.id = id;
    this.rut = rut;
    this.name = name;
    this.lastName = lastName;
    this.age = age;
    this.course = course;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getRut() {
    return rut;
  }
  
  public void setRut(String rut) {
    this.rut = rut;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public Integer getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public CourseDTO getCourse() {
    return course;
  }
  
  public void setCourse(CourseDTO course) {
    this.course = course;
  }
  
  @Override
  public String toString() {
    return "StudentDTO{" +
      "id=" + id +
      ", rut='" + rut + '\'' +
      ", name='" + name + '\'' +
      ", lastName='" + lastName + '\'' +
      ", age=" + age +
      ", course=" + course +
      '}';
  }
}
