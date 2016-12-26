import com.mycompany.model.Contact;
import static org.junit.Assert.*;

import com.mycompany.service.IContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.*;
import java.util.regex.PatternSyntaxException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-servlet.xml")
public class ContactServiceTest {

    @Autowired
    IContactService contactService;

    @Test
    public  void getContactsTest(){

        String regex = "^.*[23].*$";

        final int expectedSize = 4;

        Set<Contact> expectedContactNames = new HashSet<>(expectedSize);
        expectedContactNames.add(new Contact(1, "Contact1"));
        expectedContactNames.add(new Contact(4, "Contact1"));
        expectedContactNames.add(new Contact(7, "Contact1"));
        expectedContactNames.add(new Contact(10, "Contact1"));

        Collection<Contact> actualContacts = contactService.getContacts(regex);
        assertEquals(expectedContactNames, actualContacts);

    }

    @Test
    public  void getContactsTest3(){

        String regex = "^.*[123].*$";

        int expectedSize = 0;
        Collection<Contact> actualContacts = contactService.getContacts(regex);

        assertEquals(expectedSize, actualContacts.size());
    }

}
