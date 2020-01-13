package com.pluralsight.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController
{

    @RequestMapping(value = "/greeting")
    public String sayHello(Model model)
    {
        model.addAttribute("greeting", "Hello World");

        return "hello";

        /*
        bad practice to directly refer to the jsp page, since user can go directly to /hello.jsp and nothing will be
        displayed as it doesn't route through greeting.html (this is called deep/direct linking):
        return "hello.jsp";
        */
    }

    @RequestMapping(value="/index")
    public String index(Model model)
    {
        return "forward:index.jsp"; //a forward continues a single request, using redirect would make a completely new request
    }
}
