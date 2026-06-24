# Chapter05

## 빌더 패턴이란?

### 1. 정의

**빌더 패턴(Builder Pattern)** 은 복잡한 객체의 생성 과정과 표현 방법을 분리하여  
다양한 구성의 인스턴스를 만들 수 있게 하는 **생성 패턴**이다.

쉽게 말하면, 객체를 만들 때 생성자에 값을 한꺼번에 넣는 대신  
필요한 값들을 메서드로 하나씩 설정한 뒤, 마지막에 `build()`로 객체를 완성하는 방식이다.

---

### 2. 빌더 패턴을 사용하는 이유

객체를 만들 때 필요한 값이 많으면 생성자가 복잡해지고,  
각 값이 어떤 의미인지 헷갈릴 수 있다.


예를 들어 다음 코드를 보면:

```java
User user = new User("김경민", 23, "test@email.com", "010-1234-5678", "서울");
```

이 코드만 봐서는 `"김경민"`은 이름이라는 것을 알 수 있지만,  
뒤에 나오는 값들이 각각 어떤 필드에 들어가는지 한눈에 파악하기 어렵다.

특히 매개변수가 많아질수록 코드의 가독성이 떨어진다.

---

### 3. 빌더 패턴 사용 예시

빌더 패턴을 사용하면 다음과 같이 작성할 수 있다.

```java
User user = User.builder()
        .name("김경민")
        .age(23)
        .email("test@email.com")
        .phone("010-1234-5678")
        .address("서울")
        .build();
```

이렇게 작성하면 어떤 값이 어떤 필드에 들어가는지 바로 알 수 있다.

예를 들어:

- `.name("김경민")` → 이름
- `.age(23)` → 나이
- `.email("test@email.com")` → 이메일
- `.phone("010-1234-5678")` → 전화번호
- `.address("서울")` → 주소

---

### 4. 장점

빌더 패턴을 사용하면 다음과 같은 장점이 있다.

- 생성자에 많은 매개변수를 넣지 않아도 된다.
- 어떤 값이 어떤 필드에 들어가는지 알기 쉽다.
- 코드의 가독성이 좋아진다.
- 필요한 값만 선택해서 객체를 만들 수 있다.
- 유지보수가 편해진다.

---

### 5. 정리

**빌더 패턴은 복잡한 객체를 만들 때 필요한 값을 하나씩 설정하고,  
마지막에 `build()`로 객체를 완성하는 생성 방식이다.**

클래스의 선택적 매개변수가 많은 상황에서 유용하게 사용한다.


## record vs static class

record란 java에서 데이터를 담기 위한 클래스를 간단하게 만들 수 있는 문법

일반 클래스로 만들었을때보다 간단하게 만들 수 있다. 

record필드의 특징
1. 필드가 자동으로 private final이 된다.


2. getter 이름이 다르다

일반 클래스에서는 보통 user.getName(); 이렇게 쓰고

record에서는 user.name(); 이렇게 사용한다. 

3. 생성자가 자동으로 생긴다

4. toString도 자동으로 만들어진다


Static class

정적 중첩 클래스를 말한다

최상위 클래스에는 static를 붙일 수 없다. 단 클래스 안에 있는 클래스에는 static를 붙일 수 
있다.


static class를 왜 쓰는가?

어떤 클래스 안에서만 의미가 있는 클래스를 묶어두고 싶을 때 사용하는 것이다

예를 들어 Adress는 주소정보이다.
이 주소정보를 혼자쓰는거보다 User의 주소 정보로 쓰이면 User클래스 안에 넣어서 관리할 수 있다. 

static class 특징

바깥 클래스 객체 없이 만들 수 있다.

바깥 클래스의 일반 변수에 바로 접근할 수 없다

record / static class 비교

| 구분                       | record             | static class            |
| ------------------------ | ------------------ | ----------------------- |
| 목적                       | 데이터를 간단히 담기 위해 사용  | 클래스 안에 관련 클래스를 묶기 위해 사용 |
| 위치                       | 단독 클래스 가능          | 클래스 내부에 선언              |
| 필드 변경                    | 기본적으로 변경 불가        | 직접 설계하기 나름              |
| 생성자                      | 자동 생성              | 직접 작성해야 함               |
| getter                   | 자동 생성, `name()` 형태 | 직접 작성하거나 Lombok 사용      |
| equals/hashCode/toString | 자동 생성              | 직접 작성하거나 Lombok 사용      |
| 주 사용처                    | DTO, 응답 객체, 요청 객체  | Builder, 내부 DTO, 보조 클래스 |





# 제네릭이란?

### 정의 
클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법을 의미한다. 
객체별로 다른 타입의 자료가 저장될 수 있도록 한다.

