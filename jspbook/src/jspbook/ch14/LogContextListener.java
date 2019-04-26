package jspbook.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LogContextListener implements ServletContextListener {
	private static Logger LOG = LoggerFactory.getLogger(LogContextListener.class);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		LOG.info("LogContextListener stop");
	}
	
	public void contextInitialized(ServletContextEvent arg0) {
		LOG = LoggerFactory.getLogger(this.getClass());
		LOG.info("LogContextListener start");
	}
}
