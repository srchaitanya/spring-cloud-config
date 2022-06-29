The config client tells config server which props file to look up.
Two key configs
1. spring.application.name ===> tells which props name to lookup
2. spring.config.import ===> tells which config server to request

#curl localhost:8080/config/prop1
#curl localhost:8080/config/prop2
#curl localhost:8080/config/secret

Change the property for prop1, prop2 in GIT and commit and retry below urls.
#curl localhost:8080/config/prop1
#curl localhost:8080/config/prop2

No change would appear.

Use the refresh end point to refresh the changes to Git. The following is a POST method.
#curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"


Now retry below urls and the changes should appear for prop2 but not for prop1 because the "TestController2" is marked with @RefreshScope
#curl localhost:8080/config/prop1
#curl localhost:8080/config/prop2

