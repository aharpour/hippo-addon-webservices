/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onehippo.forge.webservices.testing.jcr;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.nodetype.NodeType;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.commons.testing.jcr.MockNodeIterator;
import org.apache.sling.commons.testing.jcr.MockNodeType;

/**
 * Simple implementation supporthing child nodes.
 * Created by jreijn on 14/04/14.
 */
public class MockNode extends org.apache.sling.commons.testing.jcr.MockNode {

    private List<MockNode> childNodes = new ArrayList<MockNode>();
    private List<MockNodeType> mixins = new ArrayList<MockNodeType>();

    private MockNode parent;

    public MockNode(final String path) {
        super(path);
    }

    public MockNode(final String path, final String type) {
        super(path, type);
    }

    @Override
    public Node getNode(final String relPath) {
        for(MockNode node : childNodes) {
            final String childNodeName = node.getPath().substring(node.getPath().lastIndexOf('/') + 1);
            if(childNodeName.equals(relPath)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean hasNodes() {
        return !childNodes.isEmpty();
    }

    @Override
    public NodeIterator getNodes() {
        return new MockNodeIterator(childNodes.toArray(new Node[childNodes.size()]));
    }

    @Override
    public Node addNode(final String relPath, final String primaryNodeTypeName) {
        final MockNode mockNode = new MockNode(relPath, primaryNodeTypeName);
        mockNode.setParent(this);
        childNodes.add(mockNode);
        return mockNode;
    }

    @Override
    public void addMixin(final String mixinName) {
        mixins.add(new MockNodeType(mixinName));
    }

    @Override
    public boolean canAddMixin(final String mixinName) {
        return true;
    }

    @Override
    public NodeType[] getMixinNodeTypes() {
        return mixins.toArray(new NodeType [mixins.size()]);
    }

    public MockNode getParent() {
        return parent;
    }

    public void setParent(final MockNode parent) {
        this.parent = parent;
    }

}
