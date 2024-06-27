package br.ueg.progweb1.cadastrodepacientesvet.controller;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import br.ueg.progweb1.cadastrodepacientesvet.exceptions.DataException;
import br.ueg.progweb1.cadastrodepacientesvet.exceptions.MandatoryException;
import br.ueg.progweb1.cadastrodepacientesvet.exceptions.VetLogicException;
import br.ueg.progweb1.cadastrodepacientesvet.mapper.VetMapper;
import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;
import br.ueg.progweb1.cadastrodepacientesvet.model.dtos.CreatePacienteDTO;
import br.ueg.progweb1.cadastrodepacientesvet.model.dtos.PacienteDto;
import br.ueg.progweb1.cadastrodepacientesvet.model.dtos.UpdatePacienteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "${api.version}/paciente")
public class VetController {

    @Autowired
    private br.ueg.progweb1.cadastrodepacientesvet.service.PacienteService service;

    @Autowired
    private VetMapper mapper;

    @PostMapping
    @Operation(description = "End point para inclusão de paciente", responses = {
            @ApiResponse(responseCode = "200", description = "incluido",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public ResponseEntity<Object> create(@RequestBody CreatePacienteDTO dto) {
        Paciente pacienteSaved =  null;
        try{
            Paciente pacienteModel = mapper.toModel(dto);
            pacienteSaved = service.create(pacienteModel);
        }catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Erro:" + e.getMessage());
        }catch (VetLogicException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                    .body("Erro:"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(pacienteSaved);
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "End point para inclusão de paciente", responses = {
            @ApiResponse(responseCode = "200", description = "alterado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public ResponseEntity<Object> update(
            @RequestBody UpdatePacienteDTO dto,
            @PathVariable("id") Long id){
        Paciente pacienteSaved =  null;
        try{
            Paciente data = mapper.toModel(dto);
            data.setId(id);
            pacienteSaved = service.update(data);

        }catch (MandatoryException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body("Erro:" + e.getMessage());
        }catch (VetLogicException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                    .body("Erro:"+e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(pacienteSaved);
    }

    @GetMapping
    @Operation(description = "lista todos os pacientes", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
    })
    public ResponseEntity<List<PacienteDto>> listAll(){

        List<Paciente> pacienteList = new ArrayList<>();
        try {
            pacienteList = service.listAll();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(mapper.toListDto(pacienteList));
    }

    @GetMapping(path = "listarPorSituacao/{situacao}")
    @Operation(description = "lista todos os pacientes por situação", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral por situação",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema())),
    })
    public ResponseEntity<List<PacienteDto>> listarPorSituacao(@PathVariable SituacaoAnimal situacao){

        List<Paciente> pacienteList = new ArrayList<>();
        try {
            pacienteList = service.listByStatus(situacao);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(mapper.toListDto(pacienteList));
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "End point para obter dados de paciente", responses = {
            @ApiResponse(responseCode = "200", description = "buscar",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public ResponseEntity<Object> getById(
            @PathVariable("id") Long id
    ) {
        Paciente pacienteDB =  Paciente.builder().id(0L).build();
        try{

            pacienteDB = service.getById(id);

        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
        return ResponseEntity.ok(pacienteDB);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "End point para excluir paciente", responses = {
            @ApiResponse(responseCode = "200", description = "remover",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))})
    public ResponseEntity<Object> remove(
            @PathVariable("id") Long id
    ) {
        try {
            service.remove(id);
            return ResponseEntity.ok().build();
        } catch (DataException de) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:" + de.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:" + e.getMessage());
        }
    }

    @PutMapping(path = "status/espera/{id}")
    @Operation(description = "End point para alterar status do paciente para espera")
    public ResponseEntity<Object> setStatusEspera(
            @PathVariable("id") Long id
    ) {
        try{
            service.updateStatus(id, SituacaoAnimal.EM_ESPERA);
            return ResponseEntity.ok().build();
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
    }

    @PutMapping(path = "status/atendimento/{id}")
    @Operation(description = "End point para alterar status do paciente para atendimento")
    public ResponseEntity<Object> setStatusAtendimento(
            @PathVariable("id") Long id
    ) {
        try{
            service.updateStatus(id, SituacaoAnimal.EM_ATENDIMENTO);
            return ResponseEntity.ok().build();
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
    }

    @PutMapping(path = "status/retirada/{id}")
    @Operation(description = "End point para alterar status do paciente para retirado")
    public ResponseEntity<Object> setStatusRetirada(
            @PathVariable("id") Long id
    ) {
        try{
            service.updateStatus(id, SituacaoAnimal.RETIRADO);
            return ResponseEntity.ok().build();
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
    }

    @PutMapping(path = "status/aguardadoRetirada/{id}")
    @Operation(description = "End point para alterar status do paciente para aguardado retirado")
    public ResponseEntity<Object> setStatusAguardandoRetirada(
            @PathVariable("id") Long id
    ) {
        try{
            service.updateStatus(id, SituacaoAnimal.AGUARDANDO_RETIRADA);
            return ResponseEntity.ok().build();
        }catch (DataException de){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro de dados ocorreu. Detalhe:"+de.getMessage());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: desconhecido aconteceu:"+e.getMessage());
        }
    }

}
