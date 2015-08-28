package com.jopss.apostas.web.form;

import com.jopss.apostas.util.FormatterAndValues;
import com.jopss.apostas.util.Modelos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.transaction.TransactionSystemException;

public class Resposta implements Serializable {

        private static final int HTTP_STATUS_ERROR = 500;
        private static final int HTTP_STATUS_VALIDATION = 403;
        private static final int HTTP_STATUS_SUCCESS = 200;

        private Modelos modelo;
        private List lista;
        private PaginacaoForm form;
        private List<Retorno> mensagens;

        public void setModelo(Modelos modelo, HttpServletResponse resp) {
                this.setModelo(modelo, resp, "sucesso");
        }

        public void setModelo(Modelos modelo, HttpServletResponse resp, String msg) {
                this.modelo = modelo;
                resp.setStatus(HTTP_STATUS_SUCCESS);
                getMensagens().add(new Retorno("mensagem", FormatterAndValues.getMessage(msg)));
        }

        public void setLista(List lista, HttpServletResponse resp) {
                this.setLista(lista, resp, "sucesso");
        }

        public void setLista(List lista, HttpServletResponse resp, String msg) {
                this.lista = lista;
                resp.setStatus(HTTP_STATUS_SUCCESS);
                getMensagens().add(new Retorno("mensagem", FormatterAndValues.getMessage(msg)));
        }

        public void setForm(PaginacaoForm form, HttpServletResponse resp, String msg) {
                this.form = form;
                resp.setStatus(HTTP_STATUS_SUCCESS);
                getMensagens().add(new Retorno("mensagem", FormatterAndValues.getMessage(msg)));
        }

        public void setMensagemSucesso(String msg, HttpServletResponse resp) {
                getMensagens().add(new Retorno("mensagem", FormatterAndValues.getMessage(msg)));
                resp.setStatus(HTTP_STATUS_SUCCESS);
        }

        /**
         * Adiciona qualquer mensagem de erro, alterando o Status HTTP relativo.
         * Pode tratar erros SQL nativo, como unique.
         *
         * @param ex Exception
         * @param resp HttpServletResponse
         */
        public void addErroGenerico(Exception ex, HttpServletResponse resp) {
                Throwable cause = ex.getCause();
                resp.setStatus(HTTP_STATUS_ERROR);
                getMensagens().add(new Retorno("mensagem", ex.getMessage()));
        }

        /**
         * Adiciona qualquer mensagem de validacao gerada a partir do nao
         * cumprimento de regras de negocio.
         *
         * @param str String
         * @param resp HttpServletResponse
         */
        public void addErro(String str, HttpServletResponse resp) {
                getMensagens().add(new Retorno("mensagem", FormatterAndValues.getMessage(str)));
                resp.setStatus(HTTP_STATUS_VALIDATION);
        }

        public void addErroLogin(String str) {
                getMensagens().add(new Retorno("mensagem", str));
        }

        /**
         * Adiciona qualquer mensagem de validacao de campos BeanValidator,
         * alterando o Status HTTP relativo.
         *
         * @param ex TransactionSystemException
         * @param resp HttpServletResponse
         */
        public void addErros(TransactionSystemException ex, HttpServletResponse resp) {
                this.addErroGenerico(ex, resp);
        }

        public List<Retorno> getMensagens() {
                if (mensagens == null) {
                        mensagens = new ArrayList<>();
                }
                return mensagens;
        }

        public Modelos getModelo() {
                return modelo;
        }

        public List<Modelos> getLista() {
                return lista;
        }

        public PaginacaoForm getForm() {
                return form;
        }

        public void setForm(PaginacaoForm form) {
                this.form = form;
        }

        /**
         * Indica campos ou chaves com seus respectivos valores sobre erros e
         * validacoes.
         */
        public static class Retorno implements Serializable {

                private String chave;
                private String valor;

                public Retorno(String chave, String valor) {
                        this.chave = chave;
                        this.valor = valor;
                }

                public String getChave() {
                        return chave;
                }

                public String getValor() {
                        return valor;
                }

        }
}
