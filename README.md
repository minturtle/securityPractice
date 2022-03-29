# SecurityPractice
스프링 시큐리티 연습

##   일반 로그인
- 회원가입에 사용할 passwordEncoder를 Bean으로 등록해준다. 
- 그 다음 SecurityConfiguration 클래스에 폼 로그인을 설정해준다. 
 ```
    .formLogin()
    .loginPage("/user/login").permitAll()
    .loginProcessingUrl("/user/login")
    .defaultSuccessUrl("/")
```

- UserDetails를 구현한 클래스를 만들어준다. 
- UserDetailsService를 구현한 서비스 클래스를 만들어준다. 그 후 loadUserByUsername 메서드에서 username을 이용해 UserDetail를 찾아내서 리턴해준다. 
```
 @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findUserByUsername(username);
        if(findUser == null){
            throw new UsernameNotFoundException(username);
        }
        return findUser;

    }
``` 
- SecutiryCongiguration 클래스에서 구현한 userDetailsService 클래스를 넣어준다. 
```
@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }
```  
## OAuth2 로그인
- application.yml에 필요한 정보들을 적어준다. 
```
spring:
  security:
    oauth2:
      client:
        registration:
          kakao:
            client-id: {}
            client-secret: {}
            redirect-uri: http://localhost:8080/login/oauth2/code/kakao
            authorization-grant-type: authorization_code
            client-name: kakao
            client-authentication-method: POST
        provider:
          kakao:
            authorization-uri: https://kauth.kakao.com/oauth/authorize
            token-uri: https://kauth.kakao.com/oauth/token
            user-info-uri: https://kapi.kakao.com/v2/user/me
            user-name-attribute: id

```
- SecurityConfiguration 클래스에 필요한 정보를 세팅해준다.  
```
.oauth2Login()
                .defaultSuccessUrl("/")
                .userInfoEndpoint().userService(customOauth2UserService); #성공하면 userService의 파라미터 클래스로 간다는 뜻
```
- OAuth2UserService 인터페이스를 구현한 클래스를 하나 만든다
```
@Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        return oAuth2User;
    }
```
### OAuth 정보 조회하는 방법
```
  #아래의 코드로 user객체를 가져올 수 있음.
 OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
```
