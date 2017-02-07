package com.mycompany.model;

import org.json.simple.JSONObject;

public class Contact {
    private int id;
    private String name;


    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //uses simple JSON library to convert object to JSON
    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("name", name);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Contact{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        return name.equals(contact.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
