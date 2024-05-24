package br.ueg.progweb1.cadastrodepacientesvet.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePacienteDTO {
    @Schema(description = "Nome do pet",example = "Jack")
    private String namePet;

    @Schema(description = "Nome do tutor", example = "nome do tutor")
    private String nameTutor;

    @Schema(description = "Possui doença autoimune", example = "true/false")
    private Boolean autoimmuneDisease;

    @Schema(description = "Especie do paciente", example = "Gato")
    private String especie;

    @Schema(description = "Nome do veterinario", example = "nome do veterinario")
    private String nameVet;
}
