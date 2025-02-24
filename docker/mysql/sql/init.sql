create schema `global-order`;

create table sales_order_kr
(
    id bigint            not null comment 'IDX' primary key,
    user_id        bigint            not null comment '주문자 회원 ID',
    order_number      varchar(50)       null comment '주문 ID(로컬 국가)',
    payment_datetime      datetime          null comment '결제일',
    created_datetime      datetime          not null comment '등록일',
    modified_datetime     datetime          not null comment '수정일',
    version               bigint            not null comment 'DATA VERSION'
);

create table sales_order_master
(
    id bigint            not null comment 'IDX' primary key,
    region_code           varchar(10) comment '국가 코드'      null,
    user_id        bigint            not null comment '주문자 회원 ID',
    sales_order_id        bigint            not null comment '주문 IDX(로컬 국가)',
    order_number      varchar(50)       null comment '주문 ID(로컬 국가)',
    payment_datetime      datetime          null comment '결제일',
    created_datetime      datetime          not null comment '등록일',
    modified_datetime     datetime          not null comment '수정일',
    version               bigint            not null comment 'DATA VERSION'
);