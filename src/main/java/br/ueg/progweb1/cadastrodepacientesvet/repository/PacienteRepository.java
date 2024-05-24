package br.ueg.progweb1.cadastrodepacientesvet.repository;

import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository
        extends JpaRepository<Paciente, Long> {
}
