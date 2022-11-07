package com.example.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Entity(name = "customer")
public class Customer implements UserDetails {
    @Id
    private String custId;

    @NotNull
    @Size(min = 5, max = 20, message = "Username length is expected between 5 to 20")
    private String username;

    @NotNull
    private String password;

    @JsonFormat(pattern = "DD-MMM-YY")
    @Column
    @NotNull
    private Date dob;

    @Column
    @Email
    private String email;

    @Column
    @NotNull
    @Size(max = 36)
    private String licenseNo;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "DD-MMM-YY")
    private Date licenseExpDt;

    private String role;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Date getLicenseExpDt() {
        return licenseExpDt;
    }

    public void setLicenseExpDt(Date licenseExpDt) {
        this.licenseExpDt = licenseExpDt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", licenseNo='" + licenseNo + '\'' +
                ", licenseExpDt=" + licenseExpDt +
                ", role='" + role + '\'' +
                '}';
    }
}
