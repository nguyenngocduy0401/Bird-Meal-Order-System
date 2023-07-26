/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author haong
 */
public class InformationCreateError implements Serializable {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final String PHONE_NUMBER_REGEX = "0\\d{9}";
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullnameLengthError;
    private String confirmNotMatched;
    private String usernameIsExisted;
    private String emailIsExisted;
    private String emailFormatError;
    private String phoneNumberFormatError;
    private String sendEmailFailedError;
    private String passwordOldWrong;
    private String provinceNotSelect;
    private String districtNotSelect;
    private String wardNotSelect;
    private String addressLengthError;
    private String emailNotFound;
    private String accountNotAvailable;
    
    private String productNameLengthError;
    private String productPriceFormatError;
    private String productQuantityFormatError;
    private String productDetailLengthError;
    private String productSizeFormatError;
    private String productCategoriesBirdNotSelect;
    private String productAgeRecommendationLengthError;
    private String productDateExpireValueError;
    private String productDateManuNotSelect;
    private String productCountryNotSelect;
    private String productImageNotSelect;

    public String getEmailNotFound() {
        return emailNotFound;
    }

    public void setEmailNotFound(String emailNotFound) {
        this.emailNotFound = emailNotFound;
    }

    public String getAccountNotAvailable() {
        return accountNotAvailable;
    }

    public void setAccountNotAvailable(String accountNotAvailable) {
        this.accountNotAvailable = accountNotAvailable;
    }
    
    
    
    
    
    public String getPasswordOldWrong() {
        return passwordOldWrong;
    }

    public void setPasswordOldWrong(String passwordOldWrong) {
        this.passwordOldWrong = passwordOldWrong;
    }
    
    

    public String getSendEmailFailedError() {
        return sendEmailFailedError;
    }

    public void setSendEmailFailedError(String sendEmailFailedError) {
        this.sendEmailFailedError = sendEmailFailedError;
    }
    
    public static boolean validPhoneNumber(String phoneNumberStr) {
        return phoneNumberStr.matches(PHONE_NUMBER_REGEX);
    }

    public static boolean validEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public String getPhoneNumberFormatError() {
        return phoneNumberFormatError;
    }

    public void setPhoneNumberFormatError(String phoneNumberFormatError) {
        this.phoneNumberFormatError = phoneNumberFormatError;
    }
    
    

    public InformationCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    public String getEmailFormatError() {
        return emailFormatError;
    }

    public void setEmailFormatError(String emailFormatError) {
        this.emailFormatError = emailFormatError;
    }

    public String getProvinceNotSelect() {
        return provinceNotSelect;
    }

    public void setProvinceNotSelect(String provinceNotSelect) {
        this.provinceNotSelect = provinceNotSelect;
    }

    public String getDistrictNotSelect() {
        return districtNotSelect;
    }

    public void setDistrictNotSelect(String districtNotSelect) {
        this.districtNotSelect = districtNotSelect;
    }

    public String getWardNotSelect() {
        return wardNotSelect;
    }

    public void setWardNotSelect(String wardNotSelect) {
        this.wardNotSelect = wardNotSelect;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    public String getProductNameLengthError() {
        return productNameLengthError;
    }

    public void setProductNameLengthError(String productNameLengthError) {
        this.productNameLengthError = productNameLengthError;
    }

    public String getProductPriceFormatError() {
        return productPriceFormatError;
    }

    public void setProductPriceFormatError(String productPriceFormatError) {
        this.productPriceFormatError = productPriceFormatError;
    }

    public String getProductQuantityFormatError() {
        return productQuantityFormatError;
    }

    public void setProductQuantityFormatError(String productQuantityFormatError) {
        this.productQuantityFormatError = productQuantityFormatError;
    }

    public String getProductDetailLengthError() {
        return productDetailLengthError;
    }

    public void setProductDetailLengthError(String productDetailLengthError) {
        this.productDetailLengthError = productDetailLengthError;
    }

    public String getProductSizeFormatError() {
        return productSizeFormatError;
    }

    public void setProductSizeFormatError(String productSizeFormatError) {
        this.productSizeFormatError = productSizeFormatError;
    }

    public String getProductCategoriesBirdNotSelect() {
        return productCategoriesBirdNotSelect;
    }

    public void setProductCategoriesBirdNotSelect(String productCategoriesBirdNotSelect) {
        this.productCategoriesBirdNotSelect = productCategoriesBirdNotSelect;
    }

    public String getProductAgeRecommendationLengthError() {
        return productAgeRecommendationLengthError;
    }

    public void setProductAgeRecommendationLengthError(String productAgeRecommendationLengthError) {
        this.productAgeRecommendationLengthError = productAgeRecommendationLengthError;
    }

    public String getProductDateExpireValueError() {
        return productDateExpireValueError;
    }

    public void setProductDateExpireValueError(String productDateExpireValueError) {
        this.productDateExpireValueError = productDateExpireValueError;
    }

    public String getProductDateManuNotSelect() {
        return productDateManuNotSelect;
    }

    public void setProductDateManuNotSelect(String productDateManuNotSelect) {
        this.productDateManuNotSelect = productDateManuNotSelect;
    }

    public String getProductCountryNotSelect() {
        return productCountryNotSelect;
    }

    public void setProductCountryNotSelect(String productCountryNotSelect) {
        this.productCountryNotSelect = productCountryNotSelect;
    }

    public String getProductImageNotSelect() {
        return productImageNotSelect;
    }

    public void setProductImageNotSelect(String productImageNotSelect) {
        this.productImageNotSelect = productImageNotSelect;
    }
    
    
}
