package by.itacademy.javaenterprise.lepnikau.app.dao;

import by.itacademy.javaenterprise.lepnikau.app.entity.Student;
import by.itacademy.javaenterprise.lepnikau.app.connection.DSCreator;
import by.itacademy.javaenterprise.lepnikau.app.sql.StudentSQLRequests;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentDAO.class.getSimpleName());

    @Override
    public Student save(Student student) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.INSERT);

            stmt.setString(1, student.getLastName());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getPatronymic());
            stmt.setInt(4, student.getClassId());
            if (stmt.executeUpdate() > 0) {
                return student;
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Student get(int id) {
        Connection connection = null;
        PreparedStatement stmt = null;
        Student student;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.SELECT_BY_ID);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setLastName(rs.getString("last_name"));
                student.setFirstName(rs.getString("first_name"));
                student.setPatronymic(rs.getString("patronymic"));
                student.setClassId(rs.getInt("class_id"));
                return student;
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Student> getAllPageByPage(int limit, int offset) {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DSCreator.getDataSource().getConnection();
            stmt = connection.prepareStatement(StudentSQLRequests.SELECT_ALL);

            ResultSet rs = stmt.executeQuery();
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setLastName(rs.getString("last_name"));
                student.setFirstName(rs.getString("first_name"));
                student.setPatronymic(rs.getString("patronymic"));
                student.setClassId(rs.getInt("class_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            log.error(e.toString());
        } finally {
            DAOServant.closePrepareStatement(stmt);
            DAOServant.closeConnection(connection);
        }
        return students;
    }
}
