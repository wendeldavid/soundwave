package inf.furb.synthesis.mbrola.comp;

import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Classe utilitária para formatação de datas e horários.
 */
public final class Calendar {

	/**
	 * Recebe uma data no formato <code>"dd/mm/yyyy"</code> retorna data por extenso.<br>
	 * 
	 * @param date
	 * @return data por extenso
	 */
	public static String formatDate(String date) {
		GregorianCalendar cal = new GregorianCalendar();
		try {
			String[] tokens = date.split("/");
			int dayOfMonth = Integer.parseInt(tokens[0]);
			int month = Integer.parseInt(tokens[1]) - 1;// janeiro == 0
			int year = Integer.parseInt(tokens[2]);
			cal = new GregorianCalendar(year, month, dayOfMonth);

		} catch (NumberFormatException e) {
			throw new RuntimeException("Invalid date format. Only number is accepted.", e);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new RuntimeException("Invalid date format \"dd/mm/yyyy\".", e);
		}

		Locale locale = null;
		for (Locale loc : GregorianCalendar.getAvailableLocales()) {
			if ("Brasil".equals(loc.getDisplayCountry())) {
				locale = loc;
				// System.out.println(loc.getLanguage() + "_" + loc.getCountry());
				break;
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(cal.getDisplayName(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.LONG, locale));
		sb.append(", ");
		sb.append(cal.get(GregorianCalendar.DAY_OF_MONTH));
		sb.append(" de ");
		sb.append(cal.getDisplayName(GregorianCalendar.MONTH, GregorianCalendar.LONG, locale));
		sb.append(" de ");
		sb.append(cal.get(GregorianCalendar.YEAR));

		return sb.toString();
	}

	/**
	 * Recebe um horário no formato "hh", "hh:mm" ou "hh:mm:ss" e retorna o horário por extenso.
	 * 
	 * @param time
	 * @return horario por extenso
	 */
	public static String formatTime(String time) {
		StringBuilder sb = new StringBuilder();
		String[] tokens = time.split(":");

		if (Integer.parseInt(tokens[0]) == 1) {
			sb.append("uma hora");
		} else if (Integer.parseInt(tokens[0]) == 2) {
			sb.append("duas horas");
		} else {
			sb.append(tokens[0]).append(" horas");
		}

		if (tokens.length >= 2) {
			sb.append(" e ").append(tokens[1]).append(" minutos");
		}
		if (tokens.length >= 3) {
			sb.append(" e ").append(tokens[2]).append(" segundos");
		}

		return sb.toString();
	}
}
