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
/*
 */
package test.cloudml.core;

import org.cloudml.core.ArtefactPort;
import org.cloudml.core.ServerPort;
import static org.cloudml.core.builders.Commons.*;


public class ServerPortTest extends ArtefactPortTest {

    @Override
    public ArtefactPort getPortWithoutOwner() {
        return new ServerPort("sp", null, true);
    }

    @Override
    public ArtefactPort getValidPort() {
        return aServerPort().named("ssh").remote().build();
    }

    @Override
    public ArtefactPort getPortWithoutName() {
        return new ServerPort("sp", null, true);
    }
}
