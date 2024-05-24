package br.ueg.progweb1.cadastrodepacientesvet.service.impl;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import br.ueg.progweb1.cadastrodepacientesvet.exceptions.DataException;
import br.ueg.progweb1.cadastrodepacientesvet.exceptions.VetLogicError;
import br.ueg.progweb1.cadastrodepacientesvet.exceptions.VetLogicException;
import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;
import br.ueg.progweb1.cadastrodepacientesvet.repository.PacienteRepository;
import br.ueg.progweb1.cadastrodepacientesvet.service.PacienteService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;
    public List<Paciente> listAll(){

        return repository.findAll();
    }

    @Override
    public Paciente create(Paciente dado) {
        prepareToCreate(dado);
        //validateMandatoryFields(dado);
        validateBusinessLogic(dado);
        validateBusinessLogicForInsert(dado);
        return repository.save(dado);
    }

    private void prepareToCreate(Paciente dado) {
        dado.setId(null);
        dado.setDateRegister(LocalDate.now());
        dado.setStatusPet(SituacaoAnimal.EM_ESPERA);
    }

    @Override
    public Paciente update(Paciente dataToUpdate){
        var dataDB = validateIdPacienteExists(dataToUpdate.getId());
        //validateMandatoryFields(dataToUpdate);
        validateBusinessLogic(dataToUpdate);
        validateBusinessLogicForUpdate(dataToUpdate);
        updateDataDBFromUpdate(dataToUpdate, dataDB);
        return repository.save(dataDB);
    }

    private void updateDataDBFromUpdate(Paciente dataToUpdate, Paciente dataDB) {
        dataDB.setNameTutor(dataToUpdate.getNameTutor());
        dataDB.setEspecie(dataToUpdate.getEspecie());
        dataDB.setNamePet(dataToUpdate.getNamePet());
        dataDB.setNameVet(dataToUpdate.getNameVet());
        dataDB.setAutoimmuneDisease(dataToUpdate.getAutoimmuneDisease());
    }

    public Paciente getById(Long id){
        return this.validateIdPacienteExists(id);
    }

    @Override
    public void remove(Long id) {
        var dataDB = validateIdPacienteExists(id);
        repository.delete(dataDB);
    }

    @Override
    public void updateStatus(Long id, SituacaoAnimal situacaoAnimal) {
        var dataDB = validateIdPacienteExists(id);
        dataDB.setStatusPet(situacaoAnimal);
        repository.save(dataDB);
    }

    private Paciente validateIdPacienteExists(Long id){
        boolean valid = true;
        Paciente dadoBD = null;

        if(Objects.nonNull(id)) {
            dadoBD = this.internalGetById(id);
            if (dadoBD == null) {
                valid = false;
            }
        }else{
            valid = false;
        }

        if(Boolean.FALSE.equals(valid)){
            throw new DataException("Paciente não existe");
        }
        return dadoBD;
    }

    private Paciente internalGetById(Long id){
        Optional<Paciente> byId = repository.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    private void validateBusinessLogicForInsert(Paciente dado) {
        if(Strings.isEmpty(dado.getNamePet()) ){
            throw new VetLogicException(VetLogicError.MANDATORY_FIELD_NOT_FOUND);
        }
        /*Optional<Paciente> byRegisterNumber = repository.findByRegisterNumber(dado.getNamePet());
        if(byRegisterNumber.isPresent()){
            throw new VetLogicException(VetLogicError.REGISTER_NUMBER_DUPLICATED);
        }*/

    }

    private void validateBusinessLogicForUpdate(Paciente dado) {
        if(dado.getId() <= 0L ){
            throw new VetLogicException("Id Inválido");
        }
    }

    private void validateBusinessLogic(Paciente dado) {

    }


}
