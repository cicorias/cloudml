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
package org.cloudml.codecs;


import org.cloudml.core.*;
import org.cloudml.core.RequiredPortInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nicolas Ferry on 25.02.14.
 */
public class BridgeToKmf {

    private Map<String, net.cloudml.core.VM> vms = new HashMap<String, net.cloudml.core.VM>();
    private Map<String, net.cloudml.core.Provider> providers = new HashMap<String, net.cloudml.core.Provider>();
    private Map<String, net.cloudml.core.InternalComponent> internalComponents = new HashMap<String, net.cloudml.core.InternalComponent>();
    private Map<String, net.cloudml.core.RequiredPort> requiredPorts = new HashMap<String, net.cloudml.core.RequiredPort>();
    private Map<String, net.cloudml.core.ProvidedPort> providedPorts = new HashMap<String, net.cloudml.core.ProvidedPort>();
    private Map<String, net.cloudml.core.InternalComponentInstance> internalComponentInstances = new HashMap<String, net.cloudml.core.InternalComponentInstance>();
    private Map<String, net.cloudml.core.RequiredPortInstance> requiredPortInstances = new HashMap<String, net.cloudml.core.RequiredPortInstance>();
    private Map<String, net.cloudml.core.ProvidedPortInstance> providedPortInstances = new HashMap<String, net.cloudml.core.ProvidedPortInstance>();
    private Map<String, net.cloudml.core.VMInstance> VMInstances = new HashMap<String, net.cloudml.core.VMInstance>();
    private Map<String, net.cloudml.core.Relationship> relationships = new HashMap<String, net.cloudml.core.Relationship>();
    private Map<String, net.cloudml.core.ProvidedExecutionPlatform> providedExecutionPlatforms = new HashMap<String, net.cloudml.core.ProvidedExecutionPlatform>();
    private Map<String, net.cloudml.core.ProvidedExecutionPlatformInstance> providedExecutionPlatformInstances = new HashMap<String, net.cloudml.core.ProvidedExecutionPlatformInstance>();
    private Map<String, net.cloudml.core.RequiredExecutionPlatformInstance> requiredExecutionPlatformInstances = new HashMap<String, net.cloudml.core.RequiredExecutionPlatformInstance>();

    private net.cloudml.core.CoreFactory factory = new net.cloudml.factory.MainFactory().getCoreFactory();
    private net.cloudml.core.CloudMLModel kDeploy = factory.createCloudMLModel();

    public BridgeToKmf(){}

    public net.cloudml.core.CloudMLModel getKmfModel(){
        return this.kDeploy;
    }

    public net.cloudml.core.CloudMLModel toKMF(CloudMLModel deploy) {
        kDeploy.setName(deploy.getName());

        providersToKmf(deploy.getProviders());

        externalComponentToKmf(deploy.getExternalComponents());
        internalComponentsToKmf(deploy.getInternalComponents());

        externalComponentInstanceToKmf(deploy.getExternalComponentInstances());
        internalComponentInstancesToKmf(deploy.getInternalComponentInstances());

        executesToKmf(deploy.getExecuteInstances());

        relationshipsToKmf(deploy.getRelationships().values());
        relationshipInstancesToKmf(deploy.getRelationshipInstances());
        return kDeploy;
    }


    private void convertProperties(CloudMLElementWithProperties element, net.cloudml.core.CloudMLElementWithProperties kElement, net.cloudml.core.CoreFactory factory) {
        for (Property p : element.getProperties()) {
            net.cloudml.core.Property kp = factory.createProperty();
            kp.setName(p.getName());
            kp.setValue(p.getValue());
            kElement.addProperties(kp);
        }
    }

