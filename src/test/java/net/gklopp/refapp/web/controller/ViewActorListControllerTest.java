package net.gklopp.refapp.web.controller;

import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.service.ActorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@@link ViewActorListController} unit test.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewActorListControllerTest {

    @InjectMocks
    private ViewActorListController viewActorListController;

    @Mock
    private ActorService actorService;

    // ***************************** INITIALIZATION ****************************

    @Before
    public void init() {

        List<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("actor1", "actor1FirstName", "actor1LastName");
        Actor actor2 = new Actor("actor2", "actor2FirstName", "actor2LastName");
        Actor actor3 = new Actor("actor3", "actor3FirstName", "actor3LastName");
        actors.add(actor1);
        actors.add(actor2);
        actors.add(actor3);
        when(actorService.getActors()).thenReturn(actors);

        viewActorListController.init();
    }

    // *************************************************************************

    @Test
    public void shouldGetActorList() throws Exception {

        // Given
        List<Actor> actorList = viewActorListController.getActorList();

        // When
        List<Actor> actorListRetrieved = viewActorListController.getActorList();

        // Then
        verify(actorService).getActors();
        assertThat(actorListRetrieved).isEqualTo(actorList);
    }
}