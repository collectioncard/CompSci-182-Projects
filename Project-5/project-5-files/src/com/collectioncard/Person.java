package com.collectioncard;


//So, this is essentially creating a node class, but it has two values. It should have a left and right value
public class Person {

    private String number = "";
    private String name = "";
    private Person left = null;
    private Person right = null;

    public Person(String newNumber, String newName){
        setName(newName);
        setNumber(newNumber);
    }

    //Setters
    public void setNumber(String newNumber){
        number = newNumber;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setLeft(Person toSet){
        left = toSet;
    }

    public void setRight(Person toSet){
        right = toSet;
    }

    //getters
    public String getNumber(){
        return number;
    }

    public String getName(){
        return name;
    }

    public Person getLeft(){
        return left;
    }

    public Person getRight(){
        return right;
    }
}
