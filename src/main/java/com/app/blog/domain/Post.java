package com.app.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Audited
public class Post extends Auditable<String>{
	@Id
	@GenericGenerator(name = "gen_post_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {@org.hibernate.annotations.Parameter(name = "hibernate_sequence", value = "post_seq"),
					@org.hibernate.annotations.Parameter(name = "sequence_name", value = "post_seq")}
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_post_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "post_title")
	private String postTitle;

	@Column(name = "post_detail")
	private String postDetail;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "post_category",
			joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
	)
	//@JsonManagedReference
	//@JsonIgnore
	private Set<Category> categories = new HashSet<>();

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", postTitle='" + postTitle + '\'' +
				", categories='" + categories.stream().map(Category::getCategoryName).collect(Collectors.toList()) + '\'' +
				'}';
	}
}
