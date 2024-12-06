package it.uniroma2.sc.demospringhibernate.config;

import it.uniroma2.sc.demospringhibernate.control.CreationAndRetrievalController;
import it.uniroma2.sc.demospringhibernate.enums.Role;
import it.uniroma2.sc.demospringhibernate.secutiry.TokenStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component()
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Autowired
    CreationAndRetrievalController creationAndRetrievalController;

    @Autowired
    TokenStorage tokenStorage;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        creationAndRetrievalController.createSampleData();

//        String token = tokenStorage.generateToken();
        String token = "VetToken";
        tokenStorage.storeToken(token, Role.VETERINARIO);

        token = "ClientToken";
        tokenStorage.storeToken(token, Role.CLIENTE);

        //List<Impresa> impreseConCf = impresaDAO.findByCodiceFiscale("13931671005");

        //List<Impresa> impreseConCfERS = impresaDAO.findByCodiceFiscaleAndRagioneSociale("13931671005", "LH1");

        /*ImpresaDTO impresaDto = new ImpresaDTO();
        impresaDto.setCodiceFiscale("CF1");
        impresaDto.setRagioneSociale("RS1");
        impresaDto.setContratto(new ContrattoDTO("COM"));

        try {
            impresaService.creaImpresa(impresaDto);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*Contratto contratto1 = new Contratto("COM", "Contratto del commercio");
        contratto1 = contrattoDAO.save(contratto1);
        Impresa newImpresa1 = new Impresa("13931671005", "LH", contratto1);
        impresaDAO.save(newImpresa1);*/
    }

}