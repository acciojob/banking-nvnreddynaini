package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    int tradeIdLength;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
        tradeIdLength = tradeLicenseId.length();
        if(balance < 5000){
            throw new Exception("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean isValid = true;
        for(int i=1;i<tradeIdLength;i++){
            char curr = tradeLicenseId.charAt((i-1));
            char next = tradeLicenseId.charAt((i));
            if(curr==next){
                isValid = false;
                break;
            }
        }
        if(isValid) return;
        int freq[] = new int[26];
        boolean isValidPossible = true;
        for(int i=0;i<tradeIdLength;i++){
            char curr = tradeLicenseId.charAt((i));
            freq[curr-'A']++;
            if(freq[curr-'A'] > tradeIdLength/2){
                isValidPossible = false;
                break;
            }
        }
        if(!isValidPossible) throw new Exception("Valid License can not be generated");
        int start1 = 0;
        int start2 = tradeIdLength/2;
        String newTradeId = "";
        for(int i=0;i<tradeIdLength/2;i++){
            while(freq[start1]<=0){
                start1++;
            }
            newTradeId += (char) ('A' + start1);
            freq[start1]--;
            while(freq[start2]<=0){
                start2++;
            }
            newTradeId += (char) ('A' + start2);
            freq[start2]--;
        }
        if(tradeIdLength%2!=0){
            while(freq[start2]<=0){
                start2++;
            }
            newTradeId += (char) ('A' + start2);
            freq[start2]--;
        }
        tradeLicenseId = newTradeId;
    }

}
