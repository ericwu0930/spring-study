package io.github.ericwu0930.staticProxy;

public class Proxy implements Rent{
    private Landlord landlord;

    public Proxy() {
    }

    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    public void rent(){
        seeHouse();
        contract();
        fare();
        landlord.rent();
    }

    public void seeHouse(){
        System.out.println("中介带看");
    }

    public void fare(){
        System.out.println("收中介费");
    }

    public void contract(){
        System.out.println("签合同");
    }
}
