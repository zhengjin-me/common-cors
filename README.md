# common-cors

![GitHub Workflow Status](https://img.shields.io/github/workflow/status/zhengjin-me/common-cors/Gradle%20Package?style=flat-square)
[![Maven Central](https://img.shields.io/maven-central/v/me.zhengjin/common-cors.svg?style=flat-square&color=brightgreen)](https://maven-badges.herokuapp.com/maven-central/me.zhengjin/common-cors/)
![GitHub](https://img.shields.io/github/license/zhengjin-me/common-cors?style=flat-square)

```
dependencies {
    implementation "me.zhengjin:common-cors:version"
}
```

```yaml
customize:
    cors:
      configs:
        - mapping: "/**"
          origins:
            - "*"
          allowCredentials: true
          allowMethods:
            - "GET"
            - "POST"
            - "PUT"
            - "OPTIONS"
            - "DELETE"
          allowHeaders:
            - "*"
          exposedHeaders:
            - "x-auth-token"
          maxAge: 300
```