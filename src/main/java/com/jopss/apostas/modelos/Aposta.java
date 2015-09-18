package com.jopss.apostas.modelos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.excecoes.DataNaoPermitidaException;
import com.jopss.apostas.servicos.repositorio.ApostaRepository;
import com.jopss.apostas.util.DateUtilsApostas;
import com.jopss.apostas.util.JsonDateDeserializer;
import com.jopss.apostas.util.JsonDateSerializer;
import com.jopss.apostas.util.Modelos;
import com.jopss.apostas.web.form.ApostaForm;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Aposta extends Modelos {

        private static final long serialVersionUID = 8765060059417187982L;

        @Id
        private String id;
        private String descricao;
        
        @JsonSerialize(using = JsonDateSerializer.class)
        @JsonDeserialize(using = JsonDateDeserializer.class)
        private Date dateFinalizacao;

        @DBRef(lazy = true)
        private List<Palpite> palpites;

        public Aposta() {
        }

        public Aposta(String id) {
                this.id = id;
        }

        @Override
        protected ApostaRepository getRepository() {
                return (ApostaRepository) super.getRepository();
        }

        public Aposta buscarPorId() {
                return this.getRepository().findOne(this.getId());
        }

        public List<Aposta> buscarRegistroPaginado(ApostaForm form) throws ApostasException {
                PageRequest pageRequest = form.getPageRequest();
                Page<Aposta> pagina;
                if (form.getDataInicial() != null && form.getDataFinal() != null) {
                        if (form.getDataInicial().after(form.getDataFinal())) {
                                throw new DataNaoPermitidaException("aposta.falha.intervalo_data_invalido");
                        }

                        pagina = this.getRepository().findByDateFinalizacaoBetween(
                                form.getDataInicial(), form.getDataFinal(), pageRequest);

                } else {
                        pagina = this.getRepository().findAll(pageRequest);
                }

                form.setTotalRegistros(pagina.getTotalElements());
                List<Aposta> ret = pagina.getContent();
                return ret;
        }

        public Aposta salvar() throws ApostasException {

                if (this.dateFinalizacao.before(DateUtilsApostas.arredondaDataZerandoHora(new Date()))) {
                        throw new DataNaoPermitidaException("aposta.falha.data_nao_permitida");
                }
                
                List<Palpite> palpites = this.getPalpites();
                this.palpites = null;
                
                Aposta aposta = this.getRepository().save(this);

                for (Palpite palpite : palpites) {
                        palpite.setAposta(aposta);
                        palpite.salvar();
                }
                aposta.palpites = palpites;
                return this.getRepository().save(aposta);
        }

        public void remover() throws ApostasException {
                Aposta aposta = this.buscarPorId();
                if (aposta.getPalpites() != null) {
                        aposta.getPalpites().clear(); //forca o cascade
                }
                this.getRepository().delete(aposta);
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

        public Date getDateFinalizacao() {
                return dateFinalizacao;
        }

        public void setDateFinalizacao(Date dateFinalizacao) {
                this.dateFinalizacao = dateFinalizacao;
        }

        public List<Palpite> getPalpites() {
                return palpites;
        }

        public void setPalpites(List<Palpite> palpites) {
                this.palpites = palpites;
        }
}
