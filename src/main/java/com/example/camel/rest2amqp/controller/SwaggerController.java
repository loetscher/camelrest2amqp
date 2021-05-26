package com.example.camel.rest2amqp.controller;

import static com.example.camel.rest2amqp.routes.ActiveMqSenderRouter.API_PATH;
import static com.example.camel.rest2amqp.routes.ActiveMqSenderRouter.DOC_CONTEXT_PATH;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

    @RequestMapping("/swagger")
    public String redirectToUi() {
        return "redirect:/webjars/swagger-ui/index.html?url=" + DOC_CONTEXT_PATH + API_PATH + "&validatorUrl=";
    }
}
