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

import org.cloudml.core.ArtefactInstance;
import org.cloudml.core.ArtefactPortInstance;
import org.cloudml.core.ServerPort;
import org.cloudml.core.ServerPortInstance;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ServerPortInstanceTest extends ArtefactPortInstanceTest {

    @Override
    public ArtefactPortInstance getValidInstance() {
        return new ServerPortInstance("spi", new ServerPort("sp", null, true), new ArtefactInstance());
    }
    
    
}
