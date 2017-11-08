package command.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ControllerTest {

    @GetMapping(value = "/hallo")
    public String restHallo(){
        return "Hallo !";
    }
}

