package com.gc26.injecao.hello;

public class GCService implements HelloService {

    @Override
    public String hello() {
        return "Hello GC 26!";
    }
}
