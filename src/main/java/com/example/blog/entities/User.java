package com.example.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
@Id //for p.k
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;

@Column(name="user_name",nullable=false,length=100)//for changing column name
private String name;
private String email;
private String password;

}
