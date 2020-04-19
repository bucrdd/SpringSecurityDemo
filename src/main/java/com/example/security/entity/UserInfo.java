package com.example.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo implements UserDetails {
  public static final long serialVersionId = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotEmpty
  @Column(length = 50)
  private String username;

  @NotEmpty
  private String password;

  @ManyToMany
  @JoinTable(name = "user_info_role",
      joinColumns = {@JoinColumn(name = "user_info_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  private List<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
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
