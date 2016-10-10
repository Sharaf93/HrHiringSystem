package rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class initRest extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		Set<Class<?>> setOfClasses = new HashSet<Class<?>>();
		setOfClasses.add(RestGraph.class);
		return setOfClasses;
	}

}
