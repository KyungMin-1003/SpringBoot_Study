# Chapter06


## JPA란?

JPA(Java Persistence API)

자바 어플리케이션에서 관계형 데이터베이스(RDBMS)를 어떻게 사용할지 정의한 표준 명세이다.

JPA의 핵심은 ORM(Object-Relational Mapping)이라는 기술에 있다. 이름 
그대로 '객체(Object)'와 '관계형 데이터베이스의 테이블(Relational)'을 매핑(Mapping)해주는 기술.

JPA는 Java에서 DB를 객체처럼 다루게 해주는 기술이야.

정의 1:JPA(Java Persistence API)가 무엇인지 알아보려고한다.
JPA는 자바 진영에서 ORM(Object-Relational Mapping) 기술 표준으로 사용되는 인터페이스의 모음이다.
그 말은 즉, 실제적으로 구현된것이 아니라 구현된 클래스와 매핑을 해주기 위해 사용되는 프레임워크이다.

ORM(Object-Relational Mapping)
정의: 우리가 일반 적으로 알고 있는 애플리케이션 Class와 RDB(Relational DataBase)의 테이블을 매핑(연결)한다는 뜻이며, 
기술적으로는 어플리케이션의 객체를 RDB 테이블에 자동으로 영속화 해주는 것이라고 보면된다.



JAP를 왜 쓰는가 
원래 DB에 데이터를 저장하려면 SQL을 직접 써야한다. 

하지만 springboot에서 JPA를 쓰면 
```java
memberRepository.save(member);      // 저장
memberRepository.findById(1L);      // 조회
memberRepository.delete(member);    // 삭제

```
이렇게 쓸 수 있고 SQL을 직접 많이 쓰지 않아도 된다. 


JPA에서 중요한건 Entity이다. 
Entity는 DB테이블과 연결되는 자바 클래스이다. 

정리하면 JPA는 자바 객체와 DB 테이블을 연결해서, SQL을 직접 많이 쓰지 않고도 데이터를 저장/조회/수정/삭제
할 수 있게 해주는 기술

흐름
```text
Controller
   ↓
Service
   ↓
Repository
   ↓
JPA
   ↓
DB


회원가입 요청
→ Controller가 받음
→ Service에서 회원 생성 로직 처리
→ Repository.save(member)
→ JPA가 INSERT SQL 실행
→ DB에 저장
```

## N+1 문제란?

N+1문제 정의: 연관관계가 있는 엔티티를 조회할 때 조회된 개수 N개 만큼의 쿼리가 추가로 발생하는 것

회원을 3명 조회한다고 했을 때 
회원목록 조회 1번 각 회원리뷰 3회

총 4번의 쿼리가 발생한다. 

이렇게 적은 인원수일 때는 상관없음
하지만 회원이 많아지면 많아질 수록 DB에 너무 많은 요청이 나가서 성능이 안좋아진다.

추가쿼리가 너무 많이 반복해서 나가는게 문제라고 할 수 있다. 
1000명이면 DB에 요청을 1001번 보내서 서버가 그만큼 일을 하기 때문

회원 총 목록(100명 가정)을 불러와달라고 DB요청한다음에 다시 회원 1번 리뷰,2번....100번리뷰 달라고
말하는 거임. 이렇게 점점 사람이 많아지면 많아질수록 시간이 오래걸린다. 

1. 응답속도 느려짐
2. DB에 부담이 커진다. 
3. 사용자가 많아지면 서버가 버티기 어렵다

지연로딩을 사용하다보면 N+1 문제는 자연스럽게 발생할 수 있다.

사실 아직 회원을 많이 받아본 적이 없어서 이게 얼마나 큰 문제로 다가오는지 실감이 안난다,,

특히 N+1 문제는 코드상으로 에러가 안난다. 코드는 정상 작동하는데 내부적으로 SQL이 많이 나갈 수 있다.
화면은 잘 나오는데 데이터가 많아지면 느려지는 현상이 나오기도 한다.

해결방안

- fetch join - 필요한 데이터를 처음부터 같이 가져오는 방법이다. 
엔티티를 조회하면서 연관 엔티티도 같이 가져오는 방식
엔티티를 그대로 가져오기 때문에 비즈니스 로직에서 사용하기 좋다, 엔티티 중심으로 작업할 때 좋음
지연 로딩 때문에 생기는 N+1을 줄일 수 있어.

단점: 필요 없는 데이터까지 많이 가져올 수 있다. etity정보를 다 가져오기 때문이다.


- DTO로 필요한 데이터만 조회하는 방법이다. 
처음부터 필요한 데이터만 골라서 가져오는 방식
필요한 데이터만 가져오기 때문에 API응답용으로 효율적이다. 

단점: DTO조회는 조회전용에 가깝다. 
DTO로 가져온 데이터는 엔티티가 아니기 때문에JPA가 변경 감지를 해주지 않는다. 

DTO 값을 바꿔도 DB에 자동 반영되지 않음.
DB를 수정하려면 결국 엔티티를 다시 조회해서 수정해야한다.


DTO 조회
= 화면에 보여줄 데이터만 가져올 때 좋음
= 조회 API에 적합
= 성능 좋고 응답 구조 만들기 편함

fetch join
= 엔티티를 가져오면서 연관 데이터도 같이 필요할 때 좋음
= 비즈니스 로직 처리에 적합
= 수정, 상태 변경 작업에 유리함


