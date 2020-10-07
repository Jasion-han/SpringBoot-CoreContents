server.port=8080

spring.resources.static-locations = classpath:/METAINF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,classpath:/templates/

#session失效时间，30m表示30分钟
server.servlet.session.timeout=30M
# Maximum number of connections that the server accepts and processes at any given time.
server.tomcat.max-connections=10000

# Maximum size of the HTTP post content.
server.tomcat.max-http-form-post-size=2048

# Maximum amount of worker threads
server.jetty.threads.max=200