즉, 제네릭은 클래스와 인터페이스,
그리고 메소드를 정의할 때 타입(type)을 파라미터(parameter)로 사용할 수 있도록 하며,
타입 파라미터는 코드 작성 시 구체적인 타입으로 대체되어 다양한 코드를 생성하도록 해준다.




### @RestControllerAdvice이란?

Spring Boot 에서 예외처리를 한 곳에 모아서 처리할 때 사용하는 어노테이션이다.

@ControllerAdvice와 @ResponseBody를 합친 어노테이션 @ControllerAdvice의 
역할을 수행하고, @ResponseBody를 통해 객체를 리턴할 수 있다.
따라서 단순히 예외 처리를 하고 싶다면 @ControllerAdvice를,
응답으로 객체를 리턴해야 한다면 @RestControllerAdvice를 적용하면 된다.

위 두 어노테이션 모두 적용 범위를 클래스나 패키지 단위로 제한할 수 있다.

예외 처리 결과를 JSON 같은 응답 본문으로 바로 반환할 때 사용
그래서 REST API를 만들 때 자주 사용한다.


+ @ControllerAdvice는 @ExceptionHandler, @ModelAttribute, @InitBinder가 
적용된 메소드들에 AOP를 적용하여 Controller단에 적용하기 위해 고안된 어노테이션이다. 
클래스에 선언되며, 모든 @Controller에 대한 전역적으로 발생할 수 있는 예외를 잡아서 
처리할 수 있다.

쉽게 설명하면 컨트롤러에서 발생하는 에러를 각각의 Controller마다 처리하지 않고 
공통예외 처리 클래스를 만들어서 한 번에 관리해주는 기능

```java
@GetMapping("/members/{id}")
public Member getMember(@PathVariable Long id) {
Member member = memberService.findById(id);

    if (member == null) {
        throw new RuntimeException("회원을 찾을 수 없습니다.");
    }

    return member;
}

```
이러한 예외처리를 controller에 계속 써야하는데 코드가 지저분해지고 계속 반복해야해서 
효율적이 않다.

### 사용 예시

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }
}
```
이렇게 만들면,
Controller에서 RuntimeException이 발생했을 때 이 메서드가 자동으로 실행돼.

이러면 @ExceptionHandler 이라는 개념도 알아야 한다.

**@ExceptionHandler** - 어떤 예외가 발생했을 때 어떤 메서드가 처리할지 정하는 어노테이션


# Optional이란?

정의

null일 수 있는 객체를 감싸는 Wrapper클래스
주로 값이 존재할 수도, 존재하지 않을 수도 있는 상황에서 
null을 직접 다루지 않고 안전하게 처리하기 위해 사용됩니다.

예를 들어, Repository에서 findById() 메서드를 통해 데이터를 조회할 때, 해당 ID에 해당하는 값이 없으면 null이 반환됩니다.
이런 경우, 아무런 체크 없이 null 객체에 바로 접근하면 NullPointerException(NPE)가 발생하게 된다

Optional은 이런 상황을 방지하기 위해 사용되며, 값을 감싸는 형태로 존재 여부를 명시적으로 처리할 수 있게 도와줍니다.

```java
Optional<Member> member = memberRepository.findById(id);
```
id로 회원을 찾았는데, 회원이 있을 수도 있고 없을 수도 있다. 

여기서 get()을 이용하려면 주의가 필요하다 값이 없는데 get()로 값을 꺼내려고하면
NoSuchElementException 오류가 발생하낟.

가장 많이 사용하는 방법은

```java
Member member = memberRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
```
이러한 방법을 가장 많이 사용한다. 

뜻
id로 회원을 찾는다.
있으면 Member를 꺼낸다.
없으면 예외를 발생시킨다.

여기서 @RestControllerAdvice와 연관시켜보면

```java
@GetMapping("/members/{id}")
public Member getMember(@PathVariable Long id) {
    return memberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
}
```
여기서 회원이 없으면 RuntimeException이 발생한다.

@RestControllerAdvice를 사용

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }
}
```
예외 발생시

```java
throw new RuntimeException("회원을 찾을 수 없습니다.");
// GlobalExceptionHandler 안에 있는 이 메서드가 실행

@ExceptionHandler(RuntimeException.class)
public String handleRuntimeException(RuntimeException e) {
    return e.getMessage();
}
```
결과적으로 "회원을 찾을 수 없습니다." 이런 응답이 가게 된다


---

### 자주 쓰는 Optional 메서드

| 메서드             | 의미                  |
| --------------- | ------------------- |
| `isPresent()`   | 값이 있으면 true         |
| `isEmpty()`     | 값이 없으면 true         |
| `get()`         | 값을 꺼냄. 단, 비어 있으면 오류 |
| `orElse()`      | 값이 없으면 기본값 반환       |
| `orElseThrow()` | 값이 없으면 예외 발생        |
| `ifPresent()`   | 값이 있을 때만 실행         |


