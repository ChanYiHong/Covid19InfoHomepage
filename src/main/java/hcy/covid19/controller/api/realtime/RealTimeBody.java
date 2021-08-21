package hcy.covid19.controller.api.realtime;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "body")
public class RealTimeBody {

    @XmlElement(name = "items")
    private RealTimeItems realTimeItems;

    @Getter
    @ToString
    @XmlRootElement(name = "items")
    public static class RealTimeItems {

        @XmlElement(name = "item")
        private List<RealTimeItem> realTimeItems;

    }

}
