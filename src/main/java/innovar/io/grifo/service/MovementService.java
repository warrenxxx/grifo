/**
 * The MovementService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.dto.RequestMovementDto;
import innovar.io.grifo.entity.Movement;
import innovar.io.grifo.repository.MovementDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class MovementService {
    @Autowired
    MovementDao movementDao;

//    public Mono<ServerResponse> userCreate(ServerRequest request){
//        return request.bodyToMono(RequestMovementDto.class).flatMap(
//                movement -> movementDao.insert(
//                        new Movement(
//                                new ObjectId().toString(),
//                                movement.getDate(),
//                                ,
//                                movement.getDocumentNumber(),
//                                movement.getTypeOfDocument(),
//                                movement.getIsBill(),
//                                Stream.of(movement.getMovementDetails()).map(
//                                        e->
//                                        {
//                                            System.out.println(e);
//                                            return new MovementDetail(new ObjectId(e.getIdProduct()),e.getQuantity(),0.0,e.getUnitaryPrice());
//                                        }
//                                ).toArray(size->new MovementDetail[size])
//                        )
//                        ,metadate).flatMap(
//                        created->AppResponse.AppResponseOk()
//                    )
//    }
}
