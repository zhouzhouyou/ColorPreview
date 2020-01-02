package edu.colorpreview.model;

import androidx.annotation.NonNull;
import lombok.Data;

@Data
public class User {
    public Integer id;

    public String name;

    public String password;

    public Double money;

    public User(@NonNull Integer id, String name, String password, Double money) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
    }
}
