/**
 * This file is part of CloudML [ http://cloudml.org ]
 *
 * Copyright (C) 2012 - SINTEF ICT
 * Contact: Franck Chauvel <franck.chauvel@sintef.no>
 *
 * Module: root
 *
 * CloudML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * CloudML is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with CloudML. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.cloudml.core;

import java.util.List;
import org.cloudml.core.validation.CanBeValidated;
import org.cloudml.core.validation.Report;
import org.cloudml.core.visitors.Visitable;
import org.cloudml.core.visitors.Visitor;

public class NodeInstance extends WithProperties implements Visitable, CanBeValidated {

    private Node type;
    private String publicAddress = "";
    private String id = "";
    private State status;

    public enum State {

        stopped,
        running,
        error,
    }

    public NodeInstance() {
    }

    public NodeInstance(String name, Node type) {
        super(name);
        this.type = type;
        this.status = State.stopped;
    }

    public NodeInstance(String name, Node type, List<Property> properties) {
        super(name, properties);
        this.type = type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitNodeInstance(this);
    }

    @Override
    public Report validate() {
        Report report = new Report();
        if (type == null) {
            final String message = String.format("Node instance '%s' has no type ('null' found)", getName());
            report.addError(message);
        }
        return report;
    }

    public Node getType() {
        return this.type;
    }

    public void setType(Node type) {
        this.type = type;
    }

    public void setPublicAddress(String publicAddress) {
        this.publicAddress = publicAddress;
    }

    public String getPublicAddress() {
        return this.publicAddress;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public State getStatus() {
        return this.status;
    }

    public void setStatusAsStopped() {
        this.status = State.stopped;
    }

    public void setStatusAsError() {
        this.status = State.error;
    }

    public void setStatusAsRunning() {
        this.status = State.running;
    }

    @Override
    public String toString() {
        return "NodeInstance: " + getName() + " Type:" + type.getName() + "{\n"
                + "minRam:" + type.getMinRam() + "\n"
                + "minCore" + type.getMinCore() + "\n"
                + "minDisk" + type.getMinDisk() + "\n"
                + "OS" + type.getOS() + "\n"
                + "location" + type.getLocation() + "\n"
                + "publicAdress" + getPublicAddress() + "\n"
                + "groupName" + type.getGroupName();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof NodeInstance) {
            NodeInstance otherNode = (NodeInstance) other;
            return getName().equals(otherNode.getName()) && type.equals(otherNode.getType());
        }
        else {
            return false;
        }
    }
}
