/*
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
	var instance;
	
	jsPlumb.ready(function() {

		instance = jsPlumb.getInstance({
			// default drag options
			DragOptions : { cursor: 'pointer', zIndex:2000 },
			// the overlays to decorate each connection with.  note that the label overlay uses a function to generate the label text; in this
			// case it returns the 'labelText' member that we set on each connection in the 'init' method below.
			ConnectionOverlays : [
				[ "Arrow", { location:1 , id:"myarrow"} ],
				[ "Label", { 
					location:0.5,
					id:"label",
					cssClass:"aLabel"
				}]
			],
			Container:"flowchart-demo"
		});		

		// this is the paint style for destination binding
		connectorDestPaintStyle = {
			lineWidth:4,
			strokeStyle:"#61B7CF",
			joinstyle:"round",
			outlineColor:"white",
			outlineWidth:2
		},
		// this is the connecting style for mandatory communication
		connectorPaintStyle = {
			lineWidth:4,
			strokeStyle:"#aa1111",
			joinstyle:"round",
			outlineColor:"white",
			outlineWidth:2
		},
		// this is the connecting style for optional communication
		connectorOptionalPaintStyle = {
			lineWidth:4,
			strokeStyle:"#aa1111",
			joinstyle:"round",
			outlineColor:"white",
			dashstyle:"4 2",
			outlineWidth:2
		},
		// this is the connecting style for optional communication
		connectorLocalPaintStyle = {
			lineWidth:4,
			strokeStyle:"#aa1111",
			outlineColor:"white",
			dashstyle:"4 2",
			outlineWidth:2,
		},
		// .. and this is the hover style. 
		connectorHoverStyle = {
			lineWidth:4,
			strokeStyle:"#216477",
			outlineWidth:2,
			outlineColor:"white"
		},
		endpointHoverStyle = {
			fillStyle:"#216477",
			strokeStyle:"#216477"
		},
		// the definition of source endpoints (the small blue ones)
		sourceOptionalEndpoint = {
			endpoint:"Dot",
			paintStyle:{ 
				strokeStyle:"#7AB02C",
				fillStyle:"transparent",
				radius:7,
				lineWidth:3 
			},				
			isSource:true,
			connector:[ "Flowchart", { stub:[40, 10], gap:5, cornerRadius:5, alwaysRespectStubs:true } ],								                
			connectorStyle:connectorOptionalPaintStyle,
			hoverPaintStyle:endpointHoverStyle,
			connectorHoverStyle:connectorHoverStyle,
            dragOptions:{},
            overlays:[
            	[ "Label", { 
                	location:[0.5, 1.5], 
                	label:"Drag",
                	cssClass:"endpointSourceLabel" 
                }],
            ]
		},
		// the definition of source endpoints (the small blue ones)
		sourceDestEndpoint = {
			endpoint:"Dot",
			paintStyle:{ 
				strokeStyle:"#7AB02C",
				fillStyle:"transparent",
				radius:7,
				lineWidth:3 
			},				
			isSource:true,
			connector:[ "Flowchart", { stub:[40, 10], gap:5, cornerRadius:5, alwaysRespectStubs:true } ],								                
			connectorStyle:connectorDestPaintStyle,
			hoverPaintStyle:endpointHoverStyle,
			connectorHoverStyle:connectorHoverStyle,
            dragOptions:{}
		},
		// the definition of source endpoints (the small red ones)
		sourceEndpoint = {
			endpoint:"Dot",
			paintStyle:{ 
				strokeStyle:"#7AB02C",
				fillStyle:"transparent",
				radius:7,
				lineWidth:3 
			},				
			isSource:true,
			connector:[ "Flowchart", { stub:[40, 60], gap:5, cornerRadius:5, alwaysRespectStubs:true } ],								                
			connectorStyle:connectorPaintStyle,
			hoverPaintStyle:endpointHoverStyle,
			connectorHoverStyle:connectorHoverStyle,
            dragOptions:{},
            overlays:[
            	[ "Label", { 
                	location:[0.5, 1.5], 
                	label:"Drag",
                	cssClass:"endpointSourceLabel" 
                } ]
            ]
		},	
		// the definition of target endpoints (will appear when the user drags a connection) 
		targetEndpoint = {
			endpoint:"Dot",					
			paintStyle:{ fillStyle:"#7AB02C",radius:11 },
			hoverPaintStyle:endpointHoverStyle,
			maxConnections:-1,
			dropOptions:{ hoverClass:"hover", activeClass:"active" },
			isTarget:true,			
            overlays:[
            	[ "Label", { location:[0.5, -0.5], label:"Drop", cssClass:"endpointTargetLabel" } ]
            ]
		},		
		init = function(connection) {			
			connection.getOverlay("label").setLabel(connection.sourceId.substring(15) + "-" + connection.targetId.substring(15));
			connection.bind("editCompleted", function(o) {
				if (typeof console != "undefined")
					console.log("connection edited. path is now ", o.path);
			});
		};			

		var _addEndpoints = function(toId, sourceAnchors, targetAnchors) {
				for (var i = 0; i < sourceAnchors.length; i++) {
					var sourceUUID = toId + sourceAnchors[i];
					instance.addEndpoint(toId, sourceEndpoint, { anchor:sourceAnchors[i], uuid:sourceUUID });						
				}
				for (var j = 0; j < targetAnchors.length; j++) {
					var targetUUID = toId + targetAnchors[j];
					instance.addEndpoint(toId, targetEndpoint, { anchor:targetAnchors[j], uuid:targetUUID });						
				}
			};

		instance.doWhileSuspended(function() {
			addNodeInstances();
			addArtefactsInstances();
			instance.draggable(jsPlumb.getSelector(".flowchart-demo .window"), { grid: [20, 20] });
			addPortInstances();
			addBindinInstances();
		});
		
		
		instance.bind("click", function(conn, originalEvent) {
			if (confirm("Delete connection from " + conn.sourceId + " to " + conn.targetId + "?"))
				jsPlumb.detach(conn); 
		});	
	});
	
	
	function addNodeInstances(){
		if(deploymentModel.nodeInstances != null){
			for(a=0;a<deploymentModel.nodeInstances.length;a++){
				var nodeInfo="name:"+deploymentModel.nodeInstances[a].name+
							"&lt;br&gt; type:"+deploymentModel.nodeInstances[a].type;
				
				$( "#flowchart-demo").append( "<div class='window _vm' data-content='"+nodeInfo+"' data-original-title='Node Info:' id='flowchartWindow_"+deploymentModel.nodeInstances[a].name+"'><strong>"+ deploymentModel.nodeInstances[a].name +"</strong><br/><br></div>" );
				placeRandomly("flowchartWindow_"+deploymentModel.nodeInstances[a].name);
				targetEndpoint.overlays[0][1].label = "destination";
				instance.addEndpoint("flowchartWindow_"+deploymentModel.nodeInstances[a].name, targetEndpoint, { anchor:"Continuous", uuid:"nodeInstances["+deploymentModel.nodeInstances[a].name+"]" });
				
				$('#flowchartWindow_'+deploymentModel.nodeInstances[a].name).popover({html:true});
			}
		}
	}
		
		function addArtefactsInstances(){
			if(deploymentModel.artefactInstances != null){
				for(a=0;a<deploymentModel.artefactInstances.length;a++){
					$( "#flowchart-demo").append( "<div class='window _art' id='flowchartWindow_"+deploymentModel.artefactInstances[a].name+"'><strong>"+ deploymentModel.artefactInstances[a].name +"</strong><br/><br></div>" );
					placeRandomly("flowchartWindow_"+deploymentModel.artefactInstances[a].name);
					//$('#flowchartWindow_'+deploymentModel.artefactInstances[a].name).popover();
				}
			}
		}
		
		function placeRandomly(id){
			var x=Math.floor((Math.random()*50)+1);
			var y=Math.floor((Math.random()*50)+1);
			$("#"+id).attr("style","top:"+y+"em;left:"+x+"em;");
		}
		
		function addPortInstances(){
			if(deploymentModel.artefactInstances != null){
				for(a=0;a<deploymentModel.artefactInstances.length;a++){
					var i=0;
					instance.addEndpoint("flowchartWindow_"+deploymentModel.artefactInstances[a].name, sourceDestEndpoint, { anchor:"Continuous", uuid:deploymentModel.artefactInstances[a].name+"_destination"});
					if(deploymentModel.artefactInstances[a].required != null){
						for(i=0; i<deploymentModel.artefactInstances[a].required.length;i++){
							sourceEndpoint.overlays[0][1].label = deploymentModel.artefactInstances[a].required[i].name;
							var port=findPortType(deploymentModel.artefactInstances[a].type,deploymentModel.artefactInstances[a].required[i].type);
							if(!port.isOptional){
								instance.addEndpoint("flowchartWindow_"+deploymentModel.artefactInstances[a].name, sourceEndpoint, { anchor:"Continuous", uuid:"artefactInstances["+deploymentModel.artefactInstances[a].name+"]/required["+deploymentModel.artefactInstances[a].required[i].name +"]"});
							}else{
								instance.addEndpoint("flowchartWindow_"+deploymentModel.artefactInstances[a].name, sourceOptionalEndpoint, { anchor:"Continuous", uuid:"artefactInstances["+deploymentModel.artefactInstances[a].name+"]/required["+deploymentModel.artefactInstances[a].required[i].name +"]"});
							}
							
						}
					}
					if(deploymentModel.artefactInstances[a].provided != null){
						for(i=0; i<deploymentModel.artefactInstances[a].provided.length;i++){
							targetEndpoint.overlays[0][1].label = deploymentModel.artefactInstances[a].provided[i].name;
							instance.addEndpoint("flowchartWindow_"+deploymentModel.artefactInstances[a].name, targetEndpoint, { anchor:"Continuous", uuid:"artefactInstances["+deploymentModel.artefactInstances[a].name+"]/provided["+deploymentModel.artefactInstances[a].provided[i].name +"]"});
						}
					}
				}
			}
		}
		
		
		function addBindinInstances(){
			for(a=0; a< deploymentModel.bindingInstances.length;a++){
				var connection=instance.connect({uuids:[deploymentModel.bindingInstances[a].server, deploymentModel.bindingInstances[a].client], editable:true});
				if(!connection){
					alertMessage("error","Binding:"+deploymentModel.bindingInstances[a].name+" not well defined!",5000);
				}
				connection.getOverlay("label").setLabel(deploymentModel.bindingInstances[a].name);
				
				var artefactinstance=findArtefactInstanceByName(deploymentModel.bindingInstances[a].client);
				var portinstance=findPortInstanceByName(artefactinstance,deploymentModel.bindingInstances[a].client);
				var port=findPortType(artefactinstance.type, portinstance.type);
				
				if((port != null) && !port.isRemote){
					connection.removeOverlay("myarrow");
					connection.addOverlay([ "PlainArrow", { location:1 , id:"myarrow"} ]);
				}
			}
			for(a=0;a<deploymentModel.artefactInstances.length;a++){
				if(deploymentModel.artefactInstances[a].destination != null){
					var connection=instance.connect({uuids:[ deploymentModel.artefactInstances[a].name+"_destination", deploymentModel.artefactInstances[a].destination ], editable:true});
				}
			}
		}
	
	
function loadFile(inputDiv) {
    var input, file, fr;
	var progress = $('#'+inputDiv).find('#progressBar');
	progress.css("width","0%");
	progress.parent().show();
    if (typeof window.FileReader !== 'function') {
        showAlert("p", "The file API isn't supported on this browser yet.");
        return;
    }

	//Error Catching
    input = $('#'+inputDiv).find('input').get(0);
	
    if (!input) {
        console.log("error","Um, couldn't find the fileinput element.",5000);
    }
    else if (!input.files) {
        console.log("error","This browser doesn't seem to support the `files` property of file inputs",5000);
    }
    else if (!input.files[0]) {
        console.log("error","Please select a file before clicking 'Load'",5000);
    }
	//its ok
    else {
        file = input.files[0];
        fr = new FileReader();
		fr.onprogress = updateProgress;
        fr.onload = receivedText;
        fr.readAsText(file);
    }

    function receivedText() {
	    progress.css("width",'100%');
		progress.parent().hide();
		loadDeploymentModel(fr.result);
    }
}

 function reset(){
	instance.reset();
	instance.repaintEverything();
	$( "#flowchart-demo").empty();
	$( "#bindingTable>tbody" ).empty();
	$( "#nodeTable>tbody" ).empty();
	$( "#artefactTable>tbody" ).empty();
	$( "#providerTable>tbody" ).empty();
 }
  
 //Load the deployment model
function loadDeploymentModel(jsonString) {
	deploymentModel=eval("(" + jsonString + ")");
	reset();
	addArtefactType();
	addNodeTypes();
	addBindingType();
	addProviders();
	instance.doWhileSuspended(function() {
		addNodeInstances();
		addArtefactsInstances();
		instance.draggable(jsPlumb.getSelector(".flowchart-demo .window"), { grid: [20, 20] });
		addPortInstances();
		addBindinInstances();
	});

}

function addArtefactType(){
	if(deploymentModel.artefactTypes != null){
		for(var a=0;a<deploymentModel.artefactTypes.length;a++){
			$( "#artefactTable>tbody" ).append("<tr><td>"+deploymentModel.artefactTypes[a].name+" </td><td><button type='button' class='btn btn-xs btn-primary'>Instantiate</button>&nbsp;<button type='button' data-toggle='modal' data-target='#editArtefactModal' class='btn btn-xs btn-warning'>Edit</button>&nbsp;<button type='button' class='btn btn-xs btn-danger'><b>x</b></button></td></tr>");
		}
	}
}

function addBindingType(){
	if(deploymentModel.bindingTypes != null){
		for(var a=0;a<deploymentModel.bindingTypes.length;a++){
			$( "#bindingTable>tbody" ).append("<tr><td>"+deploymentModel.bindingTypes[a].name+" </td><td><button type='button' class='btn btn-xs btn-primary'>Instantiate</button>&nbsp;<button type='button' data-toggle='modal' data-target='#editBindingModal' class='btn btn-xs btn-warning'>Edit</button>&nbsp;<button type='button' class='btn btn-xs btn-danger'><b>x</b></button></td></tr>");
		}
	}
}

function addNodeTypes(){
	if(deploymentModel.nodeTypes != null){
		for(var a=0;a<deploymentModel.nodeTypes.length;a++){
			$( "#nodeTable>tbody" ).append("<tr><td>"+deploymentModel.nodeTypes[a].name+" </td><td><button type='button' class='btn btn-xs btn-primary'>Instantiate</button>&nbsp;<button type='button'   data-toggle='modal' data-target='#editNodeModal' class='btn btn-xs btn-warning'>Edit</button>&nbsp;<button type='button' class='btn btn-xs btn-danger'><b>x</b></button></td></tr>");
		}
	}
}

function addProviders(){
	if(deploymentModel.providers != null){
		for(var a=0;a<deploymentModel.providers.length;a++){
			$( "#providerTable>tbody" ).append("<tr><td>"+deploymentModel.providers[a].name+" </td><td><button type='button' class='btn btn-xs btn-warning'>Edit</button>&nbsp;<button type='button' class='btn btn-xs btn-danger'><b>x</b></button></td></tr>");
		}
	}
}
