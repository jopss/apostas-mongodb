package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.modelos.Palpite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PalpiteRepository extends MongoRepository<Palpite, String> {
}
