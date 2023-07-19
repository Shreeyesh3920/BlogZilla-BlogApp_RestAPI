package com.BlogApp.Entities;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int postid;
@Column(name="Post_Title",length=100)
@NotEmpty(message="Title of an Blog cannot be empty")
private String title;

@Column(name="Post_Content",length=10000)
@NotEmpty(message="Write some content, It cannot be empty")
private String content;
private String imageName;
private Date addedDate;

@ManyToOne
private Category category;
@ManyToOne
private User user;

@OneToMany(mappedBy="post" ,cascade=CascadeType.ALL)
private List<Comment> comments=new LinkedList<>();

}
