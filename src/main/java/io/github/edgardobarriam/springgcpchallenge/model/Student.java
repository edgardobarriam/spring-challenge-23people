package io.github.edgardobarriam.springgcpchallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;
  @Column(name = "rut")
  private String rut;
  @Column(name = "name")
  private String name;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "age")
  private int age;
  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;
  
  public Student() {
  }
  
  public Student(String rut, String name, String lastName, int age) {
    this.rut = rut;
    this.name = name;
    this.lastName = lastName;
    this.age = age;
  }
  
  public int getId() {
    return id;
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
  
  public int getAge() {
    return age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public Course getCourse() {
    return course;
  }
  
  public void setCourse(Course course) {
    this.course = course;
  }
  
  @Override
  public String toString() {
    return "Student{" +
      "id=" + id +
      ", rut='" + rut + '\'' +
      ", name='" + name + '\'' +
      ", lastName='" + lastName + '\'' +
      ", age=" + age +
      '}';
  }
}
