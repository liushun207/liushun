package com;

import java.io.Serializable;

/**
 * ContractReturn
 * @author 64251
 * @date 2020/9/4
 */
public class ContractReturn implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 交易返回码
     */
    private String code;

    /**
     * 交易返回信息
     */
    private String note;

    /**
     * 交易状态
     */
    private String tranState;

    /**
     * 请求流水
     */
    private String sendChannelSn;

    /**
     * 合同内容
     */
    private String channelExt;

    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public String getTranState() {
        return this.tranState;
    }

    public void setTranState(final String tranState) {
        this.tranState = tranState;
    }

    public String getSendChannelSn() {
        return this.sendChannelSn;
    }

    public void setSendChannelSn(final String sendChannelSn) {
        this.sendChannelSn = sendChannelSn;
    }

    public String getChannelExt() {
        return this.channelExt;
    }

    public void setChannelExt(final String channelExt) {
        this.channelExt = channelExt;
    }
}