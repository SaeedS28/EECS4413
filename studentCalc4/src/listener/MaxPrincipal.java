package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements ServletContextAttributeListener {

	private static double maxPrincipal=0;
    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
    	
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
       attributeReplaced(arg0);
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
    	boolean isAdded = arg0.getName().equals("principal");
        
        if(isAdded){
     	   try{
     		   double tempPrincipal = Double.parseDouble(arg0.getValue().toString());
     		   if(tempPrincipal>maxPrincipal) {
     			   maxPrincipal=tempPrincipal;
     		   }
     	   }
     	   catch(Exception e){
     		   
     	   }
        }
    }

    public static double getMaxValue(){
    	return maxPrincipal;
    }
}