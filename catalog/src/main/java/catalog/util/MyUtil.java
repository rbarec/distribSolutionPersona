package catalog.util;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Contiene métodos genéricos que son utilizados en todo el servicio.
 * @author robert.macias@gizlocorp.com
 *
 */
@Log4j2
public class MyUtil {
	
	private MyUtil() {}

	/**
	 * Genera Id de transacción por medio de la librería {@link UUID}
	 * @return
	 */
	public static String generateUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Genera Id de transacción por medio de la librería {@link UUID}
	 * @return
	 */
	public static Long generateLongId() {
		long refno = 0L;

	    SecureRandom r = new SecureRandom();
	    refno = r.nextLong() & Long.MAX_VALUE;
	    return refno;
	}
	
	
	/**
	 * Realiza la obtención de la ip local de donde se está ejecutando la aplicación, 
	 * primero consulta la variable de entorno IP_HOST que puede ser agregada desde el docker compose. 
	 * Si no existe la variable de entorno la consulta de forma directa en el siguiente 
	 * metodo {@link InetAddress.getLocalHost().getHostAddress()}. Si no existe devuelve la IP 0.0.0.0
	 * @return
	 */
	public static String getLocalAddr() {
		String ipHost = "0.0.0.0";
		try {
			ipHost = System.getenv("IP_HOST");
		}catch(Exception e) {
			log.error("No se pudo obtener la variable de entorno: {}", e::getMessage);
		} 
		if(ipHost==null) {
			try {
				return InetAddress.getLocalHost().getHostAddress();
			}catch(Exception e) {
				log.error(e);
				log.error("No local address: {}", e::getMessage);
				return "0.0.0.0";
			}
		}
		return ipHost;
	}

	/**
	 * Obtiene los datos datos básicos de una excepción, tales como número de línea y 
	 * @author robert.macias@gizlocorp.com
	 *
	 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ExceptionData {
		public ExceptionData(Exception e) {
			String packageNamea = this.getClass().getPackage().getName().replace(".common.util", "");
			List<StackTraceElement> lstStackTrace = Arrays.asList(e.getStackTrace());
			List<StackTraceElement> lstFiltered = lstStackTrace.stream()
					.filter(s -> s.getClassName().contains(packageNamea)).collect(Collectors.toList());
			this.className = lstFiltered.get(0).getClassName();
			this.methodName = lstFiltered.get(0).getMethodName();
			this.lineNumber = lstFiltered.get(0).getLineNumber();
			this.msgLog = String.format("%1$s - %2$s() - Linea %3$s", className, methodName, lineNumber);
		}
		String className;
		String methodName;
		String msgLog;
		Integer lineNumber;
	}
	

	
	/**
	 * Agrega al contexto el valor transactionTime generar log
	 * @param startTransactionTime
	 */
	public static void addTransactionTime(long startTransactionTime) {
		CloseableThreadContext.put("transactionTime", (System.currentTimeMillis()-startTransactionTime)+"Ms");
	}
	
	/**
	 * Remueve del contexto el valor transactionTime generar log
	 * @param startTransactionTime
	 */
	public static void removeTransactionTime() {
		CloseableThreadContext.put("transactionTime", null);
	}

	
	/**
	 * Agrega al contexto de la ejecución variables con los valores enviados por parámetros. 
	 * Los parámetros son transactionId, externalTransactionId, operationId y remoteAddress. 
	 * Se utiliza el método {@link CloseableThreadContext.put("llave", valor)}
	 * @param transactionId
	 * @param externalTransactionId
	 * @param operationId
	 * @param remoteAddress
	 */
	public static void addDataContext(
			String transactionId, String externalTransactionId,String operationId, String remoteAddress) {
		addDataContext("transactionId",transactionId);
		addDataContext("externalTransactionId",externalTransactionId);
		addDataContext("operationId",operationId);
		addDataContext("ipClient",remoteAddress);
		addDataContext("ipServer", getLocalAddr());
		addDataContext("transactionDate", new Date().getTime()+"");
	}
	
	/**
	 * Agrega al contexto de la ejecución variables con la llave y el valor enviados 
	 * Se utiliza el método {@link CloseableThreadContext.put("llave", valor)}
	 */
	public static void addDataContext(String llave, String valor) {
		CloseableThreadContext.put(llave, valor);
	}
	
	public static String getStringDateOnlyNumbers() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	

	
	/**
	 * Retorna la fecha actual en formato yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getStringDateFullFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public static String appendTimeToDate(String dateString, long quantityTime) {
		try {
			Date actualDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			actualDate.setTime(quantityTime*1000);
			String appendedDate =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(actualDate);
			return appendedDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getValueFromThreadContext(String key) {
		return ThreadContext.get(key);
	}
	
	public static void generateLogError(Logger log, MessageSource messageSource, boolean isMessageProperty, String messageCode, Object... params) {
		String message = null;
		String correctStep = null;
		if(isMessageProperty)
			message = messageSource.getMessage(messageCode, params, Locale.getDefault());
		else 
			message = messageCode;
		String step = getDataFromContext("step");
		if(Strings.isNotBlank(step))  {
			correctStep = step;
			addDataContext("step","");	
		}
		addDataContext("correctStep",correctStep);	
		if(isMessageProperty)
			log.error(message);
		else 
			log.error(message, params);
	}
	
	public static void generateLogInfo(
			Logger log, MessageSource messageSource, 
			boolean isMessageProperty, 
			String messageCode, 
			Object... params) {
		String message = null;
		if(isMessageProperty)
			message = messageSource.getMessage(messageCode, params, Locale.getDefault());
		else 
			message = messageCode;
		String correctStep = getDataFromContext("correctStep");
		if(Strings.isNotBlank(correctStep)) {
			addDataContext("step","");	
		} else {
			String step = getDataFromContext("step");
			if(Strings.isNotBlank(step)) {
				step = (Long.parseLong(step)+1)+"";
			} else {
				step = "1";
			}
			addDataContext("step",step);	
		}
		if(isMessageProperty)
			log.info(message);
		else 
			log.info(message, params);
	}
	
	public static String getDataFromContext(String key) {
		return ThreadContext.get(key);
	}


	
}