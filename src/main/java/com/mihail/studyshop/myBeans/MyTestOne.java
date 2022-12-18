package com.mihail.studyshop.myBeans;

public class MyTestOne implements MyTestIface{
    @Override
    public void load() {
        System.out.println("load1");
    }

    @Override
    public void unLoad() {
        System.out.println("load2");
    }
}
