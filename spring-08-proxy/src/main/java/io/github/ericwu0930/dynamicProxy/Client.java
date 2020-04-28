package io.github.ericwu0930.dynamicProxy;

public class Client {
    public static void main(String[] args) {
        //真实角色
        Rent landlord = new Landlord();

        //代理角色
        ProxyInvocationHandler proxyInvocationHandler = new ProxyInvocationHandler();
        proxyInvocationHandler.setRent(landlord);
        Rent proxy = (Rent)proxyInvocationHandler.getProxy();
        //全程没有写代理类，由动态生成
        proxy.rent();

    }
}
