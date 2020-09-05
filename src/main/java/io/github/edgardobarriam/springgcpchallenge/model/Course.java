package io.github.edgardobarriam.springgcpchallenge.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "code")
  private String code;

  public Course() {
  }
  
  public Course(String name, String code) {
    this.name = name;
    this.code = code;
  }
  
  public int getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  @Override
  public String toString() {
    return "Course{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", code='" + code + '\'' +
      '}';
  }
}
