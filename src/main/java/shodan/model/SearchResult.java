package shodan.model;

import java.util.List;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.Lists;
import jersey.repackaged.com.google.common.collect.Maps;

public class SearchResult<T> {

	private List<T> matches = Lists.newArrayList();
	private Map<String, SearchFacet> facets = Maps.newHashMap();
	private long total = 0;

	public List<T> getMatches() {
		return matches;
	}

	public void setMatches(List<T> matches) {
		this.matches = matches;
	}

	public Map<String, SearchFacet> getFacets() {
		return facets;
	}
	
	public SearchFacet getFacet(String type) {
		return facets.get(type);
	}

	public void setFacets(Map<String, SearchFacet> facets) {
		this.facets = facets;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
