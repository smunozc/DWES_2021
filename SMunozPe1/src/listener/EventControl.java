package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

@WebListener
public class EventControl implements ServletContextListener, 		// context events listener
									 HttpSessionListener,			// session events listener
									 HttpSessionAttributeListener, 	// session events listener (attributes)
									 ServletRequestListener			// servlet events listener
									 			
									 									{
	/**
	 * more information can be read here:
	 * https://www.javatpoint.com/Event-and-Listener-in-Servlet
	 */
	
	
	final static Logger logger = Logger.getLogger(EventControl.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce){
		logger.info("Application: start");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce){
		logger.info("Application: stop");
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		logger.info("Attribute added ['" + hsbe.getName() + "' - '" + hsbe.getValue().getClass() + "']");
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe){
		logger.info("Attribute removed ['" + hsbe.getName() + "' - '" + hsbe.getValue().getClass() + "']");
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		logger.info("Attribute replaced ['" + hsbe.getName() + "' - '" + hsbe.getValue().getClass() + "']");
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		logger.info("Servlet request initialization detected ['" + sre.getServletRequest().getRemoteAddr() + "']");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		logger.info("Request destroyed ['" + sre.getServletRequest().getRemoteAddr() + "']");
		
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		logger.info("Session created ['" + hse.getSession().getId() + "']");
    }
	
	@Override
    public void sessionDestroyed(HttpSessionEvent hse) {
		logger.info("Session destroyed ['" + hse.getSession().getId() + "']");
    }
	
}
