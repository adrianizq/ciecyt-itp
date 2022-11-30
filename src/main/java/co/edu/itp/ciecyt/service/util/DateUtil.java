package co.edu.itp.ciecyt.service.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.SimpleTimeZone;


/**
 * @author JLT
 * @version 1.0
 */
public class DateUtil
{

	public static final long DAY = 86400000; //1000 * 60 * 60 * 24; 86400000 milisegundos
	private static final long MS_HR = 3600000;
	private static final long MS_MIN = 60000;
	private static final long MS_SEC = 1000;


	private static DateUtil date;
	private DateUtil()
	{

	}

	public static DateUtil getInstance()
	{
		if (date == null)
			date = new DateUtil();
		return date;

	}

	/**
	 *
	 * @param format Formato que se le desea dar a la fecha
	 * @param locale Localizaci�n espec�fica para determinar el formato de la fecha
	 * @param date Fecha a tranformar al nuevo formato
	 * @return fecha
	 */
	public String format(String format, Locale locale, Object date)
	{
		String s_date = null;
		if(date==null)
			return "";

		DateFormat df = new SimpleDateFormat(format,
				locale);
		try
		{
			Date objectDate  = null;
			if( !(date instanceof Date || date instanceof java.sql.Date) ){
				objectDate = DateFormat.getInstance().parse(date.toString());
			}
			else
				objectDate = (Date)date;

			s_date = df.format(objectDate);

		}
		catch (Exception ex)
		{

			s_date = date.toString();
		}

		return s_date;
	}

	/**
	 *
	 * @param format Formato que se le desea dar a la fecha
	 * @param date date Fecha a tranformar al nuevo formato
	 * @return fecha
	 */
	public String format(String format, Object date)
	{
		String s_date = null;
		if(date==null)
			return "";
		DateFormat df = new SimpleDateFormat(format);
		s_date = df.format(date);
		return s_date;
	}

	public boolean isBeforeDate(Date source, Date anotherDate)
	{
		return source.before(anotherDate);

	}

	public boolean isAfterDate(Date source, Date anotherDate)
	{
		return source.after(anotherDate);
	}

	public boolean isBeforeDate(String format, String sourceDate, String s_anotherDate)
	{
		DateFormat df = new SimpleDateFormat(format);
		try
		{
			Date source = df.parse(sourceDate);
			Date anotherDate = df.parse(s_anotherDate);
			return source.before(anotherDate);
		}
		catch (ParseException ex)
		{
			ex.printStackTrace();
			return false;
		}

	}

	public boolean isAfterDate(String format, String sourceDate, String s_anotherDate)
	{
		DateFormat df = new SimpleDateFormat(format);
		try
		{
			Date source = df.parse(sourceDate);
			Date anotherDate = df.parse(s_anotherDate);
			return source.after(anotherDate);
		}
		catch (ParseException ex)
		{
			ex.printStackTrace();
			return false;
		}

	}

	public Date parse(String format,String s_date)
			throws ParseException
	{
		Date date = null;
		DateFormat df = new SimpleDateFormat(format);
		date = df.parse(s_date);
		return date;
	}

	public Date parse(String s_date)
			throws ParseException
	{
		Date date = null;
		DateFormat df = new SimpleDateFormat();
		date = df.parse(s_date);
		return date;
	}


	public int differenceDaysWeek(Date initDate, Date finalDate)
	{

		Calendar.getInstance().setTime(finalDate);
		int dayFinal = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		Calendar.getInstance().setTime(initDate);
		int dayInit = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		return dayFinal - dayInit;

	}

	public int differenceDaysWeek(String format, String dateInit, String dateFinal)
	{
		int difference = -1;
		Date initDate = null;
		Date finalDate = null;
		try
		{
			initDate = parse(format,dateInit);
			finalDate = parse(format,dateFinal);
			if(finalDate.getTime() - initDate.getTime() > DAY*7)
				return -1;
		}
		catch (ParseException ex)
		{
			return -1;
		}
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c.setTime(initDate);
		c2.setTime(finalDate);
		int dayFinal = c.get(Calendar.DAY_OF_WEEK);

		int dayInit = c2.get(Calendar.DAY_OF_WEEK);

		difference = dayFinal - dayInit;
		return difference;

	}
	public int differenceDaysMonth(String format, String dateInit, String dateFinal)
	{
		int difference = -1;
		Date initDate = null;
		Date finalDate = null;
		try
		{
			initDate = parse(format,dateInit);
			finalDate = parse(format,dateFinal);

		}
		catch (ParseException ex)
		{
			return -1;
		}
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c.setTime(initDate);
		c2.setTime(finalDate);
		if(c2.get(Calendar.MONTH) != c.get(Calendar.MONTH))
			return -1;
		int dayFinal = c.get(Calendar.DAY_OF_MONTH);

		int dayInit = c2.get(Calendar.DAY_OF_MONTH);

		difference = dayFinal - dayInit;
		return difference;

	}

