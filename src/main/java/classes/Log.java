package classes;

import java.time.LocalDateTime;

public class Log {
    private String userName;
    private String password;
    private LocalDateTime loginData;

    public Log(String userName, String password) {
        this.userName = userName;
        this.password = password;
        loginData = LocalDateTime.now();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLoginData() {
        return loginData;
    }

    public void setLoginData(LocalDateTime loginData) {
        this.loginData = loginData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
