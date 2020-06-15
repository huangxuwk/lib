package com.dl.text1;

public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        S = S.toUpperCase().replaceAll("-", "");
        StringBuilder sb = new StringBuilder(S);
        int len = S.length();
        for (int i = K; i < len; i += K) {
            sb.insert(len - i, "-");
        }
        return sb.toString();
    }
	
}
