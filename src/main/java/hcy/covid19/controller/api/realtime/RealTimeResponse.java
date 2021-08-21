package hcy.covid19.controller.api.realtime;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "response")
public class RealTimeResponse {

    @XmlElement(name = "body")
    private RealTimeBody realTimeBody;

}
