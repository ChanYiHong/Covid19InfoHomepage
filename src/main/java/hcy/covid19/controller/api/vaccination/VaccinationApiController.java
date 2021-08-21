package hcy.covid19.controller.api.vaccination;

import lombok.RequiredArgsConstructor;
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
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vaccination")
@Slf4j
public class VaccinationApiController {

    @GetMapping
    public ResponseEntity<Map<String, VaccinationResponse>> getVaccinationInfo() throws IOException {

        StringBuilder urlBuilder = new StringBuilder("https://nip.kdca.go.kr/irgd/cov19stats.do?list=all");
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode("32hmVfmyuzAN/Keh22jiPFEVvGM/v4Ghvm07M0ZFAx0KyNwGs2QjybzOZZd8lyrrx+g4PBVr9B0cQEBvk9mqyw==", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8"));

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Content-type", "application/json");

        BufferedReader bufferedReader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        log.info("Result : {}", result);

        bufferedReader.close();
        connection.disconnect();

        Map<String, VaccinationResponse> map = new HashMap<>();

        try {
            String html = result.toString();

            JAXBContext jaxbContext = JAXBContext.newInstance(VaccinationResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            VaccinationResponse vaccination = (VaccinationResponse) unmarshaller.unmarshal(new StringReader(html));

            map.put("vaccinationInfo", vaccination);

            return new ResponseEntity<>(map, HttpStatus.OK);

        } catch (JAXBException e) {

            log.error("error 발생.. : ", e);

            map.put("vaccination", new VaccinationResponse());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

}
