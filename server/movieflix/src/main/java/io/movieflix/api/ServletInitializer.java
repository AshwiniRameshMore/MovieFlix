package io.movieflix.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	private static final Logger LOGGER = Logger.getLogger(ServletInitializer.class.getName() );
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {APIConfig.class, JPAConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		LOGGER.log(Level.INFO, "Initializing Servlet");
		return new String[] {"/api/*"};
	}

}
