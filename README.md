# Mybatis DynamicSqlSupport Generator

Generator from Java POJO for XXDynamicSqlSupport in Mybatis Dynamic Sql

# Requirements

* JDK>=8
* Gradle>=7

# Gradle

~~~groovy
implementation("xyz.codemeans.mybatis:mds-annotation:1.1")
implementation("xyz.codemeans.mybatis:mds-generator:1.1")
~~~

# Example

See:

* [`mds-generator/src/test/java/mds/example/ExampleApplication.java`](https://github.com/YuanWenqing/mds-generator/blob/main/mds-generator/src/test/java/mds/example/ExampleApplication.java) 
* [`mds-generator/src/test/java/mds/example/ExampleMain.java`](https://github.com/YuanWenqing/mds-generator/blob/main/mds-generator/src/test/java/mds/example/ExampleMain.java) 
* [`mds-generator/src/test/resources/application.properties`](https://github.com/YuanWenqing/mds-generator/blob/main/mds-generator/src/test/resources/application.properties) 

Usage: 

1. Import `mds-annotation` into model module, and then annotated classes to generate Mybatis DynamicSqlSupport from.
2. Import `mds-generator` into some module, and then make a Spring Application class like the `ExampleApplication` or just a main class to initialize`GeneratorRunner` corretly like `ExampleMain`.
3. Config some generation tasks for the generator.
4. Run the main class, and you'll get all needed DynamicSqlSupport classes generated!😁

Download this project and run the `ExampleApplication` for a try.

