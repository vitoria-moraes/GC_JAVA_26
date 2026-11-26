package com.gc26.injecao.hello;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public void execute() {
        System.out.println(helloService.hello());
    }
}
