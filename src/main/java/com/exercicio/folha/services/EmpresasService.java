package com.exercicio.folha.services;

import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.models.EventosModel;
import com.exercicio.folha.models.EventosPK;
import com.exercicio.folha.repositories.EmpresasRepository;
import com.exercicio.folha.repositories.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmpresasService {
    @Autowired
    EmpresasRepository empresas;

    @Autowired
    EventosRepository eventos;

    public List<EmpresasModel> buscaTodos() {
        return empresas.findAll();
    }

    public EmpresasModel buscaUm (Integer empresaID){
        return empresas.findById(empresaID).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void inclui (EmpresasModel empresaNova) {
        // salva a empresa na tabela do banco de dados
        empresas.save (empresaNova);

        // inclusao dos eventos fixos na empresa criada
        EmpresasModel empresaID = new EmpresasModel();
        empresaID.setCodigoEmpresa(empresaNova.getCodigoEmpresa());
        empresaID.setNomeEmpresa(empresaNova.getNomeEmpresa());
        EventosPK eventoID = new EventosPK();
        eventoID.setCodigoEmpresa(empresaID);
        EventosModel eventoNovo = new EventosModel();

        // 1 - Horas trabalhadas
        eventoID.setCodigoEvento(1);
        eventoNovo.setEventoID(eventoID);
        eventoNovo.setDescricaoEvento("HORAS TRABALHADAS");
        eventoNovo.setTipoEvento('P');
        eventoNovo.setUnidadeEvento('1');
        eventoNovo.setCompoeLiquido('S');
        eventoNovo.setSomaBaseINSS('S');
        eventoNovo.setSomaBaseFGTS('S');
        eventoNovo.setSomaBaseIRRF('S');
        eventoNovo.setSomaBaseHoraExtra('N');
        eventoNovo.setBaseCalculo(1);
        eventoNovo.setTaxaBase(100.00);
        eventos.save(eventoNovo);

        // 150 - Horas extras
        eventoID.setCodigoEvento(150);
        eventoNovo.setEventoID(eventoID);
        eventoNovo.setDescricaoEvento("HORAS EXTRAS");
        eventoNovo.setTipoEvento('P');
        eventoNovo.setUnidadeEvento('1');
        eventoNovo.setCompoeLiquido('S');
        eventoNovo.setSomaBaseINSS('S');
        eventoNovo.setSomaBaseFGTS('S');
        eventoNovo.setSomaBaseIRRF('S');
        eventoNovo.setSomaBaseHoraExtra('N');
        eventoNovo.setBaseCalculo(5);
        eventoNovo.setTaxaBase(150.00);
        eventos.save(eventoNovo);

        // 996 - FGTS
        eventoID.setCodigoEvento(996);
        eventoNovo.setEventoID(eventoID);
        eventoNovo.setDescricaoEvento("FGTS");
        eventoNovo.setTipoEvento('P');
        eventoNovo.setUnidadeEvento('2');
        eventoNovo.setCompoeLiquido('N');
        eventoNovo.setSomaBaseINSS('N');
        eventoNovo.setSomaBaseFGTS('N');
        eventoNovo.setSomaBaseIRRF('N');
        eventoNovo.setSomaBaseHoraExtra('N');
        eventoNovo.setBaseCalculo(3);
        eventoNovo.setTaxaBase(8.00);
        eventos.save(eventoNovo);

        // 998 - INSS
        eventoID.setCodigoEvento(998);
        eventoNovo.setEventoID(eventoID);
        eventoNovo.setDescricaoEvento("INSS");
        eventoNovo.setTipoEvento('D');
        eventoNovo.setUnidadeEvento('2');
        eventoNovo.setCompoeLiquido('S');
        eventoNovo.setSomaBaseINSS('N');
        eventoNovo.setSomaBaseFGTS('N');
        eventoNovo.setSomaBaseIRRF('S');
        eventoNovo.setSomaBaseHoraExtra('N');
        eventoNovo.setBaseCalculo(2);
        eventoNovo.setTaxaBase(100.00);
        eventos.save(eventoNovo);

        // 999 - IRRF
        eventoID.setCodigoEvento(999);
        eventoNovo.setEventoID(eventoID);
        eventoNovo.setDescricaoEvento("IRRF");
        eventoNovo.setTipoEvento('D');
        eventoNovo.setUnidadeEvento('2');
        eventoNovo.setCompoeLiquido('S');
        eventoNovo.setSomaBaseINSS('N');
        eventoNovo.setSomaBaseFGTS('N');
        eventoNovo.setSomaBaseIRRF('N');
        eventoNovo.setSomaBaseHoraExtra('N');
        eventoNovo.setBaseCalculo(4);
        eventoNovo.setTaxaBase(100.00);
        eventos.save(eventoNovo);

    }

    public boolean existe (Integer empresaID) {return empresas.existsById(empresaID);}
}
