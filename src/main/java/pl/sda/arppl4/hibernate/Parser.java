package pl.sda.arppl4.hibernate;


import pl.sda.arppl4.hibernate.dao.StudentDao;
import pl.sda.arppl4.hibernate.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Parser {

    private StudentDao studentDao;
    private Scanner scanner;
    private Student student;


    public Parser(StudentDao studentDao, Scanner scanner, Student student) {
        this.studentDao = studentDao;
        this.scanner = scanner;
        this.student = student;
    }

    public Parser(StudentDao studentDao, Scanner scanner) {
        this.studentDao = studentDao;
        this.scanner = scanner;


    }

    private void obsluzEdytuj() {
        System.out.println("Podaj numer id Studenta");
        Long podaneId = scanner.nextLong();
        Optional<Student> szukanyStudent = studentDao.zwrocStudenta(podaneId);

        if (szukanyStudent.isPresent()) {
           Student wybranyStudent = szukanyStudent.get();
            System.out.println("Wskaż imie po modyfikacji");
            String noweImie = scanner.next();
            wybranyStudent.setName(noweImie);
            System.out.println("Imie studenta " + podaneId + " zostało zmienione na: " + noweImie);
            System.out.println("Wskaż nazwisko po modyfikacji");
            String noweNazwisko = scanner.next();
            wybranyStudent.setSurname(noweNazwisko);
            System.out.println("Nazwisko studenta " + podaneId + " zostało zmienione na: " + noweNazwisko);
            System.out.println("Wskaż nr indeksu po modyfikacji");
            String nowyIndeks = scanner.next();
            System.out.println("Indeks studenta " + podaneId + " został zmieniony na: " + nowyIndeks);
            wybranyStudent.setIndexNumber(nowyIndeks);

            studentDao.updateStudent(wybranyStudent);
        }
    }

    private void obsluzLista() {
        List<Student> listaStudentow = studentDao.zwrocListeStudentow();
        for (Student student : listaStudentow) {
            System.out.println(student);
        }
    }

    private void obsluzUsun() {
        System.out.println("Podaj numer id Studenta");
        Long podaneId = scanner.nextLong();
        Optional<Student> szukanyStudent = studentDao.zwrocStudenta(podaneId);
        if (szukanyStudent.isPresent()) {
            Student student = szukanyStudent.get();
            studentDao.usunStudenta(student);
        }
    }

    private void obsluzDodaj() {
        System.out.println("Podaj imię:");
        String imie = scanner.next();
        System.out.println("Podaj nazwisko:");
        String nazwisko = scanner.next();
        System.out.println("Podaj numer indeksu");
        String indeks = scanner.next();
        System.out.println("Podaj datę urodzenia");
        String data = scanner.next();

        Student student = new Student(null, imie, nazwisko, indeks, data);
        studentDao.dodajStudenta(student);
    }

    public void dzialaj() {
        String tekst;
        do {
            System.out.println("Wybierz komendę: dodaj/usun/lista/edytuj/quit");
            tekst = scanner.next();


            if (tekst.equalsIgnoreCase("dodaj")) {
                obsluzDodaj();
            } else if (tekst.equalsIgnoreCase("usun")) {
                obsluzUsun();
            } else if (tekst.equalsIgnoreCase("lista")) {
                obsluzLista();
            } else if (tekst.equalsIgnoreCase("edytuj")) {
                obsluzEdytuj();
            }
        } while (!tekst.equalsIgnoreCase("quit"));
    }
}

