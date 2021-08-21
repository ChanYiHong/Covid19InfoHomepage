package hcy.covid19.controller.api.realtime;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "item")
public class RealTimeItem {

    @XmlElement(name = "createDt")
    private String createDt;

    @XmlElement(name = "deathCnt")
    private String deathCnt;

    @XmlElement(name = "defCnt")
    private String defCnt;

    @XmlElement(name = "gubun")
    private String gubun;

    @XmlElement(name = "gubunCn")
    private String gubunCn;

    @XmlElement(name = "gubunEn")
    private String gubunEn;

    @XmlElement(name = "incDec")
    private String incDec;

    @XmlElement(name = "isolClearCnt")
    private String isolClearCnt;

    @XmlElement(name = "isolIngCnt")
    private String isolIngCnt;

    @XmlElement(name = "localOccCnt")
    private String localOccCnt;

    @XmlElement(name = "overFlowCnt")
    private String overFlowCnt;

    @XmlElement(name = "qurRate")
    private String qurRate;

    @XmlElement(name = "seq")
    private String seq;

    @XmlElement(name = "stdDay")
    private String stdDay;

    @XmlElement(name = "updateDt")
    private String updateDt;

}
