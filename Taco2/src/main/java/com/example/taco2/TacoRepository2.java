package com.example.taco2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository2 extends CrudRepository<Taco,Long> {

}
