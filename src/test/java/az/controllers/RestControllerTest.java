package az.controllers;

import org.junit.Before;
import org.junit.Test;
import az.service.INoteManager;
import az.service.ITagManager;
import az.model.Note;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;


 

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
@WebAppConfiguration
public class RestControllerTest {

    private MockMvc mockMvc;
 
    @Autowired
    private INoteManager noteManagerMock;

    @Autowired
    private ITagManager tagManagerMock;
 
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MainController mainController;

    @Before
    public void setUp() {
        Mockito.reset(noteManagerMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void List() throws Exception{
        Note first = new Note("first", "first");
        Note second = new Note("second", "second");
 
        when(noteManagerMock.getNotes()).thenReturn(Arrays.asList(first, second));
        mockMvc.perform(get("/dummyList")).andExpect(status().isOk());

        verify(noteManagerMock, times(1)).getNotes();
        verifyNoMoreInteractions(noteManagerMock);
    }
}