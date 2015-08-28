package com.jopss.apostas.servicos.repositorio;

import com.jopss.apostas.modelos.Aposta;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApostaRepository extends PagingAndSortingRepository<Aposta, String>, ApostaRepositoryCustom {

        Page<Aposta> findByDateFinalizacaoBetween(Date dataInicial, Date dataFinal, Pageable page);

        /* Solucao inicial para buscas com parametros opcionais
         * 
         * @EntityGraph(value = "palpites", type = EntityGraph.EntityGraphType.LOAD)
         * @Query("select a from Aposta a where a.dateFinalizacao BETWEEN :dataInicial AND :dataFinal AND (a.descricao LIKE %:descricao% OR :descricao is null)")
         * Page<Aposta> findByDateFinalizacaoBetweenAndDescricao(@Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal,@Param("descricao") String descricao, Pageable page);
         */
}
