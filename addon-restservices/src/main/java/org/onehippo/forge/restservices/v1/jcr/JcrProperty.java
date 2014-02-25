package org.onehippo.forge.restservices.v1.jcr;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wordnik.swagger.annotations.ApiModel;

@ApiModel(value = "A representation of a JCR property")
@XmlRootElement(name = "property")
@XmlType(propOrder = {"name","type","multiple","values"})
public class JcrProperty {

    private String name;
    private String type;
    private boolean multiple;
    private List<String> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @XmlElements(
            @XmlElement(name = "value", type = String.class))
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("JcrProperty");
        sb.append("{name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", multiple=").append(multiple);
        sb.append('}');
        return sb.toString();
    }


}
