
support bundle for deploying mongodb morphia in osgi

taken from: http://java.dzone.com/articles/deploying-mongodb-morphia-osgi


To use, add to your pom.xml:

      <dependency>
        <groupId>us.literat.mongodb</groupId>
        <artifactId>MorphiaSupport</artifactId>
        <version>1.0</version>
      </dependency>

      <repositories
        <repository>
          <id>MorphiaSupport-mvn-repo</id>
          <url>https://raw.github.com/lithium/MorphiaSupport/mvn-repo/</url>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
          </snapshots>
        </repository>
      </repositories>


To deploy the features to karaf:

	feature:repo-add mvn:us.literat.mongodb/MorphiaSupport/1.0/xml/features
	feature:install morphia-support
