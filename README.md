# StatementOfQosts

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Lombok

Da im Projekt das Framework Lombok eingesetzt wird, müssen in IntelliJ noch folgende Einstellungen vorgenommen werden:

* Annotation Prozessor aktivieren:
  Unter `File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors`
  die Checkbox **Enable annotation processing** anhaken und die Option **Obtain processors from project classpath**
  auswählen.