package shodan.search;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

	private ObjectMapper mapper;

	@Override
	public ObjectMapper getContext(Class<?> type) {
		if (mapper == null) {
			mapper = get();
		}
		return mapper;
	}

	public ObjectMapper get() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		return mapper;
	}
}
