package com.exercicio.folha.controllers;

import com.exercicio.folha.models.EmpregadosModel;
import com.exercicio.folha.models.EmpregadosPK;
import com.exercicio.folha.models.EmpresasModel;
import com.exercicio.folha.repositories.EmpregadosRepository;
import com.exercicio.folha.services.EmpregadosService;
import com.exercicio.folha.services.EmpresasService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empregados")
public class EmpregadosController {
    @Autowired
    private EmpregadosService servicoEmpregados;

    @Autowired
    private EmpregadosRepository empregadosEmpresa;

    @Autowired
    private EmpresasService servicoEmpresas;

    @GetMapping (value = "/todos/{empresaID}")
    public ResponseEntity <List<EmpregadosModel>> buscaTodosEmpregados(@PathVariable int empresaID) {
        return new ResponseEntity<>(empregadosEmpresa.buscaTodos(empresaID), HttpStatus.OK);
    }

    @GetMapping (value = "/especifico")
    public ResponseEntity <Object> buscaEmpregado (@RequestBody @NotNull EmpregadosPK empregadoID) {
        if (servicoEmpregados.existe(empregadoID))
            return new ResponseEntity<>(servicoEmpregados.buscaUm(empregadoID),HttpStatus.OK);
        else
            return new ResponseEntity<>("Empregado inválido nesta empresa.",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity <Object> incluiEmpregado (@Validated @RequestBody EmpregadosModel empregado) {
        // verifica se a empresa existe antes de incluir o novo empregado
        EmpregadosPK empregadoPK = new EmpregadosPK();
        empregadoPK.setCodigoEmpresa(empregado.getEmpregadoID().getCodigoEmpresa());
        EmpresasModel empresaID = new EmpresasModel();
        empresaID = empregadoPK.getCodigoEmpresa();
        if (servicoEmpresas.existe(empresaID.getCodigoEmpresa())){
            servicoEmpregados.inclui(empregado);
            return new ResponseEntity<>("Empregado incluído com sucesso.", HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Empresa não cadastrada.",HttpStatus.NOT_FOUND);
    }
}
