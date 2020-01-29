package com.workjam.interview.objects.user;


import java.time.LocalDate;
import java.util.UUID;

public interface User {

    public UUID getUuid();

    public Permission getPermission();

    public String getName();

    public void setName(String name);

    public boolean isActive();

    public void setActive(boolean active);

    public LocalDate getBirthDay();

    public void setBirthDay(LocalDate birthDay);

    public enum Permission {
        EMPLOYEE,
        MANAGER,
        ADMIN
    }
}
