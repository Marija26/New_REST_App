package org.mary.pro.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;


@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String name;
    private String lastName;
    private String position;

    public User() {

    }

    public User(String name, String lastName, String position) {
        this.name = name;
        this.lastName = lastName;
        this.position = position;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(name, user.name) &&
//                Objects.equals(lastName, user.lastName) &&
//                Objects.equals(position, user.position);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, lastName, position);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", position='" + position + '\'' +
//                '}';
//    }
}
