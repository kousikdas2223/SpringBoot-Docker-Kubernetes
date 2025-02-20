package com.springboottutorial.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {

        return Mono.just("An error has occurred, try after sometime or please contact Support");

    }
}
