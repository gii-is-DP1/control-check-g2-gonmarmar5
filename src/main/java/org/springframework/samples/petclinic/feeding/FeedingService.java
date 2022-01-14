package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {

    @Autowired
    FeedingRepository feedingRepository;

    public List<Feeding> getAll(){
        return this.feedingRepository.findAll();
    }

    public List<FeedingType> getAllFeedingTypes(){
        return null;
    }

    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.findFeedingTypeByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class )
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
       
        /*Implementar el método “save” del servicio de gestión de planes de alimentación. Si la mascota
        especificada en el plan de alimentación no es del tipo de mascota asociado al del tipo de alimentación
        correspondiente, se debe lanzar una excepción de tipo “UnfeasibleFeedingException” (esta clase está ya
        creada en el paquete feeding). Además, en caso de lanzarse la excepción, la transacción asociada a la
        operación de guardado del plan de alimentación debe echarse atrás (hacer rollback). */
        if(!p.getFeedingType().petType.equals(p.pet.getType())){
            throw new UnfeasibleFeedingException();
        }

        this.feedingRepository.save(p);

        return p;       
    }

    
}
