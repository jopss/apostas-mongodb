package com.jopss.apostas.web.util;

import com.jopss.apostas.modelos.Usuario;
import com.jopss.apostas.servicos.security.SessionUserSupport;
import com.jopss.apostas.util.NumbersUtils;
import com.jopss.apostas.servicos.ParametrosSistema;
import com.jopss.apostas.util.ValidatorUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.PropertyValuesEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Superclasse de todo Controlador do sistema.
 */
public abstract class ApostasController {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	protected SessionUserSupport sessionUserSupport;
	
        @Autowired
        protected ParametrosSistema parametrosSistema;
        
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Usuario.class, new PropertyValuesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (ValidatorUtil.isNotNullAndNotEmpty(text)) {
					setValue(new Usuario(text, null).buscarPorId());
				} else {
					setValue(null);
				}
			}

			@Override
			public String getAsText() {
				if (getValue() == null || ((Usuario) getValue()).getId() == null) {
					return "";
				}
				return ((Usuario) getValue()).getId().toString();
			}
		});

		binder.registerCustomEditor(BigDecimal.class, new PropertyValuesEditor() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(NumbersUtils.convertStringToBigDecimal(text));
			}

			@Override
			public String getAsText() {
				return NumbersUtils.convertBigDecimalToStringFormat((BigDecimal) getValue());
			}
		});

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
	}
}
