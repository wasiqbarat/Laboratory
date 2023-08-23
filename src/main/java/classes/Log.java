package classes;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private final SimpleStringProperty action;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty password;
    private LocalDateTime loginData;
    private final SimpleStringProperty loginDateString;
    private SimpleStringProperty info;



    public Log(String userName, String password, String action) {
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
        loginData = LocalDateTime.now();

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd | HH:mm a");
        loginDateString = new SimpleStringProperty(loginData.format(formatter));
        this.action = new SimpleStringProperty(action);
    }

    public String getAction() {
        return action.get();
    }

    public SimpleStringProperty actionProperty() {
        return action;
    }

    public void setAction(String action) {
        this.action.set(action);
    }

    public String getInfo() {
        return info.get();
    }

    public SimpleStringProperty infoProperty() {
        return info;
    }

    public void setInfo(String info) {
        this.info = new SimpleStringProperty(info);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;

    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public LocalDateTime getLoginData() {
        return loginData;
    }

    public void setLoginData(LocalDateTime loginData) {
        this.loginData = loginData;
    }

    public String getLoginDateString() {
        return loginDateString.get();
    }

    public SimpleStringProperty loginDateStringProperty() {
        return loginDateString;
    }

    public void setLoginDateString(String loginDateString) {
        this.loginDateString.set(loginDateString);
    }
}
