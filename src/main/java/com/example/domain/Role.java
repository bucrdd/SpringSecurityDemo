package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
@ApiModel("角色")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @ApiModelProperty("id")
  private long id;

  @ApiModelProperty(value = "角色")
  private String role;

  @ApiModelProperty("描述")
  private String description;
}
