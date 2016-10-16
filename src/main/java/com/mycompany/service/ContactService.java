package com.mycompany.service;
import java.util.*;
import com.mycompany.dao.ContactDao;
import com.mycompany.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService implements IContactService{

    @Autowired
    ContactDao contactDao;


    @Override
    public Collection<Contact> getContacts(String regex){
        List<Contact> contacts = null;
        Collection<Contact> filteredContacts = null;

        contacts = contactDao.getContacts();

        try {
            filteredContacts = executeConcurrent(contacts, regex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return filteredContacts;
    }

    private void execute(final List<Contact> l1, final Set<Contact> l2, final String regex,
                         final int i, final int valCount, final int threadCount ){
        int from = i * valCount;
        int to = (i == threadCount - 1) ? l1.size() : from + valCount;

        for (Contact c : l1.subList(from, to)){

            if (c.getName().matches(regex) == false) {
                l2.add(c);
            }

        }
    }

    /**
     * Executes list filtering in several threads (a thread per processor).
     * List is divided into parts(almost even) among all threads.
     * @param contacts
     * @param regex
     * @return
     * @throws InterruptedException
     */
    private Set<Contact> executeConcurrent(final List<Contact> contacts, final String regex) throws InterruptedException {
        Set<Contact> filteredContacts = Collections.synchronizedSet(new HashSet<>());
        final int threadsCount = Runtime.getRuntime().availableProcessors();
        final int valuesPerThread = contacts.size() / threadsCount;

        final List<Thread> threads = new ArrayList<>(threadsCount);
        for (int i = 0; i < threadsCount; i++) {
            final int threadNumber = i;
            Thread thread = new Thread(() -> execute(contacts, filteredContacts, regex,
                    threadNumber, valuesPerThread, threadsCount));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return filteredContacts;
    }

    /**
     * Execute list filtering in a single thread.
     * @param l1 list to be filtered.
     * @param regex
     * @return the same List without elements which are not match regex.
     */
    private List<Contact> executeInSingleThread(List<Contact> l1, String regex) {

        Iterator<Contact> it = l1.iterator();
        while (it.hasNext()) {
            Contact c = it.next();
            if (c.getName().matches(regex) == false)
                it.remove();
        }

        return  l1;
    }

}
