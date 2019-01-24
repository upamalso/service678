package com.dialog.service678.repository;

import com.dialog.service678.dto.ActionFormDto;
import com.dialog.service678.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ActionRepository extends JpaRepository<Action, Long> {

}
