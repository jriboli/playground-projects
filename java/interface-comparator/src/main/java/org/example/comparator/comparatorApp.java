package org.example.comparator;

import org.example.comparator.demo.ComparatorDemo;
import org.example.comparator.service.BookService;
import org.example.comparator.service.MovieService;
import org.example.comparator.service.StudentService;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class comparatorApp {
    public static void main(String[] args) {
        ComparatorDemo.sortLexiconigoly();
        //ForEachDemo.runForEach();
        //StreamDemo.runStreamsBreakdown();
        //movieService();
    }

    public static void studentService() {
        StudentService svc = new StudentService();
        svc.CreateStudents();
        svc.AddGrades();
        svc.SortByName();
        svc.SortByGrade();
        svc.SortByGradeAndName();
        svc.SortCleanedUp();
        svc.FilterByName("e");
    }

    public static void bookService() {
        BookService bSvc = new BookService();
        bSvc.setupBookService();
        bSvc.SortByAuthor();
        bSvc.SortByAuthorThenTitle();
        bSvc.SortByPriceThenAuthor();
        bSvc.FindMedianPriceByAuthor("J. K. Rowlings");
    }

    public static void movieService() {
        MovieService mSvc = new MovieService();
        mSvc.populateMovies();
        mSvc.FilterNSort("Christopher Nolan", 8.5);
        mSvc.FilterNComplexSort(2004);
    }
}