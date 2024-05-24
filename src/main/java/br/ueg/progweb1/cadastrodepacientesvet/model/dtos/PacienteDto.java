package br.ueg.progweb1.cadastrodepacientesvet.model.dtos;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDto {

    private Long id;

    private String namePet;

    private String nameTutor;

    private String especie;

    private LocalDate dateRegister;

    private Boolean autoimmuneDisease;

    private String nameVet;

    private SituacaoAnimal statusPet;
}
