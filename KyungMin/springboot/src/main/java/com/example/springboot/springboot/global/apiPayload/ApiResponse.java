package com.example.springboot.springboot.global.apiPayload;

import com.example.springboot.springboot.global.apiPayload.code.BaseErrorCode;
import com.example.springboot.springboot.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message", "result"})

public class ApiResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result){
        return  new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return  new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}

/**
 *
 * ApiResponse
 * 이 클래스는 API 응답 형식을 통일하기 위한 객체야.
 * 예를 들어 회원 조회, 리뷰 작성, 가게 조회 API가 각각 다른 응답 모양을 가지면 프론트엔드에서 처리하기 힘들어져.
 * 그래서 모든 API 응답을 아래 형식으로 통일하는 거야.
 *isSuccess : 요청 성공 여부
 * code      : 응답 코드
 * message   : 응답 메시지
 * result    : 실제 응답 데이터
 *
 *
 * @JsonProperty java 객체를 JSON으로 바꿀 때, JSON 필드 이름을 지정하는 어노테이션
 *
 *
 *
   Lombok이 있어서 getter을 만들 필요가 없음을 알아야 한다. 물론 @Getter 어노테이션을 사용해야한다.

  @AllArgsConstructor 때문에 기본적으로 전체 필드를 받는 생성자가 만들어져있다.
  외부에서 이렇게 만들 수 있다.
  new ApiResponse<>(true, "COMMON200", "성공입니다.", result); 이렇게 외부에서 만들 수 있다.

 응답 객체를 만드는 방식을 더 강하게 통제하고 싶으면 private 생성자가 더 좋다.
 간단하게 쓰고 싶으면 @AllArgsConstructor

 ----
 @JsonProperty("isSuccess")
 private final Boolean isSuccess;

 이렇게 쓰면 JSON 응답에서 이름이 "isSuccess"로 나가.

 {
 "isSuccess": true
 }


@JsonPropertyOrder JSON 응답 필드 순서를 정하는 어노테이션

 @JsonPropertyOrder({"isSuccess", "code", "message", "result"}) 이렇게 되어있으면
 isSuccess
 code
 message
 result
 이 순서대로 응답한다.

 Lombok의 Getter
 @Getter 사용하면
 Getter메서드를 자동으로 만들어준다


 ------
 ApiResponse<T> result에 들어갈 데이터 타입을 미리 정하지 않고, 사용할 때 정하겠다는 뜻
 그래서 ApiResponse<T>는 어떤 API 응답에도 재사용할 수 있다

 ---
 isSuccess 필드 부터
 Api 요청이 성공했는지 실패했는지 나타내는거
 "isSuccess": true -> 성공시
 "isSuccess": false -> 실패시

 Boolean 참 거짓 저장하는 타입

 final
 final은 한 번 들어가면 바꿀 수 없다.

 즉, 응답 객체를 만들 때 성공 여부가 true로 정해졌으면, 나중에 false로 바꿀 수 없어.
 응답 객체는 한 번 만들어지면 바뀌지 않는 게 자연스럽기 때문에 final을 붙이는 게 좋아.

 -----

  code 필드

 응답 코드를 의미한다.
 성공했을 때 ->"code": "COMMON200"
회원을 찾지 못했을때 ->"code": "MEMBER404"

보통은 200, 400 숫자만 쓰는데 더 구체적으로 알기 위해 MEMBER404 이러한 형식으로 진행한다.

 -----



 */
