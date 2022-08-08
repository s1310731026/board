package com.jasoncode.repository;

import com.jasoncode.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardResposity extends JpaRepository<Board,Integer> {
}
