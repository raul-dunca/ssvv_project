package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;
import view.UI;

import java.time.LocalDate;

import static java.lang.Integer.MAX_VALUE;

/**
 * Unit test for simple App.
 */
public class IntegrationTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public IntegrationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( IntegrationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }


    public void testrunallTests() {
        testaddTemaIncorrectPrimire();
        testaddNotWrongGrade();
        testaddStudentGood();
}

    public void testaddStudentandTema() {
        testaddTemaIncorrectPrimire();
        testaddStudentGood();
    }


    public void testaddTemaIncorrectPrimire(){


        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        Tema tema= new Tema("4","cool",5,0) ;

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);


        try {
            service.addTema(tema);
            fail("Expected ValidationException for negative group");
        } catch (ValidationException e) {
            assertEquals("Saptamana primirii trebuie sa fie intre 1-14.", e.getMessage());
        }

    }


    public void testaddNotWrongGrade(){


        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        Nota nota= new Nota("4","1","1",-1, LocalDate.of(2022, 4, 15));

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);


        try {
            service.addNota(nota,"cool");
            fail("Expected ValidationException for negative group");
        } catch (ValidationException e) {
            assertEquals("Valoarea notei nu este corecta!", e.getMessage());
        }

    }

    public void testaddStudentGood(){


        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        Student s= new Student("103","Lory",931,"lory@gmail.com") ;

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);



        Student st1=service.addStudent(s);

        assertEquals(st1.getID(), s.getID());
        assertEquals(st1.getEmail(), s.getEmail());
        assertEquals(st1.getNume(), s.getNume());
        assertEquals(st1.getGrupa(), s.getGrupa());
    }


}
