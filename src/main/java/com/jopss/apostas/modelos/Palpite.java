package com.jopss.apostas.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.servicos.repositorio.PalpiteRepository;
import com.jopss.apostas.util.Modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Palpite extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	private String id;
        private String descricao;
        private Boolean venceu = false;

        @DBRef
        private Usuario usuario;
        
        @JsonIgnore
        @DBRef
        private Aposta aposta;

        public Palpite() {
        }

        @Override
        protected PalpiteRepository getRepository(){
                return (PalpiteRepository) super.getRepository();
        }
        
        public Palpite buscarPorId() {
		return this.getRepository().findOne(this.getId());
	}
        
        /**
         * Regras de unicidade de login e obrigatoriedade está ou banco.
         */
        public Palpite salvar() throws ApostasException{
                return this.getRepository().save(this);
        }
        
        @Override
        public String getId() {
                return id;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public Boolean getVenceu() {
                return venceu;
        }

        public void setVenceu(Boolean venceu) {
                this.venceu = venceu;
        }

        public Usuario getUsuario() {
                return usuario;
        }

        public void setUsuario(Usuario usuario) {
                this.usuario = usuario;
        }

        public Aposta getAposta() {
                return aposta;
        }

        public void setAposta(Aposta aposta) {
                this.aposta = aposta;
        }
}
