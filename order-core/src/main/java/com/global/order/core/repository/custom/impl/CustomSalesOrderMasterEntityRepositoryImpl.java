package com.global.order.core.repository.custom.impl;

import com.global.order.core.annotation.CustomTsid;
import com.global.order.core.application.dto.SalesOrderMasterEntityDto;
import com.global.order.core.domain.SalesOrderMasterEntity;
import com.global.order.core.repository.custom.CustomSalesOrderMasterEntityRepository;
import com.global.order.core.utils.CustomQuerydslUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 대용량 데이터이기 때문에 jpaRepository.saveAll() 사용이 아닌 jdbcTemplate 사용으로 빠른 처리
 */
@Repository
public class CustomSalesOrderMasterEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomSalesOrderMasterEntityRepository {
    private final JdbcTemplate jdbcTemplate;

    public CustomSalesOrderMasterEntityRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(SalesOrderMasterEntity.class);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void bulkInsert(List<SalesOrderMasterEntity> entities) {
        String sql = """
                insert ignore into sales_order_master (id, user_id, region_code, sales_order_id, order_number, payment_datetime, created_datetime, modified_datetime, version) values (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        List<Object[]> batchArgs = new ArrayList<>();
        int[] args = {Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.BIGINT, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP, Types.TIMESTAMP, Types.BIGINT};

        CustomTsid.FactorySupplier instance = CustomTsid.FactorySupplier.INSTANCE;
        entities.forEach(entity -> entity.updateTsid(instance.generate()));

        for (int i = 0; i < entities.size(); i += CustomQuerydslUtils.DEFAULT_BATCH_SIZE) {
            int end = Math.min(entities.size(), i + CustomQuerydslUtils.DEFAULT_BATCH_SIZE);
            List<SalesOrderMasterEntity> batchList = entities.subList(i, end);
            for (SalesOrderMasterEntity entity : batchList) {
                batchArgs.add(new Object[] {
                        entity.getId(),
                        entity.getUserId(),
                        entity.getRegionCode(),
                        entity.getSalesOrderId(),
                        entity.getOrderNumber(),
                        entity.getPaymentDatetime(),
                        entity.getCreatedDatetime(),
                        entity.getModifiedDatetime(),
                        1L,
                });
            }
            jdbcTemplate.batchUpdate(sql, batchArgs, args);
            batchArgs.clear();
        }
    }

    @Override
    public void bulkUpdate(List<SalesOrderMasterEntityDto> dtoList) {
        String sql = """
                update sales_order_master set payment_datetime = ?, modified_datetime = ? where sales_order_id = ? and region_code = ?
                """;

        List<Object[]> batchArgs = new ArrayList<>();
        int[] args = {Types.TIMESTAMP, Types.TIMESTAMP, Types.BIGINT, Types.VARCHAR};

        for (int i = 0; i < dtoList.size(); i += CustomQuerydslUtils.DEFAULT_BATCH_SIZE) {
            int end = Math.min(dtoList.size(), i + CustomQuerydslUtils.DEFAULT_BATCH_SIZE);
            List<SalesOrderMasterEntityDto> batchList = dtoList.subList(i, end);

            for (SalesOrderMasterEntityDto dto : batchList) {
                batchArgs.add(new Object[] {
                        dto.getPaymentDatetime(),
                        dto.getModifiedDatetime(),
                        dto.getSalesOrderId(),
                        dto.getRegionCode()
                });
            }

            jdbcTemplate.batchUpdate(sql, batchArgs, args);
            batchArgs.clear();
        }
    }
}
