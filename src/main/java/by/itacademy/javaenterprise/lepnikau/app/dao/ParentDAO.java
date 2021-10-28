package by.itacademy.javaenterprise.lepnikau.app.dao;

import java.util.List;

public interface ParentDAO<P> {

    P save(P t);

    P get(int id);

    List<P> getAllPageByPage(int limit, int offset);

}
