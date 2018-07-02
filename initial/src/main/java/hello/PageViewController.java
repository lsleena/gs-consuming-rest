package hello;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pageview")
public class PageViewController {
    private static final Logger log = LoggerFactory.getLogger(PageViewController.class);

    @Autowired
    private RestTemplate restTemplate;

   /* @GetMapping("/pageview")
    public String getPageViewList(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\n" +
                "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL limit 10;\",\n" +
                "  \"streamsProperties\": {\n" +
                "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                "  }\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity<List<Row>> response = restTemplate.exchange("http://localhost:8088/query", HttpMethod.POST, entity, new ParameterizedTypeReference<List<Row>>() {
        });
        //String response = restTemplate.postForObject("http://localhost:8088/query",entity, String.class);

        log.info(response.getBody().get(0).toString());
        return "This is test";

    }*/

    @GetMapping
    public List<ResponseRow> getStreamingResource(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();

        String requestJson = "{\n" +
                "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL limit 10;\",\n" +
                "  \"streamsProperties\": {\n" +
                "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                "  }\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity<Resource> responseEntity = restTemplate.exchange( "http://localhost:8088/query", HttpMethod.POST, entity, Resource.class );

        List<ResponseRow> responseRowList = new ArrayList<>();
        InputStream responseInputStream;
        try {
            responseInputStream = responseEntity.getBody().getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
            String responseLine = null;
            while ((responseLine = reader.readLine()) != null){
                System.out.println("Row response line " +responseLine);
                if(responseLine.trim().length() !=0 ) {
                    RequestRow requestRow = objectMapper.readValue(responseLine, RequestRow.class);
                    if(requestRow.row != null){
                        ResponseRow responseRow = new ResponseRow(requestRow.getRow());
                        responseRowList.add(responseRow);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseRowList;
    }

    @GetMapping("/{userId}")
    public List<ResponseRow> getPageViewsByUser(@PathVariable String userId){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();

        String requestJson = "{\n" +
                "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL where userid='" +userId+ "' limit 10;\",\n" +
                "  \"streamsProperties\": {\n" +
                "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                "  }\n" +
                "}";

        System.out.println("Executing SQL Query");
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity<Resource> responseEntity = restTemplate.exchange( "http://localhost:8088/query", HttpMethod.POST, entity, Resource.class );

        List<ResponseRow> responseRowList = new ArrayList<>();
        InputStream responseInputStream;
        try {
            responseInputStream = responseEntity.getBody().getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
            String responseLine = null;
            while ((responseLine = reader.readLine()) != null){
              //  System.out.println("Row response line " +responseLine);
                if(responseLine.trim().length() !=0 ) {
                    RequestRow requestRow = objectMapper.readValue(responseLine, RequestRow.class);
                    if(requestRow.row != null){
                        ResponseRow responseRow = new ResponseRow(requestRow.getRow());
                        responseRowList.add(responseRow);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseRowList;
    }

    @PostMapping
    public List<ResponseRow> getPageViewByUserAndPageId(@ModelAttribute PostParams postParams){
        System.out.println(" Post params " + postParams);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();

        String requestJson = "{\n" +
                "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL where userid='" +postParams.getUserId()+ "' and PAGEID='" +postParams.getPageId()+ "' limit 10;\",\n" +
                "  \"streamsProperties\": {\n" +
                "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                "  }\n" +
                "}";

        System.out.println("Executing SQL Query "+ requestJson);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
        ResponseEntity<Resource> responseEntity = restTemplate.exchange( "http://localhost:8088/query", HttpMethod.POST, entity, Resource.class );

        List<ResponseRow> responseRowList = new ArrayList<>();
        InputStream responseInputStream;
        try {
            responseInputStream = responseEntity.getBody().getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
            String responseLine = null;
            while ((responseLine = reader.readLine()) != null){
                //  System.out.println("Row response line " +responseLine);
                if(responseLine.trim().length() !=0 ) {
                    RequestRow requestRow = objectMapper.readValue(responseLine, RequestRow.class);
                    if(requestRow.row != null){
                        ResponseRow responseRow = new ResponseRow(requestRow.getRow());
                        responseRowList.add(responseRow);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseRowList;
    }

   /* @GetMapping("/stream")
    public StreamingResponseBody handleRequest () {

            return new StreamingResponseBody() {


                @Override
                public void writeTo(OutputStream out) throws IOException {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    ObjectMapper objectMapper = new ObjectMapper();
                    String requestJson = "{\n" +
                            "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL;\",\n" +
                            "  \"streamsProperties\": {\n" +
                            "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                            "  }\n" +
                            "}";
                    HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
                    ResponseEntity<Resource> responseEntity = restTemplate.exchange("http://localhost:8088/query", HttpMethod.POST, entity, Resource.class);
                    InputStream responseInputStream = responseEntity.getBody().getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out);

                    String responseLine = null;
                    while ((responseLine = reader.readLine()) != null) {
                        //  System.out.println("Row response line " +responseLine);
                        if (responseLine.trim().length() != 0) {
                            RequestRow requestRow = objectMapper.readValue(responseLine, RequestRow.class);
                            if (requestRow.row != null) {
                                ResponseRow responseRow = new ResponseRow(requestRow.getRow());
                                outputStreamWriter.write(responseRow.toString());
                                outputStreamWriter.flush();
                            }
                        }
                    }
                }
            };

    }*/

    @RequestMapping(value = "/stream1")
    public void processRequest(HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(HttpStatus.OK.value());

        String requestJson = "{\n" +
                "  \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL;\",\n" +
                "  \"streamsProperties\": {\n" +
                "    \"ksql.streams.auto.offset.reset\": \"earliest\"\n" +
                "  }\n" +
                "}";

        restTemplate.execute("http://localhost:8088/query",
                HttpMethod.POST,
                new BodySettingRequestCallBack("{ \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL;\", \"streamsProperties\": { \"ksql.streams.auto.offset.reset\": \"earliest\" } }"),
                responseExtractor -> {
                    InputStream responseInputStream = responseExtractor.getBody();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(responseInputStream));
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream());

                    String responseLine = null;
                    while ((responseLine = reader.readLine()) != null) {
                        //  System.out.println("Row response line " +responseLine);
                        if (responseLine.trim().length() != 0) {
                            RequestRow requestRow = objectMapper.readValue(responseLine, RequestRow.class);
                            if (requestRow.row != null) {
                                ResponseRow responseRow = new ResponseRow(requestRow.getRow());
                                outputStreamWriter.write(responseRow.toString());
                                outputStreamWriter.flush();
                            }
                        }
                    }
                    // IOUtils.copy(responseExtractor.getBody(), response.getOutputStream());
                    return null;
                });

    }

}

class BodySettingRequestCallBack implements RequestCallback{
    private String body;
    ObjectMapper mapper = new ObjectMapper();

    public BodySettingRequestCallBack(String body){
        this.body = body;
    }

    @Override
    public void doWithRequest(ClientHttpRequest request) throws IOException {
        byte[] json = getEventBytes();

        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        request.getBody().write("{ \"ksql\": \"SELECT * FROM PAGEVIEWS_ORIGINAL;\", \"streamsProperties\": { \"ksql.streams.auto.offset.reset\": \"earliest\" } }".getBytes());
    }

    byte[] getEventBytes() throws JsonProcessingException {
        return mapper.writeValueAsBytes(body);
    }
}
