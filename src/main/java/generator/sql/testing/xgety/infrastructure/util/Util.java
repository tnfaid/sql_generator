package generator.sql.testing.xgety.infrastructure.util;

import generator.sql.testing.xgety.infrastructure.configuration.ConstantConfig;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Util {
	public static final Logger tdrLogger = LogManager.getLogger("tdr");
	public static final Logger debugLogger = LogManager.getLogger("debugger");

	private static final String FORMATDATEUNTILMS = "yyyy-MM-dd HH:mm:ss";
	private static final String FORMATDATE = "yyyy-MM-dd";

	private Util() {
		throw new IllegalStateException("Utility class");
	}

	public static String appendJsonObject(JsonObject data){
		StringBuilder concatenatedString = new StringBuilder();
		for (String key : data.keySet()){
			JsonElement value = data.get(key);
			concatenatedString.append(key).append("|").append(value.getAsString());
		}
		return concatenatedString.toString();
	}

	public static LocalDate convertDTOtoLocalDate(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		return LocalDate.parse(date, formatter);
	}

	public static LocalDate stringToLocalDate(String date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATDATEUNTILMS);
		return LocalDate.parse(date, formatter);
	}

	public static LocalDate getEndOfNthMonth(LocalDate startDate, int n) {
		YearMonth yearMonth = YearMonth.from(startDate);
		YearMonth futureYearMonth = yearMonth.plusMonths(n);
		return futureYearMonth.atEndOfMonth();
	}

	public static String dateEveryMonth(LocalDate date, int startDay){
		LocalDate startDate = date.withDayOfMonth(startDay);
		LocalDate endDate = startDate.plusMonths(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATDATE);
		return endDate.format(formatter);

	}

	public static Date formatStrToDate(String strDate) throws ParseException {
		if (Util.isNotEmptyOrNull(strDate)){
			SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATDATE);
			dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
			Date parsedDate = dateFormat.parse(strDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);
			calendar.add(Calendar.HOUR_OF_DAY, 7); // Add 7 hours to remove the -7 offset

			return calendar.getTime();
		}
		return null;
	}

	public static Date formatStrToDateUntilMs(String strDate) throws ParseException {
		if (Util.isNotEmptyOrNull(strDate)){
			SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATDATEUNTILMS);
			dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
			Date parsedDate = dateFormat.parse(strDate);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parsedDate);
			calendar.add(Calendar.HOUR_OF_DAY, 7); // Add 7 hours to remove the -7 offset

			return calendar.getTime();
		}
		return null;
	}

	public static String formatDateStrUntilMs(Date date) {
		if (Util.isNotEmptyOrNull(date)){
			SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATDATEUNTILMS);
			return dateFormat.format(date);
		}
		return null;
	}

	public static String formatDateStr(Date date) {
		if (Util.isNotEmptyOrNull(date)){
			SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATDATE);
			return dateFormat.format(date);
		}
		return null;
	}
	
	public static String generateTransactionId(String msisdn) {
		long startTime = System.currentTimeMillis();
		String transactionId = "";

		try {
			msisdn = msisdn.substring(msisdn.length() - 3);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String formattedDateTime = now.format(formatter);

			transactionId = ConstantConfig.getInstance().getCampaignProgramTransactionIdPrefix() + formattedDateTime + msisdn;
			Util.debugLogger.debug("ID : {}, response time{}", transactionId, System.currentTimeMillis() - startTime);
		} catch (Exception e) {
			Util.debugLogger.error("Error while generate Id For Campaign Program..", e);
		}

		return transactionId;
	}

	public static boolean isNotEmptyOrNull(Object obj) {
		if (obj == null) return false;
		if (obj instanceof String string)
			return !string.isEmpty();
		else if (obj instanceof Collection)
			return !((Collection<?>) obj).isEmpty();
		return true;
	}

	public static boolean isEmptyOrNull(Object obj) {
		if (obj == null) return true;
		if (obj instanceof String string)
			return !string.isEmpty();
		else if (obj instanceof Collection)
			return ((Collection<?>) obj).isEmpty();
		return false;
	}

	public static Throwable lastThrowable(@NonNull Throwable throwable) {
		Throwable result = throwable;

		while ((throwable = throwable.getCause()) != null) {
			result = throwable;
		}

		return result;
	}

	public static String toDelimiterString(String delimiter, Object...args){
		return Arrays.stream(args).map(String::valueOf).collect(Collectors.joining(delimiter));
	}

	public static void loggingTdr(Object...args){
		Util.tdrLogger.info(toDelimiterString("|", args));
	}

	public static Gson getGson() {
		return new GsonBuilder().setDateFormat(FORMATDATEUNTILMS).create();
	}
}
