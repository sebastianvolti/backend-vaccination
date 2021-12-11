package uy.edu.fing.tse.core.reservas.dto;

import java.util.HashMap;
import java.util.Map;

public class DateHistorgramDTO {

	Map<String, Long> histogram;
	public DateHistorgramDTO() {
		this.histogram = new HashMap<>();
	}

	public DateHistorgramDTO(Map<String, Long> histogram) {
		this.histogram = histogram;
	}

	public Map<String, Long> getHistogram() {
		return this.histogram;
	}

	public void setHistogram(Map<String, Long> histogram) {
		this.histogram = histogram;
	}

}
