# 3 Team Project
![rentbook](https://user-images.githubusercontent.com/89369983/131526770-d23daef7-0e18-4dff-8c56-419204bfd670.PNG)
## 도서 대여관리 시스템   

# Table of contents

- [도서 대여 관리 시스템](#---)
  - [서비스 시나리오](#서비스-시나리오)
  - [체크포인트](#체크포인트)
  - [분석/설계](#분석설계)
  - [구현:](#구현)
    - [DDD 의 적용](#ddd-의-적용)
    - [폴리글랏 퍼시스턴스](#폴리글랏-퍼시스턴스)
    - [폴리글랏 프로그래밍](#폴리글랏-프로그래밍)
    - [동기식 호출 과 Fallback 처리](#동기식-호출-과-Fallback-처리)
    - [비동기식 호출 과 Eventual Consistency](#비동기식-호출-과-Eventual-Consistency)
  - [운영](#운영)
    - [CI/CD 설정](#cicd설정)
    - [동기식 호출 / 서킷 브레이킹 / 장애격리](#동기식-호출-서킷-브레이킹-장애격리)
    - [오토스케일 아웃](#오토스케일-아웃)
    - [무정지 재배포](#무정지-재배포)


# 서비스 시나리오

기능적 요구사항

A. 대여
1. 고객이 대여를 위해 도서를 조회할수 있다 
2. 고객이 서적 대여 시 이미 대여중인 상태의 서적은 대여  할수 없다
3. 고객이 서적 대여 시 반납일은 대여일로 부터 7일 후로 자동 설정된다  
4. 고객이 서적 반납 시 해당 서적은 대여 가능한 상태로 변경 된다 
5. 고객이 반납일 보다 지연되면 고객에게 지연 카톡 알람 메세지를 보낸다

B. 도서관리
1. 도서관리자는 대여 서적 정보를 등록/삭제/수정 관리 할수 있다.  
 
 C. 고객
 1. 고객은 회원 가입할수 있다
 2. 고객은 적럽금을 예치할수 있다  
 3. 고객은 대여정보 및 적립금 현황을 마이페이지에서 확인할수 있다 


비기능적 요구사항
1. 트랜잭션
    1. 고객 적립금이 서적 대여 금액 보다 적다면 서적 대여는 불가하다  Sync 호출 
1. 장애격리
    1. 도서관리 기능이 수행되지 않더라도 서적 대여 기능은 365일 24시간 받을 수 있어야 한다  Async (event-driven), Eventual Consistency
    1. 대여시스템이 과중되면 사용자를 잠시동안 받지 않고 대여 진행을 잠시후에 하도록 유도한다  Circuit breaker, fallback
1. 성능
    1. 관리자는 매장 관리에서 대여현황을 조회 할 수 있어야 한다  CQRS
    1. 반납 지연 되면 대상 고객에게 카톡 등으로 알림을 줄 수 있어야 한다  Event driven


# 분석/설계


## AS-IS 조직 (Horizontally-Aligned)
![asis](https://user-images.githubusercontent.com/89369983/132091689-4094136c-c6fa-477e-b614-b478eed1fa0f.PNG)

## TO-BE 조직 (Vertically-Aligned)
![tobe](https://user-images.githubusercontent.com/89369983/132091699-2f0f9164-55da-49d5-a35f-dc6eba7eed19.PNG)

## Event Storming 결과
* MSAEz 로 모델링한 이벤트스토밍 결과:  http://www.msaez.io/#/storming/CWH9U9ZXJmRVhWyyz88s1h12bLz1/2d59a42bf5bfa04dfeb58737179ef00f


### 이벤트 도출
![event도출](https://user-images.githubusercontent.com/89369983/132147821-4215c854-1ad3-4403-ab5a-6507db392833.PNG)

### 부적격 이벤트 탈락
![부적격event](https://user-images.githubusercontent.com/89369983/132147826-22815443-4fe5-49a9-b6ef-e40de68135e4.PNG)


### 액터, 커맨드 부착하여 읽기 좋게
![action](https://user-images.githubusercontent.com/89369983/132147829-6eafb506-a812-467d-879d-251d62374b7e.PNG)

### 어그리게잇으로 묶기
![aggregate](https://user-images.githubusercontent.com/89369983/132147833-9472777f-5d02-42fe-9a65-a0d9e511f332.PNG)

### 바운디드 컨텍스트로 묶기
![boundedct](https://user-images.githubusercontent.com/89369983/132147838-6f1daa46-972c-4039-824b-e26ead7d9ed4.PNG)

### 폴리시 부착 (괄호는 수행주체, 폴리시 부착을 둘째단계에서 해놔도 상관 없음. 전체 연계가 초기에 드러남)
![policy](https://user-images.githubusercontent.com/89369983/132147844-a3fef6f5-f7c0-4659-84f9-c01b0f616261.PNG)

### 폴리시의 이동과 컨텍스트 매핑 (점선은 Pub/Sub, 실선은 Req/Resp)
![policy_ct](https://user-images.githubusercontent.com/89369983/132147847-f73fe720-d99d-47b9-9288-b0ebb09743fe.PNG)

### 완성된 1차 모형
![complete](https://user-images.githubusercontent.com/89369983/132147854-216852a7-9191-4cde-ac19-a818abcdca6d.PNG)

### 1차 완성본에 대한 기능적/비기능적 요구사항을 커버하는지 검증
![검증1](https://user-images.githubusercontent.com/89369983/132147856-4da33f2f-a09a-44ae-aa02-5d77b32d9fdb.PNG)
![검증2](https://user-images.githubusercontent.com/89369983/132147861-c3a424bc-7f38-412b-a55b-3f09b6b725db.PNG)

### 모델 수정
TBD

### 비기능 요구사항에 대한 검증
![비기능](https://user-images.githubusercontent.com/89369983/132147864-393eace5-f9fc-4540-9d22-1804e5407d4e.PNG)



## 헥사고날 아키텍처 다이어그램 도출
    
TBD
![polii](https://user-images.githubusercontent.com/89369983/132992132-6bcaf4dc-a4d8-48ba-a4ea-ba74adc01d7d.PNG)



# 구현

분석/설계 단계에서 도출된 헥사고날 아키텍처에 따라,구현한 각 서비스를 로컬에서 실행하는 방법은 아래와 같다 (각자의 포트넘버는 8081 ~ 8084, 8088 이다)

```shell
cd book
mvn spring-boot:run

cd payment
mvn spring-boot:run 

cd point
mvn spring-boot:run 

cd rental 
mvn spring-boot:run

cd mypage 
mvn spring-boot:run

cd alert 
mvn spring-boot:run

cd gateway
mvn spring-boot:run 
```
## DDD(Domain-Driven-Design)의 적용
msaez Event-Storming을 통해 구현한 Aggregate 단위로 Entity 를 정의 하였으며,
Entity Pattern 과 Repository Pattern을 적용하기 위해 Spring Data REST 의 RestRepository 를 적용하였다.

Bookrental 서비스의 rental.java

```java

package book.rental.system;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Rental_table")
public class Rental {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long rentalId;
    private Integer bookId;
    private String bookName;
    private Integer price;
    private Date startDate;
    private Date returnDate;
    private Integer customerId;
    private String customerPhoneNo;
    private String rentStatus;

    @PostPersist
    public void onPostPersist(){

        //  서적 대여 시 상태변경 후 Publish 
        BookRented bookRented = new BookRented();
        BeanUtils.copyProperties(this, bookRented);
        bookRented.publishAfterCommit();

    }

    @PostUpdate 
    public void onPostUpdate(){

        if("RETURN".equals(this.rentStatus)){           // 반납 처리 Publish
            BookReturned bookReturned = new BookReturned();
            BeanUtils.copyProperties(this, bookReturned);
            bookReturned.publishAfterCommit();

        } else if("DELAY".equals(this.rentStatus)){     // 반납지연 Publish
            ReturnDelayed returnDelayed = new ReturnDelayed();
            BeanUtils.copyProperties(this, returnDelayed);
            returnDelayed.publishAfterCommit();
        }
    }    

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    .. getter/setter Method 생략
```

 Payment 서비스의 PolicyHandler.java
 rental 완료시 Payment 이력을 처리한다.
```java
package book.rental.system;

import book.rental.system.config.kafka.KafkaProcessor;

import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookRented_PayPoint(@Payload BookRented bookRented){

        if(!bookRented.validate()) return;

        System.out.println("\n\n##### listener PayPoint : " + bookRented.toJson() + "\n\n");

        if("RENT".equals(bookRented.getRentStatus())){

            Payment payment =new Payment();

            payment.setBookId(bookRented.getBookid());
            payment.setCustomerId(bookRented.getCustomerId());
            payment.setPrice(bookRented.getPrice());
            payment.setRentalId(bookRented.getRentalId());
            paymentRepository.save(payment);
        }else{
            System.out.println("\n\n##### listener PayPoint Process Failed : Status -->" +bookRented.getRentStatus() + "\n\n");
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}

```

 BookRental 서비스의 RentalRepository.java


```java
package book.rental.system;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="rentals", path="rentals")
public interface RentalRepository extends PagingAndSortingRepository<Rental, Long>{


}
```

## 적용 후 REST API 의 테스트
각 서비스들의 Rest API 호출을 통하여 테스트를 수행하였음

```shell
책 대여 처리
http post localhost:8081/rent bookId=1 price=1000 startDate=20210913 returnDate=20211013 customerId=1234 customerPhoneNo=01012345678 rentStatus=RENT

책 대여를 위한 예치금 적립
TBD

책 등록 
TBD
```

## Gateway 적용
GateWay 구성를 통하여 각 서비스들의 진입점을 설정하여 라우팅 설정하였다.
```yaml
server:
  port: 8088

---

spring:
  profiles: default
  cloud:
    gateway:
      routes:
        - id: Rental
          uri: http://localhost:8081
          predicates:
            - Path=/rentals/** 
        - id: Book
          uri: http://localhost:8082
          predicates:
            - Path=/books/** 
        - id: Payment
          uri: http://localhost:8083
          predicates:
            - Path=/payments/** 
        - id: Alert
          uri: http://localhost:8084
          predicates:
            - Path=/alerts/** 
        - id: View
          uri: http://localhost:8085
          predicates:
            - Path= /mypages/**
        - id: Point
          uri: http://localhost:8086
          predicates:
            - Path=/points/** 
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins:
              - "*"
            allowedMethods:
              - "*"
            allowedHeaders:
              - "*"
            allowCredentials: true


---

spring:
  profiles: docker
  cloud:
    gateway:
      routes:
        - id: Rental
          uri: http://Rental:8080
          predicates:
            - Path=/rentals/** 
        - id: Book
          uri: http://Book:8080
          predicates:
            - Path=/books/** 
        - id: Payment
          uri: http://Payment:8080
          predicates:
            - Path=/payments/** 
        - id: Alert
          uri: http://Alert:8080
          predicates:
            - Path=/alerts/** 
        - id: View
          uri: http://View:8080
          predicates:
            - Path= /mypages/**
        - id: Point
          uri: http://Point:8080
          predicates:
            - Path=/points/** 
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins:
              - "*"
            allowedMethods:
              - "*"
            allowedHeaders:
              - "*"
            allowCredentials: true

server:
  port: 8080

```
## CQRS 적용
TBD

## 폴리글랏 퍼시스턴스
TBD
![polii](https://user-images.githubusercontent.com/89369983/132992132-6bcaf4dc-a4d8-48ba-a4ea-ba74adc01d7d.PNG)

## 동기식 호출과 Fallback 처리
TBD

## 비동기식 호출 / 시간적 디커플링 / 장애격리 / 최종 (Eventual) 일관성 테스트
TBD

# 운영
TBD

