package com.global.order.worker.controller;

import com.global.order.client.kafka.service.KafkaProducerCluster;
import com.global.order.common.code.MessageMethodType;
import com.global.order.common.code.RegionCode;
import com.global.order.core.domain.SalesOrderKREntity;
import com.global.order.core.repository.SalesOrderKREntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Deprecated
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class KafkaTestcontroller {
    private final KafkaProducerCluster cluster;
    private final SalesOrderKREntityRepository salesOrderKREntityRepository;

    @PostMapping("/insert/single")
    public String insertOne() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("regionCode", RegionCode.KR);
        map.put("methodType", MessageMethodType.POST);
        cluster.sendMessage(map, "sales-order-local-kr");
        return "ok";
    }

    /**
     * 대용량 테스트 준비, 10만개 한국 주문 데이터 생성
     */
    @PostMapping("/prepare")
    public String prepare() {
        List<SalesOrderKREntity> entities = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            entities.add(SalesOrderKREntity.toEntity((long) i));
        }
        salesOrderKREntityRepository.saveAll(entities);
        return "ok";
    }

    /**
     * 10만개 데이터 kafka 발송
     */
    @PostMapping("/insert")
    public String insert() {
        List<SalesOrderKREntity> entities = salesOrderKREntityRepository.findAll();
        List<HashMap<String, Object>> msg = new ArrayList<>();
        for (SalesOrderKREntity entity : entities) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", entity.getId());
            map.put("regionCode", RegionCode.KR);
            map.put("methodType", MessageMethodType.POST);

            msg.add(map);
        }
        for (HashMap<String, Object> data : msg) {
            cluster.sendMessage(data, "sales-order-local-kr");
        }
        return "ok";
    }

    @PostMapping("/update")
    public String update() {
        SalesOrderKREntity entity = salesOrderKREntityRepository.findById(1L).get();
        entity.updateTest();
        salesOrderKREntityRepository.save(entity);

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("regionCode", RegionCode.KR);
        map.put("methodType", MessageMethodType.PUT);

        cluster.sendMessage(map, "sales-order-local-kr");
        return "ok";
    }
}
