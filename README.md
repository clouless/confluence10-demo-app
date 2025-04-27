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