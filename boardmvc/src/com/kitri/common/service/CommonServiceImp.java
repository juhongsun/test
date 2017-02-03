package com.kitri.common.service;

import java.util.HashMap;
import java.util.Map;

import com.kitri.common.dao.CommonDaoImpl;
import com.kitri.util.BoardConstance;
import com.kitri.util.PageNavigation;

public class CommonServiceImp implements CommonService {

	private static CommonService commonService;

	static {
		commonService = new CommonServiceImp();
	}

	private CommonServiceImp() {
	}

	public static CommonService getCommonService() {
		return commonService;
	}

	@Override
	public int getNextSeq() {
		return CommonDaoImpl.getCommonDao().getNextSeq();
	}

	@Override
	public void updateHit(int seq) {
		CommonDaoImpl.getCommonDao().updateHit(seq);
	}

	@Override
	public PageNavigation getPageNavigation(int bcode, int pg, String key, String word) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("bcode", bcode + "");
		map.put("key", key);
		map.put("word", word);
		
		PageNavigation navigation = new PageNavigation();
		int newArticleCount = CommonDaoImpl.getCommonDao().getNewArticleCount(bcode);
		navigation.setNewArticleCount(newArticleCount);
		int totalArticleCount = CommonDaoImpl.getCommonDao().getTotalArticleCount(map);
		navigation.setTotalArticleCount(totalArticleCount);
		int totalPageCount = (totalArticleCount - 1) / BoardConstance.ARTICLE_OF_LIST + 1;
		navigation.setTotalPageCount(totalPageCount);
		navigation.setPageNo(pg);
		navigation.setNowFirst(pg <= BoardConstance.COUNT_OF_PAGE);
		int pgc = BoardConstance.COUNT_OF_PAGE;
		navigation.setNowEnd((totalPageCount - 1) / pgc == (pg - 1) / pgc);
		return navigation;
	}

}
