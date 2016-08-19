package shodan.model;

import java.util.List;

import com.google.common.collect.Lists;

public class Query {

	private String queryString;
	private List<String> facets;
	private Integer page;

	public List<String> getFacets() {
		return facets;
	}

	public void setFacets(List<String> facets) {
		this.facets = facets;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		
		private Query query = new Query();
		
		public Builder facet(String facet) {
			List<String> facets = query.getFacets();
			if(facets == null) facets = Lists.newArrayList();
			facets.add(facet);
			query.setFacets(facets);
			return this;
		}
		
		public Builder page(Integer page) {
			query.setPage(page);
			return this;
		}
		
		public Builder queryString(String queryString) {
			query.setQueryString(queryString);
			return this;
		}
		
		public Query build() {
			return query;
		}
	}
}
