package br.com.ufu.bsi.log4j;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Classe que gerencia o log do sistema. Usa orientação a aspecto para
 * interceptar a execução de alguns pontos do programa para tratar o log.
 */
@Component
@Aspect
public class LogManager {

	private Logger logger = Logger.getLogger(LogManager.class);

	/**
	 * Quando um mï¿½todo lanï¿½a uma exceÃ§Ã£o Ã© logado no arquivo de log do sistema,
	 * {@link #logAround(ProceedingJoinPoint)}.
	 * 
	 * @param ex
	 *            exceÃ§Ã£o lanÃ§ada.
	 */
	@AfterThrowing(
		pointcut =
			"execution(* br.com.ufu.bsi.service.*.* (..)) || " +
			"execution(* br.com.ufu.bsi.service.util.*.* (..))",
		throwing = "ex")
	public void logAfterThrowing(Exception ex) {
		logger.error(ex.getMessage(), ex);
	}
	
}
