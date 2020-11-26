package com.app.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Audited
public class Category extends Auditable<String>{
	@Id
	@GenericGenerator(name = "gen_category_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {@org.hibernate.annotations.Parameter(name = "hibernate_sequence", value = "category_seq"),
					@org.hibernate.annotations.Parameter(name = "sequence_name", value = "category_seq")}
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_category_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "parent_category_id")
	private Integer parentCategoryId;
}
