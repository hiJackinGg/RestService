package com.mycompany.controller;

import com.mycompany.model.Contact;
import com.mycompany.service.IContactService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@RestController
public class ContactController {

    @Autowired
    IContactService contactService;

    @RequestMapping(value = "/contacts", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity getFilteredContacts(@RequestParam String nameFilter) {

        Collection<Contact> contacts = null;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

            try {
                Pattern.compile(nameFilter);
            } catch (PatternSyntaxException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            contacts = contactService.getFilteredContacts(nameFilter);

            for (Contact c : contacts) {
                jsonArray.add(c.toJSON());
            }

            jsonObject.put("contacts", jsonArray);

        return new ResponseEntity<>(jsonObject.toJSONString(), HttpStatus.OK);

    }



}
