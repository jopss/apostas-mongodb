package com.jopss.apostas.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.apostas.util.Modelos;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Perfil extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	private String id;
        private String nome;
        private String descricao;
        
        @DBRef
        private List<Permissao> permissoes;
        
        @JsonIgnore
        @DBRef(lazy = true)
        private List<Usuario> usuarios;

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

        public List<Permissao> getPermissoes() {
                return permissoes;
        }

        public void setPermissoes(List<Permissao> permissoes) {
                this.permissoes = permissoes;
        }

        public List<Usuario> getUsuarios() {
                return usuarios;
        }

        public void setUsuarios(List<Usuario> usuarios) {
                this.usuarios = usuarios;
        }
        
}
