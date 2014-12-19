
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



Using in your blueprint/spring xml:

      <bean id="mongoClient" class="com.mongodb.MongoClient">
        <argument value="${db.mongo.host}"/>
        <argument value="${db.mongo.port}"/>
      </bean>

      <bean id="morphia" factory-ref="morphiaFactory" factory-method="get"/>
      <bean id="morphiaFactory" class="us.literat.mongodb.morphia.MorphiaFactory">
        <property name="bundleContext" ref="blueprintBundleContext"/>
        <property name="classes">
          <list>
            <value>com.example.mongodb.entities.EntityPojo</value>
          </list>
        </property>
      </bean>

      <bean id="linksDb" factory-ref="datastoreFactory" factory-method="get"/>
      <bean id="datastoreFactory" class="us.literat.mongodb.morphia.DatastoreFactory">
        <property name="mongoClient" ref="mongoClient"/>
        <property name="morphia" ref="morphia"/>
        <property name="database" value="${db.mongo.database}"/>
      </bean>