## 지연로딩과 즉시로딩의 차이는?

지연로딩: 필요할 때 가져오는 방식

지연로딩 
예로 member조회하고 나중에 review를 조회한다.

장점:불필요한 데이터를 안가져와서 성능에 유리할 수 있다. 

단점: 잘못사용하면 N+1 문제가 생길 수 있다.

지연로딩을 사용하다보면 N+1 문제는 자연스럽게 발생할 수 있다. 

사실 아직 회원을 많이 받아본 적이 없어서 이게 얼마나 큰 문제로 다가오는지 실감이 안난다,,

특히 N+1 문제는 코드상으로 에러가 안난다. 코드는 정상 작동하는데 내부적으로 SQL이 많이 나갈 수 있다.
화면은 잘 나오는데 데이터가 많아지면 느려지는 현상이 나오기도 한다.


즉시로딩: 처음부터 같이 가져오는 방식

에로 member 조회할 때 Review도 바로 조회한다. 

장점: 연관된 데이터가 무조건 필요할 때는 편할 수 있다.

단점: 필요 없는 데이터까지 가져와서 성능이 나빠질 수 있다, 예상치 못한 
SQL이 많이 나갈 수 있다.

이것도 N+1 문제 발생 가능

## JPQL란?

JPQL(Java Persistence Query Language)
JPA에서 사용하는 객체 중심 쿼리 언어

SQL은 DB테이블을 대상으로 쿼리를 작성하고, JPQL은 Entity 객체를 대상으로 쿼리를 작성한다. 

SQL은 테이블 이름을 직접 사용한다. 하지만 JPQL은 테이블이 아니라 Entity 클래스 이름과 필드명을 사용한다.

JPA를 쓰면 기본적인 조회는 메서드로 가능하지만 조건이 복잡해지면 메서드 이름만으로는 한계가 있다. 
이러한 조건을 작성하고 싶을 떄 

```java
@Query("SELECT m FROM Mission m")
List<Mission> findAllMissions();
```



## Fetch Join란?

연관된 Entity를 한 번의 쿼리로 같이 조회하는 방법이다. 

따로따로 불러올 데이터를 Join Fetch를 사용해서 한 번에 같이 가져오는 것이다. 

N+1 문제를 방지하려고 사용한다.

이게 JPQL에서 작성하는 것이다.
```java
@Query("SELECT m FROM Member m JOIN FETCH m.reviews")
List<Member> findAllWithReviews();
```
Member를 조회할 때,
Member와 연관된 reviews도 같이 가져와라.


실제로는 SQL JOIN처럼 동작해서 한 번의 쿼리로 가져온다.
```sql
select m.*, r.*
from member m
join review r on m.id = r.member_id;
```

결국 N+1문제를 줄이기 위해 자주 사용하는 JPQL기능이다.

## @EntityGraph란?

연관 Entity를 함께 조회하도록 지정하는 방법이다.

Repository 메서드에서 연관 Entity를 함께 조회하도록 지정하는 기능

Fetch Join은 JPQL로 직접 작성하는 방식
@EntityGraph는 어노테이션으로 가져올 연관관계를 지정하는 방식

장점: JPQL을 직접 안 써도 된다, 메서드 이름 쿼리와 같이 사용할 수 있다, 
N+1 문제를 줄일 수 있다,코드 의도가 명확하다

단점: 필요 없는 데이터까지 가져올 수 있다, OneToMany관계(1:N 관계)에서는 데이터가 중복될 수 있다,
복잡한 조건 조회에는 한계가 있다, 너무 많이 연결하면 쿼리가 무거워진다



## commit과 flush 차이점은?

flush() - 영속성 컨텍스트의 변경 내용을 DB에서 SQL로 반영한 것 

+ 영속성 컨텍스트 JPA가 엔티티 객체를 관리하는 임시공간

```java
Member member = new Member("김경민");

entityManager.persist(member);
```
바로 DB에 저장되지 않고, JPA에서 먼저 member 객체를 영속성 컨텍스트에 넣는다.

```text
자바 객체
  ↓ persist()
영속성 컨텍스트
  ↓ flush 또는 commit
DB

```
즉, persist()를 했다고 항상 즉시 DB에 INSERT 되는 건 아님.

JPA가 일단 영속성 컨텍스트에 객체를 보관하고 있다가, 나중에 flush나 commit 시점에 SQL을 DB로 보낸다.

flush를 하면 지금까지 바뀐 내용들이 DB SQL로 보내진다.
flush는 변경 내용을 DB에 실제 SQL로 보내는 것이다.

하지만 flush는 최종 저장은 아니다. rollback가능하다

flush 후에 
```java
transaction.rollback();
```
이걸 사용해서 롤백 할 수 있다.

commit - 트랜젝션을 최종 확정하는 것

commit 하면 rollback 불가능



spring에서는 @Transactional을 주로 사용한다.
```text
메서드 시작 → 트랜잭션 시작
메서드 정상 종료 → commit
메서드 중 에러 발생 → rollback
```


## 3. 미션
- (필수) 엔티티를 제작하고 매핑까지 완료하기
- (필수) 아래 화면을 구현하기 위한 서비스를 만들고 5주차에 제작한 컨트롤러와 연결하기
    - (페이징 부분은 @Query를 통해 구현)
    - (구현한 뒤 Swagger 화면 캡쳐)