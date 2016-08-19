package shodan.search;

import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;

public class JerseyClientProvider {
	
	public Client get() {
		return JerseyClientBuilder.newBuilder()
				.register(JacksonFeature.class)
				.register(ObjectMapperProvider.class)
				.build();
	}
}
