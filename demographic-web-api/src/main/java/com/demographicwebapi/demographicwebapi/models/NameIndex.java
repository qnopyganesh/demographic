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
@Table(name = "name_index")
public class NameIndex {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "type")
	char type;
	
	@Column(name = "encode")
	String encode;


	@Column(name = "name_json")
	String name_json;
	
	@Column(name = "encode_json")
	String encode_json;
	
	@Column(name = "algo")
	Long algo;

	public NameIndex(String name, char type, String encode, String name_json, String encode_json, Long algo) {
		this.name = name;
		this.type = type;
		this.encode = encode;
		this.name_json = name_json;
		this.encode_json = encode_json;
		this.algo = algo;
	}

	public NameIndex(){}

}
