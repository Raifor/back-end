package br.ueg.progweb1.cadastrodepacientesvet.model;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PACIENTE")
public  class Paciente {

    @Id
    @SequenceGenerator(
            name="paciente_sequence",
            sequenceName = "paciente_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "paciente_sequence"
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome",  nullable = false, length = 10)
    private String namePet;

    @Column(name = "nome_tutor",  nullable = false, length = 150)
    private String nameTutor;

    @Column(name = "especie")
    private String especie;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dateRegister;

    @Column(name = "doenca_autoimune", nullable = false)
    private Boolean autoimmuneDisease;

    @Column(name = "nome_vet", nullable = false)
    private String nameVet;

    @Column(name = "statusPet", nullable = false)
    private SituacaoAnimal statusPet;
}
