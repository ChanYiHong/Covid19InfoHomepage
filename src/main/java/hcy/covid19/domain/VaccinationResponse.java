package hcy.covid19.domain;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "response")
public class VaccinationResponse {

    @XmlElement(name = "body")
    private Vaccination vaccination;

    public VaccinationResponse() {
    }
}
