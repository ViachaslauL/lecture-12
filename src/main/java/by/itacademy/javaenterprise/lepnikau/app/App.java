package by.itacademy.javaenterprise.lepnikau.app;

import by.itacademy.javaenterprise.lepnikau.app.dao.MarkDAO;
import by.itacademy.javaenterprise.lepnikau.app.entity.Mark;
import by.itacademy.javaenterprise.lepnikau.app.dao.implement.MarkDAOImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring/context.xml");
        MarkDAO markDAO = context.getBean(MarkDAOImpl.class);

        getAllPageByPageTest(markDAO, 5, 0);
    }

    private static void getAllPageByPageTest(MarkDAO dao, int limit, int offset) {

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
