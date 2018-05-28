/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

/**
 * The UbigeoDaoImpl class is implemment to
 *
 * @version :1.0
 * @Author :Warren
 * @since :8/05/2018
 */
package innovar.io.grifo.repository;


import innovar.io.grifo.dto.LocalizationDto;
import innovar.io.grifo.entity.Ubigeo;
import innovar.io.grifo.security.UserMetadate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.Collection;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class UbigeoDaoImpl{


    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    public Mono<Integer> insertAll(Collection<? extends Ubigeo> ubigeoCollections) {

        return reactiveMongoOperations.insertAll( ubigeoCollections).collectList().map(e->e.size());
    }


    public Flux<LocalizationDto> getDepartament() {
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                replaceRoot("properties"),
                project("c_departamento","c_ubigeo"),
                group("c_departamento")
                        .first("c_ubigeo").as("ubigeo"),
                project("_id")
                        .and("ubigeo").substring(0,2).as("ubigeo"),
                sort(Sort.Direction.ASC,"ubigeo")
        ),"ubigeo",LocalizationDto.class);
    }


    public Flux<LocalizationDto> getProvince(String departament) {
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                match(Criteria.where("properties.c_departamento").is(departament)),
                replaceRoot("properties"),
                project("c_provincia","c_ubigeo"),
                group("c_provincia")
                        .first("c_ubigeo").as("ubigeo"),
                project("_id")
                        .and("ubigeo").substring(0,2).as("ubigeo"),
                sort(Sort.Direction.ASC,"ubigeo")
        ),"ubigeo",LocalizationDto.class);
    }


    public Flux<LocalizationDto> getDistrict(String province) {
        return reactiveMongoOperations.aggregate(Aggregation.newAggregation(
                match(Criteria.where("properties.c_provincia").is(province)),
                replaceRoot("properties"),
                project("c_distrito","c_ubigeo"),
                group("c_distrito")
                        .first("c_ubigeo").as("ubigeo"),
                project("_id")
                        .and("ubigeo").substring(0,2).as("ubigeo"),
                sort(Sort.Direction.ASC,"ubigeo")
        ),"ubigeo",LocalizationDto.class);
    }


}
