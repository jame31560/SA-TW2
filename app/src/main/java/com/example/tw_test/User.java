package com.example.tw_test;

public class User{
    private String username;
    private DBMgr dbMgr = new DBMgr();

    User(String username) {
        setUsername(username);
    }

    User(String username,
         String password,
         String name,
         String phone) {
        dbMgr.addUser(username, password, name, phone);
        setUsername(username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return dbMgr.getUserPassword(this.username);
    }

    public String getName() {
        return dbMgr.getUserName(this.username);
    }

    public String getPhone() {
        return dbMgr.getUserPhone(this.username);
    }

    public int getBalance() {
        return dbMgr.getUserBalance(this.username);
    }

    public String geyQRCode() {
        return dbMgr.getUserQRCodeID(this.username);
    }

    public void setUserPassword(String password) {
        dbMgr.setUserPassword(this.username, password);
    }

    public void deductUserMoney(int amount) {
        dbMgr.deductUserMoney(this.username, amount);
    }

    public void addUserMoney(int amount) {
        dbMgr.addUserMoney(this.username, amount);
    }
}
