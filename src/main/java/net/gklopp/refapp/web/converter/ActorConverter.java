package net.gklopp.refapp.web.converter;

import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.service.ActorService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * A JSF converter that can handle the {@link net.gklopp.refapp.domain.Actor}.
 */
@FacesConverter("actorConverter")
public class ActorConverter implements Converter {

    @Inject
    private ActorService actorService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String actorIdAsString) {

        Actor actor = null;

        if ((actorIdAsString != null) && !actorIdAsString.isEmpty()) {
            long actorId = Long.valueOf(actorIdAsString);
            actor = actorService.getActorById(actorId);
        }

        return actor;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object actor) {

        String actorIdAsString = "";

        if (actor != null) {

            long actorId = ((Actor) actor).getId();
            actorIdAsString = Long.toString(actorId);
        }

        return actorIdAsString;
    }
}
