package hcy.covid19.domain;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "body")
public class Vaccination {

    @XmlElement(name = "dataTime")
    private String dataTime;

    @XmlElement(name = "items")
    private Items items;

    @Getter
    @ToString
    @XmlRootElement(name = "items")
    public static class Items {

        @XmlElement(name = "item")
        private List<Item> items;

    }

    public Vaccination() {
    }
}
