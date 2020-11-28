package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
