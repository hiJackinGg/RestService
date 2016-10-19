import com.mycompany.dao.IContactDao;
import com.mycompany.model.Contact;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-servlet.xml")
public class ContactDaoTest {

    @Autowired
    IContactDao contactDao;

    final List<Contact> expectedContacts = new LinkedList<Contact>(){
        {
            for (int i = 1, j = 1; i < 13; i++, j++){

                if (j == 4) j = 1;

                add(new Contact(i, "Contact"+j));


            }
        }
    };

    @Test
    public  void getContactsTest(){

        List<Contact> actualContacts = contactDao.getContacts();

        assertEquals(expectedContacts, actualContacts);

    }
}
