package com.kitri.factory;

import com.kitri.action.Action;
import com.kitri.admin.board.action.*;
import com.kitri.album.action.*;
import com.kitri.reboard.action.*;

public class BoardActionFactory {

	private static Action boardMenuAction;
	private static Action boardTypeListAction;
	private static Action boardTypeMakeAction;
	private static Action categoryListAction;
	private static Action categoryMakeAction;

	private static Action reboardListAction;
	private static Action reboardReplyAction;
	private static Action reboardMoveReplyAction;
	private static Action reboardModifyAction;
	private static Action reboardMoveModifyAction;
	private static Action reboardDeleteAction;
	private static Action reboardViewAction;
	private static Action reboardWriteAction;
	
	private static Action albumListAction;
	private static Action albumModifyAction;
	private static Action albumMoveModifyAction;
	private static Action albumDeleteAction;
	private static Action albumViewAction;
	private static Action albumWriteAction;

	static {
		boardMenuAction = new BoardMenuAction();
		boardTypeListAction = new BoardTypeListAction();
		boardTypeMakeAction = new BoardTypeMakeAction();
		categoryListAction = new CategoryListAction();
		categoryMakeAction = new CategoryMakeAction();

		reboardListAction = new ReboardListAction();
		reboardReplyAction = new ReboardReplyAction();
		reboardMoveReplyAction = new ReboardMoveReplyAction();
		reboardModifyAction = new ReboardModifyAction();
		reboardMoveModifyAction = new ReboardMoveModifyAction();
		reboardDeleteAction = new ReboardDeleteAction();
		reboardViewAction = new ReboardViewAction();
		reboardWriteAction = new ReboardWriteAction();
		
		albumListAction = new AlbumListAction();
		albumModifyAction = new AlbumModifyAction();
		albumMoveModifyAction = new AlbumMoveModifyAction();
		albumDeleteAction = new AlbumDeleteAction();
		albumViewAction = new AlbumViewAction();
		albumWriteAction = new AlbumWriteAction();
	}

	public static Action getAlbumListAction() {
		return albumListAction;
	}

	public static Action getAlbumModifyAction() {
		return albumModifyAction;
	}

	public static Action getAlbumMoveModifyAction() {
		return albumMoveModifyAction;
	}

	public static Action getAlbumDeleteAction() {
		return albumDeleteAction;
	}

	public static Action getAlbumViewAction() {
		return albumViewAction;
	}

	public static Action getAlbumWriteAction() {
		return albumWriteAction;
	}

	public static Action getReboardModifyAction() {
		return reboardModifyAction;
	}

	public static Action getReboardMoveModifyAction() {
		return reboardMoveModifyAction;
	}

	public static Action getReboardDeleteAction() {
		return reboardDeleteAction;
	}

	public static Action getBoardMenuAction() {
		return boardMenuAction;
	}

	public static Action getBoardTypeListAction() {
		return boardTypeListAction;
	}

	public static Action getReboardListAction() {
		return reboardListAction;
	}

	public static Action getReboardReplyAction() {
		return reboardReplyAction;
	}

	public static Action getReboardMoveReplyAction() {
		return reboardMoveReplyAction;
	}

	public static Action getReboardViewAction() {
		return reboardViewAction;
	}

	public static Action getReboardWriteAction() {
		return reboardWriteAction;
	}

	public static Action getBoardTypeMakeAction() {
		return boardTypeMakeAction;
	}

	public static Action getCategoryListAction() {
		return categoryListAction;
	}

	public static Action getCategoryMakeAction() {
		return categoryMakeAction;
	}

}
