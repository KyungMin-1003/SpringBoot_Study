# Chapter05

## 1. 빌더 패턴이란?

### 1. 정의

**빌더 패턴(Builder Pattern)** 은 복잡한 객체의 생성 과정과 표현 방법을 분리하여, 다양한 구성의 인스턴스를 만들 수 있게 하는 **생성 패턴**이다.

쉽게 말하면 객체를 만들 때 생성자에 값을 한꺼번에 넣는 대신, 필요한 값들을 메서드로 하나씩 설정한 뒤 마지막에 `build()`로 객체를 완성하는 방식이다.

---

### 2. 빌더 패턴을 사용하는 이유

객체를 만들 때 필요한 값이 많으면 생성자가 복잡해지고, 각 값이 어떤 의미인지 헷갈릴 수 있다.

예를 들어 다음 코드를 보면:

```java
User user = new User("김경민", 23, "test@email.com", "010-1234-5678", "서울");
```

이 코드만 봐서는 `"김경민"`은 이름이라는 것을 알 수 있지만, 뒤에 나오는 값들이 각각 어떤 필드에 들어가는지 한눈에 파악하기 어렵다.

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

**빌더 패턴은 복잡한 객체를 만들 때 필요한 값을 하나씩 설정하고, 마지막에 `build()`로 객체를 완성하는 생성 방식이다.**

클래스의 선택적 매개변수가 많은 상황에서 유용하게 사용한다.

---

## 2. record vs static class

## record란?

`record`란 Java에서 데이터를 담기 위한 클래스를 간단하게 만들 수 있는 문법이다.

일반 클래스로 만들었을 때보다 간단하게 만들 수 있다.

---

### record 필드의 특징

#### 1. 필드가 자동으로 `private final`이 된다.

record의 필드는 기본적으로 변경할 수 없는 형태로 만들어진다.

#### 2. getter 이름이 다르다.

일반 클래스에서는 보통 다음과 같이 사용한다.

```java
user.getName();
```

record에서는 다음과 같이 사용한다.

```java
user.name();
```

#### 3. 생성자가 자동으로 생긴다.

record는 필드에 맞는 생성자가 자동으로 생성된다.

#### 4. `toString()`도 자동으로 만들어진다.

객체 정보를 문자열로 출력할 수 있는 `toString()` 메서드도 자동으로 생성된다.

---

## static class란?

`static class`는 **정적 중첩 클래스**를 말한다.

최상위 클래스에는 `static`을 붙일 수 없다.  
단, 클래스 안에 있는 클래스에는 `static`을 붙일 수 있다.

---

### static class를 왜 쓰는가?

어떤 클래스 안에서만 의미가 있는 클래스를 묶어두고 싶을 때 사용한다.

예를 들어 `Address`는 주소 정보이다.  
이 주소 정보를 혼자 쓰는 것보다 `User`의 주소 정보로 사용한다면, `User` 클래스 안에 넣어서 관리할 수 있다.

---

### static class 특징

- 바깥 클래스 객체 없이 만들 수 있다.
- 바깥 클래스의 일반 변수에 바로 접근할 수 없다.

---

## record / static class 비교

| 구분 | record | static class |
| --- | --- | --- |
| 목적 | 데이터를 간단히 담기 위해 사용 | 클래스 안에 관련 클래스를 묶기 위해 사용 |
| 위치 | 단독 클래스 가능 | 클래스 내부에 선언 |
| 필드 변경 | 기본적으로 변경 불가 | 직접 설계하기 나름 |
| 생성자 | 자동 생성 | 직접 작성해야 함 |
| getter | 자동 생성, `name()` 형태 | 직접 작성하거나 Lombok 사용 |
| equals/hashCode/toString | 자동 생성 | 직접 작성하거나 Lombok 사용 |
| 주 사용처 | DTO, 응답 객체, 요청 객체 | Builder, 내부 DTO, 보조 클래스 |

---

## 3. 제네릭이란?

### 정의

제네릭은 클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법을 의미한다.

객체별로 다른 타입의 자료가 저장될 수 있도록 한다.

즉, 제네릭은 클래스와 인터페이스, 그리고 메서드를 정의할 때 타입을 파라미터로 사용할 수 있도록 한다.

타입 파라미터는 코드 작성 시 구체적인 타입으로 대체되어 다양한 코드를 생성하도록 해준다.

---

## 4. Optional이란?

### 정의

`Optional`은 `null`일 수 있는 객체를 감싸는 Wrapper 클래스이다.

주로 값이 존재할 수도 있고, 존재하지 않을 수도 있는 상황에서 `null`을 직접 다루지 않고 안전하게 처리하기 위해 사용된다.

