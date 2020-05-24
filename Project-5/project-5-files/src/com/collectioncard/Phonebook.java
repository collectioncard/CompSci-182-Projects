package com.collectioncard;

import com.collectioncard.Exceptions.PersonNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Phonebook {

    //Global Reference to the Root Person
    Person root = null;

    //Public Methods

    /**
     * add - Creates a person and adds them to a binary search tree
     *
     * @param newNumber
     * @param newName
     */
    public void add(String newNumber, String newName) {
        //unfortunately, I have to call a recursive method
        root = recursiveAdd(root, newNumber, newName);
    }

    /**
     * find - Returns a person in a binary search tree
     *
     * @param name
     * @return Person Object
     */
    public Person find(String name) throws PersonNotFoundException {
        //Call a recursive method to find a value
        try {
            return findRecursive(root, name);
        } catch (Exception e) {
            throw new PersonNotFoundException("Person Not found");
        }


    }

    /**
     * delete - Deletes a node from a binary search tree
     *
     * @param name
     */
    public void delete(String name) throws PersonNotFoundException {

        Person toDelete = find(name);

        Person parent = getParent(root, toDelete);
        //Check to see what side of parent toDelte is on
        boolean rightSide = false;

        if (parent.getRight() == toDelete) {
            rightSide = true;
        }

        //check to see how many children toDelete has

        //If there is no children
        if (toDelete.getRight() == null && toDelete.getLeft() == null) {
            //If the node is root
            if (toDelete == root) {
                root = null;
            } else if (rightSide) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }
        }
        //If there is one child
        else if ((toDelete.getRight() == null && toDelete.getLeft() != null) || (toDelete.getRight() != null && toDelete.getLeft() == null)) {
            if (toDelete == root) {
                if (toDelete.getLeft() != null) {
                    root = toDelete.getLeft();
                } else {
                    root = toDelete.getRight();
                }
            } else if (rightSide) {
                if (toDelete.getLeft() == null) {
                    parent.setRight(toDelete.getRight());
                } else {
                    parent.setRight(toDelete.getLeft());
                }
            } else {
                if (toDelete.getLeft() == null) {
                    parent.setLeft(toDelete.getRight());
                } else {
                    parent.setLeft(toDelete.getLeft());
                }
            }


        //2 children
        } else if (toDelete.getRight() != null && toDelete.getLeft() != null) {
            if (toDelete == root) {

                //move root.left onto root.right

                getNextEmptyLeft(toDelete.getRight()).setLeft(toDelete.getLeft());
                root = toDelete.getRight();
            } else if (rightSide) {
                parent.setRight(toDelete.getRight());
                getNextEmptyLeft(parent).setLeft(toDelete.getLeft());
            } else {
                parent.setLeft(toDelete.getRight());
                getNextEmptyLeft(parent).setLeft(toDelete.getLeft());


            }
        }
    }

    /**
     * saveToFile - Writes a binary search tree to a file
     */
    public void saveToFile() {
        emptyFile();
        preOrderWrite(root);
    }

    /**
     * readFromFile - Creates a binary search tree from a file
     *
     * @throws FileNotFoundException
     */
    public void readFromFile() throws FileNotFoundException {

        //variables
        String fromFile;

        //open the file
        File treeFile = new File("tree.thomas");

        //if that didnt fail, we can probably finish the program.
        Scanner input = new Scanner(treeFile);

        while (input.hasNextLine()) {
            fromFile = input.nextLine();
            add(fromFile.substring(0, fromFile.indexOf(":")), fromFile.substring(fromFile.indexOf(":") + 1));
        }
    }

    /**
     * Change Data - Change the information stored in a person object
     *
     * @param name
     * @param newNumber
     * @return newNumber
     */
    public String changeData(String name, String newNumber) throws PersonNotFoundException {
        Person toModify = find(name);
        toModify.setNumber(newNumber);
        return toModify.getNumber();
    }

    //Private Methods


    //Helper method for the add method
    private Person recursiveAdd(Person current, String newNumber, String newName) {
        //Check to see if the currently selected Person has a value
        if (current == null) {
            return new Person(newNumber, newName);
        }

        //if the selected person has values, select a new position
        if (newName.hashCode() < current.getName().hashCode()) {
            current.setLeft(recursiveAdd(current.getLeft(), newNumber, newName));
        } else if (newName.hashCode() > current.getName().hashCode()) {
            current.setRight(recursiveAdd(current.getRight(), newNumber, newName));
        } else {
            //If the program reaches this point, the value entered is already in the tree
            return current;
        }
        return current;
    }

    //Helper method for the find method
    private Person findRecursive(Person current, String name) {
        if (current.getName().hashCode() == name.hashCode()) {
            return current;
        }

        if (name.hashCode() < current.getName().hashCode()) {
            return findRecursive(current.getLeft(), name);
        } else if (name.hashCode() > current.getName().hashCode()) {
            return findRecursive(current.getRight(), name);
        }
        return current;
    }

    //Method that returns the parent of a person. Returns root if node is root
    private Person getParent(Person root, Person child) {
        //Check to see if root is the parent
        if (root.getLeft() == child || root.getRight() == child) {
            return root;
        } else if (child.getName().hashCode() < root.getName().hashCode()) {
            return getParent(root.getLeft(), child);
        } else if (child.getName().hashCode() > root.getName().hashCode()) {
            return getParent(root.getRight(), child);
        } else return root;
    }

    //Method that finds the next empty left node in a tree
    private Person getNextEmptyLeft(Person current) {
        if (current.getLeft() != null) {
            current = getNextEmptyLeft(current.getLeft());
        }



        return current;
    }

    //Method that writes people to a file in preOrder
    private void preOrderWrite(Person person) {
        if (person == null) {
            return;
        }

        //print first node
        writeToFile(person);

        preOrderWrite(person.getLeft());

        preOrderWrite(person.getRight());

    }

    //Method that empties a file
    private void emptyFile() {
        File file = new File("tree.thomas");
        try {
            PrintWriter output = new PrintWriter(file);
            output.close();
        } catch (FileNotFoundException e) {
            //No need to empty a file if it doesnt exist
        }

    }

    //Helper method for preOrderWrite
    private boolean writeToFile(Person toWrite) {
        //create a file
        File file = new File("tree.thomas");
        //Attempt to write data to file
        try (PrintWriter output = new PrintWriter((new FileWriter(file, true)))) {
            //write the file as name:number
            output.println(toWrite.getNumber() + ":" + toWrite.getName());
            return true;
        } catch (Exception e) {
            return false;
        }


    }


}
