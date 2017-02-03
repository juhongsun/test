package com.kitri.common.service;

import com.kitri.util.PageNavigation;

public interface CommonService {
	
	int getNextSeq();
	void updateHit(int seq);
	
	PageNavigation getPageNavigation(int bcode, int pg, String key, String word);
}