    private void convertResources(CloudMLElementWithProperties element, net.cloudml.core.CloudMLElementWithProperties kElement, net.cloudml.core.CoreFactory factory){
        for(Resource r:element.getResources()){
            net.cloudml.core.Resource kr = factory.createResource();
            kr.setName(r.getName());
            kr.setInstallCommand(r.getInstallCommand());
            kr.setDownloadCommand(r.getRetrieveCommand());
            kr.setConfigureCommand(r.getConfigureCommand());
            kr.setStartCommand(r.getStartCommand());
            kr.setStopCommand(r.getStopCommand());
            kElement.addResources(kr);

            String kup="";
            for(Map.Entry<String, String> up : r.getUploadCommand().entrySet()){
                kup+=up.getKey()+" "+up.getValue()+";";
            }
            kr.setUploadCommand(kup);
        }
    }

    public void checkNull(Object obj, String message){
        if(obj == null)
            throw new IllegalArgumentException(message);
    }

    public void providersToKmf(List<Provider> providers){
        checkNull(providers, "Cannot iterate on null!");
        for (Provider p : providers) {
            net.cloudml.core.Provider kProvider = factory.createProvider();
            convertProperties(p, kProvider, factory);
            kProvider.setName(p.getName());
            kProvider.setCredentials(p.getCredentials());
            kDeploy.addProviders(kProvider);

            this.providers.put(kProvider.getName(), kProvider);
        }
    }


    public void executesToKmf(List<ExecuteInstance> executeInstances){
        checkNull(executeInstances,"Cannot iterate on null!");
        for(ExecuteInstance ei: executeInstances){
            executeToKmf(ei);
        }
    }

    public void executeToKmf(ExecuteInstance ei){
        checkNull(ei,"Cannot iterate on null!");
        net.cloudml.core.ExecuteInstance kExecute=factory.createExecuteInstance();
        convertProperties(ei,kExecute,factory);
        convertResources(ei,kExecute,factory);

        kExecute.setName(ei.getName());
        kExecute.setProvidedExecutionPlatformInstance(providedExecutionPlatformInstances.get(calculateExecutionPlatformIdentifier(ei.getProvidedExecutionPlatformInstance())));
        kExecute.setRequiredExecutionPlatformInstance(requiredExecutionPlatformInstances.get(calculateExecutionPlatformIdentifier(ei.getRequiredExecutionPlatformInstance())));

        kDeploy.addExecutesInstances(kExecute);
    }

    public void externalComponentToKmf(List<ExternalComponent> externalComponents){
        checkNull(externalComponents,"Cannot iterate on null!");
        for (ExternalComponent ec : externalComponents) {
            if(ec instanceof org.cloudml.core.VM){
                org.cloudml.core.VM vm=(org.cloudml.core.VM)ec;
                net.cloudml.core.VM kNode = factory.createVM();
                convertProperties(vm, kNode, factory);
                convertResources(vm, kNode, factory);
                kNode.setName(vm.getName());

                kNode.setProvider(providers.get(vm.getProvider().getName()));
                kNode.setGroupName(vm.getGroupName());
                kNode.setImageId(vm.getImageId());
                kNode.setIs64os(vm.getIs64os());
                kNode.setLocation(vm.getLocation());
                kNode.setMinCores(vm.getMinCores());
                kNode.setMinStorage(vm.getMinStorage());
                kNode.setMinRam(vm.getMinRam());
                kNode.setOs(vm.getOs());
                kNode.setPrivateKey(vm.getPrivateKey());
                kNode.setSecurityGroup(vm.getSecurityGroup());
                kNode.setSshKey(vm.getSshKey());

                vms.put(kNode.getName(), kNode);
                initProvidedExecutionPlatforms(ec, kNode);

                kDeploy.addComponents(kNode);
            }
        }
    }

    public void internalComponentsToKmf(List<InternalComponent> internalComponents){
        checkNull(internalComponents, "Cannot iterate on null!");
        for (InternalComponent ic : internalComponents) {//first pass on the contained elements
            internalComponentToKmf(ic);
        }
    }

