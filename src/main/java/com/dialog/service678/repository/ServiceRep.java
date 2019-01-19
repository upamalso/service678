package com.dialog.service678.repository;

import com.dialog.service678.entity.DService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRep extends JpaRepository<DService, Long> {
    @Query("SELECT Ser,Act,Ke,Api FROM DService Ser, Action Act, KeyWord Ke, SCApi Api")
    List<Object> findServices();
}
