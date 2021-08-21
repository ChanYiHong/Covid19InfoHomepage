package hcy.covid19.controller.api.vaccination;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "body")
public class VaccinationBody {

    @XmlElement(name = "dataTime")
    private String dataTime;

    @XmlElement(name = "items")
    private Items items;

    @Getter
    @ToString
    @XmlRootElement(name = "items")
    public static class Items {

        @XmlElement(name = "item")
        private List<VaccinationItem> vaccinationItems;

    }

    public VaccinationBody() {
    }
}
