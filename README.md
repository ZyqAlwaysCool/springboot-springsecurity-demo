## springboot-SpringSecurity-demo
SpringSecurity是针对Spring项目的安全框架，也是SpringBoot底层安全模块默认的技术选型，它可以实现强大的Web安全控制，对于安全控制仅需要引入`spring-boot-starter-security`模块，采用AOP思想，进行少量的配置即可实现强大的安全管理。
### denpendency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
### SecurityConfig
```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //链式编程，设置授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level/1").hasRole("guest")
                .antMatchers("/level/2").hasRole("user")
                .antMatchers("/level/3").hasRole("root");
        http.formLogin().failureForwardUrl("/");
    }

    //认证
    //密码编码: PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("guest").password(new BCryptPasswordEncoder().encode("guest")).roles("guest")
                .and()
                .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("user")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("root", "user", "guest");
    }
```
* `WebSecurityConfigurerAdapter`：自定义Security策略
* `AuthenticationManagerBuilder`：自定义认证策略
* `@EnableWebSecurity`：开启WebSecurity模式