    public void convertAndAddProvidedPorts(List<ProvidedPort> ports, net.cloudml.core.Component kc){
        checkNull(ports, "cannot iterate on null!");
        for (ProvidedPort pp : ports) {
            checkNull(pp, "cannot convert null!");
            net.cloudml.core.ProvidedPort kpp = factory.createProvidedPort();
            kpp.setName(pp.getName());
            convertProperties(pp, kpp, factory);
            kpp.setPortNumber(pp.getPortNumber());
            kpp.setIsLocal(pp.getIsLocal());
            kc.addProvidedPorts(kpp);
            kpp.setComponent(kc);

            providedPorts.put(calculatePortIdentifier(pp), kpp);
        }
        assert providedPorts.size() >= ports.size();
        assert kc.getProvidedPorts().size() == ports.size();
    }

    public void convertAndAddRequiredPorts(List<RequiredPort> ports, net.cloudml.core.InternalComponent kic){
        checkNull(ports, "Cannot iterate on null!");
        for (RequiredPort rp : ports) {
            checkNull(rp, "cannot convert null!");
            net.cloudml.core.RequiredPort krp = factory.createRequiredPort();
            krp.setName(rp.getName());
            convertProperties(rp, krp, factory);
            krp.setPortNumber(rp.getPortNumber());
            krp.setIsLocal(rp.getIsLocal());
            krp.setIsMandatory(rp.getIsMandatory());
            kic.addRequiredPorts(krp);
            krp.setComponent(kic);
            requiredPorts.put(calculatePortIdentifier(rp), krp);
        }
        assert requiredPorts.size() >= ports.size();
        assert kic.getRequiredPorts().size() == ports.size();
    }


    public void internalComponentToKmf(InternalComponent internalComponent){
        checkNull(internalComponent, "Cannot convert null!");
        net.cloudml.core.InternalComponent kic = factory.createInternalComponent();
        kic.setName(internalComponent.getName());
        convertProperties(internalComponent, kic, factory);
        convertResources(internalComponent, kic, factory);
        this.internalComponents.put(kic.getName(), kic);

        initRequiredExecutionPlatform(internalComponent, kic);
        initProvidedExecutionPlatforms(internalComponent, kic);

        convertAndAddProvidedPorts(internalComponent.getProvidedPorts(), kic);
        convertAndAddRequiredPorts(internalComponent.getRequiredPorts(), kic);

        kDeploy.addComponents(kic);
    }

    private void initRequiredExecutionPlatform(InternalComponent ic, net.cloudml.core.InternalComponent ka) {
        if (ic.getRequiredExecutionPlatform() != null) {
            net.cloudml.core.RequiredExecutionPlatform repi=factory.createRequiredExecutionPlatform();
            repi.setName(ic.getRequiredExecutionPlatform().getName());
            repi.setOwner(ka);
            convertProperties(ic.getRequiredExecutionPlatform(), repi, factory);
            convertResources(ic.getRequiredExecutionPlatform(), repi, factory);
            ka.setRequiredExecutionPlatform(repi);
        }
    }

    private void initProvidedExecutionPlatforms(Component c, net.cloudml.core.Component kc){
        List<ProvidedExecutionPlatform> providedExecutionPlatforms= c.getProvidedExecutionPlatforms();
        if(providedExecutionPlatforms != null){
            List<net.cloudml.core.ProvidedExecutionPlatform> temp=new ArrayList<net.cloudml.core.ProvidedExecutionPlatform>();
            for(ProvidedExecutionPlatform pep: providedExecutionPlatforms){
                if(pep != null){
                    net.cloudml.core.ProvidedExecutionPlatform kpep= initProvidedExecutionPlatform(pep, kc);
                    temp.add(kpep);
                    this.providedExecutionPlatforms.put(pep.getName(), kpep);
                }
            }
            kc.setProvidedExecutionPlatforms(temp);
        }
    }

    private net.cloudml.core.ProvidedExecutionPlatform initProvidedExecutionPlatform(ProvidedExecutionPlatform pep, net.cloudml.core.Component kc){
        if(pep != null){
            net.cloudml.core.ProvidedExecutionPlatform pepi=factory.createProvidedExecutionPlatform();
            pepi.setName(pep.getName());
            pepi.setOwner(kc);
            convertProperties(pep, pepi, factory);
            convertResources(pep,pepi,factory);
            return pepi;
        }
        return null;
    }