예를 들어 Repository에서 `findById()` 메서드를 통해 데이터를 조회할 때, 해당 ID에 해당하는 값이 없으면 `null`이 반환될 수 있다.

이런 경우 아무런 체크 없이 `null` 객체에 바로 접근하면 `NullPointerException`, 즉 NPE가 발생하게 된다.

`Optional`은 이런 상황을 방지하기 위해 사용되며, 값을 감싸는 형태로 존재 여부를 명시적으로 처리할 수 있게 도와준다.

```java
Optional<Member> member = memberRepository.findById(id);
```

위 코드는 id로 회원을 찾았는데, 회원이 있을 수도 있고 없을 수도 있다는 의미이다.

---

### get() 사용 시 주의점

`get()`을 이용하려면 주의가 필요하다.

값이 없는데 `get()`으로 값을 꺼내려고 하면 `NoSuchElementException` 오류가 발생한다.

그래서 가장 많이 사용하는 방법은 다음과 같다.

```java
Member member = memberRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
```

뜻은 다음과 같다.

1. id로 회원을 찾는다.
2. 있으면 `Member`를 꺼낸다.
3. 없으면 예외를 발생시킨다.

---

### 자주 쓰는 Optional 메서드

| 메서드 | 의미 |
| --- | --- |
| `isPresent()` | 값이 있으면 true |
| `isEmpty()` | 값이 없으면 true |
| `get()` | 값을 꺼냄. 단, 비어 있으면 오류 |
| `orElse()` | 값이 없으면 기본값 반환 |
| `orElseThrow()` | 값이 없으면 예외 발생 |
| `ifPresent()` | 값이 있을 때만 실행 |

---

## 5. @RestControllerAdvice란?

### 정의

`@RestControllerAdvice`는 Spring Boot에서 예외 처리를 한 곳에 모아서 처리할 때 사용하는 어노테이션이다.

`@ControllerAdvice`와 `@ResponseBody`를 합친 어노테이션이다.

`@ControllerAdvice`의 역할을 수행하고, `@ResponseBody`를 통해 객체를 리턴할 수 있다.

따라서 단순히 예외 처리를 하고 싶다면 `@ControllerAdvice`를 사용하고, 응답으로 객체를 리턴해야 한다면 `@RestControllerAdvice`를 적용하면 된다.

두 어노테이션 모두 적용 범위를 클래스나 패키지 단위로 제한할 수 있다.

---

### 사용하는 이유

예외 처리 결과를 JSON 같은 응답 본문으로 바로 반환할 때 사용한다.

그래서 REST API를 만들 때 자주 사용한다.

쉽게 설명하면, 컨트롤러에서 발생하는 에러를 각각의 Controller마다 처리하지 않고 공통 예외 처리 클래스를 만들어서 한 번에 관리해주는 기능이다.

---

### @ControllerAdvice 설명

`@ControllerAdvice`는 `@ExceptionHandler`, `@ModelAttribute`, `@InitBinder`가 적용된 메서드들에 AOP를 적용하여 Controller 단에 적용하기 위해 고안된 어노테이션이다.

클래스에 선언되며, 모든 `@Controller`에 대해 전역적으로 발생할 수 있는 예외를 잡아서 처리할 수 있다.

---

### 예외 처리가 반복되는 경우

예를 들어 다음과 같은 코드가 있다고 하자.

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

이러한 예외 처리를 Controller마다 계속 작성해야 하면 코드가 지저분해지고 반복이 많아져 효율적이지 않다.

---

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

이렇게 만들면 Controller에서 `RuntimeException`이 발생했을 때 이 메서드가 자동으로 실행된다.

여기서 `@ExceptionHandler`라는 개념도 알아야 한다.

---

### @ExceptionHandler란?

`@ExceptionHandler`는 어떤 예외가 발생했을 때 어떤 메서드가 처리할지 정하는 어노테이션이다.

---

## 6. Optional과 @RestControllerAdvice의 관계

다음 코드를 보자.

```java
@GetMapping("/members/{id}")
public Member getMember(@PathVariable Long id) {
    return memberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
}
```

여기서 회원이 없으면 `RuntimeException`이 발생한다.

이때 `@RestControllerAdvice`를 사용하면 다음과 같이 처리할 수 있다.

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException e) {
        return e.getMessage();
    }
}
```

예외가 발생하면 다음 코드가 실행된다.

```java
throw new RuntimeException("회원을 찾을 수 없습니다.");
```

그리고 `GlobalExceptionHandler` 안에 있는 이 메서드가 실행된다.

```java
@ExceptionHandler(RuntimeException.class)
public String handleRuntimeException(RuntimeException e) {
    return e.getMessage();
}
```

결과적으로 다음과 같은 응답이 가게 된다.

```text
회원을 찾을 수 없습니다.
```