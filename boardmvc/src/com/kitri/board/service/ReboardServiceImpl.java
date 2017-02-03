package com.kitri.board.service;

import java.util.*;

import com.kitri.board.dao.ReboardDaoImpl;
import com.kitri.board.model.ReboardDto;
import com.kitri.common.service.CommonServiceImp;
import com.kitri.util.BoardConstance;

public class ReboardServiceImpl implements ReboardService {

	private static ReboardService reboardService;

	static {
		reboardService = new ReboardServiceImpl();
	}

	private ReboardServiceImpl() {
	}

	public static ReboardService getReboardService() {
		return reboardService;
	}

	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int seq = CommonServiceImp.getCommonService().getNextSeq();
		reboardDto.setSeq(seq);
		return ReboardDaoImpl.getReboardDao().writeArticle(reboardDto) != 0 ? seq : 0;
	}

	@Override
	public int replyArticle(ReboardDto reboardDto) {
		int seq = CommonServiceImp.getCommonService().getNextSeq();
		reboardDto.setSeq(seq);
		return ReboardDaoImpl.getReboardDao().replyArticle(reboardDto) != 0 ? seq : 0;
	}

	@Override
	public List<ReboardDto> listArticle(int bcode, int pg, String key, String word) {
		int end = pg * BoardConstance.ARTICLE_OF_LIST;
		int start = end - BoardConstance.ARTICLE_OF_LIST;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bcode", bcode + "");
		map.put("start", start + "");
		map.put("end", end + "");
		map.put("key", key);
		map.put("word", word);
		return ReboardDaoImpl.getReboardDao().listArticle(map);
	}

	@Override
	public ReboardDto getArticle(int seq) {
		return ReboardDaoImpl.getReboardDao().getArticle(seq);
	}

	@Override
	public int modifyArticle(ReboardDto reboardDto) {
		return ReboardDaoImpl.getReboardDao().modifyArticle(reboardDto) != 0 ? reboardDto.getSeq() : 0;
	}

	@Override
	public int deleteArticle(int seq, int reply, int pseq) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("seq", seq + "");
		map.put("reply", reply + "");
		map.put("pseq", pseq + "");
		return ReboardDaoImpl.getReboardDao().deleteArticle(map);
	}
}