    public void relationshipsToKmf(Iterable<Relationship> relationships){
        checkNull(relationships, "cannot iterate on null!");
        for (Relationship relationship : relationships) {
            relationshipToKmf(relationship);
        }
    }

    public String calculatePortIdentifier(Port p){
        return String.format("%s_%s", p.getComponent().getName(), p.getName());
    }

    public String calculateExecutionPlatformIdentifier(ExecutionPlatformInstance epi){
        return String.format("%s-%s",epi.getOwner().getName(), epi.getName());
    }

    public void checkValidPort(Port p){
        if(p == null)
            throw new IllegalArgumentException("Port is null! ");
        if(p.getName() == null)
            throw new IllegalArgumentException("Port name is null! "+p.getClass().getName());
        if(p.getComponent() == null)
            throw new IllegalArgumentException("Port has no container! "+p.getClass().getName());
    }


    public void relationshipToKmf(Relationship relationship){
        checkNull(relationship, "cannot convert null");
        net.cloudml.core.Relationship krelationship = factory.createRelationship();

        krelationship.setName(relationship.getName());

        RequiredPort rp=relationship.getRequiredPort();
        checkValidPort(rp);
        krelationship.setRequiredPort(requiredPorts.get(calculatePortIdentifier(rp)));
        assert krelationship.getRequiredPort() != null;

        ProvidedPort pp=relationship.getProvidedPort();
        checkValidPort(pp);
        krelationship.setProvidedPort(providedPorts.get(calculatePortIdentifier(pp)));
        assert krelationship.getProvidedPort() != null;

        if (relationship.getClientResource() != null) {
            net.cloudml.core.Resource cr = factory.createResource();
            cr.setName(relationship.getClientResource().getName());
            if (relationship.getClientResource().getInstallCommand() != null) {
                cr.setInstallCommand(relationship.getClientResource().getInstallCommand());
            }
            if (relationship.getClientResource().getRetrieveCommand() != null) {
                cr.setDownloadCommand(relationship.getClientResource().getRetrieveCommand());
            }
            if (relationship.getClientResource().getConfigureCommand() != null) {
                cr.setConfigureCommand(relationship.getClientResource().getConfigureCommand());
            }
            if (relationship.getClientResource().getStartCommand() != null) {
                cr.setStartCommand(relationship.getClientResource().getStartCommand());
            }
            if (relationship.getClientResource().getStopCommand() != null) {
                cr.setStopCommand(relationship.getClientResource().getStopCommand());
            }
            krelationship.setRequiredPortResource(cr);
        }

        if (relationship.getServerResource() != null) {
            net.cloudml.core.Resource cr = factory.createResource();
            cr.setName(relationship.getServerResource().getName());
            if (relationship.getServerResource().getInstallCommand() != null) {
                cr.setInstallCommand(relationship.getServerResource().getInstallCommand());
            }
            if (relationship.getServerResource().getRetrieveCommand() != null) {
                cr.setDownloadCommand(relationship.getServerResource().getRetrieveCommand());
            }
            if (relationship.getServerResource().getConfigureCommand() != null) {
                cr.setConfigureCommand(relationship.getServerResource().getConfigureCommand());
            }
            if (relationship.getServerResource().getStartCommand() != null) {
                cr.setStartCommand(relationship.getServerResource().getStartCommand());
            }
            if (relationship.getServerResource().getStopCommand() != null) {
                cr.setStopCommand(relationship.getServerResource().getStopCommand());
            }
            krelationship.setProvidedPortResource(cr);
        }


        kDeploy.addRelationships(krelationship);
        this.relationships.put(krelationship.getName(), krelationship);
    }

