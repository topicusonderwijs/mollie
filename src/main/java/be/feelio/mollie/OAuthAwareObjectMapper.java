package be.feelio.mollie;

import be.feelio.mollie.util.Config;
import be.feelio.mollie.util.ObjectMapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kong.unirest.ObjectMapper;

import java.io.IOException;

public class OAuthAwareObjectMapper implements ObjectMapper {

    @Override
    public <T> T readValue(String value, Class<T> type) {
        try {
            return ObjectMapperService.getInstance().getMapper().readValue(value, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String writeValue(Object value) {
        try {
            JsonNode node = ObjectMapperService.getInstance().getMapper().valueToTree(value);
            if (node.isObject() && Config.getInstance().shouldAddTestMode()) {
                ObjectNode object = (ObjectNode) node;
                object.put("testmode", true);
                return ObjectMapperService.getInstance().getMapper().writeValueAsString(object);
            } else {
                return ObjectMapperService.getInstance().getMapper().writeValueAsString(value);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
