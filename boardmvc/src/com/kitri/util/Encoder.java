package com.kitri.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoder {

	public static String isoToUtf(String tmp) {
		String utf = "";
		try {
			if (tmp != null)
				utf = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf;
	}

	public static String utfUrlFormat(String tmp) {
		String utf = "";
		try {
			if (tmp != null)
				utf = URLEncoder.encode(tmp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf;
	}

}
