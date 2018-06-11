/**
 * The EmployesService class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :26/05/2018
 */
package innovar.io.grifo.service;

import innovar.io.grifo.config.AppResponse;
import innovar.io.grifo.config.ExceptionHandling.ObjetcNotFoundException;
import innovar.io.grifo.config.ExceptionHandling.UserNotFoundException;
//import innovar.io.grifo.config.models.Controller;
//import innovar.io.grifo.config.models.NodeController;
import innovar.io.grifo.dto.*;
import innovar.io.grifo.entity.*;
import innovar.io.grifo.repository.EmployesDao;
import innovar.io.grifo.repository.ProductDao;
import innovar.io.grifo.security.UserMetadate;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.fxml.JavaFXBuilderFactory;
//import javafx.print.PrinterJob;
//import javafx.scene.Node;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
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

        ObjectId id=new ObjectId();
        return
                getNumOFBill().flatMap(
                        total ->
                                request.bodyToMono(RequestMovementDto.class).flatMap(
                                        movement ->

                                            reactiveMongoOperations.update(Employe.class).matching(new Query(where("_id").is(idUser))).apply(new Update().push("movements", new Movement(
                                                    id.toString(),
                                                    movement.getDate(),
                                                    movement.getDocumentNumber(),
                                                    "dni",
                                                    Stream.of(movement.getMovementDetails()).map(
                                                            e -> new MovementDetail(new ObjectId(e.getIdProduct()), e.getQuantityGal(), e.getQuantitySol(), 0.0, e.getUnitaryPrice()))
                                                            .map(
                                                                    e -> {

                                                                        reactiveMongoOperations.update(Product.class)
                                                                                .matching(new Query(where("_id").is(e.get_idProduct())))
                                                                                .apply(new Update().inc("stock", e.getQuantityGal() * -1))
                                                                                .first().subscribe();
                                                                        return e;
                                                                    }
                                                            )
                                                            .toArray(size -> new MovementDetail[size]),
                                                    new NumberOfPrint[]{
                                                            new NumberOfPrint(total + 1, true)
                                                    },movement.getName(),
                                                    movement.getAddres()
                                            ))).first().flatMap(
                                                    updateResult -> AppResponse.AppResponseOk(id.toString())
                                            )

                                )
                ).onErrorResume(AppResponse::AppResponseError);
    }


    public Mono<ServerResponse> newBill(ServerRequest request) {
        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
        ObjectId id=new ObjectId();
        return getNumOFBill().flatMap(
                total -> request.bodyToMono(RequestMovementDto.class)

                        .flatMap(
                        movement -> reactiveMongoOperations.update(Employe.class).matching(new Query(where("_id").is(idUser))).apply(new Update().push("movements", new Movement(
                                id.toString(),
                                movement.getDate(),
                                movement.getDocumentNumber(),
                                "ruc",
                                Stream.of(movement.getMovementDetails()).map(
                                        e -> new MovementDetail(new ObjectId(e.getIdProduct()), e.getQuantityGal(), e.getQuantitySol(), 0.0, e.getUnitaryPrice())
                                ).map(
                                        e -> {
                                            reactiveMongoOperations.update(Product.class)
                                                    .matching(new Query(where("_id").is(e.get_idProduct())))
                                                    .apply(new Update().inc("stock", e.getQuantityGal() * -1))
                                                    .first().subscribe();
                                            return e;
                                        }
                                ).toArray(size -> new MovementDetail[size]),
                                new NumberOfPrint[]{
                                        new NumberOfPrint(total + 1, true)
                                },movement.getName(),movement.getAddres()
                        ))).first().flatMap(
                                updateResult -> AppResponse.AppResponseOk(id.toString())
                        ))
        ).onErrorResume(AppResponse::AppResponseError);
    }

