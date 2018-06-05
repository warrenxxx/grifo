/**
 * The EmployesService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.config.ExceptionHandling.UserNotFoundException;
import innovar.io.grifo.config.models.Controller;
import innovar.io.grifo.config.models.NodeController;
import innovar.io.grifo.dto.LoginDto;
import innovar.io.grifo.dto.RequestMovementDto;
import innovar.io.grifo.dto.RequestUserDto;
import innovar.io.grifo.dto.ResponseUserDto;
import innovar.io.grifo.entity.*;
import innovar.io.grifo.repository.EmployesDao;
import innovar.io.grifo.repository.ProductDao;
import innovar.io.grifo.security.UserMetadate;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.InputStream;
import java.time.Duration;
import java.util.stream.Stream;

import static innovar.io.grifo.security.Jwt.OBJECT_USER;
import static innovar.io.grifo.security.Jwt.TO_JWT;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class EmployesService {

    @Autowired
    EmployesDao dao;

    @Autowired
    ReactiveMongoOperations reactiveMongoOperations;

    @Autowired
    ProductDao productDao;


    public Mono<ServerResponse> createEmployes(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        ObjectId idEmploye = new ObjectId();
        return request.bodyToMono(RequestUserDto.class).flatMap(
                requestUserDto -> dao.insert(requestUserDto.getEmployes().set_id(idEmploye.toString())).flatMap(
                        user -> reactiveMongoOperations.update(User.class).matching(new Query(where("_id").is(idUser))).apply(new Update().push("employes", idEmploye)).first()
                                .flatMap(
                                        e -> AppResponse.AppResponseOk()
                                )
                )
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }

    public Mono<ServerResponse> loginEmploye(ServerRequest request) {
        return request.bodyToMono(LoginDto.class).flatMap(
                loginDto -> dao.getUserByPasswordAndUserName(loginDto.getPassword(), loginDto.getUser()).flatMap(
                        employe -> AppResponse.AppResponseOk(new ResponseUserDto(
                                employe.get_id(),
                                employe.getAllName(),
                                employe.getBirthDate(),
                                employe.getEmail(),
                                employe.getUserName(),
                                TO_JWT(employe.get_id(), "employe"),
                                null
                        ))
                ).switchIfEmpty(Mono.error(new UserNotFoundException()))
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }

    public Mono<ServerResponse> newTicket(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return request.bodyToMono(RequestMovementDto.class).flatMap(
                movement -> reactiveMongoOperations.update(Employe.class).matching(new Query(where("_id").is(idUser))).apply(new Update().push("movements", new Movement(
                        new ObjectId().toString(),
                        movement.getDate(),
                        movement.getDocumentNumber(),
                        "dni",
                        Stream.of(movement.getMovementDetails()).map(
                                e -> new MovementDetail(new ObjectId(e.getIdProduct()), e.getQuantityGal(), e.getQuantitySol(), 0.0, e.getUnitaryPrice())
                        ).map(
                                e->{
                                    reactiveMongoOperations.update(Product.class)
                                            .matching(new Query(where("_id").is(e.get_idProduct())))
                                            .apply(new Update().inc("stock",e.getQuantityGal()*-1))
                                            .first().subscribe();
                                    return e;
                                }
                        ).toArray(size -> new MovementDetail[size])
                ))).first().flatMap(
                        updateResult -> AppResponse.AppResponseOk()
                )
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }


    public Mono<ServerResponse> newBill(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return request.bodyToMono(RequestMovementDto.class).flatMap(
                movement -> reactiveMongoOperations.update(Employe.class).matching(new Query(where("_id").is(idUser))).apply(new Update().push("movements", new Movement(
                        new ObjectId().toString(),
                        movement.getDate(),
                        movement.getDocumentNumber(),
                        "ruc",
                        Stream.of(movement.getMovementDetails()).map(
                                e -> new MovementDetail(new ObjectId(e.getIdProduct()), e.getQuantityGal(), e.getQuantitySol(), 0.0, e.getUnitaryPrice())
                        ).map(
                                e->{
                                    reactiveMongoOperations.update(Product.class)
                                            .matching(new Query(where("_id").is(e.get_idProduct())))
                                            .apply(new Update().inc("stock",e.getQuantityGal()*-1))
                                            .first().subscribe();
                                    return e;
                                }
                        ).toArray(size -> new MovementDetail[size])
                ))).first().flatMap(
                        updateResult -> AppResponse.AppResponseOk()
                )
        ).onErrorResume(e -> AppResponse.AppResponseError(e));
    }

    public Mono<ServerResponse> newPrint(ServerRequest request) {

        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        return request.bodyToMono(RequestMovementDto.class).map(
                movement -> {

                    try {
                        NodeController nodeController = replaceSceneContent("/sample.fxml");
                        Controller c = (Controller) nodeController.getController();
                        c.addAllItems(movement.getMovementDetails())
                                .setDtpFecha(movement.getDate())
                                .setLblDireccion(movement.getAddres())
                                .setLblNombre(movement.getName())
                                .setLblRuc(movement.getDocumentNumber());
                        print(nodeController.getNode());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    return movement;
                }
        ).flatMap(e -> AppResponse.AppResponseOk())

                .onErrorResume(e -> AppResponse.AppResponseError(e));
    }


    private NodeController replaceSceneContent(String fxml) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = getClass().getResourceAsStream(fxml);

        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(getClass().getResource(fxml));
        Node page;

        try {

            page =  loader.load(in);

        } finally {
            in.close();
        }
        return new NodeController((Initializable) loader.getController(), page);
    }

    private void print(Node node) throws InterruptedException {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean printed = job.printPage(node);
            Thread.sleep(500);
            if (printed) {
                job.endJob();
                System.out.println("imprimio");
            }
        }
    }
}
