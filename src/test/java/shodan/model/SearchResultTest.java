package shodan.model;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import shodan.search.ObjectMapperProvider;

public class SearchResultTest {

	private ObjectMapper mapper = new ObjectMapperProvider().get();
	
	@Test
	public void testSearchExploitParsing() {
		SearchResult<Exploit> result;
		try {
			String json = getResource("sample/exploitSearch_01.json");
			result = mapper.readValue(json, new TypeReference<SearchResult<Exploit>>() {});
		} catch(Exception e) {
			Assert.fail("Exception occurred during parsing.");
			return;
		}
		
		Assert.assertNotNull("Result should not be null.", result);
		Assert.assertTrue("Matches not found.", result.getMatches() != null && !result.getMatches().isEmpty());
		Assert.assertTrue("Facets should not exist.", result.getFacets() != null && result.getFacets().isEmpty());
	}
	
	@Test
	public void testSearchExploitWithFacetParsing() {
		SearchResult<Exploit> result;
		try {
			String json = getResource("sample/exploitSearchWithFacet_01.json");
			result = mapper.readValue(json, new TypeReference<SearchResult<Exploit>>() {});
		} catch(Exception e) {
			Assert.fail("Exception occurred during parsing.");
			return;
		}
		
		Assert.assertNotNull("Result should not be null.", result);
		Assert.assertTrue("Matches not found.", result.getMatches() != null && !result.getMatches().isEmpty());
		Assert.assertTrue("Facets not found.", result.getFacets() != null && !result.getFacets().isEmpty());
	}
	
	protected String getResource(String path) throws IOException {
		URL url = Resources.getResource(path);
		return Resources.toString(url, Charsets.UTF_8);
	}
}
