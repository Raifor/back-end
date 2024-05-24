package br.ueg.progweb1.cadastrodepacientesvet.exceptions;

import lombok.Getter;

public @Getter class VetLogicException extends RuntimeException{
    private VetLogicError error;
    public VetLogicException(String message, Throwable e){
        super(message, e);
        this.error = VetLogicError.GENERAL;
    }
    public VetLogicException(String message){
        super(message);
        this.error = VetLogicError.GENERAL;
    }


    public VetLogicException(VetLogicError be){
        super(be.getMessage());
        this.error = be;
    }
}
