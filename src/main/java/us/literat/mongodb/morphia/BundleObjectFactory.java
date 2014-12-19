package us.literat.mongodb.morphia;

import org.mongodb.morphia.mapping.DefaultCreator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleWiring;

/**
 http://java.dzone.com/articles/deploying-mongodb-morphia-osgi
 */
public class BundleObjectFactory extends DefaultCreator {
    private BundleContext bundleContext;

    @Override
    protected ClassLoader getClassLoaderForClass() {
        ClassLoader cl = ((BundleWiring)bundleContext.getBundle().adapt(
                BundleWiring.class)).getClassLoader();

        return cl;
    }

    public BundleObjectFactory(BundleContext bundleContext) {
        super();
        this.bundleContext = bundleContext;
    }
}
