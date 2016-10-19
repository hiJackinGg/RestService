package com.mycompany.service;

import com.mycompany.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository()
public class ContactService implements IContactService  {

    @Autowired
    private DataSource dataSource;

    @Override
    public Collection<Contact> getFilteredContacts(String regex){
        List<Contact> contacts = new LinkedList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;

        //будем вытаскивать из базы и хранить в пямяти только окола 1000 строк
        //(при каждом обращении к базе перезаполняется буффер для хранения 1000 строк)
        int fetchSize = 1000; //тоесть, учитывая размер строки, для данного запроса в памяти будет всегда занято только 66кб
        try(Connection conn = dataSource.getConnection()){
            conn.setAutoCommit(false);
            try(Statement stmt = conn.createStatement()) {
                stmt.setFetchSize(fetchSize);
                String sql = "SELECT * FROM Contacts";
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");

                        if (pattern.matcher(name).matches() == false)
                            contacts.add(new Contact(id, name));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return contacts;
    }
}

