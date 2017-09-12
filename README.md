# 基于spring的@configuration注解进行无xml配置文件实现web mvc工程

1. Spring框架提供了多种灵活的方式配置Bean。例如XML、注解和Java配置。随着功能数量的增加，复杂性也随之增加，配置Spring应用将变得乏味而且容易出错。
2. @Configuration注解就相当于传统springmvc项目中的一个配置文件
3. @Bean注解就相当于传统spring项目配置文件中的<bean></bean>配置

# 配置解析

1. AppConfig.java
  * 使用@Configuration注解标记为一个Spring配置类。
  * 使用@EnableTransactionManagement开启基于注解的事务管理。
  * 配置@EnableJpaRepositories的basePackages属性指定去哪查找Spring Data JPA资源库（repository）。
  * 使用@PropertySource注解和PropertySourcesPlaceholderConfigurer Bean定义配置PropertyPlaceHolder bean从classpath下的config.properties文件加载配置。
  * 为DataSource、JAP的EntityManagerFactory和JpaTransactionManager定义Bean
  * 配置DataSourceInitializer Bean，在应用启动时，执行data.sql脚本来初始化数据库。
  
2. WebMvcConfig.java
  * 使用@Configuration注解标记为一个Spring配置类。
  * 使用@EnableWebMvc注解启用基于注解的Spring MVC配置。
  * 通过注册TemplateResolver、SpringTemplateEngine和`hymeleafViewResolver Bean来配置Thymeleaf视图解析器。
  * 注册ResourceHandler Bean将以URI为/resource/**的静态资源请求定位到/resource/目录下。
  
3. 注册Spring MVC的前端控制器DispatcherServlet
  * 在Servlet 3.x规范之前，我们必须在web.xml中注册Servlet/Filter.但Servlet 3.x之后的规范，我们可以使用ServletContainerInitializer以编程的方式注册Servlet/Filter。
  * 基于java-config的spring mvc,我们一般都会继承AbstractAnnotationConfigDispatcherServletInitializer(它当然是WebApplicationInitializer的实现类)来写一个Initializer.这个Initializer最基本的就帮我们注册了DispatcherServlet.
  * 本文中我们使用SpringWebInitializer.java来注册DispatcherServlet，其实该文件就相当于传统工程中的web.xml
4. SpringWebInitializer.java
  * 我们将AppConfig.class配置为RootConfigurationClass，它将成为包含了所有子上下文（DispatcherServlet）共享的Bean定义的父ApplicationContext。
  * 我们将WebMvcConfig.class配置为ServletConfigClass，它是包含了WebMvc Bean定义的子ApplicationContext。
  * 我们将/配置为ServletMapping，这意味所有的请求将由DispatcherServlet处理。
  * 我们将OpenEntityManagerInViewFilter注册为Servlet过滤器，以便我们在渲染视图时可以延迟加载JPA Entity的延迟集合。
  
5. 注意：
  项目启动时，**要求tomcat的版本要在7.0及以上，以下的servlet版本不是3.x之后的版本导致项目不能运行。**

# 说明

通过本文我们其实是实现了完全无xml化的配置web工程，这也是springboot的前身，在没有springboot出现之前，我们每次创建项目的时候都要重复这一套东西，虽然没有了很繁琐的配置，但还是不够轻便，所以这个时候springboot应运而生，它帮我们做掉了这大部分工作，而且有很多插件可以让我们开箱即用，简直不能再爽一点。
也正是因为springboot简化了大部分配置操作，对应像微服务这样需要创建很多工程才能满足系统的场景，springboot无疑是最合适的。
