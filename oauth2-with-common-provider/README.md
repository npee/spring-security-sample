# oauth2-with-common-provider
잘 알려진 제공자를 이용한 OAuth 2.0 로그인
## `CommonOAuth2Provider`의 기본 제공자 4가지
- google
- github
- facebook
- okta
## 환경
IntelliJ 2020.1 + Springboot 2.2.6(Spring Security 5.2.2)
## 개발
### API Key
각 제공자의 개발자 센터에서 Client id, Cleint secret 얻기
### build.gradle
```groovy
dependencies {
    implementation project(":parents:boot2-parent")
}
```
참고 : <a href = "https://github.com/npee/spring-security-sample/blob/master/parents/boot2-parent/README.md">boot2-parent dependencies</a>
### 주요 방식
#### application 설정 파일 내 값 수정
* 파일 위치
    * resources/<a href="https://github.com/npee/spring-security-sample/blob/master/oauth2-with-common-provider/src/main/resources/application.yml">application.yml</a>
* 속성    
    * spring.security.oauth2.client.registration.{registrationId}
* 값
    * Client ID
    * Client Secret
## 동작
### 매핑된 URL 요청 시(권한 없음)
#### `{baseUrl}`   
"main page" 문자열을 출력하는 페이지
#### `{baseUrl}` 이외   
스프링 시큐리티에서 기본으로 제공하는 인증 페이지   

### 매핑된 URL 요청 시(USER 권한)
#### `{baseUrl}/me`   
인증한 사이트에 따라 다르게 제공하는 사용자 정보를 출력하는 페이지
