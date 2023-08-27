package com.gml.client.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Client {

	@Column(length = 20)
	@Id
	@Size(min=2, max=20, message="SharedKey size should be between 2 and 20")
	private String sharedKey;
	@NotBlank(message = "Business ID is required")
	private String businessId;
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String email;
	@Column(length = 20)
	@Positive(message = "Invalid phone number")
	private Long phone;
	@Temporal(TemporalType.DATE)
    private Date creationDate;    
    private boolean active;
    
    
    @PrePersist
    public void prePersist() {
        this.creationDate = new Date();
        this.active = true;
    }
    
	public String getSharedKey() {
		return sharedKey;
	}
	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	} 
	
    
    
}
