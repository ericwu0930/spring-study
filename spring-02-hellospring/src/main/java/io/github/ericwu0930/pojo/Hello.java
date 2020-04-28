package io.github.ericwu0930.pojo;

public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }

    public void setStr(String str) {
        this.str = str;
    }
}
