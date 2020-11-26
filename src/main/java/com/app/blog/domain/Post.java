package com.app.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;

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
}