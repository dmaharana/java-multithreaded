## mvn project creation command
mvn archetype:generate -DgroupId=in.home.app -DartifactId=my-multithreaded-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

## Add following plugin to build to create runnable jar
```<plugin>
	<!-- Build an executable JAR -->
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-jar-plugin</artifactId>
	<version>3.1.0</version>
	<configuration>
		<archive>
			<manifest>
				<addClasspath>true</addClasspath>
				<classpathPrefix>lib/</classpathPrefix>
				<mainClass>in.home.app.App</mainClass>
			</manifest>
		</archive>
	</configuration>
</plugin>```

### Create jar with ```mvn package```
### Run the application with ```java -jar target/my-multithreaded-app-1.0-SNAPSHOT.jar```