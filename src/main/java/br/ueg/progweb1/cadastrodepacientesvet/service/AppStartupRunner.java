package br.ueg.progweb1.cadastrodepacientesvet.service;

import br.ueg.progweb1.cadastrodepacientesvet.SituacaoAnimal;
import br.ueg.progweb1.cadastrodepacientesvet.model.Paciente;
import br.ueg.progweb1.cadastrodepacientesvet.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppStartupRunner implements ApplicationRunner {
    public static final String CREATE_DROP="create-drop";


    private static final Logger LOG =
            LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private PacienteRepository pacienteRepository;

    public void initDados(){
        LOG.info("Inicio da execução do InitDados!");
        Paciente student = Paciente.builder()
                .namePet("Jack")
                .nameTutor("Fulano da Silva")
                .especie("Gato")
                .nameVet("Dr. João")
                .autoimmuneDisease(false)
                .statusPet(SituacaoAnimal.EM_ESPERA)
                .dateRegister(LocalDate.now().minusDays(1))
                .build();
        this.pacienteRepository.save(student);
        LOG.info("Fim da execução");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            this.initDados();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
