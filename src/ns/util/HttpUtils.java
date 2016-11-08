package ns.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.WebUtils;

public class HttpUtils extends WebUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(HttpUtils.class);

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static String[] getParameterValues(String name, Whitelist whitelist) {
		String[] values = getRequest().getParameterValues(name);
		if (ArrayUtils.isEmpty(values)) {
			return values;
		}
		return getParameterValues(name, values, whitelist);
	}

	public static String[] getParameterValues(String name, String[] values,
			Whitelist whitelist) {
		return getParameterValues(name, values, whitelist,
				Boolean.valueOf(false));
	}

	public static String[] getParameterValues(String name, String[] values,
			Whitelist whitelist, Boolean escape) {
		if ((null == values) || (values.length == 0)) {
			return values;
		}
		List<String> list = new ArrayList<String>();
		for (String value : values) {
			list.add(clean(name, value, whitelist, escape.booleanValue()));
		}
		return list.toArray(new String[0]);
	}

	public static String clean(String name, String value, Whitelist whitelist) {
		return clean(name, value, whitelist, false);
	}

	public static String clean(String name, String value, Whitelist whitelist,
			boolean escapeHTML) {
		if ((org.apache.commons.lang3.StringUtils.isEmpty(value))
				|| (null == whitelist)) {
			return value;
		}
		String val = Jsoup.clean(value, whitelist);
		if (escapeHTML) {
			val = HtmlUtils.htmlEscape(val);
		}
		if (!val.equals(value)) {
			logger.warn("parameter({}) value is unsafe:{} ,be cleaned : {}",
					new Object[] { name, value, val });
		}

		return val;
	}

	public static String getParameter(String name) {
		return (String) getParameter(name, "");
	}

	@SuppressWarnings("unchecked")
	public static <T> T getParameter(String name, Object sDefault) {
		return (T)getParameter(name, sDefault, Whitelist.basic());
	}

	@SuppressWarnings("unchecked")
	public static <T> T getParameter(String name, Object sDefault,
			Whitelist whitelist) {
		return (T)getParameter(name, sDefault, whitelist, false);
	}

	public static <T> T getParameter(String name, Object sDefault,
			Whitelist whitelist, boolean escape) {
		String value = getRequest().getParameter(name);
		if (null == value) {
			return (T) sDefault;
		}
		String val = clean(name, value, whitelist);
		return (T) convert(val, sDefault.getClass());
	}

	public static <T> T convert(Object o, Class<T> clas) {
		try {
			return (T) ConvertUtils.convert(o.toString(), clas);
		} catch (Exception e) {
		}
		return (T) o;
	}

}