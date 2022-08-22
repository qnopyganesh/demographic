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
	String type;
	
	@Column(name = "encode_type")
	String encode_type;
	
	@Column(name = "json")
	String json;
	
	@Column(name = "algo")
	Long algo;
	
	public NameIndex(String name , String encode_type, String type, String json , Long algo){
		this.name = name;
		this.encode_type = encode_type;
		this.type = type;
		this.json = json;
		this.algo = algo;
	}
   public NameIndex(){
	   
   }

	
	

}
