package net.gklopp.refapp.web.controller;

import net.gklopp.refapp.domain.Actor;
import net.gklopp.refapp.service.ActorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link CreateActorController} unit test.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateActorControllerTest {

    @InjectMocks
    private CreateActorController createActorController;

    @Mock
    private ActorService actorService;

    @Mock
    private Instance<FacesContext> facesContextProducer;

    @Mock
    private FacesContext facesContext;


    @Before
    public void init() {

        when(facesContextProducer.get()).thenReturn(facesContext);

        createActorController.init();
    }

    @Test
    public void shouldCreateActor() {

        // Given
        Actor newActor = createActorController.getNewActor();
        newActor.setUserId("actorId");
        newActor.setFirstName("actorFirstName");
        newActor.setLastName("actorLastName");

        // When
        createActorController.createActor();

        // Then
        verify(actorService).createActor(newActor);
        verify(facesContext).addMessage(anyString(), anyObject());

        Actor actorAfterCreation = createActorController.getNewActor();
        assertThat(actorAfterCreation).isEqualTo(new Actor());
    }
}