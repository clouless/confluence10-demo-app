# confluence10-demo-app

### Test Endpoints


**Ini Parser Test**

Test apache commons configutation2 ini parser:

```
curl -u admin:admin http://$CONFLUENCE:8090/rest/ctendemo/1/ini/config
```

expected result

```json
["foo","bar"]
```

See `src/main/java/io/codeclou/ctendemo/rest/IniEndpoint.java`


----

**Vanilla HTTP Client**

Uses Java11+ Http Client instead of apache commons HTTP Client

```
curl -u admin:admin http://$CONFLUENCE:8090/rest/ctendemo/1/httpclient/download
```

expected result

```
prints license txt
```


----

**Health Endpoint and logging test**

Performs a `log.error()` call when called.

```
curl -u admin:admin http://$CONFLUENCE:8090/rest/ctendemo/1/health/test
```

expected result: message in the confluence log

```
2025-04-28 06:55:02,267 ERROR [http-nio-8090-exec-9] [codeclou.ctendemo.rest.HealthEndpoint] getAtlassianConfluenceLog TEST_LOGGING
 -- url: /rest/ctendemo/1/health/test | userName: admin | traceId: dc7bf1b039662ea7
```