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
package org.cloudml.facade.mrt.cmd.gen;

import org.cloudml.facade.mrt.cmd.abstracts.Change;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Removed extends Change {
  public Removed() {
  }
  
  public Removed(final String nouse) {
  }
  
  public Removed(final Procedure1<Removed> initializer) {
    initializer.apply(this);
  }
  
  public Object parent;
  
  public String property;
  
  public Object index;
  
  public Object removedValue;
  
  Object parent_repr = null;
  
  String property_repr = null;
  
  Object index_repr = null;
  
  Object removedValue_repr = null;
  
  @Override
  public Change obtainRepr() {
    Removed toRepr = new Removed();
    if(this.parent_repr!=null)
    	toRepr.parent = this.parent_repr;
    else
    	toRepr.parent = this.parent;
    if(this.property_repr!=null)
    	toRepr.property = this.property_repr;
    else
    	toRepr.property = this.property;
    if(this.index_repr!=null)
    	toRepr.index = this.index_repr;
    else
    	toRepr.index = this.index;
    if(this.removedValue_repr!=null)
    	toRepr.removedValue = this.removedValue_repr;
    else
    	toRepr.removedValue = this.removedValue;
    toRepr.fromPeer = this.fromPeer;
    return toRepr;
    
  }
}
