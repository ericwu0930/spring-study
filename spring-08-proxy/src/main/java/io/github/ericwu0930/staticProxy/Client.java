package io.github.ericwu0930.staticProxy;

public class Client {
    public static void main(String[] args) {
        Landlord host=new Landlord();
        Proxy proxy=new Proxy(host);
        proxy.rent();
    }
}
