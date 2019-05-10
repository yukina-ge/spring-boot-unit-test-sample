# spring-boot-unit-test-sample

## はじめに

SpringBootのWEBアプリ開発でユニットテストを実装したサンプル。  
H2のオンメモリデータベースを使用し、flywayでマイグレートしているので起動は`gradlew bootRun`する必要があります。

## アプリについて

**テスト実行**

アプリルートディレクトリで下記実行
```
gradlew test
```

**起動**

アプリルートディレクトリで下記実行
```
gradlew bootRun
```

**アクセス**

http://localhost:8080

Hello!と出れば成功。


**エンドポイント**

* /users
* /users/{userId}

マイグレートはDDLのみなのでデータはありません。


**H2コンソール**

http://localhost:8080/h2-console

ログイン情報はapplication.ymlを参照

※テスト用のDBはテスト実行が終わると一瞬で消えるので見ることができません。

