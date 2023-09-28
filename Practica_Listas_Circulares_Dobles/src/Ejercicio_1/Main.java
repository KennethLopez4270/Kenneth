package Ejercicio_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.csvreader.CsvReader;
import java.math.BigDecimal;
import java.time.*;
import java.util.Iterator;
import java.util.Scanner;

class Nodo<T> {
    private T data;
    private Nodo<T> next;

    public Nodo(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodo<T> getNext() {
        return next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
}

class CircularList<T> implements Iterable<Student> {
    Nodo<T> head;
    Nodo<T> tail;
    int size;

    public CircularList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void insert(T data) {
        Nodo<T> newNode = new Nodo<>(data);
        if (head == null) {
            head = newNode;
            newNode.setNext(head);
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setNext(head);
            tail = newNode;
        }
        size++;
    }

    public void assignPaymentDate() {
        if (head == null) {
            return;
        }
        LocalDateTime currentDate = LocalDateTime.now();
        for (Student student : this) {
            student.setPaymentDate(currentDate);
            currentDate = currentDate.plusMonths(1);
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Student> iterator() {
        return new CircularListIterator(head);
    }
}

class CircularListIterator implements Iterator<Student> {
    private Nodo current;
    private Nodo start;
    private boolean firstIteration;

    public CircularListIterator(Nodo head) {
        this.current = head;
        this.start = head;
        this.firstIteration = true;
    }

    @Override
    public boolean hasNext() {
        return (current != null && (firstIteration || current != start));
    }

    @Override
    public Student next() {
        if (current == null) {
            return null;
        }
        Student student = (Student) current.getData();
        current = current.getNext();
        firstIteration = false;
        return student;
    }
}

public class CircularLinkedList {
    public static void main(String[] args) {
        System.out.println("PASANAKU SISTEMAS");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cuota para el pasanaku por favor: ");
        BigDecimal fee = scanner.nextBigDecimal();
        scanner.close();

        BigDecimal multipliedFee = fee.multiply(BigDecimal.TEN);

        CircularList<Student> studentList = new CircularList<>();
        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println(currentDate);
        loadFile(studentList, multipliedFee);
        studentList.assignPaymentDate();

        int numStudents = studentList.getSize();
        BigDecimal monthlyFee = multipliedFee.multiply(BigDecimal.valueOf(numStudents));
        System.out.println("A cada estudiante le corresponde " + monthlyFee + " cada mes");

        printStudents(studentList);
    }

    private static void loadFile(CircularList<Student> studentList, BigDecimal fee) {
        try {
            String file = System.getProperty("user.dir") + "\\example.csv";
            String[] line;
            CsvReader students = new CsvReader(file);
            students.readHeaders();
            while (students.readRecord()) {
                int code = Integer.parseInt(students.get("StudentCode"));
                String name = students.get("StudentName");
                studentList.insert(new Student(code, name, LocalDateTime.now(), fee));
            }
            students.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printStudents(CircularList<Student> studentList) {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}