//    public Mono<ServerResponse> newPrint(ServerRequest request) {
//
//        ObjectId idUser = ((UserMetadate) request.attributes().get(OBJECT_USER)).getId();
//
//        return getDocumentById(request.pathVariable("id")).map(
//                movement -> {
//                    try {
//                        NodeController nodeController = replaceSceneContent("/sample.fxml");
//                        Controller c = (Controller) nodeController.getController();
//                        c.addAllItems(movement.getMovementDetails())
//                                .setDtpFecha(movement.getDate())
//                                .setLblDireccion("addres")
//                                .setLblNombre(movement.getName())
//                                .setLblRuc(movement.getNumber()[movement.getNumber().length-1].getNumber().toString());
//                        print(nodeController.getNode());
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//
//                    return movement;
//                }
//        ).flatMap(e -> AppResponse.AppResponseOk())
//
//                .onErrorResume(e -> AppResponse.AppResponseError(e));
//    }
//
//
//    private NodeController replaceSceneContent(String fxml) throws Exception {
//
//        FXMLLoader loader = new FXMLLoader();
//        InputStream in = getClass().getResourceAsStream(fxml);
//
//        loader.setBuilderFactory(new JavaFXBuilderFactory());
//        loader.setLocation(getClass().getResource(fxml));
//        Node page;
//
//        try {
//
//            page = loader.load(in);
//
//        } finally {
//            in.close();
//        }
//        return new NodeController((Initializable) loader.getController(), page);
//    }
//
//    private void print(Node node) throws InterruptedException {
//        PrinterJob job = PrinterJob.createPrinterJob();
//        if (job != null) {
//            boolean printed = job.printPage(node);
//            Thread.sleep(500);
//            if (printed) {
//                job.endJob();
//                System.out.println("imprimio");
//            }
//        }
//    }
    public Mono<ServerResponse> getAllTickets(ServerRequest request) {
        return findAllTickets().map(
                e->new ResponseMovementDto(e.getId().toString(),e.getDate(),e.getName(),e.getNumberOfDocument(),e.getNumber()[e.getNumber().length-1].getNumber())
        ).collectList().flatMap(AppResponse::AppResponseOk);
    }



    public Mono<ServerResponse> getAllBills(ServerRequest request) {
        return findAllBills().map(
                e->new ResponseMovementDto(e.getId().toString(),e.getDate(),e.getName(),e.getNumberOfDocument(),e.getNumber()[e.getNumber().length-1].getNumber())
        ).collectList().flatMap(
                AppResponse::AppResponseOk
        );
    }
    public Mono<ServerResponse> anulateBill(ServerRequest request) {
        return anulate(request);
    }
    public Mono<ServerResponse> getTicketById(ServerRequest request) {
        System.out.println(request.pathVariable("id"));
        return getDocumentById(request.pathVariable("id")).flatMap(
                e->AppResponse.AppResponseOk(e.getMovementDetails()[0].setIdToString())
        ).switchIfEmpty(Mono.error(new UserNotFoundException()));
    }

    public Mono<ServerResponse> getBillById(ServerRequest request) {
        return getDocumentById(request.pathVariable("id")).flatMap(
                e->AppResponse.AppResponseOk(e.getMovementDetails()[0].setIdToString())
        ).switchIfEmpty(Mono.error(new UserNotFoundException()));
    }


    public Mono<Long> getNumOFBill() {
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("movements"),
                Aggregation.replaceRoot("movements"),
                Aggregation.unwind("movementDetails"),
                Aggregation.replaceRoot("movementDetails"),
                Aggregation.count().as("total")
        ), "employe", NumOfBill.class).map(e -> e.getTotal()).publishNext().switchIfEmpty(Mono.just(0L));
    }

    public Mono<Movement> getDocumentById(String id){
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("movements"),
                Aggregation.replaceRoot("movements"),
                Aggregation.match(where("_id").is(new ObjectId(id)))
        ),"employe",Movement.class).publishNext();
    }
    public Mono<Movement> getAllDocumentById(String id){
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("movements"),
                Aggregation.replaceRoot("movements"),
                Aggregation.match(where("_id").is(new ObjectId(id)))
        ),"employe",Movement.class).publishNext();
    }

    public Flux<Movement> findAllBills(){
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("movements"),
                Aggregation.replaceRoot("movements"),
                Aggregation.match(where("typeOfDocument").is("ruc"))
        ),"employe",Movement.class);
    }
    public Flux<Movement> findAllTickets(){
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                Aggregation.unwind("movements"),
                Aggregation.replaceRoot("movements"),
                Aggregation.match(where("typeOfDocument").is("dni"))
        ),"employe",Movement.class);
    }

    public Mono<ServerResponse>anulate(ServerRequest request){
        String idEmploye=((UserMetadate) request.attributes().get(OBJECT_USER)).getId().toString();
        String idDocument=request.pathVariable("id");


        return getNumOFBill().flatMap(
                lastNum->
          getNumOfDocument(idEmploye,idDocument).flatMap(
                numDoc->reactiveMongoOperations.update(Employe.class)
                        .matching(new Query( where("_id")
                                .is(new ObjectId(idEmploye))))
                        .apply(new Update().push("movements.0.number",new NumberOfPrint(lastNum,true)))
                        .first()
                        .flatMap(e->AppResponse.AppResponseOk())
        ));
    }
    public Mono<Long>getNumOfDocument(String idEmploye,String idDocument){
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
            Aggregation.match(where("_id").is(new ObjectId(idEmploye))),
                Aggregation.project().and(ArrayOperators.IndexOfArray.arrayOf("movements._id").indexOf(new ObjectId(idDocument))).as("total")
        ),"employe",NumOfBill.class).map(e->e.getTotal()).publishNext();
    }


    public Mono<ServerResponse> anulateTicket(ServerRequest request) {
        return anulate(request);
    }

    public Mono<ServerResponse> getfullbyid(ServerRequest request) {
        return getDocumentById(request.pathVariable("id"))
                .map(
                        e->{
                            e.getMovementDetails()[0].setIdToString();
                                    return e;
                        }
                )
                .flatMap(
                e->AppResponse.AppResponseOk(e)
        ).switchIfEmpty(Mono.error(new UserNotFoundException()));
    }
}

@Data
class NumOfBill {
    Long total;
}