package MiniMar;

import com.fasterxml.jackson.databind.ObjectMapper;
import MiniMar.dto.GetCategoryResponse;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Utils {

    @Test
    void test() throws IOException {
        GetCategoryResponse catResponseGET = new GetCategoryResponse();
        catResponseGET.setId(1);
        catResponseGET.setTitle("myTitle");
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, catResponseGET);
        String result = writer.toString();
        System.out.println(result);
        StringReader reader = new StringReader("{\"id\":1,\"title\":\"myTitle\",\"products\":[]}");
        GetCategoryResponse catResponseGETReader = mapper.readValue(reader, GetCategoryResponse.class);
    }
}
