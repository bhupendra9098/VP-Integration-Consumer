/*
 *  @copyright MogliLabs
 */
package com.moglix.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoglixUserException extends Exception {
    
    private static final Logger logger = LoggerFactory.getLogger(MoglixUserException.class);
    private static final long serialVersionUID = 4361899895941691497L;
    private String errCode;
    private String errMsg;
    private String data;

    public MoglixUserException(MoglixUserError moglixUserError) {
        this(moglixUserError, "");
    }

    public MoglixUserException(MoglixUserError moglixUserError, String msg) {
        this(Integer.toString(moglixUserError.getCode()), moglixUserError.getDescription() + ":" + msg, "");
    }
    
    public MoglixUserException(MoglixUserError moglixUserError, String msg, String data) {
        this(Integer.toString(moglixUserError.getCode()), moglixUserError.getDescription() + ":" + msg, data);
    }
    
    public MoglixUserException(String errCode, String errMsg, String data) {
    	super("["+errCode+"] "+errMsg+". "+ data);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
        logger.debug("[" + this.errCode + "]: " + this.errMsg, this);
        logger.info("[" + this.errCode + "]: " + this.errMsg);
    }

    public MoglixUserException(String errCode, String errMsg) {
        this(errCode, errMsg, "");
    }
    
    public MoglixUserException(String errMsg){
    	this("", errMsg);
    }

    public MoglixUserException(Throwable e) {
        this(MoglixUserError.UNKOWN_ERROR);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
