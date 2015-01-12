package br.com.ufu.bsi.log4j;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Classe que gerencia o log do sistema. Usa orienta��o a aspecto para
 * interceptar a execu��o de alguns pontos do programa para tratar o log.
 */
@Component
@Aspect
public class LogManager {

	private Logger logger = Logger.getLogger(LogManager.class);

	/**
	 * Quando um m�todo lan�a uma exceção é logado no arquivo de log do sistema,
	 * {@link #logAround(ProceedingJoinPoint)}.
	 * 
	 * @param ex
	 *            exceção lançada.
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
