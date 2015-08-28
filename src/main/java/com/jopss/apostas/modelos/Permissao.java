package com.jopss.apostas.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.apostas.modelos.enums.RoleEnum;
import com.jopss.apostas.util.Modelos;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Permissao extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	private String id;
        
        private String nome;
        private String descricao;
        private RoleEnum papel;

        @JsonIgnore
        @DBRef
        private Permissao permissaoPai;

        @Override
        public String getId() {
                return id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public Permissao getPermissaoPai() {
                return permissaoPai;
        }

        public void setPermissaoPai(Permissao permissaoPai) {
                this.permissaoPai = permissaoPai;
        }

        public RoleEnum getPapel() {
                return papel;
        }

        public void setPapel(RoleEnum papel) {
                this.papel = papel;
        }
        
}
