package by.itacademy.javaenterprise.lepnikau.app.dao;

import java.util.List;

public interface StudentDAO<S, P> {

    boolean saveStudentAndParent(S student, P parent);

    S save(S t);

    S get(int id);

    List<S> getAllPageByPage(int limit, int offset);

}
