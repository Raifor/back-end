package br.ueg.progweb1.cadastrodepacientesvet.mapper;

import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;
import br.ueg.progweb1.cadastrodepacientesvet.model.dtos.CreatePacienteDTO;
import br.ueg.progweb1.cadastrodepacientesvet.model.dtos.PacienteDto;
import br.ueg.progweb1.cadastrodepacientesvet.model.dtos.UpdatePacienteDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VetMapper {
    public Paciente toModel(CreatePacienteDTO dto){
        Paciente s = new Paciente();
        s.setEspecie(dto.getEspecie());
        s.setNameTutor(dto.getNameTutor());
        s.setNamePet(dto.getNamePet());
        s.setNameVet(dto.getNameVet());
        s.setAutoimmuneDisease(dto.getAutoimmuneDisease());
        return s;
    }
    public Paciente toModel(UpdatePacienteDTO dto){
        Paciente s = new Paciente();
        s.setNamePet(dto.getNamePet());
        s.setEspecie(dto.getEspecie());
        s.setNameTutor(dto.getNameTutor());
        s.setNameVet(dto.getNameVet());
        s.setAutoimmuneDisease(dto.getAutoimmuneDisease());
        return s;
    }

    public PacienteDto toDto(Paciente model){
        PacienteDto dto = new PacienteDto();
        dto.setId(model.getId());
        dto.setNamePet(model.getNamePet());
        dto.setNameTutor(model.getNameTutor());
        dto.setEspecie(model.getEspecie());
        dto.setDateRegister(model.getDateRegister());
        dto.setAutoimmuneDisease(model.getAutoimmuneDisease());
        dto.setNameVet(model.getNameVet());
        dto.setStatusPet(model.getStatusPet());
        return dto;
    }

    public List<PacienteDto> toListDto(List<Paciente> model){
        List<PacienteDto> list = new ArrayList<>();
        for (Paciente s : model){
            list.add(toDto(s));
        }
        return list;
    }
}
