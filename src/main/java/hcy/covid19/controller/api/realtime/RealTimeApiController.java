package hcy.covid19.controller.api.realtime;

import hcy.covid19.controller.api.vaccination.VaccinationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/realTime")
@Slf4j
public class RealTimeApiController {

    @GetMapping
    public ResponseEntity<Map<String, RealTimeResponse>> getRealTimeInfo() throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("32hmVfmyuzAN/Keh22jiPFEVvGM/v4Ghvm07M0ZFAx0KyNwGs2QjybzOZZd8lyrrx+g4PBVr9B0cQEBvk9mqyw==", StandardCharsets.UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("startCreate_dt",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20210821", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("endCreateDt",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20210821", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader bufferedReader;

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        log.info("Result : {}", result.toString());

        bufferedReader.close();
        connection.disconnect();

        Map<String, RealTimeResponse> map = new HashMap<>();

        try {
            String html = result.toString();

            JAXBContext jaxbContext = JAXBContext.newInstance(RealTimeResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            RealTimeResponse realTimeResponse = (RealTimeResponse) unmarshaller.unmarshal(new StringReader(html));

            map.put("realTimeInfo", realTimeResponse);

            return new ResponseEntity<>(map, HttpStatus.OK);

        } catch (JAXBException e) {

            log.error("error 발생.. : ", e);

            map.put("vaccination", new RealTimeResponse());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