	public long differenceDays(String format, String dateInit, String dateFinal)
	{
		long difference = -1;
		Date initDate = null;
		Date finalDate = null;
		try
		{
			initDate = parse(format,dateInit);
			finalDate = parse(format,dateFinal);

		}
		catch (ParseException ex)
		{
			return -1;
		}

		long dayFinal = finalDate.getTime();
		long dayInit = initDate.getTime();
		if(dayFinal > dayInit)
			difference = dayFinal - dayInit;
		else
			return -1;

		return difference;

	}

	public String getDateCurrent(String format, Locale locale)
	{
		String date = null;
		DateFormat df = new SimpleDateFormat(format,
				locale);
		Date current = GregorianCalendar.getInstance().getTime();

		date = df.format(current);

		return date;
	}

	public Date getDateCurrent()
	{

		Date current = GregorianCalendar.getInstance().getTime();

		return current;
	}

	public String getDateCurrent(String format)
	{
		String date = null;
		DateFormat df = new SimpleDateFormat(format);
		Date current = GregorianCalendar.getInstance().getTime();

		date = df.format(current);

		return date;
	}

	public String timeConverter(long time)
	{
		long hours = 0;
		long mins = 0;
		long secs = 0;



		long remainder = 0;



		if(time <MS_HR)
		{
			hours = 0;
		}
		else
		{
			hours = time / MS_HR;
			remainder = time % MS_HR;
		}

		if(remainder <MS_MIN)
		{
			mins = 0;
		}
		else
		{
			mins = remainder / MS_MIN;
			remainder = remainder % MS_MIN;
		}

		if(remainder <MS_SEC)
		{
			secs = 0;
		}
		else
		{
			secs = remainder / MS_SEC;
			remainder = remainder % MS_SEC;
		}


		DecimalFormat df = new DecimalFormat("00");

		return String.valueOf(df.format(hours)+":"+df.format(mins)+":"+df.format(secs));

	}

	public Timestamp getCurrentTimestamp(){
		return new Timestamp(getDateCurrent().getTime() );
	}
	public static String getTimestampGMT( Date date ){

		String gmtDate = "";
		SimpleDateFormat format = new 
				SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String s_date = format.format( date );
		Calendar cal = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
		format.setCalendar(cal);
		try {
			date = format.parse( s_date );
			System.out.println( "GMT: "+ date );
			//Java procesa las fechas time stamp: segundos desde 1970, en nanosegundos.
			gmtDate = String.valueOf(date.getTime() / 1000);			

		} catch (ParseException e) {

		}
		return gmtDate;
	}

	public String getTimestamp( Date date ){

		System.out.println( "Date: "+ date );
		String gmtDate = "";
		SimpleDateFormat format = new 
				SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String s_date = format.format( date );
		try {
			date = format.parse( s_date );
			//Java procesa las fechas time stamp: segundos desde 1970, en nanosegundos.
			gmtDate = String.valueOf(date.getTime() / 1000);			

		} catch (ParseException e) {

		}
		return gmtDate;
	}

	public long getHoursDifference(Date init, Date end ){
		long hours = 0;
		if(init != null && end != null){
			long tinit = init.getTime();
			long tend = end.getTime();
			
			long diff = tend - tinit;
			
			if(diff <MS_HR)
			{
				hours = 0;
			}
			else
			{
				hours = diff / MS_HR;
				
			}
			
		}
		return hours;
	}
	
	public long getMinutesDifference(Date init, Date end ){
		long hours = 0;
		if(init != null && end != null){
			long tinit = init.getTime();
			long tend = end.getTime();
			
			long diff = tend - tinit;
			
			if(diff <MS_MIN)
			{
				hours = 0;
			}
			else
			{
				hours = diff / MS_MIN;
				
			}
			
		}
		return hours;
	}
	
	public long getDaysDifference(Date init, Date end ){
		long days = -1;
		if(init != null && end != null){
			long tinit = init.getTime();
			long tend = end.getTime();
			
			long diff = tend - tinit;
			
			if(diff < DAY)
			{
				days = 1;
			}
			else
			{
				days = diff / DAY;
				
			}
			
		}
		return days;
	}
	
	public long getMilisDifference(Date init, Date end ){
		long diff = 0;
		if(init != null && end != null){
			long tinit = init.getTime();
			long tend = end.getTime();
			
			diff = tend - tinit;
		}
		return diff;
	}



	public static void main (String args[])
	{
		Date init = GregorianCalendar.getInstance().getTime();

		System.out.println(DateUtil.getInstance().format("yy-MM-dd hh:mm:ss",init));

		long end = init.getTime() + 125847635;

		Long time = new Long(end - init.getTime());
		
		Date initDate = null;
		try {
			initDate = DateUtil.getInstance().parse("yyyy-MM-dd HH:mm:ss", "2016-08-01 8:30:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(DateUtil.getInstance().timeConverter(time.longValue()));
		
		System.out.println("Horas: "+ DateUtil.getInstance().getHoursDifference(initDate, init));

		System.out.println( new Time( init.getTime()));
		System.out.println( "TimeStamp: " + DateUtil.getInstance().getTimestamp( init ));

		System.out.println( "Timestamp GTM : " +DateUtil.getInstance().getTimestampGMT( init ));
	}




}
