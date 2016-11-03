package net.devwiki.devwiki.ui.list;

/**
 * Created by zyz on 2016/11/3.
 */

public class Person {

    interface Sex {
        int MALE = 0;
        int FEMALE = 1;
    }

    private String name;
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
