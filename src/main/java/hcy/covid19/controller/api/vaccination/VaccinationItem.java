package hcy.covid19.controller.api.vaccination;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "item")
public class VaccinationItem {

    @XmlElement(name = "tpcd")
    private String tpcd;

    @XmlElement(name = "firstCnt")
    private String firstCnt;

    @XmlElement(name = "secondCnt")
    private String secondCnt;

}
