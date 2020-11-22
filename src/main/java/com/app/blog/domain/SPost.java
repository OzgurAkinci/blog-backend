package com.app.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "s_post")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Audited
public class SPost extends Auditable<String>{
	@Id
	@SequenceGenerator(name = "s_post_seq", sequenceName = "s_post_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "s_post_seq", strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "post_title")
	private String postTitle;

	@Column(name = "post_detail")
	private String postDetail;
}
