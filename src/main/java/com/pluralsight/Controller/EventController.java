package com.pluralsight.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.pluralsight.model.Event;

@Controller
//The "event" below refers to the same "event" in the @ModelAttribute arg in processEvent
@SessionAttributes("event") //binds the 'event' object to the session and not per request
public class EventController
{
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public String displayEventPage(Model model)
    {
        Event event = new Event();
        event.setName("Java User Group");

        model.addAttribute("event", event);

        return "event";
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public String processEvent(@ModelAttribute("event") Event event)
    {
        System.out.println(event);

        //if we were not using a SessionAttribute and using redirect as below, it would not remember the event name being set as it is tied to the
        //request and when it is redirected, we are creating a new request
        return "redirect:index.html"; //if using forward, it would leave the url as event.html
    }
}
