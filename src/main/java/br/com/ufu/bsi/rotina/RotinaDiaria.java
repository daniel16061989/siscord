package br.com.ufu.bsi.rotina;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;

public class RotinaDiaria {
	
	private static Logger log = Logger.getLogger(RotinaDiaria.class);

	Timer timer;

	/**
	 * MÃ©todo para iniciar a execuÃ§Ã£o das tarefas.
	 */
	@PostConstruct
	public void iniciar() {

		timer = new Timer();
		// Executa tarefa todo dia as 6 da manha
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date time = calendar.getTime();
		timer.schedule(new RotinaAtualizarSequenciaUnidade(), time, 1 * 60 * 60 * 1000);
	}

	/**
	 * MÃ©todo para interromper a execuÃ§Ã£o das tarefas.
	 */
	@PreDestroy
	public void parar() {
		timer.cancel();
	}

	/**
	 * MÃ©todo que contÃ©m as tarefas agendadas que serÃ£o executadas.
	 */
	class RotinaAtualizarSequenciaUnidade extends TimerTask {

		public void run() {
			try {
				
				
			} catch (Exception e) {
				log.error("ERRO AO EXECUTAR ROTINAS DIÃ�RIAS.");
				log.error(e.getMessage(), e);
			}
		}
	}

}