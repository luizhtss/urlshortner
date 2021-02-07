package com.luizhtss.urlshortner.repository;

import com.luizhtss.urlshortner.models.URL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface URLRepository extends CrudRepository<URL, String> {

    URL findByEncurtador(String encurtador);



}
