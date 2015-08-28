package com.jopss.apostas.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jopss.apostas.servicos.repositorio.UsuarioRepository;
import com.jopss.apostas.util.FormatterAndValues;
import com.jopss.apostas.util.Modelos;
import java.util.List;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Usuario extends Modelos {
        
        private static final long serialVersionUID = 8765060059417187982L;

	@Id
	private String id;
        
        private String nome;
        private String login;
        private String senha;

        @JsonIgnore //por seguranca tira os perfis das consultas.
        @DBRef
        private Perfil perfil;

        public Usuario() {
        }

        public Usuario(String id, String login) {
                this.id = id;
                this.login = login;
        }
        
        @Override
        protected UsuarioRepository getRepository(){
                return (UsuarioRepository) super.getRepository();
        }
        
        public Usuario buscarPorId() {
		return this.getRepository().findOne(id);
	}
        
        public Usuario buscarPorLogin(){
                return this.getRepository().findByLogin(login);
        }
        
        public List<Usuario> buscarTodos(){
                return IteratorUtils.toList(this.getRepository().findAll().iterator());
        }
        
        /**
         * Regras de unicidade de login e obrigatoriedade está ou banco.
         */
        public Usuario salvar() {
                this.setSenha( FormatterAndValues.encryptMD5(this.getSenha()) );
                return this.getRepository().save(this);
        }
        
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

        public String getLogin() {
                return login;
        }

        public void setLogin(String login) {
                this.login = login;
        }

        public String getSenha() {
                return senha;
        }

        public void setSenha(String senha) {
                this.senha = senha;
        }

        public Perfil getPerfil() {
                return perfil;
        }
        
}
