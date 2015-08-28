package com.jopss.apostas.servicos.repositorio;

import java.io.Serializable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class Repositorio implements Serializable {

        protected static final Logger logger = Logger.getLogger(Repositorio.class);
        private static final long serialVersionUID = -1340481266616282366L;
        
        @Autowired
        private MongoOperations mongoTemplate;

        MongoOperations getEntityManager() {
                return mongoTemplate;
        }
        
        public Page buscarPaginado(Pageable pageRequest){
//                Query query = em.createQuery(queryGenerator.getQuery());
//                this.configurarPaginacao(query, pageRequest);
//                queryGenerator.setParameters(query);
//                return new PageImpl(query.getResultList(), pageRequest, countRegistros(queryGenerator));
                return null;
        }
                
        private void configurarPaginacao(Query query, Pageable pageRequest){
//                int inicio = pageRequest.getPageSize()*pageRequest.getPageNumber();
//                query.setFirstResult(inicio);
//                query.setMaxResults(pageRequest.getPageSize());
        }
        
        
        private Long countRegistros() {
//                Query query = getEntityManager().createQuery(queryGenerator.getCountQuery());
//                queryGenerator.setParameters(query);
//                return (Long) query.getSingleResult();
                return 0L;
        }

}
