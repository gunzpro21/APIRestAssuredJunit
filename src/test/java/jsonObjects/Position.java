package jsonObjects;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
@Setter
/*
 * { "company_id": 3, "work_location_id": 100, "home_location_id": 253,
 * "employment_status_id": 1, "work_basis": 1, "requestor":
 * "truong.phan@modec.com", "remarks": "Remark1.test321", "demobilisation_date":
 * "2024-12-26", "mobilisation_date": "2023-12-26", "project_position":
 * "test-API-19Dec2023-011", "org_chart_id": "chart1test1.API", "budgeted":
 * true, "section_id": 52, "department": "Digital & Analytics", "category":
 * "1 - Project Management", "area": "General", "project_id": 1 }
 */

public class Position {
	@JsonProperty
	private int company_id;
	@JsonProperty
	private int work_location_id;
	@JsonProperty
	private int home_location_id;
	@JsonProperty
	private int employment_status_id;
	@JsonProperty
	private String requestor;
	@JsonProperty
	private String remarks;
	@JsonProperty
	private String demobilisation_date;
	@JsonProperty
	private String mobilisation_date;
	@JsonProperty
	private String project_position;
	@JsonProperty
	private String org_chart_id;
	@JsonProperty
	private Boolean budgeted;
	@JsonProperty
	private int section_id;
	@JsonProperty
	private String department;
	@JsonProperty
	private String category;
	@JsonProperty
	private String area;
	@JsonProperty
	private int project_id;
	@JsonProperty
	private int work_basis;

}
