package ns.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil
{
  public static Object getValue(Object bean, String name)
  {
    return BeanUtils.getPropertyValue(bean, name);
  }

  public static Object getValueNoException(Object bean, String colName)
  {
    try
    {
      return BeanUtils.getPropertyValue(bean, colName);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static void setValue(Object source, Object tag, String colName)
  {
    BeanUtils.setPropertyValue(source, tag, colName);
  }

  public static void setValue(Object source, Object tag, String sColName, String tColName)
  {
    String setMethodName = "set" + tColName.substring(0, 1).toUpperCase() + tColName.substring(1);
    String getMethodName = "get" + sColName.substring(0, 1).toUpperCase() + sColName.substring(1);
    try
    {
      Method getMethod = source.getClass().getMethod(getMethodName, new Class[0]);
      if (null == getMethod) {
        throw new RuntimeException("无效字段");
      }
      Class c = getMethod.getReturnType();

      Method setMethod = tag.getClass().getMethod(setMethodName, new Class[] { c });

      if (null == setMethod)
        throw new RuntimeException("无效字段");
      Object value = getMethod.invoke(source, new Object[0]);
      setMethod.invoke(tag, new Object[] { value });
    }
    catch (NoSuchMethodException e) {
      throw new RuntimeException("无效字段:" + e.getMessage());
    } catch (IllegalAccessException e) {
      throw new RuntimeException("无效字段:" + e.getMessage());
    } catch (InvocationTargetException e) {
      throw new RuntimeException("无效字段:" + e.getMessage());
    }
  }

  public static void setValue(Object obj, String colName, Object defObj) {
    setObjValue(obj, colName, defObj);
  }

  public static void setObjValue(Object obj, String colName, Object valueObj)
  {
    if (null == valueObj) {
      return;
    }
    String setMethodName = "set" + colName.substring(0, 1).toUpperCase() + colName.substring(1);
    String getMethodName = "get" + colName.substring(0, 1).toUpperCase() + colName.substring(1);
    try {
      Method getMethod = obj.getClass().getMethod(getMethodName, new Class[0]);
      if (null == getMethod) {
        throw new RuntimeException("无效字段");
      }
      Class c = getMethod.getReturnType();

      Method setMethod = obj.getClass().getMethod(setMethodName, new Class[] { c });

      if (null == setMethod) {
        throw new RuntimeException("无效字段");
      }
      setMethod.invoke(obj, new Object[] { valueObj });
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("无效字段:" + e.getMessage());
    } catch (IllegalAccessException e) {
      throw new RuntimeException("无效字段:" + e.getMessage());
    } catch (InvocationTargetException e) {
      throw new RuntimeException("无效字段:" + e.getMessage());
    }
  }

  public static Map<String, Method> getMethods(Class<?> clas, String methodNameHead)
  {
    Map map = new HashMap();
    Method[] methods = clas.getDeclaredMethods();

    for (Method m : methods) {
      String mname = m.getName();
      if (mname.startsWith(methodNameHead))
      {
        Class[] cs = m.getParameterTypes();
        if (cs.length == 1)
        {
          map.put(mname, m);
        }
      }
    }
    return map;
  }

  public static Object convertValue(Class<?> type, String value)
  {
    if (null == value) {
      return null;
    }
    String tn = type.getName();

    if ("java.lang.String".equals(tn)) {
      return value;
    }
    if ("java.lang.Integer".equals(tn)) {
      return Integer.valueOf(Integer.parseInt(value));
    }
    if ("int".equals(tn)) {
      return Integer.valueOf(Integer.parseInt(value));
    }
    if ("java.lang.Double".equals(tn)) {
      return Double.valueOf(Double.parseDouble(value));
    }
    if ("double".equals(tn)) {
      return Double.valueOf(Double.parseDouble(value));
    }

    return null;
  }

  public static Object loadClass(String fullClass)
  {
    try
    {
      return Class.forName(fullClass).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }

  public static Class<?> loadClassType(String fullClass)
  {
    try
    {
      return Class.forName(fullClass);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}