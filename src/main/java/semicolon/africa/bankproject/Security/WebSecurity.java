//package semicolon.africa.bankproject.Security;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.access.intercept.AuthorizationFilter;
//import org.springframework.security.web.authentication.AuthenticationFilter;
//import semicolon.africa.bankproject.services.CustomerService;
//
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//    //
//    private  CustomerService customerService;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public WebSecurity(CustomerService customerService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.customerService = customerService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//          http.csrf().disable().authorizeRequests()
//                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
//                .permitAll().anyRequest().
//                authenticated().and().addFilter(getAuthenticationFilter())
//                  .addFilter(new AuthorizationFilter(authenticationManager()))
//                  .sessionManagement()
//                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//
//    }
//
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//    public AuthenticationFilter getAuthenticationFilter() throws Exception{
//        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
//        filter.setFilterProcessesUrl("/users/login");
//        return filter;
//    }

//}
