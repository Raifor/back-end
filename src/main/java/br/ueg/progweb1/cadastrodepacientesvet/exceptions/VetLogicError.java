package br.ueg.progweb1.cadastrodepacientesvet.exceptions;

import lombok.Getter;

@Getter
public enum VetLogicError {
    GENERAL(0L, "Erro desconhecido!"),
    REGISTER_NUMBER_DUPLICATED(100L, "Número de matricula duplicado"),
    NOT_FOUND(404L, "Registro não encontrado!"),
    MANDATORY_FIELD_NOT_FOUND(1L, "Campo Obrigatório não preenchido");
    private Long id;
    private String message;

    VetLogicError(Long id, String message){
        this.id = id;
        this.message = message;
    }
}
