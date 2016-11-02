package org.mifos.util;

/**
 * Created by daniel on 11/2/16.
 */
public class Init {

    protected String BASE_URL = "";
    protected String SAVINGS_URL = "";
    protected String WITHDRAWALS_URL = "";

    protected static String momoDepositUrl = "";
    protected static String momoWithdrawUrl = "";


    public String getBaseUrl() {
        return BASE_URL;
    }

    public static String getMomoSavingsUrl() {
        return momoDepositUrl;
    }

    public void setMomoSavingsUrl(String momoSavingsUrl) {
        this.momoDepositUrl = momoSavingsUrl;
    }

    public static String getMomoWithdrawalsUrl() {
        return momoWithdrawUrl;
    }

    public void setMomoWithdrawalsUrl(String momoWithdrawalsUrl) {
        this.momoWithdrawUrl = momoWithdrawalsUrl;
    }

    public void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public String getSavingsUrl() {
        return SAVINGS_URL;
    }

    public void setSavingsUrl(String savingsUrl) {
        SAVINGS_URL = savingsUrl;
    }
    public String getWithdrawalsUrl() {
        return WITHDRAWALS_URL;
    }

    public void setWithdrawalsUrl(String withDrawalsUrl) {
        WITHDRAWALS_URL = withDrawalsUrl;
    }
}