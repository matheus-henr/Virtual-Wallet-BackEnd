package br.com.virtual_wallet.BO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.dto.MetaDTO;
import br.com.virtual_wallet.model.Meta;
import br.com.virtual_wallet.service.DespesaService;
@Service
public class MetaBO {

	
	@Autowired
	private DespesaService despesaService;
	
	
	public MetaDTO calculoMeta(Meta meta) {
		
		double totalMes = despesaService.totalDespesas();
		
		MetaDTO dto = new MetaDTO();
		dto.setMeta(meta.getMeta());
		dto.setValorTotal(totalMes);
		dto.setDiferenca(meta.getMeta() - totalMes);
		
		return dto;
	}
	
	public MetaDTO isUtrapassada(MetaDTO dto) {
		if(dto.getDiferenca() >= 0) {
			dto.setUtrapassada(false);
			return dto;
		}
		dto.setUtrapassada(true);
		return dto;
	}
	
}
