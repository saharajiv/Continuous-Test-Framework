package com.nisum.app.wadl;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hamcrest.core.IsInstanceOf;

import com.nisum.app.wadl.net.beans.Application;
import com.nisum.app.wadl.net.beans.Method;
import com.nisum.app.wadl.net.beans.Param;
import com.nisum.app.wadl.net.beans.Representation;
import com.nisum.app.wadl.net.beans.Request;
import com.nisum.app.wadl.net.beans.Resource;
import com.nisum.app.wadl.net.beans.Resources;
import com.nisum.app.wadl.net.beans.Response;

public class WADLParser {

	public static void main(String [] args){
	    URL path = ClassLoader.getSystemResource("wadl/application.xml");
	    //URL path = new WADLParser().getClass().getClassLoader().getSystemResource("wadl/application.xml");
		File file;
		
		List<Resources> resourcesList = parseWADL(path);
		
		
			
		System.out.println();
		
	}

	private static List<Resources> parseWADL(URL path) {
		Application wadlApp = null;
		File file;
		try {
			file = new File(path.toURI());
			JAXBContext context = JAXBContext.newInstance(Application.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			wadlApp = (Application)unmarshaller.unmarshal(file);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Resources> resourcesList = wadlApp.getResources();
		parseResources(resourcesList);
		return resourcesList;
	}

	private static void parseResources(List<Resources> resourcesList) {
		for(Resources resources : resourcesList){
			System.out.println("Base : "+resources.getBase());
			List<Resource> resourceList = resources.getResource();
			for(Resource resource : resourceList){
				System.out.println("------------------------------------Resource Start------------------------------------------------");
				parseResource(resource);
				System.out.println("------------------------------------Resource End------------------------------------------------");
			}
			//parseResource(resourceList);
		}
	}

	private static void parseResource(Resource resource) {
		//for(Resource resource : resourceList){
			System.out.println("Resource Path : "+resource.getPath());
			List<Param> paramList = resource.getParam();
			if(paramList!=null){
				for(Param param : paramList ){
					System.out.print(" Name : "+param.getName());
					System.out.print("\t type: "+param.getType());
					System.out.println("\t style: "+param.getStyle());
				}
			}
			List methodResourceList = resource.getMethodOrResource();
			if(methodResourceList!=null){
				Iterator iterator = methodResourceList.iterator();
				while(iterator.hasNext()){
					Object object = iterator.next();
					if(object instanceof Resource){
						Resource resource2 = (Resource) object;
						parseResource(resource2);
						//System.out.println(resource.getPath());
						//resource.getMethodOrResource();
					}else if(object instanceof Method){
						Method method = (Method)object;
						System.out.println("api-name : "+method.getId());
						System.out.println("HTTP method: "+method.getName());
						Request request = method.getRequest();
						if(request!=null){
							System.out.println("Request:");
							List<Representation> representations = request.getRepresentation();
							for(Representation representation : representations){
								System.out.println(" href : "+representation.getHref());
								System.out.println(" media type : "+ representation.getMediaType());
							}
							List<Param> requestParams = request.getParam();
							if(requestParams!=null){
								for(Param param : requestParams){
									System.out.print("Param name : "+param.getName()+"\t");
									System.out.print("Param type : "+param.getType()+"\t");
									System.out.println("Param style: "+ param.getStyle());
								}
							}
						}
						List<Response> responseList = method.getResponse();
						if(responseList!=null){
							System.out.println("Response:");
							for(Response response : responseList){
								List <Representation> responseRepresentationList = response.getRepresentation();
								if(responseRepresentationList!=null){
									for(Representation representaion : responseRepresentationList){
										System.out.println("  Media type: "+representaion.getMediaType());
									}
								}
								List<Param> responseParams = response.getParam();
								if(responseParams!=null){
									for(Param param : responseParams){
										System.out.print("Param name : "+param.getName()+"\t");
										System.out.println("Param type : "+param.getType());
									}
								}
							}
						}
						System.out.println("------------------------------------------------------------------");
						
					} 
				}
				
			}
		
	}
	
}
