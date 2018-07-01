package com.foundernest.domain;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="startup")
public class Startup {
	@Id
	private String id;
	private String founderId;
	private String name;
	private String href;
	private String html;
	private Integer netScorePositions;
	private String netScore;
	private Integer mustHaveScorePositions;
	private String mustHaveScore;
	private Integer superNiceToHaceScore;
	private Integer niceToHaceScore;
	private Integer informative;
	private Integer na;
	private List<Criteria> criteria;
	private String callToAction;
}
