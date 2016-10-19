import com.mycompany.controller.ContactController;
import com.mycompany.model.Contact;
import com.mycompany.service.IContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    private final List<Contact> contacts = new LinkedList<>();

    @Mock
    private IContactService contactService;

    private MockMvc mockMvc;

    @InjectMocks
    private ContactController contactController;

    @Before
    public void initData() {

        for (int i = 1; i < 6; i++){
            contacts.add(new Contact(i, "Contact"+i));
        }
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();

    }

    @Test
    public void test200okWithJsonResponse() throws Exception {

        when(contactService.getFilteredContacts("asd")).thenReturn(contacts);

        String jsonResponse = "{\"contacts\":[" +
                                    "{\"name\":\"Contact1\",\"id\":1}," +
                                    "{\"name\":\"Contact2\",\"id\":2}," +
                                    "{\"name\":\"Contact3\",\"id\":3}," +
                                    "{\"name\":\"Contact4\",\"id\":4}," +
                                    "{\"name\":\"Contact5\",\"id\":5}" +
                                "]}";

        mockMvc.perform(get("/contacts?nameFilter=asd")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(jsonResponse));
    }

    @Test
    public void test400IllegalFilterName() throws Exception {

        mockMvc.perform(get("/contacts?nameFilter")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/contacts?nameFilter=)))")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


    }


}
