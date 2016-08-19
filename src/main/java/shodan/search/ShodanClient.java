package shodan.search;

import shodan.exception.ShodanException;
import shodan.model.Exploit;
import shodan.model.Query;
import shodan.model.SearchResult;

public interface ShodanClient {

	public SearchResult<Exploit> searchExploits(Query query) throws ShodanException;
	
	public void setApiKey(String apiKey);
	
	public String getApiKey();
}
