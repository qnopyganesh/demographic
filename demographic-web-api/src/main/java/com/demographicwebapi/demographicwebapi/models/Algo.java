package com.demographicwebapi.demographicwebapi.models;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name = "s_algo")
public class Algo {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "name")
	String name;
	
	public Algo(String name ){
		this.name = name;
	}

}
