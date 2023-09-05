package com.billing.app.accounts.apiClassModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BSCustomers")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CustomerDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String gender;
	private String age;
	private String address;
	private String phone;
	private String email;
	private String customerCode;
	

}
