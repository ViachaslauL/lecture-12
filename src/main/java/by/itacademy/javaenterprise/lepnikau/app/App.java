package by.itacademy.javaenterprise.lepnikau.app;

import by.itacademy.javaenterprise.lepnikau.app.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.StudentDAO;
import by.itacademy.javaenterprise.lepnikau.app.dao.implement.StudentDAOImpl;
import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.app.dao.implement.MarkDAOImpl;
import by.itacademy.javaenterprise.lepnikau.app.entity.Parent;
import by.itacademy.javaenterprise.lepnikau.app.entity.Student;

import java.util.List;

public class App {

    public static void main(String[] args) {
        /*Mark mark = new Mark();
        mark.setStudentId(10);
        mark.setMark(7);
        mark.setSubjectId(10);
        mark.setDate(Date.valueOf(LocalDate.now()));*/

        StudentDAO<Student, Parent> studentDAO = new StudentDAOImpl();

        Student student = new Student();
        student.setLastName("Pupkin");
        student.setFirstName("Vasia");
        student.setPatronymic("Alecsandrovich");
        student.setClassId(1);

        Parent parent = new Parent();
        parent.setLastName("Pupkin");
        parent.setFirstName("Alecsander");
        parent.setPatronymic("Ivanovich");

        System.out.println(studentDAO.saveStudentAndParent(student, parent));

        //MarkDAO<Mark> dao = new MarkDAOImpl();

        /*System.out.println(dao.get(23));*/

        //getAllPageByPageTest(dao, 5, 0);
    }

    private static void getAllPageByPageTest(MarkDAO<Mark> dao, int limit, int offset) {

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
