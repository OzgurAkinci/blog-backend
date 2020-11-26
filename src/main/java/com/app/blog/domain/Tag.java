package com.app.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Audited
public class Tag extends Auditable<String>{
	@Id
	@GenericGenerator(name = "gen_category_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {@org.hibernate.annotations.Parameter(name = "hibernate_sequence", value = "tag_seq"),
					@org.hibernate.annotations.Parameter(name = "sequence_name", value = "tag_seq")}
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_tag_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "tag_name")
	private String tagName;
}
