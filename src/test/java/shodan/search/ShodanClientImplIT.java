package shodan.search;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.ws.rs.client.Client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import shodan.model.Exploit;
import shodan.model.Query;
import shodan.model.SearchFacet;
import shodan.model.SearchResult;

public class ShodanClientImplIT {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testSearchExploits() throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapperProvider().get();
		Client jerseyClient = new JerseyClientProvider().get();
		ShodanClient shodan = new ShodanClientImpl(jerseyClient, mapper);
		shodan.setApiKey("KEY GOES HERE");
		
		Query query = Query.builder()
				.queryString("test")
				.facet("author")
				.build();

		SearchResult<Exploit> result = shodan.searchExploits(query);
		
		log.info("###### Exploits ######");
		for (Exploit e : result.getMatches()) {
			log.info(mapper.writeValueAsString(e));
		}
		
		log.info("###### Facets ######");
		for (Map.Entry<String, SearchFacet> entry : result.getFacets().entrySet()) {
			log.info("type: " + entry.getKey());
			log.info(mapper.writeValueAsString(entry.getValue()));
		}
		
		log.info("###################");
		log.info("Done!");
	}
	
	protected String getResource(String path) throws IOException {
		URL url = Resources.getResource(path);
		return Resources.toString(url, Charsets.UTF_8);
	}
}
