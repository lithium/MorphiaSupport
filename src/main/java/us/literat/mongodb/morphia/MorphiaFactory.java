package us.literat.mongodb.morphia;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.Mapper;
import org.osgi.framework.BundleContext;
import org.osgi.framework.wiring.BundleWiring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 http://java.dzone.com/articles/deploying-mongodb-morphia-osgi
 */
public class MorphiaFactory {
 private BundleContext bundleContext;
 private List<String> classes;

 private Class<?> loadClass(String className) throws ClassNotFoundException {
  ClassLoader cl = ((BundleWiring)bundleContext.getBundle().adapt(
          BundleWiring.class)).getClassLoader();
  return cl.loadClass(className);
 }

 private Mapper getMapper() {
  Mapper mapper = new Mapper();
  mapper.getOptions().objectFactory = new BundleObjectFactory(bundleContext);
  return mapper;
 }

 private List<Class<?>> getClassObjects(List<String> classNames)
         throws ClassNotFoundException {
  if (classNames == null || classNames.size() == 0)
   return null;

  List<Class<?>> classObjs = new ArrayList<>();
  for (String className:classNames) {
   classObjs.add(loadClass(className));
  }
  return classObjs;
 }

 @SuppressWarnings("rawtypes")
 public Morphia get() throws ClassNotFoundException {
  Mapper mapper = getMapper();
  List<Class<?>> classObjs = getClassObjects(classes);

  Morphia morphia = new Morphia(mapper, new HashSet<Class>(classObjs));
  return morphia;
 }

 public void setBundleContext(BundleContext bundleContext) {
  this.bundleContext = bundleContext;
 }

 public void setClasses(List<String> classes) {
  this.classes = classes;
 }
}