    public void externalComponentInstanceToKmf(List<ExternalComponentInstance> externalComponentInstances){
        checkNull(externalComponentInstances, "Cannot iterate on null");
        for (ExternalComponentInstance eni : externalComponentInstances) {
            checkNull(eni, "Cannot convert null");
            if(eni instanceof VMInstance){
                VMInstance ni=(VMInstance)eni;
                net.cloudml.core.VMInstance kni = factory.createVMInstance();
                kni.setName(ni.getName());
                kni.setPublicAddress(ni.getPublicAddress());
                kni.setType(vms.get(ni.getType().getName()));
                convertProperties(ni, kni, factory);

                VMInstances.put(kni.getName(), kni);

                initProvidedExecutionPlatformInstances(eni, kni);

                kDeploy.addComponentInstances(kni);
            } else {
                throw new IllegalArgumentException("Unknown subtype of ExternalComponentInstance: " + eni.getClass().getName());
            }
        }
    }

    public void internalComponentInstanceToKmf(InternalComponentInstance ici) {
        checkNull(ici, "Cannot convert null");
        net.cloudml.core.InternalComponentInstance kai = factory.createInternalComponentInstance();
        kai.setName(ici.getName());
        kai.setType(internalComponents.get(ici.getType().getName()));
        convertProperties(ici, kai, factory);

        this.internalComponentInstances.put(kai.getName(), kai);

        initRequiredExecutionPlatformInstance(ici, kai);
        initProvidedExecutionPlatformInstances(ici, kai);

        convertAndAddRequiredPortInstances(ici.getRequiredPortInstances(), kai);
        convertAndAddProvidedPortInstances(ici.getProvidedPortInstances(), kai);

        kDeploy.addComponentInstances(kai);
    }

    private void initProvidedExecutionPlatformInstances(ComponentInstance ci, net.cloudml.core.ComponentInstance kci) {
        List<ProvidedExecutionPlatformInstance> providedExecutionPlatforms= ci.getProvidedExecutionPlatformInstances();
        if (providedExecutionPlatforms != null) {
            List<net.cloudml.core.ProvidedExecutionPlatformInstance> temp=new ArrayList<net.cloudml.core.ProvidedExecutionPlatformInstance>();
            for(ProvidedExecutionPlatformInstance pepi: providedExecutionPlatforms){
                if(pepi != null)
                    temp.add(initProvidedExecutionPlatformInstance(pepi, kci));
            }
            kci.setProvidedExecutionPlatformInstances(temp);
        }
    }

    private net.cloudml.core.ProvidedExecutionPlatformInstance initProvidedExecutionPlatformInstance(ProvidedExecutionPlatformInstance pepi, net.cloudml.core.ComponentInstance kci){
        if(pepi != null){
            net.cloudml.core.ProvidedExecutionPlatformInstance kpepi=factory.createProvidedExecutionPlatformInstance();
            kpepi.setName(pepi.getName());
            kpepi.setOwner(kci);

            assert kci.getType() != null;

            kpepi.setType(this.providedExecutionPlatforms.get(pepi.getType().getName()));
            convertProperties(pepi, kpepi, factory);
            convertResources(pepi, kpepi, factory);
            providedExecutionPlatformInstances.put(calculateExecutionPlatformIdentifier(pepi), kpepi);
            return kpepi;
        }
        return null;
    }

    private void initRequiredExecutionPlatformInstance(InternalComponentInstance ici, net.cloudml.core.InternalComponentInstance kai) {
        if (ici.getRequiredExecutionPlatformInstance() != null) {
            net.cloudml.core.RequiredExecutionPlatformInstance krepi=factory.createRequiredExecutionPlatformInstance();
            krepi.setName(ici.getRequiredExecutionPlatformInstance().getName());
            krepi.setOwner(kai);

            assert kai.getType() != null;

            krepi.setType(((net.cloudml.core.InternalComponent) kai.getType()).getRequiredExecutionPlatform());
            convertProperties(ici.getRequiredExecutionPlatformInstance(), krepi, factory);
            convertResources(ici.getRequiredExecutionPlatformInstance(), krepi, factory);
            kai.setRequiredExecutionPlatformInstance(krepi);
            requiredExecutionPlatformInstances.put(calculateExecutionPlatformIdentifier(ici.getRequiredExecutionPlatformInstance()), krepi);
        }
    }

