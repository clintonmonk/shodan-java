# shodan-java
Shodan API Java wrapper. Built with Jersey 2 and Jackson.

https://www.shodan.io/

# Setup
Clone the repository and build using Maven.

```xml
<dependency>
	<groupId>com.github.clintonmonk</groupId>
	<artifactId>shodan-java</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

# Getting Started
The main entry point is the ShodanClient.

```java
# get impl dependencies
ObjectMapper mapper = new ObjectMapperProvider().get();
Client jerseyClient = new JerseyClientProvider().get();

# get shodan client
ShodanClient shodan = new ShodanClientImpl(jerseyClient, mapper);
shodan.setApiKey("KEY GOES HERE");

# search exploits
Query query = Query.builder()
		.queryString("test")
		.facet("author")
		.build();
SearchResult<Exploit> result = shodan.searchExploits(query);

# use results
for (Exploit e : result.getMatches()) {
	# do stuff
}
for (SearchFacetCount count : result.getFacet("author")) {
    # do stuff
}
```

