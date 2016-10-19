import com.mycompany.model.Contact;
import static org.junit.Assert.*;

import com.mycompany.service.IContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-servlet.xml")
public class ContactServiceTest {

    @Autowired
    IContactService contactService;

    @Test
    public  void getContactsTest(){

        String regex = "^.*[23].*$";

        final int expectedSize = 4;
        Set<String> expectedContactNames = new HashSet<String>(){
            {
                for (int i = 1; i <= expectedSize; i++){

                    add("Contact1");

                }
            }
        };

        Collection<Contact> actualContacts = contactService.getFilteredContacts(regex);

        assertEquals(expectedSize, actualContacts.size());

        for (Contact c : actualContacts)
                assertTrue(expectedContactNames.contains(c.getName()));

    }

}
