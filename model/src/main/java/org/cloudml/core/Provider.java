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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudml.core.validation.CanBeValidated;
import org.cloudml.core.validation.Report;
import org.cloudml.core.visitors.Visitable;
import org.cloudml.core.visitors.Visitor;

public class Provider extends WithProperties implements Visitable, CanBeValidated {
    public static String DEFAULT_CREDENTIALS = "credentials.properties";

    private String login = "";
    private String passwd = "";
    private String credentials;

    public Provider() {
        super();
    }

    public Provider(String name) {
        super(name);
    }

    /**
     *
     * @param name
     * @param credentials defines the path to the property file declaring the
     * credentials (login and passwd properties) according to the Java way of
     * formatting property files See
     * http://docs.oracle.com/javase/tutorial/essential/environment/properties.html
     */
    public Provider(String name, String credentials) {
        this(name);
        this.credentials = credentials;
        initCredentials();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitProvider(this);
    }

    @Override
    public Report validate() {
        final Report report = new Report();
       
        if (credentials == null) {
            final String message = String.format("No credentials for provider '%s'", getName());
            report.addWarning(message);
        } 
        else if (!credentialsExist()) {
            final String message = String.format("Unable to retreive credentials from  '%s' (provider '%s')", credentials, getName());
            report.addWarning(message);
        }
        
        return report;
    }
    
    /*
     * public Provider(String name, String login, String passwd) { this.name =
     * name; this.login = login; this.passwd = passwd; }
     */
    private void initCredentials() {
        FileInputStream in = null;
        try {
            Properties props = new Properties();
            in = new FileInputStream(this.credentials);
            props.load(in);

            login = props.getProperty("login");
            passwd = props.getProperty("passwd");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*
     * public void setLogin(String login) { this.login = login; }
     */
    public String getLogin() {
        return login;
    }

    /*
     * public void setPasswd(String passwd) { this.passwd = passwd; }
     */
    public String getPasswd() {
        return passwd;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
        initCredentials();
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", login: " + login;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Provider) {
            Provider otherProvider = (Provider) other;
            return getName().equals(otherProvider.getName());
        }
        else {
            return false;
        }
    }

    private boolean credentialsExist() {
        return new File(credentials).exists();
    }
}