    private void convertAndAddRequiredPortInstances(List<RequiredPortInstance> requiredPortInstances, net.cloudml.core.InternalComponentInstance kai) {
        checkNull(requiredPortInstances, "Cannot iterate on null");
        for (RequiredPortInstance api : requiredPortInstances) {
            checkNull(api, "Cannot convert null");
            net.cloudml.core.RequiredPortInstance kapi = factory.createRequiredPortInstance();
            kapi.setName(api.getName());
            kapi.setType(requiredPorts.get(calculatePortIdentifier(api.getType())));
            //kapi.setType(requiredPorts.get(kai.getType().getName() + "_" + api.getType().getName()));
            convertProperties(api, kapi, factory);
            kai.addRequiredPortInstances(kapi);
            this.requiredPortInstances.put(escapePortInstanceName(api), kapi);
        }
    }

    private void convertAndAddProvidedPortInstances(List<ProvidedPortInstance> ports, net.cloudml.core.InternalComponentInstance kai) {
        checkNull(ports, "Cannot iterate on null");
        for (ProvidedPortInstance api : ports) {
            checkNull(api, "Cannot convert null");
            net.cloudml.core.ProvidedPortInstance kapi = factory.createProvidedPortInstance();
            kapi.setName(api.getName());
            kapi.setType(providedPorts.get(calculatePortIdentifier(api.getType())));
            // kapi.setType(providedPorts.get(kai.getType().getName() + "_" + api.getType().getName()));
            convertProperties(api, kapi, factory);
            kai.addProvidedPortInstances(kapi);
            this.providedPortInstances.put(escapePortInstanceName(api), kapi);
        }

    }

    private String escapePortInstanceName(PortInstance portInstance) {
        return String.format("%s_%s", portInstance.getOwner().getName(), portInstance.getName());
    }

    public void internalComponentInstancesToKmf(List<InternalComponentInstance> internalComponentInstances){
        checkNull(internalComponentInstances, "Cannot iterate on null");
        for (InternalComponentInstance bai : internalComponentInstances) {//pass 1
            internalComponentInstanceToKmf(bai);
        }
    }

    public void relationshipInstancesToKmf(List<RelationshipInstance> relationshipInstances){
        checkNull(relationshipInstances, "CAnnot iterate on null");
        for (RelationshipInstance b : relationshipInstances) {
            relationshipInstanceToKmf(b);
        }
    }

    public void relationshipInstanceToKmf(RelationshipInstance ri) {
        checkNull(ri, "Cannot convert null");
        net.cloudml.core.RelationshipInstance kb = factory.createRelationshipInstance();
        kb.setName(ri.getName());
        checkValidPortInstance(ri.getRequiredPortInstance());
        kb.setRequiredPortInstance(requiredPortInstances.get(escapePortInstanceName(ri.getRequiredPortInstance())));
        checkValidPortInstance(ri.getProvidedPortInstance());
        kb.setProvidedPortInstance(providedPortInstances.get(escapePortInstanceName(ri.getProvidedPortInstance())));
        kb.setType(relationships.get(ri.getType().getName()));

        kDeploy.addRelationshipInstances(kb);

    }

    private void checkValidPortInstance(PortInstance portInstance) {
        if (portInstance == null) {
            throw new IllegalArgumentException("Port instance is null");
        }
        if(portInstance.getName() == null) {
            throw new IllegalArgumentException("Port instance has no name");
        }
        if (portInstance.getOwner() == null) {
            throw new IllegalArgumentException("Port instance has no owner");
        }
        if (portInstance.getType() == null) {
            throw new IllegalArgumentException("Port instance has no type");
        }
    }

}
