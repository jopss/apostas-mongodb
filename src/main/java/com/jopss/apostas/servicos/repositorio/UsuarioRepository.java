package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
        
        Usuario findByLogin(String login);
        
}
