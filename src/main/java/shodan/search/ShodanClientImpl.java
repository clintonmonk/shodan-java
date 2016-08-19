package shodan.search;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import shodan.exception.ShodanException;
import shodan.model.Exploit;
import shodan.model.Query;
import shodan.model.SearchResult;

public class ShodanClientImpl implements ShodanClient {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	protected String exploitBaseUrl = "https://exploits.shodan.io/api/search";
	protected String apiKey;
	protected Client client;
	protected ObjectMapper mapper;
	
	@Inject
	public ShodanClientImpl(Client client, ObjectMapper mapper) {
		this.client = client;
	}

	@Override
	public SearchResult<Exploit> searchExploits(Query query) throws ShodanException {
		Response response = buildGetRequest(exploitBaseUrl, query).invoke();
		checkResponse(response);
		return response.readEntity(new GenericType<SearchResult<Exploit>>() {});
	}
	
	protected void checkResponse(Response response) throws ShodanException {
		if(response.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL) {
			String message = response.readEntity(String.class);
			log.error("Error: " + message);
			throw new ShodanException("Error: " + message);
		}
	}
	
	protected Invocation buildGetRequest(String url, Query query) {
		WebTarget target = client.target(url);
		
		if(query.getQueryString() != null) {
			String queryString = query.getQueryString().trim();
			if(!queryString.isEmpty()) {
				target = target.queryParam("query", queryString);
			}
		}
		if(query.getFacets() != null) {
			String facets = Joiner.on(",").join(query.getFacets());
			if(!facets.isEmpty()) {
				target = target.queryParam("facets", facets);
			}
		}
		if(query.getPage() != null) {
			target = target.queryParam("page", query.getPage());
		}
		target = target.queryParam("key", apiKey);
		
		return target.request()
				.accept(MediaType.APPLICATION_JSON)
				.buildGet();
	}
	
	@Override
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	@Override
	public String getApiKey() {
		return apiKey;
	}
	
	public void setExploitBaseUrl(String exploitBaseUrl) {
		this.exploitBaseUrl = exploitBaseUrl;
	}
	
	public String getExploitBaseUrl() {
		return exploitBaseUrl;
	}
}
