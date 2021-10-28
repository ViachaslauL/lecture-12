package by.itacademy.javaenterprise.lepnikau.app.dao;

import java.util.List;

public interface MarkDAO<M> {

    M save(M t);

    M get(int id);

    List<M> getAllPageByPage(int limit, int offset);

}
