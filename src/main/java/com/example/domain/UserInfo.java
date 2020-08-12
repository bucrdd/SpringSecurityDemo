package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("系统用户")
public class UserInfo implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @ApiModelProperty("用户Id")
  private long id;

  @ApiModelProperty("用户名")
  @NotBlank(message = "用户名不能为空")
  private String username;

  @ApiModelProperty("密码")
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
