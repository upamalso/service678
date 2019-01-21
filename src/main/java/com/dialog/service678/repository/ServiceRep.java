package com.dialog.service678.repository;

import com.dialog.service678.dto.ServiceFormDto;
import com.dialog.service678.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRep extends JpaRepository<Service, Long> {

}
