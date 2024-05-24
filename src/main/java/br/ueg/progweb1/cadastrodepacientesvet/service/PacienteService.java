package br.ueg.progweb1.cadastrodepacientesvet.service;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;

import java.util.List;

public interface PacienteService {
    List<Paciente> listAll();
    Paciente create(Paciente dado);
    Paciente update(Paciente dado);
    Paciente getById(Long id);

    void remove(Long id);

    void updateStatus(Long id, SituacaoAnimal situacaoAnimal);
}
