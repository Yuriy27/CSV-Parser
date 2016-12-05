package com.github.yuriy27.csvparser.ex;

import com.github.yuriy27.csvparser.annot.Column;
import com.github.yuriy27.csvparser.annot.CsvEntity;

@CsvEntity(resource = "src/main/resources/test.csv")
public class Student {

    @Column(num = 1)
    private String name;

    @Column(num = 2)
    private String surname;

    @Column(num = 3)
    private boolean city;

    public Student() {
    }

    public Student(String name, String surname, boolean city) {
        this.name = name;
        this.surname = surname;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getCity() {
        return city;
    }

    public void setCity(boolean city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (city != student.city) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        return surname != null ? surname.equals(student.surname) : student.surname == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (city ? 1 : 0);
        return result;
    }
}
