package net.gklopp.refapp.web.util;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 * Allows to inject JSF FacesContext.
 */
@RequestScoped
public class FacesContextProducer {

    private FacesContextProducer() {
    }

    @Produces
    public FacesContext getFacesContext() {

        FacesContext ctx = FacesContext.getCurrentInstance();
        if (ctx == null) {
            throw new ContextNotActiveException("FacesContext is not active");
        }
        return ctx;
    }
}
