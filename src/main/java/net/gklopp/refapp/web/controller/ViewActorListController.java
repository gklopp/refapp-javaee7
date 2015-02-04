package net.gklopp.refapp.web.controller;


import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.service.ActorService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Controller that displays the list of Actors.
 */
@Named
@RequestScoped
public class ViewActorListController {

    private List<Actor> actorList;

    @Inject
    private ActorService actorService;

    // *************************** GETTERS / SETTERS ***************************

    public List<Actor> getActorList() {
        return actorList;
    }

    // ***************************** INITIALIZATION ****************************

    @PostConstruct
    public void init() {

        actorList = actorService.getActors();
    }
}
