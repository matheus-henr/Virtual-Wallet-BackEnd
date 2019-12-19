package br.com.virtual_wallet.BO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.virtual_wallet.model.Registro;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.service.DespesaService;
import br.com.virtual_wallet.service.ReceitaService;
import br.com.virtual_wallet.service.RegistroService;
import br.com.virtual_wallet.service.UsuarioService;

@Component
@EnableScheduling
public class RegistroBO {
	
	@Autowired
	private DespesaService despesaService;
	@Autowired
	private ReceitaService receitaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RegistroService service;
	
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	

	@Scheduled(cron = "0 0 0 1 * *", zone = TIME_ZONE)
	public void geraRegistro() {
		LocalDate date = LocalDate.now().minusMonths(1);
		
		List<Usuario> usuarios = usuarioService.findAll();
		
		for(Usuario use : usuarios){
			Registro registro = new Registro(null, converteData(date),receitaService.totalReceitas(1,date), despesaService.totalDespesas(date, 1),use.getId());
			service.save(registro);
		}
		
	}


	
	private static Date converteData(LocalDate data) {
		Date dataDate = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return dataDate;
	}
}
