package com.crm.crmbe.rest.readings;

import com.crm.crmbe.database.services.ReadingsServices;
import com.crm.crmbe.entity.ReadingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ReadingItemController {

    @Autowired
    ReadingsServices readingsServices;


    @PutMapping("/api/v1/reading/item/create")
    public void create(@RequestBody ReadingItem readingItem, HttpServletResponse response) throws IOException {
        readingsServices.createReadingIrem(readingItem);
        response.sendError(HttpServletResponse.SC_OK);
    }

}
