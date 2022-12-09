package com.example.thi_modul5.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICoreCRUDService<E, K> {
    List<E> findAll(HttpServletRequest request);

    E findById(HttpServletRequest request);

    boolean save(HttpServletRequest request);

    boolean deleteById(HttpServletRequest request);
}
