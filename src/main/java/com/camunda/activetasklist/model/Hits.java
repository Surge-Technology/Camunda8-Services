package com.camunda.activetasklist.model;

import java.util.ArrayList;


public class Hits {

	 private String _index;
	 @Override
	public String toString() {
		return "Hits [_index=" + _index + ", _type=" + _type + ", _id=" + _id + ", _score=" + _score + ", _routing="
				+ _routing + ", _source=" + _source + ", total=" + total + ", max_score=" + max_score + ", hits=" + hits
				+ "]";
	}
	private String _type;
	 private String _id;
	 private double _score;
	 public String get_index() {
		return _index;
	}
	public void set_index(String _index) {
		this._index = _index;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public double get_score() {
		return _score;
	}
	public void set_score(double _score) {
		this._score = _score;
	}
	public String get_routing() {
		return _routing;
	}
	public void set_routing(String _routing) {
		this._routing = _routing;
	}
	public Source get_source() {
		return _source;
	}
	public void set_source(Source _source) {
		this._source = _source;
	}
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	public double getMax_score() {
		return max_score;
	}
	public void setMax_score(double max_score) {
		this.max_score = max_score;
	}
	public ArrayList<Hits> getHits() {
		return hits;
	}
	public void setHits(ArrayList<Hits> hits) {
		this.hits = hits;
	}
	private String _routing;
	 private Source _source;
	 
	 private Total total;
	 private double max_score;
	 private ArrayList<Hits> hits;
	
}
