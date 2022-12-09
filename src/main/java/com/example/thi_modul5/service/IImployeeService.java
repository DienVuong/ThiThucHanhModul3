package com.example.thi_modul5.service;

import com.example.thi_modul5.model.Imployee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IImployeeService extends ICoreCRUDService<Imployee, Long> {
    List<Imployee> findByNameContaining(HttpServletRequest request);
}
