package io.github.edgardobarriam.springgcpchallenge.dto;

import java.io.Serializable;

public class CourseDTO implements Serializable {
  
  private Integer id;
  private String name;
  private String code;
  
  public CourseDTO() {
  }
  
  public CourseDTO(int id, String name, String code) {
    this.id = id;
    this.name = name;
    this.code = code;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
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
    return "CourseDTO{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", code='" + code + '\'' +
      '}';
  }
}
