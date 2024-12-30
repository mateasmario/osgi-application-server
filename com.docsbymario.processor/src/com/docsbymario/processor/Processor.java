package com.docsbymario.processor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.docsbymario.blacklist.Blacklist;
import com.docsbymario.controller.Controller;
import com.docsbymario.inputreader.InputReader;
import com.docsbymario.processor.exception.ForbiddenException;
import com.docsbymario.data.request.Request;

@Component(
    property= {
        "osgi.command.scope:String=docsbymario",
        "osgi.command.function:String=start"
    },
    service=Processor.class
)
public class Processor {
	@Reference
	private InputReader inputReader;
	@Reference
	private Blacklist blacklist;
	
	private BundleContext context;
	
	@Activate
	public void activate(BundleContext context) {
		this.context = context;
	}
	
	public void start() throws ForbiddenException, InvalidSyntaxException {
		Request request = inputReader.read();
		
		if (blacklist.disallow(request)) {
			throw new ForbiddenException("Your ip is blacklisted.");
		}
		
        ServiceReference<?>[] references = context.getServiceReferences(Controller.class.getName(), null);	
        if (references != null) {
            for (ServiceReference<?> ref : references) {
                Controller controller = (Controller) context.getService(ref);
                if (controller != null && controller.canReceive(request)) {
                    System.out.println(controller.process(request));
                    return;
                }
            }
        }
        
        System.out.println("Endpoint not found. Could not handle your request.");
	}
}
