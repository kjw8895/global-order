### MSA 구조에서 대용량 데이터 처리를 구현

#### 시나리오 : 한국, 미국, 홍콩 등 여러 국가에서 발생한 주문 데이터를 MASTER 테이블로 집계
#### 구조 : 
- order-worker : kafka consumer 로 메세지를 읽고 처리하는 모듈
- order-core : domain, repository 관련 모듈
- order-common : 공통 유틸, 로직 관련 모듈
- order-client : kafka, s3, web-client 관련 모듈
- order-api : api 관련 모듈
- order-batch : 배치 관련 모듈

#### 시스템 구성도 : 
![image](https://github.com/user-attachments/assets/a0d16e8c-5a66-4206-94bb-da5cfa4a383f)
