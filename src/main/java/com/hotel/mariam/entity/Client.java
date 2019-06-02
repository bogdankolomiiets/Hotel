package com.hotel.mariam.entity;

import org.mindrot.jbcrypt.BCrypt;

public class Client {
    private int clientId;
    private String clientName;
    private String clientSurname;
    private String clientPhone;
    private String clientEmail;
    private String clientPass;

    public Client() {
    }

    public Client(String clientName, String clientSurname, String clientPhone, String clientEmail, String clientPass) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientPhone = clientPhone;
        this.clientEmail = clientEmail;
        this.clientPass = BCrypt.hashpw(clientPass, BCrypt.gensalt());
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPass() {
        return clientPass;
    }

    public void setClientPass(String clientPass) {
        this.clientPass = clientPass;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", clientPhone='" + clientPhone + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}
