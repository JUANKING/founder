package com.foundernest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
	private String criteria;
	private String value;
	private String explanation;
	private String label;
	private String labelImportance;
	private String importance;
}
