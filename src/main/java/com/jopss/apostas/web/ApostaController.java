package com.jopss.apostas.web;

import com.jopss.apostas.excecoes.ApostasException;
import com.jopss.apostas.modelos.Aposta;
import com.jopss.apostas.web.form.ApostaForm;
import com.jopss.apostas.web.form.Resposta;
import com.jopss.apostas.web.util.ApostasController;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApostaController extends ApostasController {

        @RequestMapping(value = "/aposta/", method = RequestMethod.GET)
	public String abrir() {
		return "aposta/cadastro";
	}
        
        @ResponseBody
        @RequestMapping(value = "/aposta/{id}", method = RequestMethod.DELETE)
	public Resposta deletar(@PathVariable String id, HttpServletResponse resp) {
                Resposta resposta = new Resposta();
                try {
                        new Aposta(id).remover();
                        resposta.setMensagemSucesso("aposta.sucesso.deletar", resp);
                        
                } catch (TransactionSystemException ex) {
                        log.error(ex);
                        resposta.addErros(ex, resp);
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex, resp);
                }
                return resposta;
	}
        
        @ResponseBody
        @RequestMapping(value = "/aposta/{id}", method = RequestMethod.GET)
	public Resposta editar(@PathVariable String id, HttpServletResponse resp) {
                Resposta resposta = new Resposta();
                try {
                        resposta.setModelo(new Aposta(id).buscarPorId(), resp);
                } catch (TransactionSystemException ex) {
                        log.error(ex);
                        resposta.addErros(ex, resp);
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex, resp);
                }
                return resposta;
	}
        
        @ResponseBody
        @RequestMapping(value = "/aposta/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Resposta salvar(@RequestBody Aposta aposta, HttpServletResponse resp) {
                Resposta resposta = new Resposta();
                try {
                        aposta = aposta.salvar();
                        resposta.setModelo(aposta, resp, "aposta.sucesso");
                        
                } catch (TransactionSystemException ex) {
                        log.error(ex);
                        resposta.addErros(ex, resp);
                }catch( ApostasException ex){
                        log.error(ex);
                        resposta.addErro(ex.getMessage(), resp);
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex, resp);
                }
                return resposta;
        }
        
        @ResponseBody
        @RequestMapping(value = "/apostas/pagina", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resposta buscarPorPagina(@RequestBody ApostaForm form, HttpServletResponse resp) {
                Resposta resposta = new Resposta();
                try{
                        resposta.setLista((new Aposta()).buscarRegistroPaginado(form), resp);
                        resposta.setForm(form);
                }catch( ApostasException ex){
                        log.error(ex);
                        resposta.addErro(ex.getMessage(), resp);
                } catch (Exception ex) {
                        log.error(ex);
                        resposta.addErroGenerico(ex, resp);
                }
                return resposta;
	}

}
