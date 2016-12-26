package com.mycompany.dao;

import com.mycompany.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository()
public class ContactDao implements IContactDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Contact> getContacts(){
        List<Contact> contacts = new LinkedList<>();

        int fetchSize = 1000;  //can be varied
        try(Connection conn = dataSource.getConnection()){
            conn.setAutoCommit(false);
            try(Statement stmt = conn.createStatement()) {
                stmt.setFetchSize(fetchSize);
                String sql = "SELECT * FROM Contacts";
                try (ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");

                        contacts.add(new Contact(id, name));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return contacts;
    }

    /**
     * Can be used to reduce memory consumption.
     * @param offset
     * @param fetch
     * @return
     */
    @Override
    public List<Contact> getContacts(int offset, int fetch){
        List<Contact> contacts = new ArrayList<>(fetch);
        String sql = "SELECT * FROM Contacts ORDER BY id OFFSET ("+offset+") ROWS FETCH NEXT " + fetch + " ROWS ONLY";
        try(Connection conn = dataSource.getConnection()){
            try(Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");

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

