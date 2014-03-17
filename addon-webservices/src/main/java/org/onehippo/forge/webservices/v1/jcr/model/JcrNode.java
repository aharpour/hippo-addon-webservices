package org.onehippo.forge.webservices.v1.jcr.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "A representation of a JCR node")
@XmlRootElement(name = "node")
@XmlType(propOrder = {"name","identifier","path","link","primaryType","mixinTypes","properties","nodes"})
public class JcrNode {

    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String primaryType;
    @ApiModelProperty(required = false)
    private String path;
    @ApiModelProperty(required = false)
    private String identifier;
    private URI link;
    private List<String> mixinTypes = new ArrayList<String>();
    private List<JcrProperty> properties = new ArrayList<JcrProperty>();
    private List<JcrNode> nodes = new ArrayList<JcrNode>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(final String primaryType) {
        this.primaryType = primaryType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    @XmlElementWrapper(name = "properties")
    @XmlElement(name = "property")
    public List<JcrProperty> getProperties() {
        return properties;
    }

    public void setProperties(final List<JcrProperty> properties) {
        this.properties = properties;
    }

    public boolean addNode(JcrNode name) {
        return nodes.add(name);
    }

    @XmlElementWrapper(name = "nodes")
    @XmlElement(name = "node")
    public List<JcrNode> getNodes() {
        return nodes;
    }

    public void setNodes(final List<JcrNode> nodes) {
        this.nodes = nodes;
    }

    public List<String> getMixinTypes() {
        return mixinTypes;
    }

    public void setMixinTypes(final List<String> mixinTypes) {
        this.mixinTypes = mixinTypes;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(final URI link) {
        this.link = link;
    }

}
