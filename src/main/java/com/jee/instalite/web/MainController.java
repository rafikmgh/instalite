package com.jee.instalite.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
public class MainController {

  @RequestMapping(value = "/home", method = RequestMethod.GET, produces="text/html")

    @ResponseBody
    public  String home() throws JsonProcessingException {
        return "<h1>Home Page</h1>" ;
    }


/*    @GetMapping("/login")
    public String login() {
        return "login";
    }*/

}
