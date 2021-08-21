package hcy.covid19.controller.api.vaccination;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "response")
public class VaccinationResponse {

    @XmlElement(name = "body")
    private VaccinationBody vaccination;

    public VaccinationResponse() {
    }
}
