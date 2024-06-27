package br.ueg.progweb1.cadastrodepacientesvet.repository;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository
        extends JpaRepository<Paciente, Long> {

    List<Paciente> findAllByStatusPet(SituacaoAnimal statusPet);
}
