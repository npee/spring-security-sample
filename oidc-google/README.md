### oidc-google
OpenID Connect를 활용한 Google Login Sample  
얻을 수 있는 데이터 : https://accounts.google.com/.well-known/openid-configuration
## 환경
IntelliJ 2020.1 + Springboot 2.2.6
## 순서
1. Google Developer Console 에서  Client-id, Client-secret 얻기 (링크 : OAuth 2.0 Client ID, Secret)
2. 관련된 Depencency 주입
```$xslt
compile group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: '2.2.6.RELEASE'
compile group: 'org.springframework.boot', name: 'spring-boot-starter-security', version: '2.2.6.RELEASE'
compile group: 'org.springframework.boot', name: 'spring-boot-starter-oauth2-client', version: '2.1.0.RELEASE'
```
3. 구동 클래스 생성
```$xslt
@SpringBootApplication
public class OidcApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OidcApplication.class).run(args);
    }
}

```
4. application.yml 설정
```$xslt
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: <your client-id>
            client-secret: <your client-secret>
        provider:
          google:
            issuer-uri: https://accounts.google.com
```
4. localhost:8080/me/google 으로 접근하면 body에 현재 로그인 중인 사용자의 정보를 출력하는 컨트롤러 생성
```$xslt
@RestController
@RequestMapping("/")
public class OidcRestController {

    @GetMapping("/me/google")
    public OidcUser getGoogleProfile(@AuthenticationPrincipal OidcUser user) {
        return user;
    }
}
```
5. Spring Security Congfiguration(SecurityConfiguration.java)
```$xslt
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        OidcUserService googleUserService = new OidcUserService();
        http
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login().userInfoEndpoint().oidcUserService(googleUserService);
    }
}
```