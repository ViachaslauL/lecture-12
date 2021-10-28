package by.itacademy.javaenterprise.lepnikau.app;

import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.app.dao.DAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.MarkDAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {
        /*Mark mark = new Mark();
        mark.setStudentId(10);
        mark.setMark(7);
        mark.setSubjectId(10);
        mark.setDate(Date.valueOf(LocalDate.now()));*/

        DAO<Mark> dao = new MarkDAO();

        /*System.out.println(dao.get(23));*/

        getAllPageByPageTest(5, 0);
    }

    private static void getAllPageByPageTest(int limit, int offset) {
        DAO<Mark> dao = new MarkDAO();

        while (true) {
            List<Mark> allPageByPage = dao.getAllPageByPage(limit, offset);
            if (!allPageByPage.isEmpty()) {
                for (Mark m : allPageByPage) {
                    System.out.println(m);
                }
                System.out.println("--Page--");
                offset += limit;
            } else {
                break;
            }
        }
    }
}
