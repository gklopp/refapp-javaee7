package net.gklopp.refapp.web.controller;


import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.service.ActorService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controller that creates a new Actor.
 */
@Named
@RequestScoped
public class CreateActorController {

    private Actor newActor;

    @Inject
    private ActorService actorService;

    @Inject
    private transient Instance<FacesContext> facesContext;

    // *************************** GETTERS / SETTERS ***************************

    public Actor getNewActor() {
        return newActor;
    }

    // ***************************** INITIALIZATION ****************************

    @PostConstruct
    public void init() {

        newActor = new Actor();
    }

    public void createActor() {

        actorService.createActor(newActor);
        facesContext.get().addMessage(null, new FacesMessage("Actor '" + newActor.getUserId() + "' has been created"));
        init();
    }
}
