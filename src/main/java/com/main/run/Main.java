package com.main.run;

public class Main {
    public static void main(String[] args) throws Exception {
        //add your main execute code here
        TestMain protocolTestMain = new TestMain();
        String returnedStr = protocolTestMain.sendMessage("Hello, this is protocol-trans-util.");
        System.out.println(returnedStr);
    }
}
