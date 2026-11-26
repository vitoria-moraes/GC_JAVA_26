package com.gc26.injecao.hello;

public class HelloApp {

    public static void main(String[] args) {
        HelloService helloService = new GCService();
        HelloController controller = new HelloController(helloService);

        controller.execute();
    }
}
