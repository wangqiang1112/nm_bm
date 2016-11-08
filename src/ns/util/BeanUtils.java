package ns.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);
	static Hashtable<Class<?>, Method[]> objectMethods = new Hashtable<Class<?>, Method[]>();

	private static void handleReflectionException(Exception e) {
		ReflectionUtils.handleReflectionException(e);
	}

	public static Object cloneBean(Object bean) {
		try {
			return org.apache.commons.beanutils.BeanUtils.cloneBean(bean);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static <T> T copyProperties(Class<T> destClass, Object orig) {
		try {
			T target = destClass.newInstance();
			copyProperties(target, orig);
			return target;
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			ConvertUtils.register(new DateConverter(null), Date.class);
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static void copyProperty(Object bean, String name, Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperty(bean, name,
					value);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static Map describe(Object bean) {
		try {
			return org.apache.commons.beanutils.BeanUtils.describe(bean);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String[] getArrayProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getArrayProperty(
					bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getIndexedProperty(Object bean, String name, int index) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getIndexedProperty(
					bean, name, index);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getIndexedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getIndexedProperty(
					bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getMappedProperty(Object bean, String name, String key) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getMappedProperty(
					bean, name, key);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getMappedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getMappedProperty(
					bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getNestedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getNestedProperty(
					bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getProperty(bean,
					name);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static String getSimpleProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getSimpleProperty(
					bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static void populate(Object bean, Map properties) {
		try {
			org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static void setProperty(Object bean, String name, Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(bean, name,
					value);
		} catch (Exception e) {
			setPropertyValue(bean, name, value, true);
		}
	}

	public static void setPropertyValue(Object src, Object dest, String name) {
		setProperty(dest, name, getPropertyValue(src, name));
	}

	public static void setPropertyValue(Object src, Object dest,
			String srcName, String destName) {
		setProperty(dest, destName, getPropertyValue(src, srcName));
	}

	public static boolean setPropertyValue(Object o, String name, Object value,
			boolean invokeSetProperty) {
		if (Map.class.isAssignableFrom(o.getClass())) {
			((Map) o).put(name, value);
			return true;
		}
		if (log.isDebugEnabled()) {
			log.debug("IntrospectionUtils: setProperty(" + o.getClass() + " "
					+ name + "=" + value + ")");
		}
		String setter = "set" + capitalize(name);
		try {
			Method[] methods = findMethods(o.getClass());
			Method setPropertyMethodVoid = null;
			Method setPropertyMethodBool = null;

			for (int i = 0; i < methods.length; i++) {
				Class[] paramT = methods[i].getParameterTypes();
				if (paramT.length != 0) {
					if ((setter.equals(methods[i].getName()))
							&& (paramT.length == 1)
							&& (value.getClass().equals(paramT[0]))) {
						methods[i].invoke(o, new Object[] { value });
						return true;
					}
				}
			}
			if ((invokeSetProperty)
					&& ((setPropertyMethodBool != null) || (setPropertyMethodVoid != null))) {
				Object[] params = new Object[2];
				params[0] = name;
				params[1] = value;
				if (setPropertyMethodBool != null) {
					try {
						return ((Boolean) setPropertyMethodBool.invoke(o,
								params)).booleanValue();
					} catch (IllegalArgumentException biae) {
						if (setPropertyMethodVoid != null) {
							setPropertyMethodVoid.invoke(o, params);
							return true;
						}
						throw biae;
					}
				}

				setPropertyMethodVoid.invoke(o, params);
				return true;
			}
		} catch (IllegalArgumentException ex2) {
			log.warn("IAE " + o + " " + name + " " + value, ex2);
		} catch (SecurityException ex1) {
			if (log.isDebugEnabled())
				log.debug(
						"IntrospectionUtils: SecurityException for "
								+ o.getClass() + " " + name + "=" + value + ")",
						ex1);
		} catch (IllegalAccessException iae) {
			if (log.isDebugEnabled())
				log.debug(
						"IntrospectionUtils: IllegalAccessException for "
								+ o.getClass() + " " + name + "=" + value + ")",
						iae);
		} catch (InvocationTargetException ie) {
			if (log.isDebugEnabled())
				log.debug("IntrospectionUtils: InvocationTargetException for "
						+ o.getClass() + " " + name + "=" + value + ")", ie);
		}
		return false;
	}

	public static Object invokeMethod(Object target, String method,
			Object[] args) {
		try {
			return MethodUtils.invokeMethod(target, method, args);
		} catch (Exception e) {
			handleReflectionException(e);
		}
		return null;
	}

	public static byte[] serialize(Object obj) throws IOException {
		byte[] byteArray = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream out = null;
		try {
			baos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(baos);
			out.writeObject(obj);
			byteArray = baos.toByteArray();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return byteArray;
	}

	public static Object getPropertyValue(Object o, String name) {
		if (Map.class.isAssignableFrom(o.getClass())) {
			return ((Map) o).get(name);
		}
		String getter = "get" + capitalize(name);
		String isGetter = "is" + capitalize(name);
		try {
			Method[] methods = findMethods(o.getClass());
			Method getPropertyMethod = null;
			for (int i = 0; i < methods.length; i++) {
				Class[] paramT = methods[i].getParameterTypes();
				if ((getter.equals(methods[i].getName()))
						&& (paramT.length == 0)) {
					return methods[i].invoke(o, (Object[]) null);
				}
				if ((isGetter.equals(methods[i].getName()))
						&& (paramT.length == 0)) {
					return methods[i].invoke(o, (Object[]) null);
				}

				if ("getProperty".equals(methods[i].getName())) {
					getPropertyMethod = methods[i];
				}
			}
			if (getPropertyMethod != null) {
				Object[] params = new Object[1];
				params[0] = name;
				return getPropertyMethod.invoke(o, params);
			}
		} catch (IllegalArgumentException ex2) {
			log.warn("IAE " + o + " " + name, ex2);
		} catch (SecurityException ex1) {
			if (log.isDebugEnabled())
				log.debug(
						"IntrospectionUtils: SecurityException for "
								+ o.getClass() + " " + name + ")", ex1);
		} catch (IllegalAccessException iae) {
			if (log.isDebugEnabled())
				log.debug(
						"IntrospectionUtils: IllegalAccessException for "
								+ o.getClass() + " " + name + ")", iae);
		} catch (InvocationTargetException ie) {
			if (log.isDebugEnabled())
				log.debug("IntrospectionUtils: InvocationTargetException for "
						+ o.getClass() + " " + name + ")");
		}
		return null;
	}

	public static Method findMethod(Class<?> c, String name, Class<?>[] params) {
		Method[] methods = findMethods(c);
		if (methods == null)
			return null;
		for (int i = 0; i < methods.length; i++)
			if (methods[i].getName().equals(name)) {
				Class[] methodParams = methods[i].getParameterTypes();
				if ((methodParams == null)
						&& ((params == null) || (params.length == 0)))
					return methods[i];
				if ((params == null)
						&& ((methodParams == null) || (methodParams.length == 0)))
					return methods[i];
				if (params.length == methodParams.length) {
					boolean found = true;
					for (int j = 0; j < params.length; j++) {
						if (params[j] != methodParams[j]) {
							found = false;
							break;
						}
					}
					if (found)
						return methods[i];
				}
			}
		return null;
	}

	public static Method[] findMethods(Class<?> c) {
		Method[] methods = objectMethods.get(c);
		if (methods != null) {
			return methods;
		}
		methods = c.getMethods();
		objectMethods.put(c, methods);
		return methods;
	}

	public static String capitalize(String name) {
		if ((name == null) || (name.length() == 0)) {
			return name;
		}
		char[] chars = name.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return new String(chars);
	}
}